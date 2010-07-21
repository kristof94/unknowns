package hld.coins.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class MusicManager {
	private static MusicManager instance;
	private Activity activity;
	private HashMap<Integer, Music> resMap;
	private ArrayList<Music> pauseList;

	private MusicManager() {
		resMap = new HashMap<Integer, Music>();
		pauseList = new ArrayList<Music>();
	}

	public static MusicManager getInstance() {
		if (instance == null) {
			instance = new MusicManager();
		}
		return instance;
	}

	public MediaPlayer setMusic(final String path, String name, int soundID,
			boolean isLoop) {
		return setMusic(path, name, soundID, isLoop, false);
	}

	public MediaPlayer setMusic(final String path, String name, int soundID,
			boolean isLoop, boolean isAsnyc) {
		if (resMap.containsKey(soundID)) {
			return resMap.get(soundID).playerMusic;
		}
		AssetFileDescriptor afd = null;
		try {
			afd = activity.getAssets().openFd(path + File.separator + name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (afd == null)
			return null;
		MediaPlayer mp = new MediaPlayer();
		mp.reset();
		try {
			mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
					afd.getLength());
			afd.close();
			if (isAsnyc)
				mp.prepareAsync();
			else
				mp.prepare();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Music music = new Music();
		music.id = soundID;
		music.isLoop = isLoop;
		music.playerMusic = mp;
		resMap.put(soundID, music);
		mp.setLooping(isLoop);
		return mp;
	}

	public MediaPlayer setMusic(int id, boolean isLoop) {
		return setMusic(id, isLoop, false);
	};

	public MediaPlayer setMusic(int id, boolean isLoop, boolean isAsnyc) {
		if (resMap.containsKey(id)) {
			return resMap.get(id).playerMusic;
		}
		AssetFileDescriptor afd = activity.getResources().openRawResourceFd(id);
		if (afd == null)
			return null;
		MediaPlayer mp = new MediaPlayer();
		mp.reset();
		try {
			mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
					afd.getLength());
			afd.close();
			if (isAsnyc)
				mp.prepareAsync();
			else
				mp.prepare();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Music music = new Music();
		music.id = id;
		music.isLoop = isLoop;
		music.playerMusic = mp;
		resMap.put(id, music);
		mp.setLooping(isLoop);
		return mp;
	}

	public void setVolume(int id, int vol) {
		Music music = resMap.get(id);
		if (music != null && music.playerMusic != null) {
			music.playerMusic.setVolume((float) Math.log10(vol),
					(float) Math.log10(vol));
		}
	}

	public void freeMusic(int id) {
		Music music = resMap.remove(id);
		if (music != null && music.playerMusic != null) {
			music.playerMusic.stop();
			music.playerMusic.release();
		}
	}

	public void stopMusic(int id) {
		Music music = resMap.get(id);
		if (music != null && music.playerMusic != null) {
			music.playerMusic.stop();
		}
	}

	public void pauseMusic(int id) {
		Music music = resMap.get(id);
		if (music != null && music.playerMusic != null) {
			music.playerMusic.pause();
		}
	}

	public void playMusic(int i) {
		Music music = resMap.get(i);
		if (music != null && music.playerMusic != null) {
			music.playerMusic.start();
		}
	}

	// ///////////////////////////////////////////////////////////////////
	public ArrayList<Music> synPauseAllMusic() {
		Iterator<Entry<Integer, Music>> iterator = resMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Music> entry = iterator.next();
			Music music = entry.getValue();
			if (music.playerMusic.isPlaying()) {
				pauseList.add(music);
			}
		}
		return pauseList;
	}
	
	public ArrayList<Music> synResumeAllMusic() {
		@SuppressWarnings("unchecked")
		ArrayList<Music> clone = (ArrayList<Music>) pauseList.clone();
		pauseList.clear();
		return clone;
	}
	
	public Iterator<Entry<Integer, Music>> synFreeAllMusic() {
		return resMap.entrySet().iterator();
//		Iterator<Entry<Integer, Music>> iterator = resMap.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Entry<Integer, Music> entry = iterator.next();
//			Music music = entry.getValue();
//			freeMusic(music.id);
//		}
	}
	
	public void pauseAllMusic() {
		Iterator<Entry<Integer, Music>> iterator = resMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Music> entry = iterator.next();
			Music music = entry.getValue();
			if (music.playerMusic.isPlaying()) {
				pauseMusic(music.id);
				pauseList.add(music);
			}
		}
	}

	public void freeAllMusic() {
		Iterator<Entry<Integer, Music>> iterator = resMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Music> entry = iterator.next();
			Music music = entry.getValue();
			freeMusic(music.id);
		}
	}

	public void resumeAllMusic() {
		if (pauseList.size() == 0)
			return;
		for (Music music : pauseList) {
			playMusic(music.id);
		}
		pauseList.clear();
	}

	public void setOnCompletionListener(int id, OnCompletionListener l) {
		Music music = resMap.get(id);
		if (music != null && music.playerMusic != null) {
			music.playerMusic.setOnCompletionListener(l);
		}
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	// /////////////////////////////////////////////////////////
	public class Music {
		int id;
		boolean isLoop;
		MediaPlayer playerMusic;
		public int getId() {
			return id;
		}
	}
}
