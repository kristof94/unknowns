package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 魔法师
 */
public class Mage extends Figure {

	@Override
	public String getName() {
		return "魔法师";
	}

	@Override
	public int getPower() {
		return 4;
	}

	@Override
	public String getInfo() {
		return "你所拥有的地将被消灭时，你可以展示此卡并使用此卡，使你的一个地不被消灭。你拥有魔法学院时，你的魔法师力量+2";
	}
}
