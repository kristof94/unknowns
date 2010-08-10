package hld.coins.status;

import hld.coins.interfaces.AbstractGameStatus;
import hld.coins.manager.GameViewManager;
import hld.coins.view.MenuView;
import android.app.Activity;

public class MenuStatus extends AbstractGameStatus {
	@Override
	public void EntryCurrentStatus(Activity activity) {
		new MenuView();
	}
	
	@Override
	public void RemoveCurrentStatus(Activity activity) {
		GameViewManager.getInstance().removeAllView();
	}
}
