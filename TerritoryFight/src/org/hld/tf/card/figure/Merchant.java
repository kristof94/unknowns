package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;
import org.hld.tf.card.territory.Market;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.AmendDrawCount;
import org.hld.tf.core.event.PlayFigureAfter;

/**
 * 商人
 */
public class Merchant extends Figure {

	@Override
	public String getName() {
		return "商人";
	}

	@Override
	public int getPower() {
		return 4;
	}

	@Override
	public String getInfo() {
		return "当你使用商人时，你每拥有一个市场，在你补充手卡时你就多抓一张人物卡";
	}
	
	private static final PlayFigureAfter PLAY_FIGURE_AFTER = new PlayFigureAfter() {
		@Override
		public void handle(Player player, Figure figure) {
			final int count = player.checkTerritoryCount(Market.class);
			if(count>0) {
				Game.getGame().addEvent(player, new AmendDrawCount() {
					@Override
					public int handle(Player player) {
						System.out.println(player.getName()+"使用商人能够额外摸多"+count+"张人物卡");
						return count;
					}
				}, true);
			}
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		game.addEvent(player, PLAY_FIGURE_AFTER, true);
	};
}
