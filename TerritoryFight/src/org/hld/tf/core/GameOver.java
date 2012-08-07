package org.hld.tf.core;

public class GameOver extends RuntimeException {
	private Player player;
	
	public GameOver(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
}
