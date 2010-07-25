package hld.coins.status;

import hld.coins.interfaces.AbstractGameStatus;
import hld.coins.manager.GameViewManager;
import hld.coins.view.LoadingView;
import android.app.Activity;

public class LoadingStatus extends AbstractGameStatus {
	private LoadingView view;
	
	@Override
	public void EntryCurrentStatus(Activity activity) {
		view = new LoadingView();
	}
	
	@Override
	public void RemoveCurrentStatus(Activity activity) {
		GameViewManager.getInstance().removeView(view);
	}
}
