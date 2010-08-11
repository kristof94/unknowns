package hld.coins.view;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import hld.coins.R;
import hld.coins.constants.EngineConstants;
import hld.coins.constants.GameStatusConstants.Status;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.manager.GameStatusManger;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;
import hld.coins.wrapper.Images;

public class FailureView extends AbstractView {
	private Image bg;
	private Images menu;
	private Images again;
	private Images highScore;
	private Point bgPoint;
	private Point menuPoint;
	private Point againPoint;
	private Point highScorePoint;
	private Rect menuRect;
	private Rect againRect;
	private Rect highScoreRect;
	private boolean pressMenu;
	private boolean pressAgain;
	private boolean pressHighScore;
	private MainView view;
	
	public FailureView(MainView view) {
		super(true);
		BitmapManager bitmapManager = BitmapManager.getInstance();
		bg = bitmapManager.getViewScaledImage(getClass(), R.drawable.shibaibg, scale, false);
		menu = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.menua0000, R.drawable.menua0001);
		again = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.againa0000, R.drawable.againa0001);
		highScore = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.hot0000, R.drawable.hot0001);
		bgPoint = new Point(offsetX(0), offsetY(0));
		menuPoint = new Point(offsetX(80), offsetY(166));
		againPoint = new Point(offsetX(292), offsetY(166));
		highScorePoint = new Point(offsetX(146), offsetY(220));
		menuRect = new Rect(menuPoint.x, menuPoint.y, menuPoint.x + menu.getWidth(), menuPoint.y + menu.getHeight());
		againRect = new Rect(againPoint.x, againPoint.y, againPoint.x + again.getWidth(), againPoint.y + again.getHeight());
		highScoreRect = new Rect(highScorePoint.x, highScorePoint.y, highScorePoint.x + highScore.getWidth(), highScorePoint.y
				+ highScore.getHeight());
		this.view = view;
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		graphics.drawImage(bg.imgae, bgPoint);
		graphics.drawImage(menu, menuPoint, pressMenu);
		graphics.drawImage(again, againPoint, pressAgain);
		graphics.drawImage(highScore, highScorePoint, pressHighScore);
	}
	
	@Override
	public boolean onTouchListener(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			int x = (int)event.getX();
			int y = (int)event.getY();
			if(menuRect.contains(x, y)) {
				pressMenu = true;
			} else if(againRect.contains(x, y)) {
				pressAgain = true;
			} else if(highScoreRect.contains(x, y)) {
				pressHighScore = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			if(pressMenu && menuRect.contains((int)event.getX(), (int)event.getY())) {
				GameStatusManger.getInstance().setStatusCurrent(Status.MENU);
			} else if(pressAgain && againRect.contains((int)event.getX(), (int)event.getY())) {
				close();
			} else if(pressHighScore && highScoreRect.contains((int)event.getX(), (int)event.getY())) {
				//еепп
			}
			pressMenu = pressAgain = pressHighScore = false;
			break;
		}
		return false;
	}
	
	public void open() {
		super.show();
		super.enable();
	}
	
	public void close() {
		super.disable();
		super.hide();
		view.open();
	}
}
