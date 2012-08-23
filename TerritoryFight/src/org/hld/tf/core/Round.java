package org.hld.tf.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hld.tf.card.base.Figure;
import org.hld.tf.card.base.Library;
import org.hld.tf.card.base.Territory;

public class Round {
	/**
	 * 争夺的领土
	 */
	public Territory fightTerritory;
	/**
	 * 先手位置
	 */
	public int earlyHandPosition;
	/**
	 * 使用的人物卡
	 */
	public Map<String, List<Figure>> figureMap = new HashMap<String, List<Figure>>();
	public int maxPower = -1;
	public int maxHandPosition;
	/**
	 * 当前回合各个玩家使用卡片技能的次数
	 * Map<玩家ID, Map<卡片类型, 使用次数>>
	 */
	public Map<String, Map<Class, Integer>> useCardCountMap = new HashMap<String, Map<Class,Integer>>();
	
	public void playFigure(Player player, Figure figure) {
		List<Figure> list = figureMap.get(player.getId());
		if(list==null) {
			list = new LinkedList<Figure>();
			figureMap.put(player.getId(), list);
		}
		list.add(figure);
	}
	
	/**
	 * 回合结束后的处理
	 * @param library
	 */
	public void trickContinue(Library library) {
		//将使用过后的人物卡放入弃牌堆
		Iterator<Entry<String, List<Figure>>> iterator = figureMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, List<Figure>> entry = iterator.next();
			Iterator<Figure> i = entry.getValue().iterator();
			while(i.hasNext()) {
				Figure figure = i.next();
				library.discardFigure(figure);
				i.remove();
			}
		}
		//重新设置下一回合的先手
		if(maxPower>-1) {
			maxPower = -1;
			earlyHandPosition = maxHandPosition;
		}
		//清除当前回合卡牌使用次数
		useCardCountMap.clear();
	}
}
