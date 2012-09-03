package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.PlayFigureAfter;

/**
 * 欺诈师
 */
public class Swindler extends Figure {

	@Override
	public String getName() {
		return "欺诈师";
	}

	@Override
	public int getPower() {
		return 1;
	}

	@Override
	public String getInfo() {
		return "当你使用欺诈师时，每位其他玩家从手中丢弃一张人物卡";
	}
	
	private static final PlayFigureAfter PLAY_FIGURE_AFTER = new PlayFigureAfter() {
		@Override
		public void handle(Player player, Figure figure) {
			//TODO 以后要补上提示
			Game game = Game.getGame();
			for(Player other:game.getOtherPlayers(player)) {
				Figure card = other.discardFigure();
				if(card!=null) {
					game.library.discardFigure(card);
					System.out.println(player.getName()+"丢弃了"+other.getName()+"的"+card.getName()+"卡");
				}
			}
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		game.addEvent(player, PLAY_FIGURE_AFTER, true);
	}
}
