package org.hld.mht;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MiscUtil {
    
    public static void log(String msg) {
    	Log.i("MHT View", msg==null?"null":msg);
    }
    
    public static void err(String msg, Throwable t) {
    	Log.e("MHT View", msg==null?"null":msg, t);
    }
    
    public static void toast(Context context, String msg) {
    	Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    
    public static boolean gotoParentPath(Activity activity, String currentPath) {
    	File parent = new File(currentPath).getParentFile();
    	if(parent==null || !parent.isDirectory()) {
    		return false;
    	}
    	refreshFileListView(activity, parent.getPath());
    	return true;
    }
    
    public static void refreshFileListView(Activity activity, String filePath) {
    	Context context = activity.getBaseContext();
    	File dir = new File(filePath);
    	if(!dir.isDirectory()) {
			toast(context, "输入的路径无效");
    		return;
    	}
    	File[] fileArray = dir.listFiles();
    	if(fileArray==null) {
			toast(context, "无法打开指定目录");
    		return;
    	}
//    	List<File> fileList = new LinkedList<File>();
//    	for(File file:fileArray) {
//			if(file.isDirectory()) {
//				fileList.add(file);
//			} else if(file.isFile() && file.getName().toLowerCase().endsWith(".mht")) {
//				fileList.add(file);
//			}
//		}
//    	((ListView)activity.findViewById(R.id.FileListView)).setAdapter(new ArrayAdapter<File>(context, android.R.layout.simple_list_item_1, fileList));
    	List<Map<String, Object>> fileList = new LinkedList<Map<String, Object>>();
    	for(File file:fileArray) {
    		String isDir = null;
    		if(file.isDirectory()) {
    			isDir = "";
    		} else {
    			String fileName = file.getName().toLowerCase();
    			if(!fileName.endsWith(".mht") && !fileName.endsWith(".html")) continue;
    		}
    		Map<String, Object> fileMap = new HashMap<String, Object>();
    		fileMap.put("name", file.getName());
    		fileMap.put("path", file.getAbsolutePath());
    		if(isDir!=null) fileMap.put("isDir", isDir);
    		fileList.add(fileMap);
		}
        ((ListView)activity.findViewById(R.id.FileListView)).setAdapter(new SimpleAdapter(context, fileList, android.R.layout.simple_list_item_1, new String[]{"name"}, new int[]{android.R.id.text1}));
//        ((ListView)findViewById(R.id.FileListView)).setAdapter(new ArrayAdapter<File>(this, android.R.layout.simple_list_item_1, files));
        String currentPath = dir.getAbsolutePath();
        EditText editText = (EditText)activity.findViewById(R.id.PathEditText);
        editText.setText(currentPath);
        editText.clearFocus();
		InputMethodManager inputManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS); 
        PreferencesManage.setCurrentPath(currentPath);
    }
    
    public static Intent createShowHtmlIntent(Activity activity, String filePath) {
    	Intent intent = new Intent();
    	intent.setClass(activity, WebViewActivity.class);
    	intent.putExtra("path", filePath);
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	return intent;
    }
    
    public static void showHtml(Activity activity, String filePath) {
    	activity.startActivity(createShowHtmlIntent(activity, filePath));
    }
    
}
