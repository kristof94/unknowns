package hld.coins.view;

import hld.coins.R;
import hld.coins.constants.EngineConstants;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.manager.SoundManager;
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
	private Image bluebg;
	private Image redbg;
	private Image holebg;
	private Image yying;
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
	protected Images[] coins;
	protected Images[] coinxs;
	private Images clear;
	private Images help;
	private Image colon;
	private Images addnum;
	private Images totalnum;
	private Images amountnum;
	private Images coinnum;
	private Images agcup;
	private Images bgcup;
	private Images cgcup;
	private Images whitenum;
	private Images yellownum;
	private Image cover1;
	private Image cover2;
	protected Point bgPoint;
	private Point bluebgPoint;
	private Point redbgPoint;
	private Point holebgPoint;
	private Point yyingPoint;
	protected Point coinaPoint;
	protected Point coinbPoint;
	protected Point coincPoint;
	protected Point coindPoint;
	protected Point coinePoint;
	private Point clearPoint;
	private Point helpPoint;
	private Point bestPoint;
	private Point timePoint;
	private Point levelPoint;
	private Point amountPoint;
	private Point coinPoint;
	private Point targetAmountPoint;
	private Point targetCountPoint;
	private Rect coinaRect;
	private Rect coinbRect;
	private Rect coincRect;
	private Rect coindRect;
	private Rect coineRect;
	private Rect clearRect;
	private Rect helpRect;
	protected boolean isShowHelp;
	private boolean isOpenSound;
	private boolean pressClear;
	private boolean pressHelp;
	protected List<Images> coinList;
	protected List<Rect> coinRectList;
	protected List<Integer> coinShowList;
	private List<Float> coinAmountList;
	private float coinaAmount;
	private float coinbAmount;
	private float coincAmount;
	private float coindAmount;
	private float coineAmount;
	protected float[] coinsAmount;
	private Random random;
	protected float targetAmount;
	private int targetCount;
	private long countdown;
	protected float currentAmount;
	protected int currentStage;
	protected int currentLevel;
	private long currentTime;
	protected boolean goTime;
	private int dragAddCoin, dragMoveCoin = -1;
	private Point dragAddCoinPoint;
	private long bestTime;
	protected String bestStr;
	private DecimalFormat decimalFormat;
	private SimpleDateFormat dateFormat;
	private SuccessView successView;
	private FailureView failureView;
	
	public MainView(boolean isNew) {
		super(true);
		BitmapManager bitmapManager = BitmapManager.getInstance();
		bg = bitmapManager.getViewScaledImage(getClass(), R.drawable.gamebg, scale, false);
		bluebg = bitmapManager.getViewScaledImage(getClass(), R.drawable.gamebg01, scale, false);
		redbg = bitmapManager.getViewScaledImage(getClass(), R.drawable.gamebg02, scale, false);
		holebg = bitmapManager.getViewScaledImage(getClass(), R.drawable.gamebg05, scale, false);
		yying = bitmapManager.getViewScaledImage(getClass(), R.drawable.yyin, scale, false);
		coina = bitmapManager.getGolbalImages(R.drawable.coina0000, R.drawable.coina0001);
		coinb = bitmapManager.getGolbalImages(R.drawable.coinb0000, R.drawable.coinb0001);
		coinc = bitmapManager.getGolbalImages(R.drawable.coinc0000, R.drawable.coinc0001);
		coind = bitmapManager.getGolbalImages(R.drawable.coind0000, R.drawable.coind0001);
		coine = bitmapManager.getGolbalImages(R.drawable.coine0000, R.drawable.coine0001);
		coinax = bitmapManager.getGolbalImages(R.drawable.coina0002, R.drawable.coina0003);
		coinbx = bitmapManager.getGolbalImages(R.drawable.coinb0002, R.drawable.coinb0003);
		coincx = bitmapManager.getGolbalImages(R.drawable.coinc0002, R.drawable.coinc0003);
		coindx = bitmapManager.getGolbalImages(R.drawable.coind0002, R.drawable.coind0003);
		coinex = bitmapManager.getGolbalImages(R.drawable.coine0002, R.drawable.coine0003);
		coins = new Images[]{coina, coinb, coinc, coind, coine};
		coinxs = new Images[]{coinax, coinbx, coincx, coindx, coinex};
		clear = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.clear01, R.drawable.clear02);
		help = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.help00, R.drawable.help01);
		whitenum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.whitenum, 12, 1);
		colon = bitmapManager.getViewScaledImage(getClass(), R.drawable.maohao, scale, false);
		addnum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.su01, 12, 1);
		totalnum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.su02, 12, 1);
		amountnum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.su03, 12, 1);
		coinnum = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.su04, 10, 1);
		agcup = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.aucup0000, R.drawable.aucup0001, R.drawable.aucup0002);
		bgcup = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.bgcup0000, R.drawable.bgcup0001, R.drawable.bgcup0002);
		cgcup = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.cucup0000, R.drawable.cucup0001, R.drawable.cucup0002);
		bgPoint = new Point(offsetX(0), offsetY(0));
		bluebgPoint = new Point(offsetX(113), offsetY(174));
		redbgPoint = new Point(offsetX(280), offsetY(174));
		holebgPoint = new Point(offsetX(0), offsetY(36));
		yyingPoint = new Point(offsetX(113), offsetY(174));
		clearPoint = new Point(offsetX(10), offsetY(229));
		helpPoint = new Point(offsetX(10), offsetY(276));
		coinaPoint = new Point(offsetX(116), offsetY(255));
		coinbPoint = new Point(offsetX(183), offsetY(252));
		coincPoint = new Point(offsetX(254), offsetY(263));
		coindPoint = new Point(offsetX(317), offsetY(245));
		coinePoint = new Point(offsetX(389), offsetY(218));
		bestPoint = new Point(offsetX(64), offsetY(8));
		timePoint = new Point(offsetX(0), offsetY(168));
		levelPoint = new Point(offsetX(420), offsetY(8));
		amountPoint = new Point(offsetX(223), offsetY(8));
		coinPoint = new Point(offsetX(210), offsetY(34));
		targetCountPoint = new Point(offsetX(113), offsetY(174));
		targetAmountPoint = new Point(offsetX(281), offsetY(174));
		coinaRect = new Rect(coinaPoint.x, coinaPoint.y, coinaPoint.x + coina.getWidth(), coinaPoint.y + coina.getHeight());
		coinbRect = new Rect(coinbPoint.x, coinbPoint.y, coinbPoint.x + coinb.getWidth(), coinbPoint.y + coinb.getHeight());
		coincRect = new Rect(coincPoint.x, coincPoint.y, coincPoint.x + coinc.getWidth(), coincPoint.y + coinc.getHeight());
		coindRect = new Rect(coindPoint.x, coindPoint.y, coindPoint.x + coind.getWidth(), coindPoint.y + coind.getHeight());
		coineRect = new Rect(coinePoint.x, coinePoint.y, coinePoint.x + coine.getWidth(), coinePoint.y + coine.getHeight());
		clearRect = new Rect(clearPoint.x, clearPoint.y, clearPoint.x + clear.getWidth(), clearPoint.y + clear.getHeight());
		helpRect = new Rect(helpPoint.x, helpPoint.y, helpPoint.x + help.getWidth(), helpPoint.y + help.getHeight());
		isShowHelp = preferences.getBoolean(EngineConstants.IS_SHOW_HELP, EngineConstants.DEFAULT_SHOW_HELP);
		isOpenSound = preferences.getBoolean(EngineConstants.IS_OPEN_SOUND, EngineConstants.DEFAULT_OPEN_SOUND);
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
		decimalFormat = new DecimalFormat("000.00");
		dateFormat = new SimpleDateFormat("m:ss");
		currentStage = 1;
		successView = new SuccessView(this);
		successView.disable();
		successView.hide();
		failureView = new FailureView(this);
		failureView.disable();
		failureView.hide();
		open(isNew);
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		if(goTime) {
			long l = System.currentTimeMillis();
			countdown = countdown - (l - currentTime);
			if(countdown<0) {
				countdown = 0;
				disable();
				hide();
				successView.disable();
				failureView.open();
				goTime = false;
				return;
			}
			currentTime = l;
		}
		//背景
		graphics.drawImage(bluebg.imgae, bluebgPoint);
		graphics.drawImage(redbg.imgae, redbgPoint);
		graphics.drawImage(holebg.imgae, holebgPoint);
		graphics.drawImage(bg.imgae, bgPoint);
		//阴影
		graphics.drawImage(yying.imgae, yyingPoint);
		for(int i = 0; i < coinShowList.size(); i++) {
			int index = coinShowList.get(i);
			Images images = coinList.get(index);
			Rect rect = coinRectList.get(index);
			graphics.drawImage(images, rect, isShowHelp);
		}
		graphics.drawImage(coina, coinaPoint, isShowHelp);
		graphics.drawImage(coinb, coinbPoint, isShowHelp);
		graphics.drawImage(coinc, coincPoint, isShowHelp);
		graphics.drawImage(coind, coindPoint, isShowHelp);
		graphics.drawImage(coine, coinePoint, isShowHelp);
		graphics.drawImage(clear, clearPoint, pressClear);
		graphics.drawImage(help, helpPoint, pressHelp);
		char[] c = null;
		//最好记录
		if(bestStr != null) {
			c = bestStr.toCharArray();
			for(int i = 0; i < c.length; i++) {
				switch(c[i]) {
				case ':':
					graphics.drawImage(colon.imgae, i * whitenum.getWidth() + bestPoint.x, bestPoint.y);
					break;
				default:
					graphics.drawImage(whitenum, i * whitenum.getWidth() + bestPoint.x, bestPoint.y, Character.getNumericValue(c[i])+2);
					break;
				}
			}
		}
		//硬币总值
		c = decimalFormat.format(currentAmount).toCharArray();
		for(int i = 0; i < c.length; i++) {
			graphics.drawImage(yellownum, i * yellownum.getWidth() + amountPoint.x, amountPoint.y, getIndex(c[i]));
		}
		//硬币数
		c = String.valueOf(coinList.size()).toCharArray();
		for(int i = 0; i < c.length; i++) {
			graphics.drawImage(whitenum, i * whitenum.getWidth() + coinPoint.x, coinPoint.y, getIndex(c[i]));
		}
		//级数
		c = String.valueOf(currentLevel).toCharArray();
		for(int i = 0; i < c.length; i++) {
			graphics.drawImage(whitenum, i * whitenum.getWidth() + levelPoint.x, levelPoint.y, getIndex(c[i]));
		}
		//过关条件
		c = decimalFormat.format(targetAmount).toCharArray();
		for(int i = 0; i < c.length; i++) {
			graphics.drawImage(amountnum, i * amountnum.getWidth() + targetAmountPoint.x, targetAmountPoint.y, getIndex(c[i]));
		}
		c = String.valueOf(targetCount).toCharArray();
		if(c.length==1) c = new char[]{'0', c[1]};
		for(int i = 0; i < c.length; i++) {
			graphics.drawImage(coinnum, i * coinnum.getWidth() + targetCountPoint.x, targetCountPoint.y, Character.getNumericValue(c[i]));
		}
		//将要添加的硬币
		if(dragAddCoin > -1) {
			graphics.drawImage(coins[dragAddCoin], dragAddCoinPoint, isShowHelp);
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
			if(dragAddCoin > -1) {
				Images images = coinxs[dragAddCoin];
				dragAddCoinPoint.set(x - images.getWidth() / 2, y - images.getHeight() / 2);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if(dragAddCoin > -1) {
				Images images = coinxs[dragAddCoin];
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
				Images images = coinxs[dragAddCoin];
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
					if(i > dragMoveCoin) iter.set(i - 1);
				}
				currentAmount -= coinAmountList.remove(dragMoveCoin);
				currentAmount = new BigDecimal(currentAmount, new MathContext(2)).floatValue();
				juge();
			} else if(dragMoveCoin > -1) {
				if(isOpenSound) SoundManager.getInstance().play(R.raw.clickcoin);
			}
			reset();
			break;
		}
		return false;
	}
	
	public void open() {
		open(false);
	}
	
	public void open(boolean isNew) {
		clearAll();
		show();
		enable();
		if(isNew) {
			currentLevel = 1;
			preferences.putInt(EngineConstants.LEVEL, currentLevel);
		}
		else currentLevel = preferences.getInt(EngineConstants.LEVEL, EngineConstants.DEFAULT_LEVEL);
		bestTime = preferences.getLong(EngineConstants.BEST_TIME + currentLevel, EngineConstants.DEFAULT_BEST_TIME);
		if(bestTime > EngineConstants.DEFAULT_BEST_TIME) {
			bestStr = dateFormat.format(new Date(bestTime));
		}
		currentTime = System.currentTimeMillis();
		goTime = true;
		rules();
	}
	
	protected void rules() {
		targetCount = currentLevel;
		if(currentStage > 8) {
			targetCount += 2;
		} else if(currentStage > 4) {
			targetCount += 1;
		} else if(currentStage == 1) {
			countdown = getCountdown();
		}
		targetAmount = 0;
		for(int i = 0; i < targetCount; i++) {
			targetAmount += coinsAmount[random.nextInt(coinsAmount.length)];
		}
		targetAmount = new BigDecimal(targetAmount, new MathContext(2)).floatValue();
	}
	
	private void juge() {
		if(isOpenSound) SoundManager.getInstance().play(R.raw.clickcoin);
		if(coinList.size() == targetCount && currentAmount == targetAmount) {
			clearAll();
			if(currentStage == 9) {
				currentStage = 1;
				if(countdown>bestTime) preferences.putLong(EngineConstants.BEST_TIME + currentLevel, countdown);
				disable();
				failureView.disable();
				successView.open();
				goTime = false;
				float time = (getCountdown()-countdown)/1000f-currentLevel*10;
				if(time<=10) {
					successView.setCup(agcup, R.raw.gold);
				} else if((currentLevel==1 && time<=20) || (currentLevel!=1 && time <= 30)) {
					successView.setCup(bgcup, R.raw.silver);
				} else {
					successView.setCup(cgcup, R.raw.bronze);
				}
			} else {
				if(isOpenSound) SoundManager.getInstance().play(R.raw.finishtopic);
				currentStage++;
				rules();
			}
		}
	}
	
	private int getIndex(char c) {
		switch(c) {
		case '.':
			return 0;
		case '/':
			return 1;
		default:
			return Character.getNumericValue(c) + 2;
		}
	}
	
	private long getCountdown() {
		int i = currentLevel;
		if(i > 11) i = 11;
		return (50 + (i - 1) * 20) * 1000;
	}
	
	private void reset() {
		pressClear = false;
		pressHelp = false;
		dragAddCoin = dragMoveCoin = -1;
	}
	
	protected void clearAll() {
		currentAmount = 0;
		coinList.clear();
		coinRectList.clear();
		coinShowList.clear();
		coinAmountList.clear();
		dragAddCoin = dragMoveCoin = -1;
	}
}
