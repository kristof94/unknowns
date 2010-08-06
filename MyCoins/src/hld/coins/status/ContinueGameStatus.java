package hld.coins.status;

import hld.coins.interfaces.AbstractGameStatus;
import hld.coins.manager.GameViewManager;
import hld.coins.view.MainView;
import android.app.Activity;

public class ContinueGameStatus extends AbstractGameStatus {
	@Override
	public void EntryCurrentStatus(Activity activity) {
		new MainView(false);
	}
	
	@Override
	public void RemoveCurrentStatus(Activity activity) {
		GameViewManager.getInstance().removeAllView();
	}
}
