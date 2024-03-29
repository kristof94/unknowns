package hld.coins.util;

import hld.coins.constants.EngineConstants;

import android.util.Log;

public class LogUnit {
	private final static String INFO = "GameInfo";

	private static boolean isShow() {
		return EngineConstants.isShowDebugText;
	}

	public static void i(String text) {
		if (isShow())
			Log.i(INFO, text);
	}

	public static void e(String text) {
		if (isShow())
			Log.e(INFO, text);
	}
	
	public static void e(String text, Throwable tr) {
		if (isShow())
			Log.e(INFO, text, tr);
	}

	public static void w(String text) {
		if (isShow())
			Log.w(INFO, text);
	}

	public static void w(String text, Throwable tr) {
		if (isShow())
			Log.w(INFO, text, tr);
	}
}
