package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.AmendDrawCount;

/**
 * 旅行者
 */
public class Traveller extends Figure {

	@Override
	public String getName() {
		return "旅行者";
	}

	@Override
	public int getPower() {
		return 1;
	}

	@Override
	public String getInfo() {
		return "当你使用旅行者后，在你补充手卡时能多抓2张人物卡";
	}
	
	private static final AmendDrawCount AMEND_DRAW_COUNT = new AmendDrawCount() {
		@Override
		public int handle(Player player) {
			return 2;
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		game.addEvent(player, AMEND_DRAW_COUNT, true);
	};
}
