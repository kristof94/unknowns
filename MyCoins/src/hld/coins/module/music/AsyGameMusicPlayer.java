package hld.coins.module.music;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import hld.coins.manager.MusicManager;
import hld.coins.manager.MusicManager.Music;

//暂时不使用
public class AsyGameMusicPlayer implements Runnable {

	public static final int MUSIC_MANAGER = 1;

	private static final int START = -100;
	private static final int PAUSE = -101;
	private static final int STOP = -102;

	private MusicManager music;
	private boolean running;
	private boolean pause;
	private static final ConcurrentLinkedQueue<MusicAction> queue = new ConcurrentLinkedQueue<MusicAction>();

	public AsyGameMusicPlayer() {
		music = MusicManager.getInstance();
		running = true;
		pause = false;
	}

	@Override
	public void run() {
		while (running) {
			while (running && pause) {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			while (!queue.isEmpty()) {
				MusicAction m = queue.poll();
				if (m.typeId == PAUSE) {
					pause = true;
				} else if (m.typeId == STOP) {
					running = false;
					break;
				} else if (m.typeId == START) {
					pause = false;
				} else {
					if (m.actionCode == START) {
						music.playMusic(m.musicId);
					} else if (m.actionCode == PAUSE) {
						music.pauseMusic(m.musicId);
					} else if (m.actionCode == STOP) {
						music.stopMusic(m.musicId);
					}
				}
			}
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void postMusic(int typeId, int musicId, int actionCode) {
		queue.add(new MusicAction(typeId, musicId, actionCode));
	}

	public void puaseAllMusic() {
		ArrayList<Music> list = music.synPauseAllMusic();
		for (Music m : list) {
			queue.add(new MusicAction(MUSIC_MANAGER, m.getId(), PAUSE));
		}
		queue.add(new MusicAction(MUSIC_MANAGER, PAUSE, PAUSE));
	}

	public void resumeAllMusic() {
		queue.add(new MusicAction(MUSIC_MANAGER, START, START));
		ArrayList<Music> list = music.synResumeAllMusic();
		for (Music m : list) {
			queue.add(new MusicAction(MUSIC_MANAGER, m.getId(), START));
		}
	}

	public void stopAllMusic() {
		Iterator<Entry<Integer, Music>> iterator = music.synFreeAllMusic();
		while (iterator.hasNext()) {
			Entry<Integer, Music> entry = iterator.next();
			Music m = entry.getValue();
			queue.add(new MusicAction(MUSIC_MANAGER, m.getId(), STOP));
		}
		queue.add(new MusicAction(MUSIC_MANAGER, STOP, STOP));
	}

	// ///////////////////////////////////////////////////
	static class MusicAction {
		int typeId;
		int musicId;
		int actionCode;

		public MusicAction(int typeId, int musicId, int actionCode) {
			this.typeId = typeId;
			this.musicId = musicId;
			this.actionCode = actionCode;
		}
	}

}
