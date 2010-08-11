package hld.coins.manager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import hld.coins.util.LogUnit;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager {
	private SoundPool playerMusic;
	private Activity activity;
	private HashMap<Integer, Sound> resMap;
	private AudioManager mgr;
	private static SoundManager instance;
	private int type;

	private SoundManager() {
		resMap = new HashMap<Integer, Sound>();
	}

	public static SoundManager getInstance() {
		if (instance == null) {
			instance = new SoundManager();
		}
		return instance;
	}

	/**
	 * resIdº”‘ÿ
	 * 
	 * @param resId
	 * @param priority
	 */
	public void load(int resId, int priority) {
		if (activity != null && playerMusic != null) {
			if(resMap.containsKey(resId)){
				return;
			}
			int load = playerMusic.load(activity, resId, priority);
			Sound sound = new Sound();
			sound.soundID = load;
			sound.priority = priority;
			resMap.put(resId, sound);
		}
	}

	public void load(int resId) {
		load(resId, 1);
	}
	
	/**
	 * resIdº”‘ÿ
	 * 
	 * @param resId
	 * @param priority
	 */
	public void load(String path, String name, int soundID, int priority) {
		if (activity != null && playerMusic != null) {
			if(resMap.containsKey(soundID)){
				return;
			}
			AssetFileDescriptor openFd = null;
			try {
				openFd = activity.getAssets().openFd(
						path + File.separator + name);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (openFd == null)
				return;
			int load = playerMusic.load(openFd, priority);
			Sound sound = new Sound();
			sound.soundID = load;
			sound.priority = priority;
			resMap.put(soundID, sound);
		}
	}

	/**
	 * 
	 * @param resId
	 * @param loop
	 *            loop mode (0 = no loop, -1 = loop forever)
	 */
	public void setLoop(int resId, int loop) {
		Sound sound = resMap.get(resId);
		sound.loop = loop;
		playerMusic.setLoop(sound.soundID, loop);
	}

	public void play(int resId) {
		Sound sound = resMap.get(resId);
		if (sound == null)
			return;
		float streamVolumeCurrent = mgr
				.getStreamVolume(AudioManager.STREAM_RING);
		float streamMaxVolume = mgr
				.getStreamMaxVolume(AudioManager.STREAM_RING);
		float actVol = streamVolumeCurrent / streamMaxVolume;
		playerMusic.play(sound.soundID, actVol, actVol, sound.priority,
				sound.loop, sound.rate);
	}

	public void pause(int resId) {
		playerMusic.pause(resMap.get(resId).soundID);
	}

	public void stop(int resId) {
		playerMusic.stop(resMap.get(resId).soundID);
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
		mgr = (AudioManager) activity.getSystemService(Activity.AUDIO_SERVICE);
	}

	public void sePriority(int resId, int priority) {
		playerMusic.setPriority(resMap.get(resId).soundID, priority);
	}

	public void unload(int resId) {
		playerMusic.unload(resMap.get(resId).soundID);
		resMap.remove(resId);
	}

	public void release() {
		resMap.clear();
		playerMusic.release();
		playerMusic = null;
	}

	public void setPlayerMusic(int maxStreams, int streamType, int srcQuality) {
		this.type = streamType;
		if (playerMusic != null) {
			LogUnit
					.i("the old SoundPool was released! All the sounds also was released,too!");
			release();
		}
		playerMusic = new SoundPool(maxStreams, type, srcQuality);
	}

	// /////////////////////////////////////////////////////////////////////
	class Sound {
		int soundID;
		float rate = 1.0f;
		int priority = 0;
		int loop = 0;
	}
}
