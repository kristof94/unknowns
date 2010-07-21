package hld.coins.animation;

import hld.coins.interfaces.sprite.Sprite;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;

//动画角色类
public class Animator extends Sprite {
	private Calculagraph cal = null;

	public Animator(Image img, int frameWidth, int frameHeight, int loopTime,
			boolean isJoinViewManager) {
		super(img, frameWidth, frameHeight, isJoinViewManager);
		cal = new Calculagraph(loopTime);
	}

	public Animator(Image[] imgs, int loopTime, boolean isJoinViewManager) {
		super(imgs, isJoinViewManager);
		cal = new Calculagraph(loopTime);
	}

	/**
	 * 播放动画
	 * 
	 */
	public void PlayAnimation(Graphics g) {
		if (cal.getLoopTime() > 0) {
			// 如果超时，则重新计时并播放下一Frame
			if (cal.isTimeout()) {
				cal.reset();
				this.nextFrame(current_mode);
				// paint(g,current_mode);
				onDraw(g);

			}
			// 否则继续计时
			else {
				cal.calculate();
				// paint(g,current_mode);
				onDraw(g);
			}
		}
	}

	/**
	 * 停止播放动画
	 * 
	 */
	public void StopAnimation() {
		cal.reset();
	}
}
