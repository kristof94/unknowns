package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 国王
 */
public class King extends Figure {

	@Override
	public String getName() {
		return "国王";
	}

	@Override
	public int getPower() {
		return 7;
	}

	@Override
	public String getInfo() {
		return "当你拥有城堡并使用国王卡时，可以检视人物卡堆最上面的五张卡，选择一张人物卡展示并加入你手中";
	}
}
