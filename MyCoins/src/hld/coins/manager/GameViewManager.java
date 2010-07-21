package hld.coins.manager;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.MotionEvent;

import hld.coins.constants.EngineConstants;
import hld.coins.interfaces.AbstractView;
import hld.coins.interfaces.Channel;
import hld.coins.wrapper.Graphics;

public class GameViewManager {

	public int ScreenWidth;

	public int ScreenHight;

	public double scale;

	public int startX;

	public int startY;

	private static GameViewManager instance;

	private List<AbstractView> list;

	private Channel channel;

	private GameViewManager() {
		list = new ArrayList<AbstractView>();
	}

	public static GameViewManager getInstance() {
		if (instance == null) {
			instance = new GameViewManager();
		}
		return instance;
	}

	public boolean addView(AbstractView view) {
		return list.add(view);
	}

	public boolean removeView(AbstractView view) {
		BitmapManager.getInstance().releaseViewImage(view.getClass());
		view.hide();
		return list.remove(view);
	}

	public void removeAllView() {
		BitmapManager instance = BitmapManager.getInstance();
		for (AbstractView view : list) {
			instance.releaseViewImage(view.getClass());
			view.hide();
		}
		list.clear();
	}

	public void onDraw(Graphics graphics) {
		for (AbstractView view : list) {
			view.checkShowing();
			view.onDraw(graphics);
		}
	}

	public void onTouchListener() {
		MotionEvent[] motionEvent = channel.getMotionEvent();
		int size = list.size();
		for (MotionEvent event : motionEvent) {
			for (int i = size - 1; i <= 0; i--) {
				boolean b = list.get(i).onTouchListener(event);
				if (!b)
					break;
			}
			event.recycle();
		}
	}

	public void onIntentListener() {
		Intent[] IntentEvent = channel.getIntentEvent();
		for (Intent event : IntentEvent) {
			for (AbstractView view : list) {
				view.onIntentListener(event);
			}
		}
	}

	public void addMotionEventByManual(MotionEvent event) {
		channel.addMotionEvent(event);
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void adjust() {
		double d1 = ScreenWidth / EngineConstants.GAME_WIDTH;
		double d2 = ScreenHight / EngineConstants.GAME_HEIGHT;
		double min = Math.min(d1, d2);
		scale = min;
		int w = (int) (ScreenWidth / scale - ScreenWidth);
		int h = (int) (ScreenHight / scale - ScreenHight);
		int offsetW = Math.abs(w);
		int offsetH = Math.abs(h);
		startX = offsetW / 2;
		startY = offsetH / 2;
	}
}
