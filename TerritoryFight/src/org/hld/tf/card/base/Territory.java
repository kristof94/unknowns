package org.hld.tf.card.base;

import org.hld.tf.core.Game;
import org.hld.tf.core.Player;

/**
 * 领土卡通用定义
 */
public abstract class Territory {
	/**
	 * 名字
	 */
	public abstract String getName();
	
	/**
	 * 分数
	 */
	public abstract int getPoint();
	
	/**
	 * 描述
	 */
	public abstract String getInfo();
	
	/**
	 * 能否被消灭，默认可以
	 * @return
	 */
	public boolean canWipeOut() {
		return true;
	}
	
	/**
	 * 设置事件
	 * @param game
	 * @param player
	 */
	public void setEvent(Game game, Player player) {}
	
	/**
	 * 移除事件
	 * @param game
	 * @param player
	 */
	public void removeEvent(Game game, Player player) {}
}
