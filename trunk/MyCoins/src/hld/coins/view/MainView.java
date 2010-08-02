package hld.coins.view;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import hld.coins.R;
import hld.coins.constants.EngineConstants;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.manager.GestureDetectorManager;
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
	
	private Images coinax;
	
	private Images coinbx;
	
	private Images coincx;
	
	private Images coindx;
	
	private Images coinex;
	
	private Images[] coins;
	
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
	
	private List<Rect> coinRectList;
	
	private List<Integer> coinShowList;
	
	private List<Float> coinAmountList;
	
	private float coinaAmount;
	
	private float coinbAmount;
	
	private float coincAmount;
	
	private float coindAmount;
	
	private float coineAmount;
	
	private float[] coinsAmount;
	
	private Random random;
	
	private float targetAmount;
	
	private int targetCount;
	
	private float currentAmount;
	
	private int currentStage;
	
	private int currentLevel;
	
	private int dragAddCoin,dragMoveCoin = -1;
	
	private Point dragAddCoinPoint;
	
//	private GestureDetectorManager gestureDetectorManager;
//	
//	private SimpleOnGestureListener coinGestureListener;
	
	private Point test;
	
	public MainView() {
		super(true);
//		coinGestureListener = new CoinGestureListener();
//		gestureDetectorManager = GestureDetectorManager.getInstance();
//		gestureDetectorManager.addListener(coinGestureListener);
		BitmapManager bitmapManager = BitmapManager.getInstance();
		bg = bitmapManager.getViewScaledImage(getClass(), R.drawable.gamebg, scale, false);
		coina = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coina0000, R.drawable.coina0001);
		coinb = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coinb0000, R.drawable.coinb0001);
		coinc = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coinc0000, R.drawable.coinc0001);
		coind = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coind0000, R.drawable.coind0001);
		coine = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coine0000, R.drawable.coine0001);
		coinax = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coina0002, R.drawable.coina0003);
		coinbx = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coinb0002, R.drawable.coinb0003);
		coincx = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coinc0002, R.drawable.coinc0003);
		coindx = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coind0002, R.drawable.coind0003);
		coinex = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coine0002, R.drawable.coine0003);
		coins = new Images[]{coinax,coinbx,coincx,coindx,coinex};
		clear = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.clear0000, R.drawable.clear0001);
		help = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.help0000, R.drawable.help0001);
		best = bitmapManager.getViewScaledImage(getClass(), R.drawable.best, scale, false);
		bestnum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.bestshuzhi, 10, 1);
		timenum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.daojishi, 12, 1);
		timecolon = bitmapManager.getViewScaledImage(getClass(), R.drawable.daojishimaohao, scale, false);
		stagenum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.guangshutimushu, 9, 1);
		stagedash = bitmapManager.getViewScaledImage(getClass(), R.drawable.ganghao, scale, false);
		level = bitmapManager.getViewScaledImage(getClass(), R.drawable.level, scale, false);
		levelnum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.levelshuzi, 10, 1);
		amount = bitmapManager.getViewScaledImage(getClass(), R.drawable.mianzhi, scale, false);
		amountnum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.mianzhishuzhi, 12, 1);
		coin = bitmapManager.getViewScaledImage(getClass(), R.drawable.yingbishu, scale, false);
		coinnum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.yingbishuzhi, 10, 1);
		bgPoint = new Point(offsetX(0), offsetY(0));
		clearPoint = new Point(offsetX(10), offsetY(215));
		helpPoint = new Point(offsetX(10), offsetY(290));
		coinaPoint = new Point(offsetX(116), offsetY(255));
		coinbPoint = new Point(offsetX(183), offsetY(252));
		coincPoint = new Point(offsetX(254), offsetY(263));
		coindPoint = new Point(offsetX(317), offsetY(245));
		coinePoint = new Point(offsetX(389), offsetY(218));
		bestPoint = new Point(offsetX(15), offsetY(12));
		timePoint = new Point(offsetX(54), offsetY(263));
		levelPoint = new Point(offsetX(405), offsetY(15));
		amountPoint = new Point(offsetX(206), offsetY(10));
		coinPoint = new Point(offsetX(227), offsetY(38));
		stagePoint = new Point(offsetX(238), offsetY(168));
		coinaRect = new Rect(coinaPoint.x, coinaPoint.y, coinaPoint.x + coina.getWidth(), coinaPoint.y + coina.getHeight());
		coinbRect = new Rect(coinbPoint.x, coinbPoint.y, coinbPoint.x + coinb.getWidth(), coinbPoint.y + coinb.getHeight());
		coincRect = new Rect(coincPoint.x, coincPoint.y, coincPoint.x + coinc.getWidth(), coincPoint.y + coinc.getHeight());
		coindRect = new Rect(coindPoint.x, coindPoint.y, coindPoint.x + coind.getWidth(), coindPoint.y + coind.getHeight());
		coineRect = new Rect(coinePoint.x, coinePoint.y, coinePoint.x + coine.getWidth(), coinePoint.y + coine.getHeight());
		clearRect = new Rect(clearPoint.x, clearPoint.y, clearPoint.x + clear.getWidth(), clearPoint.y + clear.getHeight());
		helpRect = new Rect(helpPoint.x, helpPoint.y, helpPoint.x + help.getWidth(), helpPoint.y + help.getHeight());
		isShowHelp = preferences.getBoolean(EngineConstants.IS_SHOW_HELP, EngineConstants.DEFAULT_SHOW_HELP);
		coinList = new LinkedList<Images>();
		coinRectList = new LinkedList<Rect>();
		coinShowList = new LinkedList<Integer>();
		coinAmountList = new LinkedList<Float>();
		coinaAmount = 0.01f;
		coinbAmount = 0.05f;
		coincAmount = 0.10f;
		coindAmount = 0.25f;
		coineAmount = 0.50f;
		coinsAmount = new float[]{coinaAmount, coinbAmount, coincAmount, coindAmount, coineAmount};
		random = new Random();
		dragAddCoinPoint = new Point();
		currentStage = 1;
		currentLevel = 1;
		rules();
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		graphics.drawImage(bg.imgae, bgPoint);
		for(int i = 0; i < coinShowList.size(); i++) {
			int index = coinShowList.get(i);
			Images images = coinList.get(index);
			Rect rect = coinRectList.get(index);
			graphics.drawImage(images, rect, isShowHelp);
		}
		if(dragAddCoin>-1) {
			graphics.drawImage(coins[dragAddCoin], dragAddCoinPoint, false);
		}
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
		//		if(test!=null) graphics.drawRect(test.x, test.y, 10, 10);
		graphics.drawString("currentAmount:" + currentAmount, 0, 25, Graphics.TOP | Graphics.LEFT);
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
			} else if(coinaRect.contains(x, y)) {
				dragAddCoin = 0;
			} else if(coinbRect.contains(x, y)) {
				dragAddCoin = 1;
			} else if(coincRect.contains(x, y)) {
				dragAddCoin = 2;
			} else if(coindRect.contains(x, y)) {
				dragAddCoin = 3;
			} else if(coineRect.contains(x, y)) {
				dragAddCoin = 4;
			} else {
				for(int i = 0; i<coinRectList.size(); i++) {
					Rect rect = coinRectList.get(i);
					if(rect.contains(x, y)) {
						dragMoveCoin = i;
						break;
					}
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if(dragAddCoin>-1) {
				Images images = coins[dragAddCoin];
				dragAddCoinPoint.set(x+images.getWidth()/2, y+images.getHeight()/2);
			} else if(dragMoveCoin>-1) {
				Images images = coinList.get(dragMoveCoin);
				int w = images.getWidth()/2;
				int h = images.getHeight()/2;
				coinRectList.get(dragMoveCoin).set(x - w, y - h, x + w, y + h);
				Collections.rotate(coinShowList.subList(dragMoveCoin, coinShowList.size()), -1);
			}
			break;
		case MotionEvent.ACTION_UP:
			if(pressClear && clearRect.contains(x, y)) {
				clearAll();
			} else if(pressHelp && helpRect.contains(x, y)) {
				isShowHelp = !isShowHelp;
				preferences.putBoolean(EngineConstants.IS_SHOW_HELP, isShowHelp);
			} else if(dragAddCoin>-1 && y < offsetY(160)) {
				Images images = coins[dragAddCoin];
				coinList.add(images);
				int w = images.getWidth() / 2;
				int h = images.getHeight() / 2;
				coinRectList.add(new Rect(x - w, y - h, x + w, y + h));
				coinShowList.add(coinShowList.size());
				coinAmountList.add(coinsAmount[dragAddCoin]);
				currentAmount += coinsAmount[dragAddCoin];
				juge();
			} else if(dragMoveCoin>-1 && y > offsetY(160)) {
				coinList.remove(dragMoveCoin);
				coinRectList.remove(dragMoveCoin);
				coinShowList.remove(Integer.valueOf(dragMoveCoin));
				currentAmount -= coinAmountList.remove(dragMoveCoin);
				juge();
			}
			reset();
			break;
		}
//		gestureDetectorManager.onTouchEvent(event);
		return false;
	}
	
	private void rules() {
		targetCount = currentLevel;
		if(currentStage > 8) {
			targetCount += 2;
		} else if(currentStage > 4) {
			targetCount += 1;
		}
		targetAmount = 0;
		for(int i = 0; i < targetCount; i++) {
			targetAmount += coinsAmount[random.nextInt(coinsAmount.length)];
		}
	}
	
	private void juge() {
		if(coinList.size() == targetCount && currentAmount == targetAmount) {
			clearAll();
			if(currentStage == 9) {
				currentStage = 1;
				currentLevel++;
			} else {
				currentStage++;
			}
			rules();
		}
	}
	
	private void reset() {
		pressClear = false;
		pressHelp = false;
	}
	
	private void clearAll() {
		currentAmount = 0;
		coinList.clear();
		coinRectList.clear();
		coinShowList.clear();
		coinAmountList.clear();
		dragAddCoin = dragMoveCoin = -1;
	}
	
	@Override
	public void hide() {
		super.hide();
//		gestureDetectorManager.remove(coinGestureListener);
	}
	private class CoinGestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			//			if (e1 == null || e2 == null){
			//				return false;
			//			}
			int x = (int)e1.getX();
			int y = (int)e1.getY();
			if(coinaRect.contains(x, y)) {
				addCoin(coinax, coinaAmount, e2);
			} else if(coinbRect.contains(x, y)) {
				addCoin(coinbx, coinbAmount, e2);
			} else if(coincRect.contains(x, y)) {
				addCoin(coincx, coincAmount, e2);
			} else if(coindRect.contains(x, y)) {
				addCoin(coindx, coindAmount, e2);
			} else if(coineRect.contains(x, y)) {
				addCoin(coinex, coineAmount, e2);
			}
			//			e1.recycle();
			//			e2.recycle();
			return false;
		}
		
		private void addCoin(Images images, float amount, MotionEvent e) {
			int x = (int)e.getX();
			int y = (int)e.getY();
			//			test = new Point(x, y);
			coinList.add(images);
			int w = images.getWidth() / 2;
			int h = images.getHeight() / 2;
			coinRectList.add(new Rect(x - w, y - h, x + w, y + h));
			coinShowList.add(coinShowList.size());
			coinAmountList.add(amount);
			currentAmount += amount;
			juge();
		}
	}
}
