package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 商人
 */
public class Merchant extends Figure {

	@Override
	public String getName() {
		return "商人";
	}

	@Override
	public int getPower() {
		return 4;
	}

	@Override
	public String getInfo() {
		return "当你使用商人时，你每拥有一个市场，在你补充手卡时你就多抓一张人物卡";
	}
}
