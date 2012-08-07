package org.hld.tf.core.event;

import org.hld.tf.card.base.Figure;
import org.hld.tf.core.Player;

/**
 * 在出牌前修正人物卡的力量
 */
public interface AmendPower {
	public int handle(Player player, Figure figure);
}
