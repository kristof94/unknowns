package org.hld.tf.core.event;

import org.hld.tf.card.base.Territory;
import org.hld.tf.core.Player;

/**
 * 土地被消灭前的处理
 */
public interface wipeOutTerritoryBefore {
	public boolean handle(Player attacker, Player defender, Territory territory);
}
