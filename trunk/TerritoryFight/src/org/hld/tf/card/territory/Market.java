package org.hld.tf.card.territory;

import org.hld.tf.card.base.Territory;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.AmendDrawCount;

/**
 * 市场
 */
public class Market extends Territory {

	@Override
	public String getName() {
		return "市场";
	}

	@Override
	public int getPoint() {
		return 4;
	}

	@Override
	public String getInfo() {
		return "只要你同时拥有两个或以上的市场时，在你补充手卡时，可多补充一张";
	}
	
	private static final AmendDrawCount AMEND_DRAW_COUNT = new AmendDrawCount() {
		@Override
		public int handle(Player player) {
			return player.checkTerritoryCount(Market.class)>=2?1:0;
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		if(player.checkTerritoryCount(Market.class)==1) game.addEvent(player, AMEND_DRAW_COUNT, false);
	}
	
	@Override
	public void removeEvent(Game game, Player player) {
		if(!player.checkTerritory(Market.class)) game.removeEvent(player, AMEND_DRAW_COUNT);
	}
}
