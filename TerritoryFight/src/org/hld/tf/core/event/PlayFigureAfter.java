package org.hld.tf.core.event;

import org.hld.tf.card.base.Figure;
import org.hld.tf.core.Player;

/**
 * 出牌之后的处理
 */
public interface PlayFigureAfter {
	public void handle(Player player, Figure figure);
}
