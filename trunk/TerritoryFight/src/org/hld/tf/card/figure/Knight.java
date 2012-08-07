package org.hld.tf.card.figure;

import org.hld.tf.card.base.Figure;

/**
 * 骑士
 */
public class Knight extends Figure {

	@Override
	public String getName() {
		return "骑士";
	}

	@Override
	public int getPower() {
		return 5;
	}

	@Override
	public String getInfo() {
		return "一回合中，后出的骑士卡比前出的骑士卡力量大1.当其他玩家对你使用强盗的抢夺技能时，牺牲骑士可以使对方无法抢夺";
	}
}
