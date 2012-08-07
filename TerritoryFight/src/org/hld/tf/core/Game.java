package org.hld.tf.core;

import org.hld.tf.DemoListener;
import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Library;
import org.hld.tf.card.base.Territory;
import org.hld.tf.card.library.SingleDeck;
import org.hld.tf.card.territory.Farm;

public class Game {
	private static final ThreadLocal<Game> THREAD_LOCAL_GAME = new ThreadLocal<Game>();
	private Listener listener;
	private Class<? extends Library> libraryType;
	private Player[] players;
	private int playerCount;
	private Library library;
	private Round round;
	
	public static void main(String[] args) {
		Player[] players = new Player[]{new Player("NPC1"), new Player("NPC2")};
		Player player = Game.createGame(new DemoListener(), SingleDeck.class, players).start();
		System.out.println("本次游戏的胜利者是["+player.getName()+"]");
	}
	
	public static Game createGame(Listener listener, Class<? extends Library> libraryType, Player... players) {
		Game game = new Game(listener, libraryType, players);
		game.init();
		THREAD_LOCAL_GAME.set(game);
		return game;
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
		for(Player player:players) {
			drawFigure(player, player.getFigureCount()==0?2:1);
		}
		Territory territory = library.drawTerritory();
		round.fightTerritory = territory;
		listener.showFightTerritory(territory);
		int i = round.earlyHandPosition;
		do {
			Player player = players[i];
			Figure figure = listener.fightTerritory(player);
			if(figure!=null) {
				round.playFigure(player, figure);
				if(figure.getPower()>=round.maxPower) {
					round.maxPower = figure.getPower();
					round.maxHandPosition = i;
					System.out.println("现在争夺权在["+player.getName()+"("+round.maxPower+")]手上");
				}
			}
			i++;
			if(i==playerCount) i = 0;
		} while(i!=round.maxHandPosition);
		if(round.maxPower>-1) {
			Player winner = players[round.maxHandPosition];
			winner.drawTerritory(territory);
			listener.roundWinner(winner);
		} else {
			library.discardTerritory(territory);
			listener.roundWinner(null);
		}
		round.trickContinue(library);
	}
	
	private void drawFigure(Player player, int n) {
		for(int i = 0; i<n; i++) player.drawFigure(library.drawFigure());
	}
	
	public static void checkGameWinner(Player player) {
		if(player.checkTerritoryCount(Farm.class)>=4 && player.checkPoint()>=20) {
			throw new GameOver(player);
		}
	}
}
