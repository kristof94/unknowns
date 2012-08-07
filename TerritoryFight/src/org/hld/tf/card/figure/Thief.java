package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 盗贼
 */
public class Thief extends Figure {

	@Override
	public String getName() {
		return "盗贼";
	}

	@Override
	public int getPower() {
		return 2;
	}

	@Override
	public String getInfo() {
		return "当你使用此卡时，你可以将任何玩家在这一轮争夺中已经使用过的一张人物卡加入你手中，但本回合争夺中此卡不能使用";
	}
}
