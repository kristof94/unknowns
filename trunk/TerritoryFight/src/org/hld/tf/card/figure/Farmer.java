package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;
import org.hld.tf.card.territory.Farm;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.AmendPower;

/**
 * 农民卡
 */
public class Farmer extends Figure {

	@Override
	public String getName() {
		return "农民";
	}

	@Override
	public int getPower() {
		return 2;
	}

	@Override
	public String getInfo() {
		return "在农田领土卡的争夺中，农民卡的力量为7";
	}
	
	private static final AmendPower AMEND_POWER = new AmendPower() {
		@Override
		public int handle(Player player, Figure figure) {
			return Farm.class.isInstance(Game.getGame().round.fightTerritory)?5:0;
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		game.addEvent(player, AMEND_POWER, true);
	};
}
