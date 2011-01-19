package org.hld.mht;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MHTActivity extends Activity {
	private boolean isShow;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferencesManage.initPreferences(PreferenceManager.getDefaultSharedPreferences(this));
        setContentView(R.layout.main);
        setTitle(R.string.app_title);
        MiscUtil.refreshFileListView(this, PreferencesManage.getRootPath());
        ((Button)findViewById(R.id.Button01)).setOnClickListener(ListenerManage.ROOT_PATH_CLICK_LISTENER);
        ((Button)findViewById(R.id.Button02)).setOnClickListener(ListenerManage.PARENT_PATH_CLICK_LISTENER);
        ((Button)findViewById(R.id.Button03)).setOnClickListener(ListenerManage.SDCARD_PATH_CLICK_LISTENER);
        ((EditText)findViewById(R.id.PathEditText)).setOnEditorActionListener(ListenerManage.SUBMIT_EDITOR_LISTENER);
        ((ListView)findViewById(R.id.FileListView)).setOnItemClickListener(ListenerManage.ITEM_CLICK_LISTENER);
        String state = Environment.getExternalStorageState();
        if(!Environment.MEDIA_MOUNTED.equals(state)) {
        	if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
        		MiscUtil.toast(this, "无法保存文件到SD卡，生成的文件将保存到缓存目录");
        	} else {
        		MiscUtil.toast(this, "无法读取SD卡，生成的文件将保存到缓存目录");
        	}
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater menuInflater = getMenuInflater();
    	menuInflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
		case R.id.PathItem:
			PreferencesManage.setRootPath(PreferencesManage.getCurrentPath());
			break;
		case R.id.CleanItem:
			new AlertDialog.Builder(this).setTitle(R.string.clean_dialog_title).setPositiveButton(R.string.clean_dialog_ok, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
						MiscUtil.deleteFile(PreferencesManage.getSdcardCachePath());
					}
					MiscUtil.deleteFile(PreferencesManage.getLocalCachePath(MHTActivity.this));
				}
			}).setNegativeButton(R.string.clean_dialog_cancel, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {}
			}).show();
			break;
		case R.id.ExitItem:
			MHTActivity.this.finish();
			break;
		}
    	return true;
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK && MiscUtil.gotoParentPath(this, PreferencesManage.getCurrentPath())) {
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	isShow = true;
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	isShow = false;
    }
    
    public boolean isShow() {
    	return isShow;
    }

}