package org.hld.tf.card.territory;

import org.hld.tf.card.base.Territory;

/**
 * 魔法学院
 */
public class MagicAcademy extends Territory {

	@Override
	public String getName() {
		return "魔法学院";
	}

	@Override
	public int getPoint() {
		return 4;
	}

	@Override
	public String getInfo() {
		return "当有一张你所拥有的土地卡将要被消灭或者牺牲时，你可以用丢弃一张手卡来代替。每个魔法学院一回合只能使用一次代替能力";
	}
}
