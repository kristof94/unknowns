package org.hld.tf.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hld.tf.DemoListener;
import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Library;
import org.hld.tf.card.base.Territory;
import org.hld.tf.card.library.SingleDeck;
import org.hld.tf.card.territory.Farm;
import org.hld.tf.core.event.AmendDrawCount;
import org.hld.tf.core.event.AmendHandLimit;
import org.hld.tf.core.event.AmendPower;
import org.hld.tf.core.event.DrawTerritoryBefore;
import org.hld.tf.core.event.FightBeginBefore;

public class Game {
	private static final ThreadLocal<Game> THREAD_LOCAL_GAME = new ThreadLocal<Game>();
	private Listener listener;
	private Class<? extends Library> libraryType;
	public Player[] players;
	private int playerCount;
	public Library library;
	private Round round;
	/**
	 * 单次事件
	 * Map<事件类型, Map<玩家ID, List<事件>>>
	 */
	private Map<Class, Map<String, List<Object>>> onceEventMap = new HashMap<Class, Map<String,List<Object>>>();
	/**
	 * 持续事件
	 * Map<事件类型, Map<玩家ID, List<事件>>>
	 */
	private Map<Class, Map<String, List<Object>>> continueEventMap = new HashMap<Class, Map<String,List<Object>>>();
	
	public static void main(String[] args) {
		Player[] players = new Player[]{new Player("NPC1"), new Player("NPC2")};
		Player player = Game.createGame(new DemoListener(), SingleDeck.class, players).start();
		System.out.println("本次游戏的胜利者是["+player.getName()+"]");
	}
	
	public static Game createGame(Listener listener, Class<? extends Library> libraryType, Player... players) {
		Game game = new Game(listener, libraryType, players);
		game.init();
		THREAD_LOCAL_GAME.set(game);
		for (Player player : players) {
			player.game = game;
		}
		return game;
	}

	public static Game getGame() {
		return THREAD_LOCAL_GAME.get();
	}
	
	public Player start() {
		ready();
		try {
			while(true) {
				round();
			}
		} catch(GameOver e) {
			return e.getPlayer();
		}
	}
	
	private Game(Listener listener, Class<? extends Library> libraryType, Player... players) {
		this.listener = listener;
		this.libraryType = libraryType;
		this.players = players;
	}
	
