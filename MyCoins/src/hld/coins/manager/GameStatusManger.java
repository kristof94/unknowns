package hld.coins.manager;

import android.app.Activity;
import android.content.Context;

import hld.coins.constants.GameStatusConstants.Status;
import hld.coins.interfaces.GameStatusInterface;
import hld.coins.util.LogUnit;

public class GameStatusManger {

	private static GameStatusManger instance = new GameStatusManger();

	private GameStatusInterface statusCurrent;

	private GameStatusInterface statusPrevious;

	private Status statusPreviousKey;

	private Status statusCurrentKey;

	private boolean isChange;

	private Activity activity;

	private GameStatusManger() {
	}

	public static GameStatusManger getInstance() {
		return instance;
	}

	public Context getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void update() {
		if (statusPrevious == null || statusCurrent == null) {
			LogUnit.e("status can not null!!!");
			return;
		}
		if (isChange) {
			if (statusPrevious == statusCurrent) {
				statusCurrent.EntryCurrentStatus(activity);
			} else if (statusPrevious != statusCurrent) {
				this.statusPrevious.RemoveCurrentStatus(activity);
				this.statusCurrent.EntryCurrentStatus(activity);
			}
			isChange = false;
		}
	}

	public GameStatusInterface getStatusCurrent() {
		return statusCurrent;
	}

	public void setStatusCurrent(Status key, GameStatusInterface statusCurrent) {
		if (statusPrevious == null) {
			statusPrevious = statusCurrent;
			statusPreviousKey = key;
		} else {
			statusPrevious = this.statusCurrent;
			statusPreviousKey = statusCurrentKey;
		}
		this.statusCurrent = statusCurrent;
		statusCurrentKey = key;
		isChange = true;
	}
}
