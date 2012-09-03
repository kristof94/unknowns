package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.PlayFigureAfter;

/**
 * 战争贩子
 */
public class Warmonger extends Figure {

	@Override
	public String getName() {
		return "战争贩子";
	}

	@Override
	public int getPower() {
		return 4;
	}

	@Override
	public String getInfo() {
		return "当你使用战争贩子时，你可以消灭目标玩家拥有的1个土地卡";
	}
	
	private static final PlayFigureAfter PLAY_FIGURE_AFTER = new PlayFigureAfter() {
		@Override
		public void handle(Player player, Figure figure) {
			//TODO 以后要补上提示
			Game game = Game.getGame();
			for(Player other:game.getOtherPlayers(player)) {
			}
		}
	};
}
