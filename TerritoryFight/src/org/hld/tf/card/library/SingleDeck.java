package org.hld.tf.card.library;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.hld.tf.card.base.*;
import org.hld.tf.card.figure.*;
import org.hld.tf.card.territory.*;

/**
 * 单副牌
 */
public class SingleDeck extends Library {
	/**
	 * 人物卡牌堆
	 */
	LinkedList<Figure> figureDeck;
	/**
	 * 人物卡弃牌堆
	 */
	LinkedList<Figure> figureDeadwood;
	/**
	 * 领土卡牌堆
	 */
	LinkedList<Territory> territoryDeck;
	/**
	 * 领土卡弃牌堆
	 */
	LinkedList<Territory> territoryDeadwood;
	
	@Override
	public void init() {
		Figure[] figures = new Figure[54];
		int i = 0;
		i = fillDeck(figures, Farmer.class, i, 4);
		i = fillDeck(figures, Traveller.class, i, 4);
		i = fillDeck(figures, Swindler.class, i, 4);
		i = fillDeck(figures, Merchant.class, i, 4);
		i = fillDeck(figures, DragonSlayer.class, i, 1);
		i = fillDeck(figures, Dragon.class, i, 1);
		i = fillDeck(figures, Refugee.class, i, 4);
		i = fillDeck(figures, Thief.class, i, 4);
		i = fillDeck(figures, Robber.class, i, 4);
		i = fillDeck(figures, Warmonger.class, i, 2);
		i = fillDeck(figures, Mercenary.class, i, 1);
		i = fillDeck(figures, Knight.class, i, 5);
		i = fillDeck(figures, TaxOfficial.class, i, 4);
		i = fillDeck(figures, Bishop.class, i, 4);
		i = fillDeck(figures, King.class, i, 4);
		i = fillDeck(figures, Mage.class, i, 4);
		Territory[] territories = new Territory[25];
		i = 0;
		i = fillDeck(territories, Farm.class, i, 8);
		i = fillDeck(territories, Market.class, i, 4);
		i = fillDeck(territories, MagicAcademy.class, i, 3);
		i = fillDeck(territories, Barrack.class, i, 3);
		i = fillDeck(territories, Castle.class, i, 4);
		i = fillDeck(territories, Church.class, i, 2);
		i = fillDeck(territories, Port.class, i, 1);
		figureDeck = new LinkedList<Figure>();
		shuffle(figureDeck, figures);
		figureDeadwood = new LinkedList<Figure>();
		territoryDeck = new LinkedList<Territory>();
		shuffle(territoryDeck, territories);
		territoryDeadwood = new LinkedList<Territory>();
	}
	
	private int fillDeck(Object[] a, Class c, int i, int n) {
		try {
			for(;n>0;n--) a[i++] = c.newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	private void shuffle(List l, Object[] a) {
		if(a==null || a.length==0) return;
		Random r = new Random();
		for(int i = a.length-1; i>0; i--) {
			swap(a, i, r.nextInt(i));
		}
		swap(a, 0, r.nextInt(a.length-1));
		for(Object o:a) {
			l.add(o);
		}
	}
	
	private void swap(Object[] a, int i1, int i2) {
		Object o = a[i1];
		a[i1] = a[i2];
		a[i2] = o;
	}
	
	@Override
	public List<Figure> getFigureDeck() {
		return figureDeck;
	}
	
	@Override
	public Figure drawFigure() {
		Figure character = figureDeck.poll();
		if(character==null) {
			Figure[] characters = figureDeadwood.toArray(new Figure[figureDeadwood.size()]);
			figureDeadwood.clear();
			shuffle(figureDeck, characters);
			return drawFigure();
		}
		return character;
	}
	
	@Override
	public void discardFigure(Figure card) {
		figureDeadwood.add(card);
	}
	
	@Override
	public List<Territory> getTerritoryDeck() {
		return territoryDeck;
	}
	
	@Override
	public Territory drawTerritory() {
		Territory territory = territoryDeck.poll();
		if(territory==null) {
			Territory[] territories = territoryDeadwood.toArray(new Territory[territoryDeadwood.size()]);
			territoryDeadwood.clear();
			shuffle(territoryDeck, territories);
			return drawTerritory();
		}
		return territory;
	}
	
	@Override
	public void discardTerritory(Territory card) {
		territoryDeadwood.add(card);
	}
}
