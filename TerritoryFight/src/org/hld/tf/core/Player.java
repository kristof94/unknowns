package org.hld.tf.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Territory;
import org.hld.tf.core.event.WipeOutTerritoryBefore;

/**
 * 玩家
 */
public class Player {
	private List<Figure> figures = new ArrayList<Figure>();
	private List<Territory> territories = new ArrayList<Territory>();
	protected Game game;
	private String id;
	private String name;
	
	public Player(String name) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return id+":"+name;
	}
	
	/**
	 * 返回拥有的人物卡
	 * @return
	 */
	public List<Figure> getFigure() {
		return figures;
	}
	
	/**
	 * 返回拥有的人物卡数
	 * @return
	 */
	public int getFigureCount() {
		return figures.size();
	}
	
	/**
	 * 获取一张人物卡
	 * @param card
	 */
	public void drawFigure(Figure card) {
		figures.add(card);
		card.setEvent(game, this);
	}
	
	/**
	 * 打出一张人物卡
	 * @param type
	 * @return
	 */
	public Figure playFigure(Class<? extends Figure> type) {
		for(int i = 0; i<figures.size(); i++) {
			if(type.isInstance(figures.get(i))) {
				return figures.remove(i);
			}
		}
		return null;
	}
	
	/**
	 * 弃一张人物卡
	 */
	public Figure discardFigure() {
		if(!figures.isEmpty()) {
			return figures.remove(new Random().nextInt(figures.size()));
		}
		return null;
	}
	
	/**
	 * 弃一张人物卡
	 */
	public Figure discardFigure(Class<? extends Figure> type) {
		Iterator<Figure> iterator = figures.iterator();
		while(iterator.hasNext()) {
			Figure figure = iterator.next();
			if(type.isInstance(figure)) {
				iterator.remove();
				figure.removeEvent(game, this);
				return figure;
			}
		}
		return null;
	}
	
	/**
	 * 清空所有人物卡
	 * @return
	 */
	public List<Figure> cleanFigure() {
		List<Figure> list = figures;
		figures = new ArrayList<Figure>();
		return list;
	}
	
	/**
	 * 判断是否有指定人物卡
	 * @param type
	 * @return
	 */
	public boolean checkFigure(Class<? extends Figure> type) {
		for(Figure figure:figures) {
			if(type.isInstance(figure)) return true;
		}
		return false;
	}
	
	/**
	 * 判断指定人物卡一共有多少张
	 * @param type
	 * @return
	 */
	public int checkFigureCount(Class<? extends Figure> type) {
		int i = 0;
		for(Figure figure:figures) {
			if(type.isInstance(figure)) i++;
		}
		return i;
	}
	
	/**
	 * 返回拥有的领土卡
	 * @return
	 */
	public List<Territory> getTerritories() {
		return territories;
	}
	
	/**
	 * 获取一张领土卡
	 * @param card
	 */
	public void drawTerritory(Territory card) {
		territories.add(card);
		card.setEvent(game, this);
		Game.checkGameWinner(this);
	}
	
	/**
	 * 弃一张领土卡
	 */
	public Territory discardTerritory() {
		if(!territories.isEmpty()) {
			Territory territory = territories.remove(new Random().nextInt(territories.size()));
			territory.removeEvent(game, this);
			return territory;
		}
		return null;
	}
	
	/**
	 * 弃一张领土卡
	 */
	public Territory discardTerritory(Class<? extends Territory> type) {
		Iterator<Territory> iterator = territories.iterator();
		while(iterator.hasNext()) {
			Territory territory = iterator.next();
			if(type.isInstance(territory)) {
				iterator.remove();
				territory.removeEvent(game, this);
				return territory;
			}
		}
		return null;
	}
	
	/**
	 * 清空所有领土卡
	 * @return
	 */
	public List<Territory> cleanTerritory() {
		List<Territory> list = territories;
		territories = new ArrayList<Territory>();
		return list;
	}
	
	/**
	 * 消灭指定的地
	 * @param type
	 * @return
	 */
	public Territory wipeOutTerritory(Class<? extends Territory> type) {
		Iterator<Territory> iterator = territories.iterator();
		while(iterator.hasNext()) {
			Territory territory = iterator.next();
			if(type.isInstance(territory)) {
				if(territory.canWipeOut()) {
					boolean allow = true;
					List<WipeOutTerritoryBefore> list = game.getEvent(this, WipeOutTerritoryBefore.class);
					for (WipeOutTerritoryBefore wipeOutTerritoryBefore : list) {
						allow = wipeOutTerritoryBefore.handle(null, this, territory);
					}
					if(allow) {
						iterator.remove();
						territory.removeEvent(game, this);
						return territory;
					} else {
						return null;
					}
				} else {
					break;
				}
			}
		}
		return null;
	}
	
	/**
	 * 牺牲指定的地
	 * @param type
	 * @return
	 */
	public Territory sacrificeTerritory(Class<? extends Territory> type) {
		Iterator<Territory> iterator = territories.iterator();
		while(iterator.hasNext()) {
			Territory territory = iterator.next();
			if(type.isInstance(territory)) {
				territory.removeEvent(game, this);
				return territory;
			}
		}
		return null;
	}
	
	/**
	 * 判断是否有指定领土卡
	 * @param type
	 * @return
	 */
	public boolean checkTerritory(Class<? extends Territory> type) {
		for(Territory territory:territories) {
			if(type.isInstance(territory)) return true;
		}
		return false;
	}
	
	/**
	 * 判断指定领土卡一共有多少张
	 * @param type
	 * @return
	 */
	public int checkTerritoryCount(Class<? extends Territory> type) {
		int i = 0;
		for(Territory territory:territories) {
			if(type.isInstance(territory)) i++;
		}
		return i;
	}
	
	/**
	 * 判断现在的分数
	 * @return
	 */
	public int checkPoint() {
		int point = 0;
		for(Territory territory:territories) {
			point+=territory.getPoint();
		}
		return point;
	}
}
