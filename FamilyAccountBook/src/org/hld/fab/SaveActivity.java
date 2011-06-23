package org.hld.fab;

import static org.hld.fab.ListenerManage.*;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SaveActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save);
        //设置日期项
        ArrayAdapter<String> yearAdapter = MiscUtil.createAdapter(this);
        ArrayAdapter<String> monthAdapter = MiscUtil.createAdapter(this);
        ArrayAdapter<String> dayAdapter = MiscUtil.createAdapter(this);
        int[] result = MiscUtil.initDateAdapter(yearAdapter, monthAdapter, dayAdapter);
        //设置日期列表
        Spinner yearSpinner = (Spinner)findViewById(R.id.yearSpinner);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setSelection(result[0]);
        Spinner monthSpinner = (Spinner)findViewById(R.id.monthSpinner);
        monthSpinner.setAdapter(monthAdapter);
        monthSpinner.setSelection(result[1]);
        Spinner daySpinner = (Spinner)findViewById(R.id.daySpinner);
        daySpinner.setAdapter(dayAdapter);
        daySpinner.setSelection(result[2]);
        //设置分类列表
        Spinner masterSpinner = (Spinner)findViewById(R.id.masterSpinner);
        masterSpinner.setAdapter(MiscUtil.createMasterAdapter(this, "自定义"));
        masterSpinner.setOnItemSelectedListener(MASTER_SPINNER_LISTENER);
        Spinner slaveSpinner = (Spinner)findViewById(R.id.slaveSpinner);
        slaveSpinner.setOnItemSelectedListener(SLAVE_SPINNER_LISTENER);
        //设置按钮的监听器
        ((Button)findViewById(R.id.listButton)).setOnClickListener(LIST_CLICK_LISTENER);
        ((Button)findViewById(R.id.saveButton)).setOnClickListener(SAVE_CLICK_LISTENER);
        ((Button)findViewById(R.id.exitButton)).setOnClickListener(EXIT_CLICK_LISTENER);
    }
}