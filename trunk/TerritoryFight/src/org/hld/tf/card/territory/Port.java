package org.hld.tf.card.territory;

import org.hld.tf.card.base.Territory;

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
}
