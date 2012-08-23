package org.hld.tf.card.base;

import org.hld.tf.core.Game;
import org.hld.tf.core.Player;

/**
 * 人物卡通用定义
 */
public abstract class Figure {
	/**
	 * 名字
	 */
	public abstract String getName();
	
	/**
	 * 力量
	 */
	public abstract int getPower();
	
	/**
	 * 描述
	 */
	public abstract String getInfo();
	
	/**
	 * 设置事件
	 * @param game
	 * @param player
	 */
	public void setEvent(Game game, Player player) {}
}
