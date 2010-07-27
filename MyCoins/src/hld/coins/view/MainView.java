package hld.coins.view;

import java.util.LinkedList;
import java.util.List;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import hld.coins.R;
import hld.coins.constants.EngineConstants;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.manager.GestureDetectorManager;
import hld.coins.util.LogUnit;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;
import hld.coins.wrapper.Images;

public class MainView extends AbstractView {
	private Image bg;
	private Images coina;
	private Images coinb;
	private Images coinc;
	private Images coind;
	private Images coine;
	private Images clear;
	private Images help;
	private Image best;
	private Images bestnum;
	private Images timenum;
	private Image timecolon;
	private Images stagenum;
	private Image stagedash;
	private Image level;
	private Images levelnum;
	private Image amount;
	private Images amountnum;
	private Image coin;
	private Images coinnum;
	private Point bgPoint;
	private Point coinaPoint;
	private Point coinbPoint;
	private Point coincPoint;
	private Point coindPoint;
	private Point coinePoint;
	private Point clearPoint;
	private Point helpPoint;
	private Point bestPoint;
	private Point bestnumPoint;
	private Point timePoint;
	private Point stagePoint;
	private Point levelPoint;
	private Point levelnumPoint;
	private Point amountPoint;
	private Point amountnumPoint;
	private Point coinPoint;
	private Point coinnumPoint;
	private Rect coinaRect;
	private Rect coinbRect;
	private Rect coincRect;
	private Rect coindRect;
	private Rect coineRect;
	private Rect clearRect;
	private Rect helpRect;
	private boolean isShowHelp;
	private boolean pressClear;
	private boolean pressHelp;
	private Image currentImage;
	private Point currentPoint;
	private List<Images> coinList;
	private GestureDetectorManager gestureDetectorManager;
	private SimpleOnGestureListener coinGestureListener;
	private Point test;
	
