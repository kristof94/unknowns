package hld.coins.view;

import android.graphics.Point;
import hld.coins.R;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
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
	private Point coinlistPoint;
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
	private boolean isShowHelp;
	
	public MainView() {
		super(true);
		BitmapManager bitmapManager = BitmapManager.getInstance();
		coina = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coina0000,
				R.drawable.coina0001, R.drawable.coina0002);
		coinb = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coinb0000,
				R.drawable.coinb0001, R.drawable.coinb0002);
		coinc = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coinc0000,
				R.drawable.coinc0001, R.drawable.coinc0002);
		coind = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coind0000,
				R.drawable.coind0001, R.drawable.coind0002);
		coine = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.coine0000,
				R.drawable.coine0001, R.drawable.coine0002);
		clear = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.clear00, R.drawable.clear01);
		help = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.help00, R.drawable.help01);
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
	}
}
