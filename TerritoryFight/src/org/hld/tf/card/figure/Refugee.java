package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Territory;
import org.hld.tf.card.territory.Farm;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.PlayFigureAfter;

/**
 * 流民
 */
public class Refugee extends Figure {

	@Override
	public String getName() {
		return "流民";
	}

	@Override
	public int getPower() {
		return 0;
	}

	@Override
	public String getInfo() {
		return "当你使用流民卡时，你可以获得目标玩家的一张农田卡";
	}
	
	private static final PlayFigureAfter PLAY_FIGURE_AFTER = new PlayFigureAfter() {
		@Override
		public void handle(Player player, Figure figure) {
			//TODO 以后要补上提示
			for(Player other:Game.getGame().getOtherPlayers(player)) {
				Territory territory = other.discardTerritory(Farm.class);
				if(territory!=null) {
					player.drawTerritory(territory);
					System.out.println(player.getName()+"获得了"+other.getName()+"的农田卡");
					return;
				}
			}
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		game.addEvent(player, PLAY_FIGURE_AFTER, true);
	};
}
