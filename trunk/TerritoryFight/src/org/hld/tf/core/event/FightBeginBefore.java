package org.hld.tf.core.event;

import org.hld.tf.core.Player;

/**
 * 争夺开始之前的处理
 */
public interface FightBeginBefore {
	public void handle(Player player);
}
