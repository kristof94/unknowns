package org.hld.tf.core.event;

import org.hld.tf.card.base.Territory;
import org.hld.tf.core.Player;

/**
 * 获得土地卡之后的处理
 */
public interface DrawTerritoryAfter {
	public void handle(Player player, Territory territory);
}
