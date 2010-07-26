package hld.coins.view;

import android.graphics.Point;
import hld.coins.R;
import hld.coins.constants.EngineConstants;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
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
	private boolean isShowHelp;
	private boolean pressClear;
	private boolean pressHelp;
	
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
	    isShowHelp=preferences.getBoolean(EngineConstants.IS_SHOW_HELP, EngineConstants.DEFAULT_SHOW_HELP);
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		graphics.drawImage(bg.imgae, bgPoint);
		coina.draw(graphics, coinaPoint, isShowHelp);
		coinb.draw(graphics, coinbPoint, isShowHelp);
		coinc.draw(graphics, coincPoint, isShowHelp);
		coind.draw(graphics, coindPoint, isShowHelp);
		coine.draw(graphics, coinePoint, isShowHelp);
		clear.draw(graphics, clearPoint, pressClear);
		help.draw(graphics, helpPoint, pressHelp);
		graphics.drawImage(best.imgae, bestPoint);
		graphics.drawImage(level.imgae, levelPoint);
		graphics.drawImage(amount.imgae, amountPoint);
		graphics.drawImage(coin.imgae, coinPoint);
	}
}
