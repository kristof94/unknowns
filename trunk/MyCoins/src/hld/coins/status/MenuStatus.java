package hld.coins.status;

import android.app.Activity;
import hld.coins.interfaces.AbstractGameStatus;
import hld.coins.manager.GameViewManager;
import hld.coins.view.MenuView;

public class MenuStatus extends AbstractGameStatus {
	@Override
	public void EntryCurrentStatus(Activity activity) {
		GameViewManager.getInstance().removeAllView();
		new MenuView();
	}
	
	@Override
	public void RemoveCurrentStatus(Activity activity) {
	}
}
