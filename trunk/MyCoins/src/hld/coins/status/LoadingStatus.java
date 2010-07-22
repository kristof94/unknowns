package hld.coins.status;

import hld.coins.interfaces.AbstractGameStatus;
import hld.coins.module.view.LoadingView;
import android.app.Activity;

public class LoadingStatus extends AbstractGameStatus {
	@Override
	public void EntryCurrentStatus(Activity activity) {
		new LoadingView(activity);
	}
	
	@Override
	public void RemoveCurrentStatus(Activity activity) {}
}
