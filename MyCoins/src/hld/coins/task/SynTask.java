package hld.coins.task;

public abstract class SynTask {

	/* If timer was cancelled */
	boolean cancelled;

	/* Slots used by Timer */
	long when;

	long period;

	boolean fixedRate;


	/*
	 * The time when task will be executed, or the time when task was launched
	 * if this is task in progress.
	 */
	private long scheduledTime;

	/*
	 * Method called from the Timer for synchronized getting of when field.
	 */
	long getWhen() {
		return when;
	}

	/*
	 * Method called from the Timer object when scheduling an event @param time
	 */
	void setScheduledTime(long time) {
		scheduledTime = time;
	}

	/*
	 * Is TimerTask scheduled into any timer?
	 * 
	 * @return {@code true} if the timer task is scheduled, {@code false}
	 * otherwise.
	 */
	boolean isScheduled() {
		return when > 0 || scheduledTime > 0;
	}

	protected SynTask() {
		super();
	}

	public void cancel() {
		cancelled = true;
	}

	public long scheduledExecutionTime() {
		return scheduledTime;
	}

	public abstract void run();

}
