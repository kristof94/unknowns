package org.hld.tf.card.territory;

import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Territory;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.AmendPower;

/**
 * 军营
 */
public class Barrack extends Territory {

	@Override
	public String getName() {
		return "军营";
	}

	@Override
	public int getPoint() {
		return 3;
	}

	@Override
	public String getInfo() {
		return "你拥有军营时，每回合可以让一张你使用的卡牌力量+1";
	}
	private static final AmendPower AMEND_POWER = new AmendPower() {
		@Override
		public int handle(Player player, Figure figure) {
			Game game = Game.getGame();
			if(player.checkTerritoryCount(Barrack.class) > game.checkUseCardCount(player, Barrack.class)) {
				//TODO 以后需要补充提示信息
				System.out.println(player.getName()+"使用军营卡让"+figure.getName()+"的力量+1");
				game.addUseCardCount(player, Barrack.class);
				return 1;
			}
			return 0;
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		if(player.checkTerritoryCount(Barrack.class)==1) game.addEvent(player, AMEND_POWER, false);
	}
	
	@Override
	public void removeEvent(Game game, Player player) {
		if(!player.checkTerritory(Barrack.class)) game.removeEvent(player, AMEND_POWER);
	}
}
