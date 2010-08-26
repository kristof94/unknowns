package hld.coins.view;

import hld.coins.R;
import hld.coins.constants.EngineConstants;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.manager.SoundManager;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;
import hld.coins.wrapper.Images;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class SuccessView extends AbstractView {
	protected static final int RANK_A = 1;
	protected static final int RANK_B = 2;
	protected static final int RANK_C = 3;
	private int rank;
	private Image bg;
	private Images again;
	private Images next;
	private Images acup;
	private Images bcup;
	private Images ccup;
	private Image time;
	private Point bgPoint;
	private Point againPoint;
	private Point nextPoint;
	private Point levelPoint;
	private Point acupPoint;
	private Point bcupPoint;
	private Point ccupPoint;
	private Point timePoint;
	private Rect againRect;
	private Rect nextRect;
	private boolean pressAgain;
	private boolean pressNext;
	private boolean flag;
	private int level;
	private MainView view;
	private int musicId;
	private boolean alreadyPlay;
	private boolean isOpenSound;
	
	public SuccessView(MainView view) {
		super(true);
		BitmapManager bitmapManager = BitmapManager.getInstance();
		bg = bitmapManager.getViewScaledImage(getClass(), R.drawable.shenglibg, scale, false);
		again = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.againa0000, R.drawable.againa0001);
		next = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.next0000, R.drawable.next0001);
		acup = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.aucup0001, R.drawable.aucup0002, R.drawable.aucup0000);
		bcup = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.bgcup0001, R.drawable.bgcup0002, R.drawable.bgcup0000);
		ccup = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.cucup0001, R.drawable.cucup0002, R.drawable.cucup0000);
		bgPoint = new Point(offsetX(0), offsetY(0));
		againPoint = new Point(offsetX(285), offsetY(224));
		nextPoint = new Point(offsetX(93), offsetY(224));
		levelPoint = new Point(offsetX(340), offsetY(220));
		acupPoint = new Point(offsetX(111), offsetY(84));
		bcupPoint = new Point(offsetX(201), offsetY(84));
		ccupPoint = new Point(offsetX(291), offsetY(84));
		timePoint = new Point(offsetX(230), offsetY(185));
		againRect = new Rect(againPoint.x, againPoint.y, againPoint.x + again.getWidth(), againPoint.y + again.getHeight());
		nextRect = new Rect(nextPoint.x, nextPoint.y, nextPoint.x + next.getWidth(), nextPoint.y + next.getHeight());
		this.view = view;
		enable();
	}
	
	public void setRank(int rank) {
		switch(rank) {
		case RANK_A:
			musicId = R.raw.gold;
			break;
		case RANK_B:
			musicId = R.raw.silver;
			break;
		case RANK_C:
			musicId = R.raw.bronze;
			break;
		}
		this.rank = rank;
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		graphics.drawImage(bg.imgae, bgPoint);
		graphics.drawString(level + "", levelPoint.x, levelPoint.y);
		graphics.drawImage(again, againPoint, pressAgain);
		graphics.drawImage(next, nextPoint, pressNext);
		if(rank==RANK_A) graphics.drawImage(acup, acupPoint, flag=!flag);
		else graphics.drawImage(acup, acupPoint, 2);
		if(rank==RANK_B) graphics.drawImage(bcup, bcupPoint, flag=!flag);
		else graphics.drawImage(bcup, bcupPoint, 2);
		if(rank==RANK_C) graphics.drawImage(ccup, ccupPoint, flag=!flag);
		else graphics.drawImage(ccup, ccupPoint, 2);
		if(isOpenSound && !alreadyPlay) {
			SoundManager.getInstance().play(musicId);
			alreadyPlay = true;
		}
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
				close();
			} else if(pressNext && nextRect.contains((int)event.getX(), (int)event.getY())) {
				preferences.putInt(EngineConstants.LEVEL, level + 1);
				close();
			}
			pressAgain = pressNext = false;
			break;
		}
		return false;
	}
	
	public void open() {
		super.show();
		super.enable();
		level = preferences.getInt(EngineConstants.LEVEL, EngineConstants.DEFAULT_LEVEL);
		isOpenSound = preferences.getBoolean(EngineConstants.IS_OPEN_SOUND, EngineConstants.DEFAULT_OPEN_SOUND);
	}
	
	public void close() {
		if(isOpenSound) {
			alreadyPlay = false;
		}
		super.disable();
		super.hide();
		view.open();
	}
}
