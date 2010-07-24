package hld.coins.constants;

import java.util.EnumMap;
import hld.coins.interfaces.GameStatusInterface;
import hld.coins.status.LoadingStatus;
import hld.coins.status.MenuStatus;

public class GameStatusConstants {
	public static enum Status {
		GAME_LOADING, GAME_MENU, GAME_HELP, GAME_MAIN, GAME_RESULT
	}
	
	private static EnumMap<Status, GameStatusInterface> map = new EnumMap<Status, GameStatusInterface>(Status.class);
	static {
		map.put(Status.GAME_LOADING, new LoadingStatus());
		map.put(Status.GAME_MENU, new MenuStatus());
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
