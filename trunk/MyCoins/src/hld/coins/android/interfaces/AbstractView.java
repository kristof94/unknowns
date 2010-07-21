package com.yingzheng.android.interfaces;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MotionEvent;

import com.yingzheng.android.manager.GameViewManager;
import com.yingzheng.android.manager.PreferencesManager;
import com.yingzheng.android.wrapper.Graphics;

public abstract class AbstractView implements ViewInterface {

	private boolean isShowing;
	protected SharedPreferences preferences;
	protected static int height;
	protected static int width;
	protected static double scale;
	private static int startX;
	private static int startY;
	private static boolean isInit;

	public AbstractView(boolean isJoinViewManager) {
		if (isJoinViewManager)
			GameViewManager.getInstance().addView(this);
		if (!isInit) {
			preferences = PreferencesManager.getInstance().getPreferences();
			scale = GameViewManager.getInstance().scale;
			startX = GameViewManager.getInstance().startX;
			startY = GameViewManager.getInstance().startY;
			isInit = true;
		}
	}

	@Override
	public void hide() {
		isShowing = false;
	}

	@Override
	public void onDraw(Graphics graphics) {

	}

	@Override
	public void onIntentListener(Intent intent) {

	}

	@Override
	public boolean onTouchListener(MotionEvent event) {
		return false;
	}

	@Override
	public void show() {

	}

	/**
	 * 供{#GameViewManager}画图前检查VIew是否已经被打开
	 */
	public void checkShowing() {
		if (!isShowing)
			show();
		isShowing = true;
	}

	protected final int offsetX(int number) {
		return (int) (startX + (number * scale));
	}

	protected final int offsetY(int number) {
		return (int) (startY + (number * scale));
	}

	protected final int offsetW(int number) {
		return (int) (number * scale);
	}

	protected final int offsetH(int number) {
		return (int) (number * scale);
	}

}
