package hld.coins.view;

import hld.coins.R;
import hld.coins.constants.GameStatusConstants.Status;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.manager.GameStatusManger;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;
import hld.coins.wrapper.Images;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class HelpView extends AbstractView {
	private Image tips;
	private Images play;
	private Point tipsPoint;
	private Point playPoint;
	private Rect playRect;
	private boolean pressPlay;
	
	public HelpView() {
		super(true);
		BitmapManager bitmapManager = BitmapManager.getInstance();
		tips = bitmapManager.getViewScaledImage(getClass(), R.drawable.tipsbg, scale, false);
		play = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.play0000, R.drawable.play0001);
		tipsPoint = new Point(offsetX(0), offsetY(0));
		playPoint = new Point(offsetX(336), offsetY(232));
		playRect = new Rect(playPoint.x, playPoint.y, play.getWidth() + playPoint.x, play.getHeight() + playPoint.y);
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		graphics.drawImage(tips.imgae, tipsPoint);
		graphics.drawImage(play, playPoint, pressPlay);
	}
	
	@Override
	public boolean onTouchListener(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(playRect.contains((int)event.getX(), (int)event.getY())) {
				pressPlay = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			if(pressPlay && playRect.contains((int)event.getX(), (int)event.getY())) {
				GameStatusManger.getInstance().setStatusCurrent(Status.NEW);
			}
			pressPlay = false;
			break;
		}
		return false;
	}
}
