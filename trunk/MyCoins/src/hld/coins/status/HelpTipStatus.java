package hld.coins.status;

import android.app.Activity;
import hld.coins.interfaces.AbstractGameStatus;
import hld.coins.manager.GameViewManager;
import hld.coins.view.HelpView;

public class HelpTipStatus extends AbstractGameStatus {
	@Override
	public void EntryCurrentStatus(Activity activity) {
		new HelpView();
	}
	
	@Override
	public void RemoveCurrentStatus(Activity activity) {
		GameViewManager.getInstance().removeAllView();
	}
}
