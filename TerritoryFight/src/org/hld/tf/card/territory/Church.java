package org.hld.tf.card.territory;

import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Territory;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.AmendHandLimit;
import org.hld.tf.core.event.DrawTerritoryBefore;

/**
 * 教会
 */
public class Church extends Territory {

	@Override
	public String getName() {
		return "教会";
	}

	@Override
	public int getPoint() {
		return 8;
	}

	@Override
	public String getInfo() {
		return "教会不能同时拥有两个或更多。拥有教会时，你的手卡没有上限";
	}
	
	private static final DrawTerritoryBefore DRAW_TERRITORY_BEFORE = new DrawTerritoryBefore() {
		@Override
		public boolean handle(Player player, Territory territory) {
			return !player.checkTerritory(Church.class);
		}
	};
	private static final AmendHandLimit AMEND_HAND_LIMIT = new AmendHandLimit() {
		@Override
		public int handle(Player player) {
			return player.checkTerritory(Church.class)?65535-7:0;
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		if(player.checkTerritoryCount(Church.class)==1) {
			Game.getGame().addEvent(player, DRAW_TERRITORY_BEFORE, false);
			Game.getGame().addEvent(player, AMEND_HAND_LIMIT, false);
		}
	}
	
	@Override
	public void removeEvent(Game game, Player player) {
		if(!player.checkTerritory(Church.class)) {
			game.removeEvent(player, DRAW_TERRITORY_BEFORE);
			game.removeEvent(player, AMEND_HAND_LIMIT);
		}
	}
}
