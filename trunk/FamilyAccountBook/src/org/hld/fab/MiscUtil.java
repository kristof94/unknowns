package org.hld.fab;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MiscUtil {
    private static final String LOG_FLAG = "FamilyAccountBook";
	private static final Pattern POSITIVE_INTEGER_PATTERN = Pattern.compile("[1-9]|[0-9]+[1-9]+|[1-9]+[0-9]+");
	
    public static void log(String msg) {
    	Log.i(LOG_FLAG, msg==null?"null":msg);
    }
    
    public static void err(Throwable t) {
    	Log.e(LOG_FLAG, Log.getStackTraceString(t));
    }
    
    public static void err(String msg, Throwable t) {
    	Log.e(LOG_FLAG, msg==null?"null":msg, t);
    }
    
    public static void toast(Context context, String msg) {
    	Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    
    public static void deleteFile(String filePath) {
    	File file = new File(filePath);
    	if(file.isDirectory()) {
    		for(String path:file.list()) {
    			deleteFile(filePath+File.separator+path);
    		}
    		file.delete();
    	} else if(file.isFile()) {
    		file.delete();
    	}
    }
    
    public static void closeReader(Reader in) {
    	if(in!=null) try {
    		in.close();
		} catch(IOException e) {
			err(e);
		}
    }
    
    public static void closeInputStream(InputStream in) {
    	if(in!=null) try {
    		in.close();
		} catch(IOException e) {
			err(e);
		}
    }
    
    public static void closeOutputStream(OutputStream out) {
    	if(out!=null) try {
			out.close();
		} catch(IOException e) {
			err(e);
		}
    }
    
    public static ArrayAdapter createAdapter(Context context) {
    	ArrayAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	return adapter;
    }
    
    public static ArrayAdapter createMasterAdapter(Context context, String append) {
    	ArrayAdapter masterAdapter = createAdapter(context);
        Iterator<String> iterator = TypeManage.getTypeMap().keySet().iterator();
        while(iterator.hasNext()) masterAdapter.add(iterator.next());
        if(append!=null) masterAdapter.add(append);
        return masterAdapter;
    }
    
    public static ArrayAdapter createSlaveAdapter(Context context, String master, String append) {
    	ArrayAdapter slaveAdapter = createAdapter(context);
    	List<String> list = TypeManage.getTypeMap().get(master);
		if(list!=null) for(String type:list) slaveAdapter.add(type);
		if(append!=null) slaveAdapter.add(append);
        return slaveAdapter;
    }
    
    public static void refreshDataListView(Activity activity) {
    	Context context = activity.getBaseContext();
    	ListView dateListView = (ListView)activity.findViewById(R.id.dateListView);
    	String year = ((Spinner)activity.findViewById(R.id.yearSpinner)).getSelectedItem().toString();
    	String month = ((Spinner)activity.findViewById(R.id.monthSpinner)).getSelectedItem().toString();
    	File dir = new File(PreferencesManage.getDataDir(), year+File.separator+month);
    	if(!dir.isDirectory()) {
			toast(context, "选择的日期没有数据");
			dateListView.setAdapter(createAdapter(context));
    		return;
    	}
    	File[] fileArray = dir.listFiles();
    	if(fileArray==null) {
			toast(context, "无法打开指定目录："+dir.getAbsolutePath());
			dateListView.setAdapter(createAdapter(context));
    		return;
    	}
    	if(fileArray.length==0) {
			toast(context, "选择的日期没有数据");
			dateListView.setAdapter(createAdapter(context));
    		return;
    	}
    	Set<Map<String, Object>> dataSet = new TreeSet<Map<String, Object>>(new Comparator<Map>() {
			@Override
			public int compare(Map object1, Map object2) {
				return -((String)object1.get("date")).compareTo((String)object2.get("date"));
			}
		});
    	for(File file:fileArray) {
    		String name = file.getName();
    		if(file.isFile() && name.endsWith(".dat")) {
    			List<HistoryLog> list = HistoryLogManage.loadLog(file);
    			if(list!=null && !list.isEmpty()) {
    				Map<String, Object> data = new HashMap<String, Object>();
    				double total = 0;
    				Iterator<HistoryLog> iterator = list.iterator();
    				while(iterator.hasNext()) {
    					HistoryLog log = iterator.next();
    					total+=log.getMoney();
    				}
    				data.put("date", year+"年"+month+"月"+file.getName().substring(0, 2)+"日");
    				data.put("data", list);
    				data.put("total", total);
    				dataSet.add(data);
    			}
    		}
		}
    	List<Map<String,Object>> dataList = new LinkedList<Map<String,Object>>();
    	Iterator<Map<String, Object>> iterator = dataSet.iterator();
    	while(iterator.hasNext()) {
    		dataList.add(iterator.next());
    	}
    	dataSet.clear();
        ((ListView)activity.findViewById(R.id.dateListView)).setAdapter(new SimpleAdapter(context, dataList, android.R.layout.simple_list_item_2, new String[]{"date", "total"}, new int[]{android.R.id.text1, android.R.id.text2}));
    }
    
    public static void refreshBalanceListView(Activity activity) {
    	Context context = activity.getBaseContext();
    	ListView dateListView = (ListView)activity.findViewById(R.id.balanceListView);
    	String year = ((Spinner)activity.findViewById(R.id.yearSpinner)).getSelectedItem().toString();
    	String month = ((Spinner)activity.findViewById(R.id.monthSpinner)).getSelectedItem().toString();
    	File file = BalanceLogManage.getDataFile(year+month);
    	if(!file.canRead()) {
			toast(context, "选择的日期没有数据");
			dateListView.setAdapter(createAdapter(context));
    		return;
    	}
    	Map<String, Double> map = BalanceLogManage.loadLog(file);
    	List<Map<String,Object>> dataList = new LinkedList<Map<String,Object>>();
    	Iterator<Entry<String, Double>> iterator = map.entrySet().iterator();
    	while(iterator.hasNext()) {
    		Entry<String, Double> entry = iterator.next();
    		Map<String, Object> temp = new HashMap<String, Object>();
    		String date = entry.getKey();
    		temp.put("date", date.substring(0, 4)+"年"+date.substring(4, 6)+"月"+date.substring(6)+"日");
    		temp.put("balance", entry.getValue());
    		dataList.add(temp);
    	}
    	dateListView.setAdapter(new SimpleAdapter(context, dataList, android.R.layout.simple_list_item_2, new String[]{"date", "balance"}, new int[]{android.R.id.text1, android.R.id.text2}));
    }
    
    public static int[] initDateAdapter(ArrayAdapter yearAdapter, ArrayAdapter monthAdapter, ArrayAdapter dayAdapter) {
    	int[] result = new int[3];
        Calendar c = Calendar.getInstance();
        if(yearAdapter!=null) {
            String year = String.valueOf(c.get(Calendar.YEAR));
            String[] dirs = PreferencesManage.getDataDir().list();
            Set<String> set = new TreeSet<String>();
            for(String d:dirs) {
            	if(POSITIVE_INTEGER_PATTERN.matcher(d).matches()) set.add(d);
            }
            if(set.isEmpty()) set.add(year);
            Iterator<String> iterator = set.iterator();
            int index = 0;
            while(iterator.hasNext()) {
            	String y = iterator.next();
            	if(year.equals(y)) result[0] = index;
            	yearAdapter.add(y);
            	index++;
            }
        }
        if(monthAdapter!=null) {
            for(String m:PreferencesManage.MONTH) monthAdapter.add(m);
            result[1] = c.get(Calendar.MONTH);
        }
        if(dayAdapter!=null) {
            for(int i = 1; i<32; i++) dayAdapter.add(i<10?"0"+i:String.valueOf(i));
            result[2] = c.get(Calendar.DATE)-1;
        }
    	return result;
    }
}
