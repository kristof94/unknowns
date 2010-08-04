package hld.coins.view;

import hld.coins.R;
import hld.coins.constants.EngineConstants;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;
import hld.coins.wrapper.Images;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

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
	
	private Image colon;
	
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
	
	private Image topic;
	
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
	
	private Point amountPoint;
	
	private Point coinPoint;
	
	private Point topicPoint;
	
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
	
	private long countdown;
	
	private float currentAmount;
	
	private int currentStage;
	
	private int currentLevel;
	
	private long currentTime;
	
	private boolean goTime;
	
	private int dragAddCoin, dragMoveCoin = -1;
	
	private Point dragAddCoinPoint;
	
	private long bestTime;
	
	private String bestStr;
	
	private DecimalFormat decimalFormat;
	
	private SimpleDateFormat dateFormat;
	
	public MainView() {
		super(true);
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
		coins = new Images[]{coinax, coinbx, coincx, coindx, coinex};
		clear = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.clear00, R.drawable.clear01);
		help = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.help00, R.drawable.help01);
		best = bitmapManager.getViewScaledImage(getClass(), R.drawable.best, scale, false);
		bestnum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.bestnum, 10, 1);
		colon = bitmapManager.getViewScaledImage(getClass(), R.drawable.maohao2, scale, false);
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
		topic = bitmapManager.getViewScaledImage(getClass(), R.drawable.timu, scale, false);
		bgPoint = new Point(offsetX(0), offsetY(0));
		clearPoint = new Point(offsetX(10), offsetY(215));
		helpPoint = new Point(offsetX(10), offsetY(290));
		coinaPoint = new Point(offsetX(116), offsetY(255));
		coinbPoint = new Point(offsetX(183), offsetY(252));
		coincPoint = new Point(offsetX(254), offsetY(263));
		coindPoint = new Point(offsetX(317), offsetY(245));
		coinePoint = new Point(offsetX(389), offsetY(218));
		bestPoint = new Point(offsetX(15), offsetY(12));
		timePoint = new Point(offsetX(-3), offsetY(255));
		levelPoint = new Point(offsetX(405), offsetY(15));
		amountPoint = new Point(offsetX(206), offsetY(10));
		coinPoint = new Point(offsetX(227), offsetY(38));
		stagePoint = new Point(offsetX(238), offsetY(168));
		topicPoint = new Point(offsetX(227), offsetY(192));
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
		decimalFormat = new DecimalFormat("0.00");
		dateFormat = new SimpleDateFormat("mm:ss");
		currentStage = 1;
		currentLevel = preferences.getInt(EngineConstants.LEVEL, EngineConstants.DEFAULT_LEVEL);
		bestTime = preferences.getLong(EngineConstants.BEST_TIME+currentLevel, EngineConstants.DEFAULT_BEST_TIME);
		if(bestTime>EngineConstants.DEFAULT_BEST_TIME) {
			bestStr = dateFormat.format(new Date(bestTime));
		}
		currentTime = System.currentTimeMillis();
		goTime = true;
		rules();
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		if(goTime) {
			long l = System.currentTimeMillis();
			countdown = countdown-(l-currentTime);
			currentTime = l;
		}
		graphics.drawImage(bg.imgae, bgPoint);
		for(int i = 0; i < coinShowList.size(); i++) {
			int index = coinShowList.get(i);
			Images images = coinList.get(index);
			Rect rect = coinRectList.get(index);
			graphics.drawImage(images, rect, isShowHelp);
		}
		if(dragAddCoin > -1) {
			graphics.drawImage(coins[dragAddCoin], dragAddCoinPoint, false);
		}
		graphics.drawImage(coina, coinaPoint, isShowHelp);
		graphics.drawImage(coinb, coinbPoint, isShowHelp);
		graphics.drawImage(coinc, coincPoint, isShowHelp);
		graphics.drawImage(coind, coindPoint, isShowHelp);
		graphics.drawImage(coine, coinePoint, isShowHelp);
		graphics.drawImage(clear, clearPoint, pressClear);
		graphics.drawImage(help, helpPoint, pressHelp);
		int x,y;
		char[] c = null;
		//best
		graphics.drawImage(best.imgae, bestPoint);
		x = bestPoint.x + 40;
		if(bestStr!=null) {
			c = bestStr.toCharArray();
			for(int i = 0; i<c.length; i++) {
				switch(c[i]) {
				case ':':
					graphics.drawImage(colon.imgae, i*(bestnum.getWidth()-2)+x, bestPoint.y);
					break;
				default:
					graphics.drawImage(bestnum, i*(bestnum.getWidth()-3)+x, bestPoint.y, Character.getNumericValue(c[i]));
					break;
				}
			}
		}
		//time
		c = dateFormat.format(new Date(countdown)).toCharArray();
		for(int i = 0; i<c.length; i++) {
			switch(c[i]) {
			case ':':
				graphics.drawImage(timecolon.imgae, i*timenum.getWidth()+timePoint.x, timePoint.y);
				break;
			default:
				graphics.drawImage(timenum, i*timenum.getWidth()+timePoint.x, timePoint.y, Character.getNumericValue(c[i])+2);
				break;
			}
		}
		//c总值
		graphics.drawImage(amount.imgae, amountPoint);
		x = amountPoint.x+15;
		c = decimalFormat.format(currentAmount).toCharArray();
		for(int i = 0; i<c.length; i++) {
			int index;
			switch(c[i]) {
			case '.':
				index = 0;
				break;
			case '/':
				index = 1;
				break;
			default:
				index = Character.getNumericValue(c[i]) + 2;
				break;
			}
			graphics.drawImage(amountnum, i*(amountnum.getWidth()-4)+x, amountPoint.y, index);
		}
		//c数
		graphics.drawImage(coin.imgae, coinPoint);
		x = coinPoint.x -20;
		c = String.valueOf(coinList.size()).toCharArray();
		for(int i = 0; i<c.length; i++) {
			graphics.drawImage(coinnum, i*coinnum.getWidth()+x, coinPoint.y, Character.getNumericValue(c[i]));
		}
		//l数
		graphics.drawImage(level.imgae, levelPoint);
		x = levelPoint.x+50;
		y = levelPoint.y+5;
		c = String.valueOf(currentLevel).toCharArray();
		for(int i = 0; i<c.length; i++) {
			graphics.drawImage(levelnum, i*levelnum.getWidth()+x, y, Character.getNumericValue(c[i]));
		}
		//目标
		graphics.drawImage(topic.imgae, topicPoint.x, topicPoint.y, Graphics.VCENTER | Graphics.HCENTER);
		graphics.drawImage(stagedash.imgae, stagePoint.x, stagePoint.y, Graphics.HCENTER | Graphics.VCENTER);
		graphics.drawImage(stagenum, stagePoint.x+20, stagePoint.y-10, currentStage);
		x = topicPoint.x+70;
		y = topicPoint.y-12;
		c = decimalFormat.format(targetAmount).toCharArray();
		for(int i = 0; i<c.length; i++) {
			int index;
			switch(c[i]) {
			case '.':
				index = 0;
				break;
			case '/':
				index = 1;
				break;
			default:
				index = Character.getNumericValue(c[i]) + 2;
				break;
			}
			graphics.drawImage(amountnum, i*(amountnum.getWidth()-4)+x, y, index);
		}
		x = topicPoint.x-85;
		y = topicPoint.y-13;
		c = String.valueOf(targetCount).toCharArray();
		for(int i = 0; i<c.length; i++) {
			graphics.drawImage(coinnum, i*coinnum.getWidth()+x, y, Character.getNumericValue(c[i]));
		}
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
				for(int i = 0; i < coinRectList.size(); i++) {
					Rect rect = coinRectList.get(i);
					if(rect.contains(x, y)) {
						dragMoveCoin = i;
						Collections.rotate(coinShowList.subList(coinShowList.indexOf(Integer.valueOf(dragMoveCoin)), coinShowList.size()), -1);
						break;
					}
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if(dragAddCoin > -1) {
				Images images = coins[dragAddCoin];
				dragAddCoinPoint.set(x - images.getWidth() / 2, y - images.getHeight() / 2);
			} else if(dragMoveCoin > -1) {
				Images images = coinList.get(dragMoveCoin);
				int w = images.getWidth() / 2;
				int h = images.getHeight() / 2;
				coinRectList.get(dragMoveCoin).set(x - w, y - h, x + w, y + h);
			}
			break;
		case MotionEvent.ACTION_UP:
			if(pressClear && clearRect.contains(x, y)) {
				clearAll();
			} else if(pressHelp && helpRect.contains(x, y)) {
				isShowHelp = !isShowHelp;
				preferences.putBoolean(EngineConstants.IS_SHOW_HELP, isShowHelp);
			} else if(dragAddCoin > -1 && y < offsetY(160)) {
				Images images = coins[dragAddCoin];
				coinList.add(images);
				int w = images.getWidth() / 2;
				int h = images.getHeight() / 2;
				coinRectList.add(new Rect(x - w, y - h, x + w, y + h));
				coinShowList.add(coinShowList.size());
				coinAmountList.add(coinsAmount[dragAddCoin]);
				currentAmount += coinsAmount[dragAddCoin];
				currentAmount = new BigDecimal(currentAmount, new MathContext(2)).floatValue();
				juge();
			} else if(dragMoveCoin > -1 && y > offsetY(160)) {
				coinList.remove(dragMoveCoin);
				coinRectList.remove(dragMoveCoin);
				coinShowList.remove(Integer.valueOf(dragMoveCoin));
				ListIterator<Integer> iter = coinShowList.listIterator();
				while(iter.hasNext()) {
					int i = iter.next();
					if(i>dragMoveCoin) iter.set(i-1);
				}
				currentAmount -= coinAmountList.remove(dragMoveCoin);
				currentAmount = new BigDecimal(currentAmount, new MathContext(2)).floatValue();
				juge();
			}
			reset();
			break;
		}
		return false;
	}
	
	private void rules() {
		targetCount = currentLevel;
		if(currentStage > 8) {
			targetCount += 2;
		} else if(currentStage > 4) {
			targetCount += 1;
		} else if(currentStage == 1) {
			int i = currentLevel;
			if(i>11) i = 11;
			countdown = (50+(i-1)*20)*1000;
		}
		targetAmount = 0;
		for(int i = 0; i < targetCount; i++) {
			targetAmount += coinsAmount[random.nextInt(coinsAmount.length)];
		}
		targetAmount = new BigDecimal(targetAmount, new MathContext(2)).floatValue();
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
		dragAddCoin = dragMoveCoin = -1;
	}
	
	private void clearAll() {
		currentAmount = 0;
		coinList.clear();
		coinRectList.clear();
		coinShowList.clear();
		coinAmountList.clear();
		dragAddCoin = dragMoveCoin = -1;
	}
}
