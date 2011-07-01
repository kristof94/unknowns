package org.hld.fab;

import static org.hld.fab.MiscUtil.closeOutputStream;
import static org.hld.fab.MiscUtil.createMasterAdapter;
import static org.hld.fab.MiscUtil.createSlaveAdapter;
import static org.hld.fab.MiscUtil.refreshBalanceListView;
import static org.hld.fab.MiscUtil.refreshDataListView;
import static org.hld.fab.MiscUtil.toast;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ListenerManage {
	private static final Pattern DECIMAL_PATTERN = Pattern.compile("^\\d+(\\d*|(\\.\\d*))$");
	private static boolean isLock = false;
	
	public static final OnClickListener LIST_CLICK_LISTENER = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Activity activity = (Activity)v.getContext();
			Intent intent = new Intent();
	    	intent.setClass(activity, ListActivity.class);
	    	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	activity.startActivity(intent);
		}
	};
	
	public static final OnClickListener BALANCE_CLICK_LISTENER = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Activity activity = (Activity)v.getContext();
			Intent intent = new Intent();
	    	intent.setClass(activity, BalanceActivity.class);
	    	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	activity.startActivity(intent);
		}
	};
	
	public static final OnClickListener SAVE_CLICK_LISTENER = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Activity activity = (Activity)v.getContext();
			if(isLock) {
				toast(activity, "正在处理，请稍候...");
				return;
			}
			isLock = true;
			try {
				EditText edit = (EditText)activity.findViewById(R.id.moneyEditText);
				String m = edit.getText().toString().trim();
				if(!DECIMAL_PATTERN.matcher(m).matches()) {
					toast(activity, "金额无效");
					return;
				}
				Double money = Double.valueOf(m);
				String year = ((Spinner)activity.findViewById(R.id.yearSpinner)).getSelectedItem().toString();
		    	String month = ((Spinner)activity.findViewById(R.id.monthSpinner)).getSelectedItem().toString();
		    	String day = ((Spinner)activity.findViewById(R.id.daySpinner)).getSelectedItem().toString();
		    	String date = year+month+day;
				if("余额".equals(((Spinner)activity.findViewById(R.id.typeSpinner)).getSelectedItem())) {
					toast(activity, BalanceLogManage.saveLog(date, money)?"保存成功":"保存失败");
				} else {
					boolean flag = false;
					Spinner spinner = (Spinner)activity.findViewById(R.id.masterSpinner);
					String master = spinner.getSelectedItem().toString();
					EditText masterEditText = (EditText)activity.findViewById(R.id.masterEditText);
					if("自定义".equals(master)) {
						master = masterEditText.getText().toString().trim();
						flag = true;
					}
					if(master.length()==0) {
						toast(activity, "主类无效");
						return;
					}
					String slave = ((Spinner)activity.findViewById(R.id.slaveSpinner)).getSelectedItem().toString();
					EditText slaveEditText = (EditText)activity.findViewById(R.id.slaveEditText);
					if("自定义".equals(slave)) {
						slave = slaveEditText.getText().toString().trim();
						if(slave.length()!=0) flag = true;
					}
					HistoryLog log = new HistoryLog();
					log.setDate(date);
					log.setMaster(master);
					log.setSlave(slave);
					log.setMoney(money);
					if(HistoryLogManage.saveLog(log)) {
						masterEditText.setText("");
						slaveEditText.setText("");
						edit.setText("");
						toast(activity, "保存成功");
					} else {
						toast(activity, "保存失败");
					}
					if(flag) {
						TypeManage.addType(master, slave);
						spinner.setAdapter(createMasterAdapter(activity, "自定义"));
					}
				}
			} finally {
				isLock = false;
			}
		}
	};
	
	public static final OnClickListener EXIT_CLICK_LISTENER = new OnClickListener() {
		@Override
		public void onClick(View v) {
			((Activity)v.getContext()).finish();
		}
	};
	
	public static final OnClickListener EXPORT_CLICK_LISTENER = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Activity activity = (Activity)v.getContext();
			ListAdapter adapter = ((ListView)activity.findViewById(R.id.dateListView)).getAdapter();
			int count = adapter.getCount();
			if(count==0) {
				toast(activity, "没有数据需要导出");
				return;
			}
			String year = ((Spinner)activity.findViewById(R.id.yearSpinner)).getSelectedItem().toString();
	    	String month = ((Spinner)activity.findViewById(R.id.monthSpinner)).getSelectedItem().toString();
			FileOutputStream out = null;
			File file = new File(PreferencesManage.getSDCardDir(), "FAB_"+year+month+".txt");
			try {
				DecimalFormat format = new DecimalFormat();
				format.setGroupingUsed(false);
				format.setDecimalSeparatorAlwaysShown(false);
				out = new FileOutputStream(file);
				for(int i = count-1; i>=0; i--) {
					Map item = (Map)adapter.getItem(i);
					String date = (String)item.get("date");
					List<HistoryLog> data = (List)item.get("data");
					Double total = (Double)item.get("total");
					out.write(date.getBytes("UTF-8"));
					out.write('\r');
					out.write('\n');
					for(HistoryLog log:data) {
						out.write(log.getMaster().getBytes("UTF-8"));
						out.write('\t');
						if(log.getSlave()!=null) out.write(log.getSlave().getBytes("UTF-8"));
						out.write('\t');
						out.write(format.format(log.getMoney()).getBytes());
						out.write('\r');
						out.write('\n');
					}
					out.write("合计".getBytes("UTF-8"));
					out.write('\t');
					out.write(total.toString().getBytes());
					out.write('\r');
					out.write('\n');
					out.write('\r');
					out.write('\n');
				}
				toast(activity, "数据已成功导出到"+file.getAbsolutePath());
			} catch(Exception e) {
				toast(activity, "导出数据时出错："+e);
			} finally {
				closeOutputStream(out);
			}
		}
	};
	
	public static final OnClickListener EXPORT_BALANCE_CLICK_LISTENER = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Activity activity = (Activity)v.getContext();
			File dir = BalanceLogManage.getBalanceDataDir();
			File[] list = null;
			if(dir.isDirectory()) list = dir.listFiles();
			if(list==null || list.length==0) {
				toast(activity, "没有数据需要导出");
				return;
			}
			Map<String, String> map = new TreeMap<String, String>();
			for(File file:list) BalanceLogManage.loadLog(file, map);
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			FileOutputStream out = null;
			File file = new File(PreferencesManage.getSDCardDir(), "FAB_Balance.txt");
			try {
				out = new FileOutputStream(file);
				while(iterator.hasNext()) {
					Entry<String, String> entry = iterator.next();
					String date = entry.getKey();
					out.write(date.substring(0, 4).getBytes());
					out.write("年".getBytes("UTF-8"));
					out.write(date.substring(4, 6).getBytes());
					out.write("月".getBytes("UTF-8"));
					out.write(date.substring(6).getBytes());
					out.write("日".getBytes("UTF-8"));
					out.write('\t');
					out.write(entry.getValue().getBytes());
					out.write('\r');
					out.write('\n');
				}
				toast(activity, "数据已成功导出到"+file.getAbsolutePath());
			} catch(Exception e) {
				toast(activity, "导出数据时出错："+e);
			} finally {
				closeOutputStream(out);
			}
		}
	};
	
	public static final OnClickListener COMPARE_SELECT_CLICK_LISTENER = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Activity activity = (Activity)v.getContext();
			Intent intent = new Intent();
	    	intent.setClass(activity, CompareActivity.class);
	    	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	activity.startActivity(intent);
		}
	};
	
	public static final OnClickListener COMPARE_CLICK_LISTENER = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Activity activity = (Activity)v.getContext();
			String master = (String)((Spinner)activity.findViewById(R.id.masterSpinner)).getSelectedItem();
			String type, title;
			if("所有".equals(master)) {
				type = "";
				title = "总计";
			} else {
				String slave = (String)((Spinner)activity.findViewById(R.id.slaveSpinner)).getSelectedItem();
				type = "所有".equals(slave)?master+"\t":(master+"\t"+slave+"\t");
				title = slave.length()==0?master:(master+"（"+slave+"）");
			}
			String[] dirs = PreferencesManage.getDataDir().list();
			if(dirs==null) {
				toast(activity, "无法读取记录");
				return;
			}
			TaskManage.showChart(activity, type, title, dirs);
		}
	};
	
	public static final OnItemSelectedListener TYPE_SPINNER_LISTENER = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			View table = ((Activity)view.getContext()).findViewById(R.id.tableLayout1);
			if("余额".equals(parent.getItemAtPosition(position))) table.setVisibility(View.GONE);
			else table.setVisibility(View.VISIBLE);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {}
	};
	
	public static final OnItemSelectedListener MASTER_SPINNER_LISTENER = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			Activity activity = (Activity)view.getContext();
			String master = (String)parent.getItemAtPosition(position);
			ArrayAdapter<String> slaveAdapter = createSlaveAdapter(activity, master, "自定义");
			activity.findViewById(R.id.masterEditText).setVisibility("自定义".equals(master)?View.VISIBLE:View.GONE);
			((Spinner)activity.findViewById(R.id.slaveSpinner)).setAdapter(slaveAdapter);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {}
	};
	
	public static final OnItemSelectedListener SLAVE_SPINNER_LISTENER = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			int visibility = View.GONE;
			if("自定义".equals(parent.getItemAtPosition(position))) visibility = View.VISIBLE;
			((Activity)view.getContext()).findViewById(R.id.slaveEditText).setVisibility(visibility);
		}
		
		@Override
		public void onNothingSelected(AdapterView<?> parent) {}
	};
	
	public static final OnItemSelectedListener COMPARE_SPINNER_LISTENER = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			Activity activity = (Activity)view.getContext();
			String master = (String)parent.getItemAtPosition(position);
			ArrayAdapter<String> slaveAdapter = createSlaveAdapter(activity, master, "所有");
			((Spinner)activity.findViewById(R.id.slaveSpinner)).setAdapter(slaveAdapter);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {}
	};
	
	public static final OnItemSelectedListener DATE_SPINNER_LISTENER = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			Context context = parent.getContext();
			if(context instanceof BalanceActivity) refreshBalanceListView((Activity)context);
			else refreshDataListView((Activity)context);
		}
		
		@Override
		public void onNothingSelected(AdapterView<?> parent) {}
	};
	
	public static final OnItemClickListener ITEM_CLICK_LISTENER = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Activity activity = (Activity)parent.getContext();
			HistoryLogManage.currentData = (Map)parent.getItemAtPosition(position);
			Intent intent = new Intent();
	    	intent.setClass(activity, ShowActivity.class);
	    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
	    	activity.startActivity(intent);
		}
	};
	
	public static final OnCreateContextMenuListener CREATE_CONTEXT_MENU_LISTENER = new OnCreateContextMenuListener() {
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
			menu.add("删除");
		}
	};
	
	public static boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo)item.getMenuInfo();
		ListView listView = (ListView)menuInfo.targetView.getParent();
		Activity activity = (Activity)listView.getContext();
		switch(listView.getId()) {
		case R.id.dateListView:
			String date = (String)((Map)listView.getItemAtPosition(menuInfo.position)).get("date");
			String result = HistoryLogManage.deleteLog(HistoryLogManage.getDataFile(date.replaceAll("[年月日]", "")))?"删除成功":"删除失败";
			toast(activity, result);
			refreshDataListView(activity);
			break;
		case R.id.logListView:
			Map map = (Map)listView.getItemAtPosition(menuInfo.position);
			String type = (String)map.get("type");
			Double money = (Double)map.get("money");
			int index = type.indexOf('（');
			String master, slave;
			if(index<0) {
				master = type;
				slave = "";
			} else {
				master = type.substring(0, index);
				slave = type.substring(index+1, type.length()-1);
			}
			Iterator iterator = ((List)HistoryLogManage.currentData.get("data")).iterator();
			boolean flag = false;
			while(iterator.hasNext()) {
				HistoryLog log = (HistoryLog)iterator.next();
				if(log.getMaster().equals(master) && log.getSlave().equals(slave) && log.getMoney().equals(money)) {
					iterator.remove();
					flag = true;
					break;
				}
			}
			if(flag) {
				HistoryLogManage.saveLog(HistoryLogManage.getDataFile(((String)HistoryLogManage.currentData.get("date")).replaceAll("[年月日]", "")), (List)HistoryLogManage.currentData.get("data"));
				HistoryLogManage.currentData.put("total", (Double)HistoryLogManage.currentData.get("total")-money);
				((ShowActivity)activity).refresh();
				toast(activity, "删除成功");
			} else {
				toast(activity, "要删除的记录不存在");
			}
			break;
		case R.id.balanceListView:
			map = (Map)listView.getItemAtPosition(menuInfo.position);
			date = (String)map.get("date");
			BalanceLogManage.deleteLog(date.replaceAll("[年月日]", ""));
			refreshBalanceListView(activity);
			break;
		}
		return false;
	}
}
