package hld.coins.task;

import java.util.Date;
import java.util.Timer;



/**
 * Java.util.Timer的管理器
 * 
 * @author Administrator
 * 
 */
public class AsyTimerManager {

	private static Timer timer;

	/**
	 * task 是在另一条线程上运行的，注意线程安全
	 * 
	 * @param task
	 */
	public static void addTask(AsyTask task, Date when) {
		checkTimerNull();
		timer.schedule(task, when);
	}

	/**
	 * task 是在另一条线程上运行的，注意线程安全
	 * 
	 * @param task
	 */
	public static void addTask(AsyTask task, long delay) {
		checkTimerNull();
		timer.schedule(task, delay);
	}

	/**
	 * task 是在另一条线程上运行的，注意线程安全
	 * 
	 * @param task
	 * @param firstTime
	 * @param period
	 *            循环的时间间隔
	 */
	public static void addTask(AsyTask task, Date when, long period) {
		checkTimerNull();
		timer.schedule(task, when, period);
	}

	/**
	 * task 是在另一条线程上运行的，注意线程安全
	 * 
	 * @param task
	 * @param delay
	 *            延迟多少时间后运行
	 * @param period
	 *            循环的时间间隔
	 */
	public static void addTask(AsyTask task, long delay, long period) {
		checkTimerNull();
		timer.schedule(task, delay, period);
	}

	/**
	 * <p>
	 * task 是在另一条线程上运行的，注意线程安全。
	 * <p/>
	 * <p>
	 * 在固定速率执行中，根据已安排的初始执行时间来安排每次执行。如果由于任何原因而延迟了某次执行，则将快速连续地出现两次或更多的执行，从而使后续执行能够
	 * “追赶上来”。
	 * <p/>
	 * 
	 * @param task
	 * @param firstTime
	 * @param period
	 *            循环的时间间隔
	 */
	public static void addFixedRateTask(AsyTask task, Date firstTime, long period) {
		checkTimerNull();
		timer.scheduleAtFixedRate(task, firstTime, period);
	}

	/**
	 * <p>
	 * task 是在另一条线程上运行的，注意线程安全。
	 * <p/>
	 * <p>
	 * 在固定速率执行中，根据已安排的初始执行时间来安排每次执行。如果由于任何原因而延迟了某次执行，则将快速连续地出现两次或更多的执行，从而使后续执行能够
	 * “追赶上来”。
	 * <p/>
	 * 
	 * @param task
	 * @param delay
	 *            延迟多少时间后运行
	 * @param period
	 *            循环的时间间隔
	 */
	public static void addFixedRateTask(AsyTask task, long delay, long period) {
		checkTimerNull();
		timer.scheduleAtFixedRate(task, delay, period);
	}

	private static void checkTimerNull() {
		if (timer == null) {
			timer = new Timer("GameTimer", true);
		}
	}
	
	public static void cancel(){
		if (timer != null) {
			timer.cancel();
		}
	}
	
	public static void purge(){
		if (timer != null) {
			timer.purge();
		}
	}

}
