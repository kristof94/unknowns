package org.hld.tf.core;

import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Territory;

/**
 * 响应监听器
 */
public interface Listener {
	
	/**
	 * 显示待争夺的领土
	 * @param territory
	 */
	public abstract void showFightTerritory(Territory territory);
	
	/**
	 * 轮到指定玩家争夺领土
	 * @param player
	 * @return
	 */
	public abstract Figure fightTerritory(Player player);
	
	/**
	 * 一轮争夺的胜利者
	 * @return
	 */
	public abstract void roundWinner(Player player);
}
