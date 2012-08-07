package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 欺诈师
 */
public class Swindler extends Figure {

	@Override
	public String getName() {
		return "欺诈师";
	}

	@Override
	public int getPower() {
		return 1;
	}

	@Override
	public String getInfo() {
		return "当你使用欺诈师时，每位其他玩家从手中丢弃一张人物卡";
	}
}
