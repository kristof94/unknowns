package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 强盗
 */
public class Robber extends Figure {

	@Override
	public String getName() {
		return "强盗";
	}

	@Override
	public int getPower() {
		return 3;
	}

	@Override
	public String getInfo() {
		return "当你使用强盗时，你可以从目标玩家手中随机抽取一张人物卡加入你手中";
	}
}
