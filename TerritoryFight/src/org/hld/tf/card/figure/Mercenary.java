package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 佣兵
 */
public class Mercenary extends Figure {

	@Override
	public String getName() {
		return "佣兵";
	}

	@Override
	public int getPower() {
		return 0;
	}

	@Override
	public String getInfo() {
		return "佣兵的力量等于之前你本回合中使用过的所有人物卡力量的总和";
	}
}
