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
	private String currentPath = "/";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	MiscUtil.log("onCreate("+savedInstanceState+")");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("选择文件");
        File file = new File("/sdcard");
        if(file.exists()) MiscUtil.refreshFileListView(this, "/sdcard");
        else MiscUtil.refreshFileListView(this, "/");
        ((Button)findViewById(R.id.Button01)).setOnClickListener(ListenerManage.ROOT_PATH_CLICK_LISTENER);
        ((Button)findViewById(R.id.Button02)).setOnClickListener(ListenerManage.PARENT_PATH_CLICK_LISTENER);
        ((Button)findViewById(R.id.Button03)).setOnClickListener(ListenerManage.SDCARD_PATH_CLICK_LISTENER);
        ((EditText)findViewById(R.id.PathEditText)).setOnEditorActionListener(ListenerManage.SUBMIT_EDITOR_LISTENER);
        ((ListView)findViewById(R.id.FileListView)).setOnItemClickListener(ListenerManage.ITEM_CLICK_LISTENER);
        PreferenceManager.getDefaultSharedPreferences(this);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK && !"/".equals(currentPath)) {
    		MiscUtil.gotoParentPath(this, currentPath);
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    
}