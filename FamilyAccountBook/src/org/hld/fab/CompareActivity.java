package org.hld.fab;

import static org.hld.fab.ListenerManage.*;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

public class CompareActivity extends Activity {
	private boolean isShow;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare);
        //设置分类列表
        Spinner masterSpinner = (Spinner)findViewById(R.id.masterSpinner);
        masterSpinner.setAdapter(MiscUtil.createMasterAdapter(this, "所有"));
        masterSpinner.setOnItemSelectedListener(COMPARE_SPINNER_LISTENER);
        //设置按钮的监听器
        ((Button)findViewById(R.id.compareButton)).setOnClickListener(COMPARE_CLICK_LISTENER);
        ((Button)findViewById(R.id.exitButton)).setOnClickListener(EXIT_CLICK_LISTENER);
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