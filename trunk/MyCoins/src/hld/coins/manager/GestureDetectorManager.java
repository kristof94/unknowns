package hld.coins.manager;

import java.util.ArrayList;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;

/**
 * 手势识别管理器
 * @author Mark
 */
public class GestureDetectorManager {

	private static GestureDetectorManager instance;

	private ArrayList<SimpleOnGestureListener> listener;

	private GestureDetectorManager() {
		listener = new ArrayList<SimpleOnGestureListener>();
	}

	public static GestureDetectorManager getInstance() {
		if (instance == null) {
			instance = new GestureDetectorManager();
		}
		return instance;
	}

	public boolean addListener(SimpleOnGestureListener l) {
		return listener.add(l);
	}

	public boolean remove(SimpleOnGestureListener l) {
		return listener.remove(l);
	}

	public boolean onTouchEvent(MotionEvent e) {
		return detector.onTouchEvent(e);
	}

	private GestureDetector detector = new GestureDetector(
			new SimpleOnGestureListener() {
				@Override
				public boolean onDown(MotionEvent e) {
					int size = listener.size();
					for (int i = 0; i < size; i++) {
						if (listener.get(i).onDown(e)) {
							return true;
						}
					}
					return false;
				}

				@Override
				public void onLongPress(MotionEvent e) {
					int size = listener.size();
					for (int i = 0; i < size; i++)
						listener.get(i).onLongPress(e);
				}

				@Override
				public boolean onFling(MotionEvent e1, MotionEvent e2,
						float velocityX, float velocityY) {
					int size = listener.size();
					for (int i = 0; i < size; i++) {
						if (listener.get(i).onFling(e1, e2, velocityX,
								velocityY)) {
							return true;
						}
					}
					return false;
				}

				@Override
				public boolean onScroll(MotionEvent e1, MotionEvent e2,
						float distanceX, float distanceY) {
					int size = listener.size();
					for (int i = 0; i < size; i++) {
						if (listener.get(i).onScroll(e1, e2, distanceX,
								distanceY)) {
							return true;
						}
					}
					return false;
				}

				@Override
				public void onShowPress(MotionEvent e) {
					int size = listener.size();
					for (int i = 0; i < size; i++)
						listener.get(i).onShowPress(e);
				}

				@Override
				public boolean onSingleTapUp(MotionEvent e) {
					int size = listener.size();
					for (int i = 0; i < size; i++) {
						if (listener.get(i).onSingleTapUp(e)) {
							return true;
						}
					}
					return false;
				}
			});
}
