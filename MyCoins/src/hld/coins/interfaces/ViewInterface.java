package hld.coins.interfaces;

import hld.coins.wrapper.Graphics;

import android.content.Intent;
import android.view.MotionEvent;

public interface ViewInterface {

	public void onDraw(Graphics graphics);

	/**
	 * 
	 * @param event
	 * @return <p>
	 *         true : 继续传传递到下一个View
	 *         </p>
	 *         <p>
	 *         false : 不传递
	 *         </p>
	 */
	public boolean onTouchListener(MotionEvent event);

	public void onIntentListener(Intent intent);


}
