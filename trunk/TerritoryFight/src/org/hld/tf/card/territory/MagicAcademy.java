package org.hld.tf.card.territory;

import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Territory;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.WipeOutTerritoryBefore;

/**
 * 魔法学院
 */
public class MagicAcademy extends Territory {

	@Override
	public String getName() {
		return "魔法学院";
	}

	@Override
	public int getPoint() {
		return 4;
	}

	@Override
	public String getInfo() {
		return "当有一张你所拥有的土地卡将要被消灭或者牺牲时，你可以用丢弃一张手卡来代替。每个魔法学院一回合只能使用一次代替能力";
	}
	
	private static final WipeOutTerritoryBefore WIPE_OUT_TERRITORY_BEFORE = new WipeOutTerritoryBefore() {
		@Override
		public boolean handle(Player attacker, Player defender, Territory territory) {
			Game game = Game.getGame();
			if(defender.checkTerritoryCount(MagicAcademy.class) > game.checkUseCardCount(defender, MagicAcademy.class)) {
				//TODO 以后需要补充提示信息
				Figure figure = defender.discardFigure();
				if(figure!=null) {
					System.out.println(attacker.getName()+"将要消灭"+defender.getName()+"的"+territory.getName()+"，被"+defender.getName()+"用"+figure.getName()+"抵挡了");
					game.addUseCardCount(defender, MagicAcademy.class);
					return false;
				}
			}
			return true;
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		if(player.checkTerritoryCount(MagicAcademy.class)==1) game.addEvent(player, WIPE_OUT_TERRITORY_BEFORE, false);
	}
	
	@Override
	public void removeEvent(Game game, Player player) {
		if(!player.checkTerritory(MagicAcademy.class)) game.removeEvent(player, WIPE_OUT_TERRITORY_BEFORE);
	}
}
