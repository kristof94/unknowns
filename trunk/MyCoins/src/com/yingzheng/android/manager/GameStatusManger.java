package com.yingzheng.android.manager;

import android.app.Activity;
import android.content.Context;

import com.yingzheng.android.interfaces.GameStatusInterface;
import com.yingzheng.android.util.LogUnit;

public class GameStatusManger {

	private static GameStatusManger instance;

	private GameStatusInterface statusCurrent;

	private GameStatusInterface statusPrevious;

	@SuppressWarnings("unused")
	private byte statusPreviousKey;

	private byte statusCurrentKey;

	private boolean isChange;

	private Activity activity;

	private GameStatusManger() {
	}

	public static GameStatusManger getInstance() {
		if (instance == null) {
			instance = new GameStatusManger();
		}
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

	public void setStatusCurrent(byte key, GameStatusInterface statusCurrent) {
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
