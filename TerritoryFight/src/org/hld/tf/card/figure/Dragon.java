package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 龙
 */
public class Dragon extends Figure {

	@Override
	public String getName() {
		return "龙";
	}

	@Override
	public int getPower() {
		return 8;
	}

	@Override
	public String getInfo() {
		return "当你使用龙卡时，你必须消灭任意数量的玩家拥有的总计3个土地卡";
	}
}
