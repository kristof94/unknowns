package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 税务官
 */
public class TaxOfficial extends Figure {

	@Override
	public String getName() {
		return "税务官";
	}

	@Override
	public int getPower() {
		return 5;
	}

	@Override
	public String getInfo() {
		return "当你使用税务官时，你可以获得目标玩家拥有的一个地，除非他丢弃2张手卡或展示国王";
	}
}
