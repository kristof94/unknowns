package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.PlayFigureAfter;

/**
 * 强盗
 */
public class Robber extends Figure {

	@Override
	public String getName() {
		return "强盗";
	}

	@Override
	public int getPower() {
		return 3;
	}

	@Override
	public String getInfo() {
		return "当你使用强盗时，你可以从目标玩家手中随机抽取一张人物卡加入你手中";
	}
	
	private static final PlayFigureAfter PLAY_FIGURE_AFTER = new PlayFigureAfter() {
		@Override
		public void handle(Player player, Figure figure) {
			//TODO 以后要补上提示
			for(Player other:Game.getGame().getOtherPlayers(player)) {
				Figure card = other.discardFigure();
				if(card!=null) {
					player.drawFigure(card);
					System.out.println(player.getName()+"抽取了"+other.getName()+"的"+card.getName()+"卡");
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
