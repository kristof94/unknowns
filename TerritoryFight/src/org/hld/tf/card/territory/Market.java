package org.hld.tf.card.territory;

import org.hld.tf.card.base.Territory;

/**
 * 市场
 */
public class Market extends Territory {

	@Override
	public String getName() {
		return "市场";
	}

	@Override
	public int getPoint() {
		return 4;
	}

	@Override
	public String getInfo() {
		return "只要你同时拥有两个或以上的市场时，在你补充手卡时，可多补充一张";
	}
}
