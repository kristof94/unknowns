package org.hld.tf.card.territory;

import java.util.List;

import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Territory;
import org.hld.tf.core.Game;
import org.hld.tf.core.Player;
import org.hld.tf.core.event.FightBeginBefore;

/**
 * 港口
 */
public class Port extends Territory {

	@Override
	public String getName() {
		return "港口";
	}

	@Override
	public int getPoint() {
		return 5;
	}

	@Override
	public String getInfo() {
		return "在每个回合开始时，你可以牺牲港口，选择一个玩家，将你和他的手卡互换，该牺牲不能被防止或者代替";
	}
	
	private static final FightBeginBefore FIGHT_BEGIN_BEFORE = new FightBeginBefore() {
		@Override
		public void handle(Player player) {
			if(player.checkTerritory(Port.class)) {
				//TODO 以后需要补充提示信息
				Game game = Game.getGame();
				for(Player other:game.players) {
					if(other.getFigureCount()>player.getFigureCount()) {
						List<Figure> f1 = other.cleanFigure();
						List<Figure> f2 = player.cleanFigure();
						for (Figure figure : f1) {
							player.drawFigure(figure);
						}
						for (Figure figure : f2) {
							other.drawFigure(figure);
						}
						System.out.println(player.getName()+"牺牲了港口和"+other.getName()+"互换了手卡");
						game.library.discardTerritory(player.sacrificeTerritory(Port.class));
						return;
					}
				}
			}
		}
	};
	
	@Override
	public void setEvent(Game game, Player player) {
		if(player.checkTerritoryCount(Port.class)==1) game.addEvent(player, FIGHT_BEGIN_BEFORE, false);
	}
	
	@Override
	public void removeEvent(Game game, Player player) {
		if(!player.checkTerritory(Port.class)) game.removeEvent(player, FIGHT_BEGIN_BEFORE);
	}
}
