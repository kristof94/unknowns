package hld.coins.interfaces;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import android.content.Intent;
import android.view.MotionEvent;

/**
 * 两条线程之间的同步请求队列
 * 
 * @author Mark
 * 
 */
public class Channel {
	private ArrayList<MotionEvent> motionList;
	private ArrayList<Intent> intentList;
	private ReentrantLock lock;

	public Channel() {
		motionList = new ArrayList<MotionEvent>();
		intentList = new ArrayList<Intent>();
		lock = new ReentrantLock();
	}

	public void addIntentEvent(Intent event) {
		try {
			lock.lock();
			intentList.add(event);
		} finally {
			lock.unlock();
		}
	}

	public Intent[] getIntentEvent() {
		try {
			lock.lock();
			Intent[] array = new Intent[intentList.size()];
			intentList.toArray(array);
			intentList.clear();
			return array;
		} finally {
			lock.unlock();
		}
	}

	public void addMotionEvent(MotionEvent event) {
		try {
			lock.lock();
			motionList.add(event);
		} finally {
			lock.unlock();
		}
	}

	public MotionEvent[] getMotionEvent() {
		try {
			lock.lock();
			MotionEvent[] array = new MotionEvent[motionList.size()];
			motionList.toArray(array);
			motionList.clear();
			return array;
		} finally {
			lock.unlock();
		}
	}
}
