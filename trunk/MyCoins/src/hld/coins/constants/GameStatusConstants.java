package hld.coins.constants;

import java.util.EnumMap;
import hld.coins.interfaces.GameStatusInterface;
import hld.coins.status.ContinueGameStatus;
import hld.coins.status.HelpTipStatus;
import hld.coins.status.LoadingStatus;
import hld.coins.status.NewGameStatus;
import hld.coins.status.MenuStatus;

public class GameStatusConstants {
	public static enum Status {
		LOADING, MENU, NEW, CONTINUE, HELP
	}
	
	private static EnumMap<Status, GameStatusInterface> map = new EnumMap<Status, GameStatusInterface>(Status.class);
	static {
		map.put(Status.LOADING, new LoadingStatus());
		map.put(Status.MENU, new MenuStatus());
		map.put(Status.NEW, new NewGameStatus());
		map.put(Status.CONTINUE, new ContinueGameStatus());
		map.put(Status.HELP, new HelpTipStatus());
	}
	
	public static GameStatusInterface getStatus(Status status) {
		return map.get(status);
//		switch(status) {
//		case GAME_LOADING:
//			return new LoadingStatus();
//		default:
//			return null;
//		}
	}
}
