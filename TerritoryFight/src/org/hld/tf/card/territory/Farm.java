package org.hld.tf.card.territory;

import org.hld.tf.card.base.Territory;

/**
 * 农田
 */
public class Farm extends Territory {

	@Override
	public String getName() {
		return "农田";
	}

	@Override
	public int getPoint() {
		return 2;
	}

	@Override
	public String getInfo() {
		return "只要任何玩家拥有4张农田，便直接获得游戏的胜利";
	}
}
