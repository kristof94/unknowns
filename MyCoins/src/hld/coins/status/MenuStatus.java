package hld.coins.status;

import android.app.Activity;
import hld.coins.interfaces.AbstractGameStatus;
import hld.coins.manager.GameViewManager;
import hld.coins.util.LogUnit;
import hld.coins.view.MenuView;

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
