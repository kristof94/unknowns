package hld.coins.view;

import hld.coins.R;
import hld.coins.constants.GameStatusConstants.Status;
import hld.coins.interfaces.AbstractView;
import hld.coins.manager.BitmapManager;
import hld.coins.manager.GameStatusManger;
import hld.coins.task.AsyTask;
import hld.coins.task.AsyTimerManager;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;
import android.graphics.Point;

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
		loadingbar0 = bitmapManager.getViewScaledImage(getClass(), R.drawable.loadingbar0000,
				scale, false);
		loadingbar1 = bitmapManager.getViewScaledImage(getClass(), R.drawable.loadingbar0001,
				scale, false);
		loadingpointer = bitmapManager.getViewScaledImage(getClass(), R.drawable.loadingpointer,
				scale, false);
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
		int w = (int)Math.min(loadingbar1.width, loadingbar1.width * task.now / (float)task.max);
		graphics.drawImage(loadingbarPoint.x, loadingbarPoint.y, loadingbar1.imgae, 0, 0,
				Math.min(w + loadingpointer.width / 2, loadingbar1.width), loadingbar1.height);
		graphics.drawImage(loadingpointer.imgae, loadingpointerPoint.x + Math.min(w, p),
				loadingpointerPoint.y);
	}
	private class LoadingTask extends AsyTask {
		private int max;
		private int now;
		
		public LoadingTask() {
			max = 100;
		}
		
		@Override
		public void run() {
			super.run();
			while(++now < max) {
				try {
					Thread.sleep(100);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			cancel();
			GameStatusManger.getInstance().setStatusCurrent(Status.GAME_MENU);
		}
	}
}
