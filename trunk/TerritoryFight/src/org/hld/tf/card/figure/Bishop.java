package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 主教
 */
public class Bishop extends Figure {

	@Override
	public String getName() {
		return "主教";
	}

	@Override
	public int getPower() {
		return 6;
	}

	@Override
	public String getInfo() {
		return "只要你拥有教会，任何玩家使用国王时，你可以展示此卡，该玩家选择将其拥有的一个土地卡给你";
	}
}
