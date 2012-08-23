package org.hld.tf.core.event;

import org.hld.tf.core.Player;

/**
 * 在弃牌前修正手牌上限
 */
public interface AmendHandLimit {
	public int handle(Player player);
}
