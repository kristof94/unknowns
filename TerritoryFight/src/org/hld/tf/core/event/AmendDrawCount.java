package org.hld.tf.core.event;

import org.hld.tf.core.Player;

/**
 * 在摸牌前修正摸牌数
 */
public interface AmendDrawCount {
	public int handle(Player player);
}
