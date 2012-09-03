package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Territory;
import org.hld.tf.card.territory.Port;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.PlayFigureAfter;

/**
 * 龙
 */
public class Dragon extends Figure {

	@Override
	public String getName() {
		return "龙";
	}

	@Override
	public int getPower() {
		return 8;
	}

	@Override
	public String getInfo() {
		return "当你使用龙卡时，你必须消灭任意数量的玩家拥有的总计3个土地卡";
	}
	
	private static final PlayFigureAfter PLAY_FIGURE_AFTER = new PlayFigureAfter() {
		@Override
		public void handle(Player player, Figure figure) {
			//TODO 以后需要处理屠龙者的情况
			Game game = Game.getGame();
			int i = 3;
			for (Player other : game.getOtherPlayers(player)) {
				while(i>0) {
					Territory territory = other.discardTerritory();
					if(territory==null) {
						break;
					} else {
						i--;
						System.out.println(player.getName()+"的龙摧毁了"+other.getName()+"的"+territory.getName());
					}
				}
				if(i==0) return;
			}
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		game.addEvent(player, PLAY_FIGURE_AFTER, true);
	}
}