	private void init() {
		try {
			library = libraryType.newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.playerCount = players.length;
		round = new Round();
	}
	
	private void ready() {
		for(Player player:players) {
			drawFigure(player, 5);
		}
	}
	
	private void round() {
		//准备阶段补充人物卡
		for(Player player:players) {
			int n = player.getFigureCount()==0?2:1;
			List<AmendDrawCount> event = getEvent(player, AmendDrawCount.class);
			for (AmendDrawCount amendDrawCount : event) {
				n+=amendDrawCount.handle(player);
			}
			drawFigure(player, n);
		}
		//执行争夺阶段前的事件
		for(Player player:players) {
			List<FightBeginBefore> list = getEvent(player, FightBeginBefore.class);
			for (FightBeginBefore fightBeginBefore : list) {
				fightBeginBefore.handle(player);
			}
		}
		//争夺阶段争夺土地
		Territory territory = library.drawTerritory();
		round.fightTerritory = territory;
		listener.showFightTerritory(territory);
		int i = round.earlyHandPosition;
		do {
			Player player = players[i];
			Figure figure = listener.fightTerritory(player);
			if(figure!=null) {
				round.playFigure(player, figure);
				int power = figure.getPower();
				List<AmendPower> event = getEvent(player, AmendPower.class);
				for (AmendPower amendPower : event) {
//					System.out.println("==========处理事件："+player.getName()+":"+amendPower.getClass());
					power+=amendPower.handle(player, figure);
				}
				if(power>=round.maxPower) {
					round.maxPower = power;
					round.maxHandPosition = i;
					System.out.println("现在争夺权在["+player.getName()+"("+round.maxPower+")]手上");
				}
			}
			i++;
			if(i==playerCount) i = 0;
		} while(i!=round.maxHandPosition);
		if(round.maxPower>-1) {
			Player winner = players[round.maxHandPosition];
			listener.roundWinner(winner);
			boolean allowDraw = true;
			List<DrawTerritoryBefore> list = getEvent(winner, DrawTerritoryBefore.class);
			for (DrawTerritoryBefore drawTerritoryBefore : list) {
				allowDraw = drawTerritoryBefore.handle(winner, territory);
			}
			if(allowDraw) winner.drawTerritory(territory);
			else library.discardTerritory(territory);
		} else {
			library.discardTerritory(territory);
			listener.roundWinner(null);
		}
		//结束阶段弃牌到手牌上限
		for(Player player:players) {
			int l = 7;
			List<AmendHandLimit> list = getEvent(player, AmendHandLimit.class);
			for (AmendHandLimit amendHandLimit : list) {
				l+=amendHandLimit.handle(player);
			}
			if(player.getFigureCount()>l) {
				do {
					library.discardFigure(player.discardFigure());
				} while (player.getFigureCount()>--l);
			}
		}
		//回合结束进行整理
		round.trickContinue(library);
	}
	
	private void drawFigure(Player player, int n) {
		for(int i = 0; i<n; i++) player.drawFigure(library.drawFigure());
	}
	
	/**
	 * 添加事件
	 * @param player 触发事件的玩家
	 * @param event 事件
	 * @param isOnce 是否单次事件
	 */
	public void addEvent(Player player, Object event, boolean isOnce) {
		Class clazz = event.getClass().getInterfaces()[0];
//		System.out.println("============添加事件："+player.getName()+":"+event.getClass()+":"+clazz);
		Map<Class, Map<String, List<Object>>> map = isOnce?onceEventMap:continueEventMap;
		Map<String, List<Object>> m = map.get(clazz);
		if(m==null) {
			m = new HashMap<String, List<Object>>();
			map.put(clazz, m);
		}
		List<Object> list = m.get(player.getId());
		if(list==null) {
			list = new LinkedList<Object>();
			m.put(player.getId(), list);
		}
		list.add(event);
	}
	
	/**
	 * 移除事件
	 * @param player
	 * @param event
	 */
	public void removeEvent(Player player, Object event) {
		Map<String, List<Object>> m = continueEventMap.get(event.getClass().getInterfaces()[0]);
		if(m!=null) {
			List<Object> list = m.get(player.getId());
			if(list!=null) {
				list.remove(event);
			}
		}
	}
	
	/**
	 * 获取事件，单次事件只能获取一次
	 * @param <T>
	 * @param player
	 * @param clazz
	 * @return
	 */
	public <T> List<T> getEvent(Player player, Class<T> clazz) {
		List<T> event = new LinkedList<T>();
		Map<String, List<Object>> map = onceEventMap.get(clazz);
		if(map!=null) {
			List<Object> list = map.get(player.getId());
			if(list!=null) {
				Iterator<Object> iterator = list.iterator();
				while(iterator.hasNext()) {
					Object object = iterator.next();
					if(clazz.isInstance(object)) {
						event.add((T) object);
						iterator.remove();
					}
				}
			}
		}
		map = continueEventMap.get(clazz);
		if(map!=null) {
			List<Object> list = map.get(player.getId());
			if(list!=null) {
				for (Object object : list) {
					if(clazz.isInstance(object)) {
						event.add((T) object);
					}
				}
			}
		}
//		System.out.println("=============获取事件："+player.getName()+":"+clazz+":"+event.size());
		return event;
	}
	
	/**
	 * 增加当前回合卡牌技能使用次数
	 * @param player
	 * @param clazz
	 */
	public void addUseCardCount(Player player, Class clazz) {
		Map<Class, Integer> map = round.useCardCountMap.get(player.getId());
		if(map==null) {
			map = new HashMap<Class, Integer>();
			round.useCardCountMap.put(player.getId(), map);
		}
		Integer i = map.get(clazz);
		map.put(clazz, i==null?1:(i+1));
	}
	
	/**
	 * 检查当前回合卡牌技能使用次数
	 * @param player
	 * @param clazz
	 * @return
	 */
	public int checkUseCardCount(Player player, Class clazz) {
		Map<Class, Integer> map = round.useCardCountMap.get(player.getId());
		if(map!=null) {
			Integer i = map.get(clazz);
			if(i!=null) return i;
		}
		return 0;
	}
	
	public static void checkGameWinner(Player player) {
		if(player.checkPoint()>=20) {
			throw new GameOver(player);
		}
	}
}
