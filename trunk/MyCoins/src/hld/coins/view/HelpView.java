package hld.coins.view;

import android.graphics.Point;
import android.graphics.Rect;
import hld.coins.R;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;
import hld.coins.wrapper.Images;

public class HelpView extends AbstractView {
	private Image tips;
	private Images play;
	private Point tipsPoint;
	private Point playPoint;
	private Rect playRect;
	private boolean pressPlay;
	
	public HelpView() {
		super(false);
		BitmapManager bitmapManager = BitmapManager.getInstance();
		tips = bitmapManager.getViewScaledImage(getClass(), R.drawable.tipsbg, scale, false);
		play = bitmapManager.getViewScaledImages(getClass(), scale, false, R.drawable.play0000, R.drawable.play0001);
		tipsPoint = new Point(offsetX(0), offsetY(0));
		playPoint = new Point(offsetX(336),offsetY(232));
		playRect = new Rect(playPoint.x, playPoint.y, play.getWidth()+playPoint.x, play.getHeight()+playPoint.y);
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		graphics.drawImage(tips.imgae, tipsPoint);
		graphics.drawImage(play, playPoint, pressPlay);
	}
}
