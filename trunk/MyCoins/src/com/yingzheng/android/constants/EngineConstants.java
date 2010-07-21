package com.yingzheng.android.constants;

import android.graphics.Bitmap;

public class EngineConstants {

	// 游戏画布刷新周期
	public static final int STEP = 30;
	// 默认游戏的标准大小
	public static final float GAME_WIDTH = 320.0f;
	public static final float GAME_HEIGHT = 480.0f;
	// 是否显示Debug信息
	public static boolean isShowDebugText = true;

	public static final String GAME_DISPATCHER = "dispatcher";
	public static final int GAME_MUSIC = 100;

	// 退出游戏
	public static final String GAME_EXIT_OK = "ok";
	public static final String GAME_EXIT_CANCEL = "cancel";
	public static final String GAME_EXIT_TITLE = "退出";
	public static final String GAME_EXIT_MSG = "确认退出游戏？";
	
	/**
	 * Bitmap.Config.ARGB_8888
	 */
	public static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
	/**
	 * Bitmap.Config.RGB_565
	 */
	public static final Bitmap.Config FAST_BITMAP_CONFIG = Bitmap.Config.RGB_565;

}
