package org.hld.mht;

import java.io.File;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;

public class PreferencesManage {
	private static Editor editor;
	private static String currentPath;
	private static String rootPath;
	private static final String CURRENT_PATH_KEY = "CURRENT_PATH_KEY";
	private static final String ROOT_PATH_KEY = "ROOT_PATH_KEY";
	private static final String SDCARD_PATH_DEFAULT = Environment.getExternalStorageDirectory().getPath();
	private static final String ROOT_PATH_DEFAULT = Environment.getRootDirectory().getPath();
	
	public static void initPreferences(SharedPreferences preferences) {
		PreferencesManage.editor = preferences.edit();
		PreferencesManage.currentPath = preferences.getString(CURRENT_PATH_KEY, SDCARD_PATH_DEFAULT);
		PreferencesManage.rootPath = preferences.getString(ROOT_PATH_KEY, ROOT_PATH_DEFAULT);
	}
	
	public static String getCurrentPath() {
		return PreferencesManage.currentPath;
	}
	
	public static void setCurrentPath(String currentPath) {
		PreferencesManage.currentPath = currentPath;
		editor.putString(CURRENT_PATH_KEY, currentPath).commit();
	}

	public static void setRootPath(String rootPath) {
		PreferencesManage.rootPath = rootPath;
		editor.putString(ROOT_PATH_KEY, rootPath).commit();
	}
	
	public static String getRootPath() {
		return PreferencesManage.rootPath;
	}

	public static String getSdcardPath() {
		return SDCARD_PATH_DEFAULT;
	}
	
	public static String getSdcardCachePath() {
		return getSdcardPath()+File.separator+".mhtCache";
	}
	
	public static String getLocalCachePath(Context context) {
		return context.getCacheDir().getAbsolutePath()+File.separator+".mhtCache";
	}
	
}
