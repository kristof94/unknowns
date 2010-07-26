package hld.coins.constants;

import android.graphics.Bitmap;

public class EngineConstants {

	// 游戏画布刷新周期
	public static final int STEP = 30;
	// 默认游戏的标准大小
	public static final float GAME_WIDTH = 480.0f;
	public static final float GAME_HEIGHT = 320.0f;
	// 是否显示Debug信息
	public static boolean isShowDebugText = true;

	public static final String GAME_DISPATCHER = "dispatcher";
	public static final int GAME_MUSIC = 100;

	// 退出游戏
	public static final String GAME_EXIT_OK = "不玩了";
	public static final String GAME_EXIT_CANCEL = "再玩一下吧";
	public static final String GAME_EXIT_TITLE = "离开";
	public static final String GAME_EXIT_MSG = "你真的不玩了么？";
	
	/**
	 * 是否开启声音
	 */
	public static final String IS_OPEN_SOUND = "openSound";
	/**
	 * 是否开启声音的默认选项
	 */
	public static final boolean DEFAULT_OPEN_SOUND = true;
	/**
	 * 是否显示帮助提示
	 */
	public static final String IS_SHOW_HELP = "showHelp";
	/**
	 * 是否显示帮助提示的默认选项
	 */
	public static final boolean DEFAULT_SHOW_HELP = true;
	
	/**
	 * Bitmap.Config.ARGB_8888
	 */
	public static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
	/**
	 * Bitmap.Config.RGB_565
	 */
	public static final Bitmap.Config FAST_BITMAP_CONFIG = Bitmap.Config.RGB_565;

}
