package com.yingzheng.android.generator;

import java.util.HashMap;

import com.yingzheng.android.interfaces.AbstractView;

/**
 * View Éú³ÉÆ÷
 * @author Mark
 *
 */
@SuppressWarnings("rawtypes")
public class ViewGenerator {

	private static HashMap<Integer, AbstractView> VIEW_MAP = new HashMap<Integer, AbstractView>();
	private static HashMap<Integer, Class> CLASS_MAP = new HashMap<Integer, Class>();

	public static AbstractView getView(Integer i) {
		AbstractView view = null;
		try {
			view = VIEW_MAP.get(i);
			if (view == null) {
				Class c = CLASS_MAP.get(i);
				view = (AbstractView) c.newInstance();
				VIEW_MAP.put(i, view);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;

	}

	public static void addView(Integer i, Class c) {
		CLASS_MAP.put(i, c);
	}

	public static void removeView(Integer i) {
		CLASS_MAP.remove(i);
	}
}
