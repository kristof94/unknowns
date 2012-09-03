package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Territory;
import org.hld.tf.card.territory.Farm;
import org.hld.tf.card.territory.MagicAcademy;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.AmendPower;
import org.hld.tf.core.event.WipeOutTerritoryBefore;

/**
 * 魔法师
 */
public class Mage extends Figure {

	@Override
	public String getName() {
		return "魔法师";
	}

	@Override
	public int getPower() {
		return 4;
	}

	@Override
	public String getInfo() {
		return "你所拥有的地将被消灭时，你可以牺牲此卡，使你的一个地不被消灭。你拥有魔法学院时，你的魔法师力量+2";
	}
	
	private static final WipeOutTerritoryBefore WIPE_OUT_TERRITORY_BEFORE = new WipeOutTerritoryBefore() {
		@Override
		public boolean handle(Player attacker, Player defender, Territory territory) {
			//TODO 以后需要补充提示
			if(defender.checkFigure(Mage.class)) {
				Figure figure = defender.discardFigure(Mage.class);
				if(figure!=null) return false;
			}
			return true;
		}
	};
	
	private static final AmendPower AMEND_POWER = new AmendPower() {
		@Override
		public int handle(Player player, Figure figure) {
			return player.checkTerritory(MagicAcademy.class)?2:0;
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		if(player.checkFigureCount(Mage.class)==1) game.addEvent(player, WIPE_OUT_TERRITORY_BEFORE, false);
		game.addEvent(player, AMEND_POWER, true);
	};
	
	@Override
	public void removeEvent(Game game, Player player) {
		if(!player.checkFigure(Mage.class)) game.removeEvent(player, WIPE_OUT_TERRITORY_BEFORE);
	}
}
