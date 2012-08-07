package org.hld.tf.card.territory;

import org.hld.tf.card.base.Territory;

/**
 * 教会
 */
public class Church extends Territory {

	@Override
	public String getName() {
		return "教会";
	}

	@Override
	public int getPoint() {
		return 8;
	}

	@Override
	public String getInfo() {
		return "教会不能同时拥有两个或更多。拥有教会时，你的手卡没有上限";
	}
}
