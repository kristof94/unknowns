package hld.coins.task;

import java.util.TimerTask;

public class AsyTask extends TimerTask {

	public boolean isPause;

	/**
	 * 重写此方法，一定先要调用父类的此方法
	 */
	@Override
	public void run() {
		if (isPause) {
			return;
		}
	}
}
