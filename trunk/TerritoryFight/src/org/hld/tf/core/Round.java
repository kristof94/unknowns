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
	 * 回合开始补充人物卡时的加成
	 */
	public Map<String, Integer> supplyFigure = new HashMap<String, Integer>();
	
	public void playFigure(Player player, Figure figure) {
		List<Figure> list = figureMap.get(player.getId());
		if(list==null) {
			list = new LinkedList<Figure>();
			figureMap.put(player.getId(), list);
		}
		list.add(figure);
	}
	
	public void trickContinue(Library library) {
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
		if(maxPower>-1) {
			maxPower = -1;
			earlyHandPosition = maxHandPosition;
		}
	}
}
