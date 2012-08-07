package org.hld.tf;

import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Territory;
import org.hld.tf.core.Listener;
import org.hld.tf.core.Player;

public class DemoListener implements Listener {
	@Override
	public void showFightTerritory(Territory territory) {
		System.out.println("本回合争夺["+territory.getName()+"("+territory.getPoint()+")]");
	}
	
	@Override
	public Figure fightTerritory(Player player) {
		Figure figure = player.discardFigure();
		if(figure==null) {
			System.out.println(player.getName()+"暂时放弃争夺");
		} else {
			System.out.println(player.getName()+"使用["+figure.getName()+"("+figure.getPower()+")]进行争夺");
		}
		return figure;
	}
	
	@Override
	public void roundWinner(Player player) {
		if(player==null) System.out.println("本次领土争夺流标");
		else System.out.println("本次领土争夺的胜利者是["+player.getName()+"("+player.checkPoint()+")]");
	}
}
