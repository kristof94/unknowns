package com.yingzheng.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;

import com.yingzheng.android.constants.EngineConstants;
import com.yingzheng.android.interfaces.Channel;
import com.yingzheng.android.manager.BitmapManager;
import com.yingzheng.android.manager.GameStatusManger;
import com.yingzheng.android.manager.GameViewManager;
import com.yingzheng.android.manager.MusicManager;
import com.yingzheng.android.manager.PreferencesManager;
import com.yingzheng.android.manager.SoundManager;

public class GameActivity extends Activity {

	private GameCanvas canvas;
	private Channel channel;
	private WakeLock wakeLock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		canvas = new GameCanvas(this, null);
		setContentView(canvas);
		channel = new Channel();

		// 设置游戏初始化状态
		initGame();

		canvas.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				channel.addMotionEvent(MotionEvent.obtain(event));
				return true;
			}
		});
		canvas.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					Builder builder = new AlertDialog.Builder(GameActivity.this);
					builder.setTitle(EngineConstants.GAME_EXIT_TITLE);
					builder.setMessage(EngineConstants.GAME_EXIT_MSG);
					builder.setPositiveButton(EngineConstants.GAME_EXIT_OK,
							new AlertDialog.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									GameCanvas.setExitGame(true);
								}
							});
					builder.setNegativeButton(EngineConstants.GAME_EXIT_CANCEL,
							new AlertDialog.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
					return true;
				}
				return false;
			}
		});
	}

	private void initGame() {
		GameStatusManger.getInstance().setActivity(this);
		MusicManager.getInstance().setActivity(this);
		PreferencesManager.getInstance().setPreferences(this, MODE_PRIVATE,
				null);
		GameViewManager.getInstance().setChannel(channel);
		BitmapManager.getInstance().setActivity(this);
		SoundManager.getInstance().setActivity(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		wakeLock = ((PowerManager) getSystemService(POWER_SERVICE))
				.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
						| PowerManager.ON_AFTER_RELEASE, "MyActivity");
		wakeLock.acquire();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (wakeLock != null) {
			wakeLock.release();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null)
			channel.addIntentEvent(data);
	}
}
