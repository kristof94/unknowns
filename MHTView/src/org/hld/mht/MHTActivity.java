package org.hld.mht;

import java.io.File;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
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
        File file = new File(PreferencesManage.getCurrentPath());
        if(file.exists()) MiscUtil.refreshFileListView(this, PreferencesManage.getCurrentPath());
        else MiscUtil.refreshFileListView(this, PreferencesManage.getRootPath());
        ((Button)findViewById(R.id.Button01)).setOnClickListener(ListenerManage.ROOT_PATH_CLICK_LISTENER);
        ((Button)findViewById(R.id.Button02)).setOnClickListener(ListenerManage.PARENT_PATH_CLICK_LISTENER);
        ((Button)findViewById(R.id.Button03)).setOnClickListener(ListenerManage.SDCARD_PATH_CLICK_LISTENER);
        ((EditText)findViewById(R.id.PathEditText)).setOnEditorActionListener(ListenerManage.SUBMIT_EDITOR_LISTENER);
        ((ListView)findViewById(R.id.FileListView)).setOnItemClickListener(ListenerManage.ITEM_CLICK_LISTENER);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK && !PreferencesManage.getRootPath().equals(PreferencesManage.getCurrentPath())) {
    		MiscUtil.gotoParentPath(this, PreferencesManage.getCurrentPath());
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