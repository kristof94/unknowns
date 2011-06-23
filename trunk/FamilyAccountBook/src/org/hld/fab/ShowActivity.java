package org.hld.fab;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ShowActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        refresh();
        ((TextView)findViewById(R.id.dateTextView)).setText((String)HistoryLogManage.currentData.get("date"));
        ((ListView)findViewById(R.id.logListView)).setOnCreateContextMenuListener(ListenerManage.CREATE_CONTEXT_MENU_LISTENER);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	return ListenerManage.onContextItemSelected(item);
    }
    
    public void refresh() {
        ((TextView)findViewById(R.id.totalTextView)).setText("："+HistoryLogManage.currentData.get("total")+"元");
        Iterator iterator = ((List)HistoryLogManage.currentData.get("data")).iterator();
        List<Map<String,Object>> dataList = new LinkedList<Map<String,Object>>();
        while(iterator.hasNext()) {
        	HistoryLog log = (HistoryLog)iterator.next();
        	Map map = new HashMap();
        	if(log.getSlave()==null || log.getSlave().length()==0) map.put("type", log.getMaster());
        	else map.put("type", log.getMaster()+"（"+log.getSlave()+"）");
        	map.put("money", log.getMoney());
        	dataList.add(map);
        }
        ((ListView)findViewById(R.id.logListView)).setAdapter(new SimpleAdapter(this, dataList, android.R.layout.simple_list_item_2, new String[]{"type", "money"}, new int[]{android.R.id.text1, android.R.id.text2}));
    }
}