package com.yingzheng.android.manager;

import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

public final class PreferencesManager {
	private SharedPreferences preferences;
	private Editor editor;
	private static PreferencesManager instance;
	
	public SharedPreferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Activity activity, int mode, String name) {
		if (name == null)
			preferences = activity.getPreferences(mode);
		else
			preferences = activity.getSharedPreferences(name, mode);
		editor = preferences.edit();
	}

	public Map<String, ?> getAll() {
		return preferences.getAll();
	}

	public boolean contains(String key) {
		return preferences.contains(key);
	}

	public boolean getBoolean(String key, boolean defValue) {
		return preferences.getBoolean(key, defValue);
	}

	public float getFloat(String key, float defValue) {
		return preferences.getFloat(key, defValue);
	}

	public int getInt(String key, int defValue) {
		return preferences.getInt(key, defValue);
	}

	public long getLong(String key, long defValue) {
		return preferences.getLong(key, defValue);
	}
	
	public String getString(String key, String defValue) {
		return preferences.getString(key, defValue);
	}

	public void registerOnSharedPreferenceChangeListener(
			OnSharedPreferenceChangeListener listener) {
		preferences.registerOnSharedPreferenceChangeListener(listener);
	}

	public void unregisterOnSharedPreferenceChangeListener(
			OnSharedPreferenceChangeListener listener) {
		preferences.unregisterOnSharedPreferenceChangeListener(listener);
	}

	public boolean putBoolean(String key, boolean b) {
		editor.putBoolean(key, b);
		return editor.commit();
	}

	public boolean putInt(String key, int i) {
		editor.putInt(key, i);
		return editor.commit();
	}

	public boolean putFloat(String key, float f) {
		editor.putFloat(key, f);
		return editor.commit();
	}

	public boolean putLong(String key, long l) {
		editor.putLong(key, l);
		return editor.commit();
	}

	public boolean putString(String key, String s) {
		editor.putString(key, s);
		return editor.commit();
	}

	public static PreferencesManager getInstance() {
		if(instance == null){
			instance = new PreferencesManager();
		}
		return instance;
	}
}
