package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 旅行者
 */
public class Traveller extends Figure {

	@Override
	public String getName() {
		return "旅行者";
	}

	@Override
	public int getPower() {
		return 1;
	}

	@Override
	public String getInfo() {
		return "当你使用旅行者后，在你补充手卡时能多抓2张、人物卡";
	}
}
