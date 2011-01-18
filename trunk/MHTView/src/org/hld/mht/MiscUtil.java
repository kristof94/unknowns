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
    	List<Map<String, Object>> fileList = new LinkedList<Map<String, Object>>();
    	for(File file:fileArray) {
    		String isDir = null;
    		boolean isHtml = false;
    		if(file.isDirectory()) {
    			isDir = "";
    		} else {
    			String fileName = file.getName().toLowerCase();
    			if(fileName.endsWith(".html")) {
    				isHtml = true;
    			} else if(!fileName.endsWith(".mht")) {
    				continue;
    			}
    		}
    		Map<String, Object> fileMap = new HashMap<String, Object>();
    		fileMap.put("name", file.getName());
    		fileMap.put("path", file.getAbsolutePath());
    		if(isDir!=null) {
    			fileMap.put("isDir", isDir);
    			fileMap.put("icon", R.drawable.icon_dir);
    		} else {
    			fileMap.put("icon", isHtml?R.drawable.icon_html:R.drawable.icon_mht);
    		}
    		fileList.add(fileMap);
		}
//        ((ListView)activity.findViewById(R.id.FileListView)).setAdapter(new SimpleAdapter(context, fileList, android.R.layout.simple_list_item_1, new String[]{"name"}, new int[]{android.R.id.text1}));
        ((ListView)activity.findViewById(R.id.FileListView)).setAdapter(new SimpleAdapter(context, fileList, R.layout.filelist, new String[]{"icon", "name"}, new int[]{R.id.FileIconImageView, R.id.FileNameTextView}));
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
}