	public MainView() {
		super(true);
		coinGestureListener = new SimpleOnGestureListener();
		gestureDetectorManager = GestureDetectorManager.getInstance();
		gestureDetectorManager.addListener(coinGestureListener);
		BitmapManager bitmapManager = BitmapManager.getInstance();
		bg = bitmapManager.getViewScaledImage(getClass(), R.drawable.gamebg, scale, false);
		coina = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coina0000,
				R.drawable.coina0001, R.drawable.coina0002, R.drawable.coina0003);
		coinb = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coinb0000,
				R.drawable.coinb0001, R.drawable.coinb0002, R.drawable.coinb0003);
		coinc = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coinc0000,
				R.drawable.coinc0001, R.drawable.coinc0002, R.drawable.coinc0003);
		coind = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coind0000,
				R.drawable.coind0001, R.drawable.coind0002, R.drawable.coind0003);
		coine = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coine0000,
				R.drawable.coine0001, R.drawable.coine0002, R.drawable.coine0003);
		clear = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.clear0000, R.drawable.clear0001);
		help = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.help0000, R.drawable.help0001);
		best=bitmapManager.getViewScaledImage(getClass(), R.drawable.best, scale, false);
		bestnum=bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.bestshuzhi, 10, 1);
		timenum=bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.daojishi, 12, 1);
		timecolon=bitmapManager.getViewScaledImage(getClass(), R.drawable.daojishimaohao, scale, false);
		stagenum=bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.guangshutimushu, 9, 1);
		stagedash=bitmapManager.getViewScaledImage(getClass(), R.drawable.ganghao, scale, false);
		level=bitmapManager.getViewScaledImage(getClass(), R.drawable.level, scale, false);
		levelnum=bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.levelshuzi, 10, 1);
		amount=bitmapManager.getViewScaledImage(getClass(), R.drawable.mianzhi, scale, false);
		amountnum=bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.mianzhishuzhi, 12, 1);
		coin=bitmapManager.getViewScaledImage(getClass(), R.drawable.yingbishu, scale, false);
		coinnum=bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.yingbishuzhi, 10, 1);
		bgPoint=new Point(offsetX(0), offsetY(0));
		clearPoint = new Point(offsetX(10),offsetY(215));
		helpPoint = new Point(offsetX(10),offsetY(290));
		coinaPoint = new Point(offsetX(116),offsetY(255));
		coinbPoint = new Point(offsetX(183),offsetY(252));
		coincPoint = new Point(offsetX(254),offsetY(263));
		coindPoint = new Point(offsetX(317),offsetY(245));
		coinePoint = new Point(offsetX(389),offsetY(218));
		bestPoint = new Point(offsetX(15),offsetY(12));
	    levelPoint = new Point(offsetX(405),offsetY(15));
	    amountPoint = new Point(offsetX(206),offsetY(10));
	    coinPoint = new Point(offsetX(227),offsetY(38));
	    stagePoint = new Point(offsetX(238),offsetY(168));
	    coinaRect = new Rect(coinaPoint.x, coinaPoint.y, coinaPoint.x+coina.getWidth(), coinaPoint.y+coina.getHeight());
	    coinbRect = new Rect(coinbPoint.x, coinbPoint.y, coinbPoint.x+coinb.getWidth(), coinbPoint.y+coinb.getHeight());
	    coincRect = new Rect(coincPoint.x, coincPoint.y, coincPoint.x+coinc.getWidth(), coincPoint.y+coinc.getHeight());
	    coindRect = new Rect(coindPoint.x, coindPoint.y, coindPoint.x+coind.getWidth(), coindPoint.y+coind.getHeight());
	    coineRect = new Rect(coinePoint.x, coinePoint.y, coinePoint.x+coine.getWidth(), coinePoint.y+coine.getHeight());
	    clearRect = new Rect(clearPoint.x, clearPoint.y, clearPoint.x+clear.getWidth(), clearPoint.y+clear.getHeight());
	    helpRect = new Rect(helpPoint.x, helpPoint.y, helpPoint.x+help.getWidth(), helpPoint.y+help.getHeight());
	    isShowHelp=preferences.getBoolean(EngineConstants.IS_SHOW_HELP, EngineConstants.DEFAULT_SHOW_HELP);
	    coinList = new LinkedList<Images>();
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		graphics.drawImage(bg.imgae, bgPoint);
		graphics.drawImage(coina, coinaPoint, isShowHelp);
		graphics.drawImage(coinb, coinbPoint, isShowHelp);
		graphics.drawImage(coinc, coincPoint, isShowHelp);
		graphics.drawImage(coind, coindPoint, isShowHelp);
		graphics.drawImage(coine, coinePoint, isShowHelp);
		graphics.drawImage(clear, clearPoint, pressClear);
		graphics.drawImage(help, helpPoint, pressHelp);
		graphics.drawImage(best.imgae, bestPoint);
		graphics.drawImage(level.imgae, levelPoint);
		graphics.drawImage(amount.imgae, amountPoint);
		graphics.drawImage(coin.imgae, coinPoint);
		graphics.drawRect(clearRect.left, clearRect.top, clearRect.right, clearRect.bottom);
		graphics.drawString("clearRect:"+clearRect.left+","+clearRect.top+","+clearRect.right+","+clearRect.bottom, 0, 20, Graphics.TOP|Graphics.LEFT);
		graphics.drawString("clear.getWidth:"+clear.getWidth()+"  clear.getHeight:"+clear.getHeight(), 0, 40, Graphics.TOP|Graphics.LEFT);
		graphics.drawRect(helpRect.left, helpRect.top, helpRect.right, helpRect.bottom);
		graphics.drawString("helpRect:"+helpRect.left+","+helpRect.top+","+helpRect.right+","+helpRect.bottom, 0, 60, Graphics.TOP|Graphics.LEFT);
		graphics.drawString("help.getWidth:"+help.getWidth()+"  help.getHeight:"+help.getHeight(), 0, 80, Graphics.TOP|Graphics.LEFT);
		if(test!=null) graphics.drawRect(test.x, test.y, test.x+10, test.y+10);
	}
	
	@Override
	public boolean onTouchListener(MotionEvent event) {
		int x = (int)event.getX();
		int y = (int)event.getY();
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(clearRect.contains(x, y)) {
				pressClear = true;
			} else if(helpRect.contains(x, y)) {
				pressHelp = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			if(pressClear && clearRect.contains(x, y)) {
				clearAll();
			} else if(pressHelp && helpRect.contains(x, y)) {
				isShowHelp = !isShowHelp;
				preferences.putBoolean(EngineConstants.IS_SHOW_HELP, isShowHelp);
			}
			reset();
			break;
		}
		gestureDetectorManager.onTouchEvent(event);
		return false;
	}
	
	private void reset() {
		pressClear = false;
		pressHelp = false;
	}
	
	private void clearAll() {
		
	}
	
	@Override
	public void hide() {
		super.hide();
		gestureDetectorManager.remove(coinGestureListener);
	}
	
	private class CoinGestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			if (e1 == null || e2 == null){
				return false;
			}
			int x = (int)e2.getX();
			int y = (int)e2.getY();
			test = new Point(x, y);
			if(coinaRect.contains(x, y)) {
				addCoin(coina, coinaPoint, e1, e2, velocityX, velocityY);
			} else if(coinbRect.contains(x, y)) {
				addCoin(coinb, coinbPoint, e1, e2, velocityX, velocityY);
			} else if(coincRect.contains(x, y)) {
				addCoin(coinc, coincPoint, e1, e2, velocityX, velocityY);				
			} else if(coindRect.contains(x, y)) {
				addCoin(coind, coindPoint, e1, e2, velocityX, velocityY);
			} else if(coineRect.contains(x, y)) {
				addCoin(coine, coinePoint, e1, e2, velocityX, velocityY);
			}
			e1.recycle();
			e2.recycle();
			return false;
		}
		
		private void addCoin(Images images, Point point, MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			
		}
	}
}
