package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 屠龙者
 */
public class DragonSlayer extends Figure {

	@Override
	public String getName() {
		return "屠龙者";
	}

	@Override
	public int getPower() {
		return 0;
	}

	@Override
	public String getInfo() {
		return "任何玩家使用龙时，你可以展示并使用此卡。屠龙者的力量比龙大，龙的灭地效果无效";
	}
}
