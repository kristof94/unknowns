package org.hld.tf.card.base;

import java.util.List;

/**
 * 牌库通用定义
 */
public abstract class Library {
	public Library() {
		init();
	}
	
	/**
	 * 初始化牌库
	 */
	public abstract void init();
	
	/**
	 * 获取人物牌堆
	 * @return
	 */
	public abstract List<Figure> getFigureDeck();
	
	/**
	 * 从牌堆抽一张人物卡
	 * @return
	 */
	public abstract Figure drawFigure();
	
	/**
	 * 弃一张人物卡到弃牌堆
	 * @param card
	 */
	public abstract void discardFigure(Figure card);
	
	/**
	 * 获取领土牌堆
	 * @return
	 */
	public abstract List<Territory> getTerritoryDeck();
	
	/**
	 * 从牌堆抽一张领土卡
	 * @return
	 */
	public abstract Territory drawTerritory();
	
	/**
	 * 弃一张领土卡到弃牌堆
	 * @param card
	 */
	public abstract void discardTerritory(Territory card);
}
