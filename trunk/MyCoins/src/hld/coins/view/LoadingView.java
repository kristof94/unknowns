package hld.coins.view;

import hld.coins.R;
import hld.coins.constants.GameStatusConstants.Status;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.manager.GameStatusManger;
import hld.coins.manager.SoundManager;
import hld.coins.task.AsyTask;
import hld.coins.task.AsyTimerManager;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;
import android.graphics.Point;
import android.media.AudioManager;

public class LoadingView extends AbstractView {
	private Image bg;
	private Image loadingbar0;
	private Image loadingbar1;
	private Image loadingpointer;
	private Point bgPoint;
	private Point loadingbarPoint;
	private Point loadingpointerPoint;
	private int p;
	private LoadingTask task;
	
	public LoadingView() {
		super(true);
		BitmapManager bitmapManager = BitmapManager.getInstance();
		bg = bitmapManager.getViewScaledImage(getClass(), R.drawable.loadingbg, scale, false);
		loadingbar0 = bitmapManager.getViewScaledImage(getClass(), R.drawable.loadingbar0000, scale, false);
		loadingbar1 = bitmapManager.getViewScaledImage(getClass(), R.drawable.loadingbar0001, scale, false);
		loadingpointer = bitmapManager.getViewScaledImage(getClass(), R.drawable.loadingpointer, scale, false);
		bgPoint = new Point(offsetX(0), offsetY(0));
		loadingbarPoint = new Point(offsetX(70), offsetY(270));
		loadingpointerPoint = new Point(offsetX(70), offsetY(275));
		p = loadingbar1.width - loadingpointer.width;
		task = new LoadingTask();
		AsyTimerManager.addTask(task, 1000);
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		graphics.drawImage(bg.imgae, bgPoint.x, bgPoint.y);
		graphics.drawImage(loadingbar0.imgae, loadingbarPoint.x, loadingbarPoint.y);
		int w = (int)Math.min(loadingbar1.width, loadingbar1.width * task.now / task.max);
		graphics.drawImage(loadingbarPoint.x, loadingbarPoint.y, loadingbar1.imgae, 0, 0, Math.min(w + loadingpointer.width / 2, loadingbar1.width),
				loadingbar1.height);
		graphics.drawImage(loadingpointer.imgae, loadingpointerPoint.x + Math.min(w, p), loadingpointerPoint.y);
	}
	private class LoadingTask extends AsyTask {
		private float max;
		private float now;
		private int[] drawableId;
		private int[] rawId;
		
		public LoadingTask() {
			drawableId = new int[]{R.drawable.coina0000, R.drawable.coina0001, R.drawable.coina0002, R.drawable.coina0003, R.drawable.coinb0000,
					R.drawable.coinb0001, R.drawable.coinb0002, R.drawable.coinb0003, R.drawable.coinc0000, R.drawable.coinc0001,
					R.drawable.coinc0002, R.drawable.coinc0003, R.drawable.coind0000, R.drawable.coind0001, R.drawable.coind0002,
					R.drawable.coind0003, R.drawable.coine0000, R.drawable.coine0001, R.drawable.coine0002, R.drawable.coine0003};
			max += drawableId.length;
			rawId = new int[]{R.raw.bronze, R.raw.clickcoin, R.raw.clickmunu, R.raw.coinfly, R.raw.finishtopic, R.raw.gold, R.raw.hit, R.raw.silver,
					R.raw.timeup};
			max += rawId.length;
		}
		
		@Override
		public void run() {
			super.run();
			for(int i = 0; i < drawableId.length; i++, now++) {
				BitmapManager.getInstance().getGlobalScaledImage(drawableId[i], scale, false);
			}
			SoundManager.getInstance().setPlayerMusic(20, AudioManager.STREAM_RING, 0);
			for(int i = 0; i < rawId.length; i++, now++) {
				SoundManager.getInstance().load(rawId[i]);
			}
			cancel();
			AsyTimerManager.purge();
			GameStatusManger.getInstance().setStatusCurrent(Status.MENU);
		}
	}
}
