package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 流民
 */
public class Refugee extends Figure {

	@Override
	public String getName() {
		return "流民";
	}

	@Override
	public int getPower() {
		return 0;
	}

	@Override
	public String getInfo() {
		return "当你使用流民卡时，你可以获得目标玩家的一张农田卡";
	}
}
