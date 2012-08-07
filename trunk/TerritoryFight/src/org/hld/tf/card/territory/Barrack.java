package org.hld.tf.card.territory;

import org.hld.tf.card.base.Territory;

/**
 * 军营
 */
public class Barrack extends Territory {

	@Override
	public String getName() {
		return "军营";
	}

	@Override
	public int getPoint() {
		return 3;
	}

	@Override
	public String getInfo() {
		return "你拥有军营时，每回合可以让一张你使用的卡牌力量+1";
	}
}
