package org.hld.tf.card.territory;

import org.hld.tf.card.base.Territory;
import org.hld.tf.core.Game;
import org.hld.tf.core.GameOver;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.DrawTerritoryAfter;

/**
 * 农田
 */
public class Farm extends Territory {

	@Override
	public String getName() {
		return "农田";
	}

	@Override
	public int getPoint() {
		return 2;
	}

	@Override
	public String getInfo() {
		return "只要任何玩家拥有4张农田，便直接获得游戏的胜利";
	}
	
	private static final DrawTerritoryAfter DRAW_TERRITORY_AFTER = new DrawTerritoryAfter() {
		@Override
		public void handle(Player player, Territory territory) {
			if(player.checkTerritoryCount(Farm.class)>=4) {
				throw new GameOver(player);
			}
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		game.addEvent(player, DRAW_TERRITORY_AFTER, true);
	}
}
