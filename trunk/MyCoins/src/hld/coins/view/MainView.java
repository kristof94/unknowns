package hld.coins.view;

import android.graphics.Point;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.wrapper.Image;

public class MainView extends AbstractView {
	private Image bg;
	private Image coina0;
	private Image coina1;
	private Image coina2;
	private Image coinb0;
	private Image coinb1;
	private Image coinb2;
	private Image coinc0;
	private Image coinc1;
	private Image coinc2;
	private Image coind0;
	private Image coind1;
	private Image coind2;
	private Image coine0;
	private Image coine1;
	private Image coine2;
	private Image clear0;
	private Image clear1;
	private Image help0;
	private Image help1;
	private Image best;
	private Image bestnum;
	private Image timenum;
	private Image timecolon;
	private Image stagenum;
	private Image stagedash;
	private Image level;
	private Image levelnum;
	private Image amount;
	private Image amountnum;
	private Image coin;
	private Image coinnum;
	
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
	}
}
