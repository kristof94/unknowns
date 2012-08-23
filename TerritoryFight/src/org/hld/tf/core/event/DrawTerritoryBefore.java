package org.hld.tf.core.event;

import org.hld.tf.card.base.Territory;
import org.hld.tf.core.Player;

/**
 * 获得土地卡之前判断是否能够获得
 */
public interface DrawTerritoryBefore {
	public boolean handle(Player player, Territory territory);
}
