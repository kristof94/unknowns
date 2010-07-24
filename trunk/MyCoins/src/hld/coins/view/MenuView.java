package hld.coins.view;

import hld.coins.R;
import hld.coins.constants.EngineConstants;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class MenuView extends AbstractView {
	private Image bg;
	private Image start0;
	private Image start1;
	private Image sound0;
	private Image sound1;
	private Image openfeint0;
	private Image openfeint1;
	private Image help0;
	private Image help1;
	private Image other0;
	private Image other1;
	private Point bgPoint;
	private Point startPoint;
	private Point soundPoint;
	private Point openfeintPoint;
	private Point helpPoint;
	private Point otherPoint;
	private Rect startRect;
	private Rect soundRect;
	private Rect openfeintRect;
	private Rect helpRect;
	private Rect otherRect;
	private boolean pressStart;
	private boolean pressSound;
	private boolean pressOpenfeint;
	private boolean pressHelp;
	private boolean pressOther;
	private boolean isOpenSound;
	
	public MenuView() {
		super(true);
		BitmapManager bitmapManager = BitmapManager.getInstance();
		bg = bitmapManager.getViewScaledImage(getClass(), R.drawable.menubg, scale, false);
		start0 = bitmapManager.getViewScaledImage(getClass(), R.drawable.startgame0000, scale,
				false);
		start1 = bitmapManager.getViewScaledImage(getClass(), R.drawable.startgame0001, scale,
				false);
		sound0 = bitmapManager.getViewScaledImage(getClass(), R.drawable.sound0000, scale, false);
		sound1 = bitmapManager.getViewScaledImage(getClass(), R.drawable.sound0001, scale, false);
		openfeint0 = bitmapManager.getViewScaledImage(getClass(), R.drawable.openfeint0000, scale,
				false);
		openfeint1 = bitmapManager.getViewScaledImage(getClass(), R.drawable.openfeint0001, scale,
				false);
		help0 = bitmapManager.getViewScaledImage(getClass(), R.drawable.helpa0000, scale, false);
		help1 = bitmapManager.getViewScaledImage(getClass(), R.drawable.helpa0001, scale, false);
		other0 = bitmapManager.getViewScaledImage(getClass(), R.drawable.othergames0000, scale,
				false);
		other1 = bitmapManager.getViewScaledImage(getClass(), R.drawable.othergames0001, scale,
				false);
		bgPoint = new Point(offsetX(0), offsetY(0));
		startPoint = new Point(offsetX(250), offsetY(108));
		soundPoint = new Point(offsetX(18), offsetY(12));
		openfeintPoint = new Point(offsetX(391), offsetY(278));
		helpPoint = new Point(offsetX(250), offsetY(222));
		otherPoint = new Point(offsetX(285), offsetY(165));
		startRect = new Rect(startPoint.x, startPoint.y, startPoint.x + start0.width, startPoint.y
				+ start0.height);
		soundRect = new Rect(soundPoint.x, soundPoint.y, soundPoint.x + sound0.width, soundPoint.y
				+ sound0.height);
		openfeintRect = new Rect(openfeintPoint.x, openfeintPoint.y, openfeintPoint.x
				+ openfeint0.width, openfeintPoint.y + openfeint0.height);
		helpRect = new Rect(helpPoint.x, helpPoint.y, helpPoint.x + help0.width, helpPoint.y
				+ help0.height);
		otherRect = new Rect(otherPoint.x, otherPoint.y, otherPoint.x + other0.width, otherPoint.y
				+ other0.height);
		isOpenSound = preferences.getBoolean(EngineConstants.IS_OPEN_SOUND,
				EngineConstants.DEFAULT_OPEN_SOUND);
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		graphics.drawImage(bg.imgae, bgPoint);
		graphics.drawImage(pressStart?start1.imgae:start0.imgae, startPoint);
		graphics.drawImage(isOpenSound?sound1.imgae:sound0.imgae, soundPoint);
		graphics.drawImage(pressOpenfeint?openfeint1.imgae:openfeint0.imgae, openfeintPoint);
		graphics.drawImage(pressHelp?help1.imgae:help0.imgae, helpPoint);
		graphics.drawImage(pressOther?other1.imgae:other0.imgae, otherPoint);
	}
	
	@Override
	public boolean onTouchListener(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(startRect.contains((int)event.getX(), (int)event.getY())) {
				pressStart = true;
			} else if(soundRect.contains((int)event.getX(), (int)event.getY())) {
				pressSound = true;
			} else if(openfeintRect.contains((int)event.getX(), (int)event.getY())) {
				pressOpenfeint = true;
			} else if(helpRect.contains((int)event.getX(), (int)event.getY())) {
				pressHelp = true;
			} else if(otherRect.contains((int)event.getX(), (int)event.getY())) {
				pressOther = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			if(pressStart && startRect.contains((int)event.getX(), (int)event.getY())) {
				//TODO
				//开始游戏
			} else if(pressSound && soundRect.contains((int)event.getX(), (int)event.getY())) {
				isOpenSound = !isOpenSound;
				preferences.putBoolean(EngineConstants.IS_OPEN_SOUND, isOpenSound);
			} else if(pressOpenfeint
					&& openfeintRect.contains((int)event.getX(), (int)event.getY())) {
				//进入平台
			} else if(pressHelp
					&& helpRect.contains((int)event.getX(), (int)event.getY())) {
				//TODO
				//游戏帮助
			} else if(pressOther && otherRect.contains((int)event.getX(), (int)event.getY())) {
				//更多游戏
			}
			reset();
			break;
		}
		return false;
	}
	
	@Override
	public void hide() {
		super.hide();
		reset();
	}
	
	private void reset() {
		pressStart = false;
		pressSound = false;
		pressOpenfeint = false;
		pressHelp = false;
		pressOther = false;
	}
}
