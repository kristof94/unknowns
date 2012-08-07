package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 农民卡
 */
public class Farmer extends Figure {

	@Override
	public String getName() {
		return "农民";
	}

	@Override
	public int getPower() {
		return 2;
	}

	@Override
	public String getInfo() {
		return "在农田领土卡的争夺中，农民卡的力量为7";
	}
}
