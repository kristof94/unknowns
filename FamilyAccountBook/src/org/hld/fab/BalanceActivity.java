package org.hld.fab;

import static org.hld.fab.ListenerManage.CREATE_CONTEXT_MENU_LISTENER;
import static org.hld.fab.ListenerManage.DATE_SPINNER_LISTENER;
import static org.hld.fab.ListenerManage.EXIT_CLICK_LISTENER;
import static org.hld.fab.ListenerManage.EXPORT_BALANCE_CLICK_LISTENER;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class BalanceActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance);
        //设置日期项
        ArrayAdapter<String> yearAdapter = MiscUtil.createAdapter(this);
        ArrayAdapter<String> monthAdapter = MiscUtil.createAdapter(this);
        int[] result = MiscUtil.initDateAdapter(yearAdapter, monthAdapter, null);
        //设置日期列表
        Spinner yearSpinner = (Spinner)findViewById(R.id.yearSpinner);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setSelection(result[0]);
        Spinner monthSpinner = (Spinner)findViewById(R.id.monthSpinner);
        monthSpinner.setAdapter(monthAdapter);
        monthSpinner.setSelection(result[1]);
        //设置监听器
        yearSpinner.setOnItemSelectedListener(DATE_SPINNER_LISTENER);
        monthSpinner.setOnItemSelectedListener(DATE_SPINNER_LISTENER);
        ((ListView)findViewById(R.id.balanceListView)).setOnCreateContextMenuListener(CREATE_CONTEXT_MENU_LISTENER);
        ((Button)findViewById(R.id.exportButton)).setOnClickListener(EXPORT_BALANCE_CLICK_LISTENER);
        ((Button)findViewById(R.id.exitButton)).setOnClickListener(EXIT_CLICK_LISTENER);
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
        MiscUtil.refreshBalanceListView(this);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	return ListenerManage.onContextItemSelected(item);
    }
}
