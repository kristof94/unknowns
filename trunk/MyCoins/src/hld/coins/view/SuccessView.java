package hld.coins.view;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import hld.coins.R;
import hld.coins.constants.EngineConstants;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;
import hld.coins.wrapper.Images;

public class SuccessView extends AbstractView {
	private Image bg;
	
	private Images again;
	
	private Images next;
	
	private Point bgPoint;
	
	private Point againPoint;
	
	private Point nextPoint;
	
	private Point levelPoint;
	
	private Rect againRect;
	private Rect nextRect;
	
	private boolean pressAgain;
	
	private boolean pressNext;
	
	private int level;
	
	public SuccessView() {
		super(true);
		BitmapManager bitmapManager = BitmapManager.getInstance();
		bg = bitmapManager.getViewScaledImage(getClass(), R.drawable.shenglibg, scale, false);
		again = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.againa0000, R.drawable.againa0001);
		next = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.next0000, R.drawable.next0001);
		bgPoint = new Point(offsetX(0), offsetY(0));
		againPoint = new Point(offsetX(285), offsetY(224));
		nextPoint = new Point(offsetX(93), offsetY(224));
		levelPoint = new Point(offsetX(340), offsetY(189));
		againRect = new Rect(againPoint.x, againPoint.y, againPoint.x+again.getWidth(), againPoint.y+again.getHeight());
		nextRect = new Rect(nextPoint.x, nextPoint.y, nextPoint.x+next.getWidth(), nextPoint.y+next.getHeight());
		level = preferences.getInt(EngineConstants.LEVEL, EngineConstants.DEFAULT_LEVEL);
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		graphics.drawImage(bg.imgae, bgPoint);
		graphics.drawString(level+"", levelPoint.x, levelPoint.y);
		graphics.drawImage(again, againPoint, pressAgain);
		graphics.drawImage(next, nextPoint, pressNext);
	}
	
	@Override
	public boolean onTouchListener(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			int x = (int)event.getX();
			int y = (int)event.getY();
			if(againRect.contains(x, y)) {
				pressAgain = true;
			} else if(nextRect.contains(x, y)) {
				pressNext = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			if(pressAgain && againRect.contains((int)event.getX(), (int)event.getY())) {
				
			} else if(pressNext && nextRect.contains((int)event.getX(), (int)event.getY())) {
				preferences.putInt(EngineConstants.LEVEL, level+1);
			}
			pressAgain = pressNext = false;
			break;
		}
		return false;
	}
}
