package org.hld.mht;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView.OnEditorActionListener;

public class MHTActivity extends Activity {
	private String currentPath = "/";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	log("onCreate("+savedInstanceState+")");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("选择文件");
        File file = new File("/sdcard");
        if(file.exists()) refreshFileListView(this, "/sdcard");
        else refreshFileListView(this, "/");
        ((Button)findViewById(R.id.Button01)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				refreshFileListView((Activity)v.getContext(), "/");
			}
		});
        ((Button)findViewById(R.id.Button02)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoParentPath((Activity)v.getContext(), currentPath);
			}
		});
        ((Button)findViewById(R.id.Button03)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				refreshFileListView((Activity)v.getContext(), "/sdcard");
			}
		});
        EditText editText = (EditText)findViewById(R.id.PathEditText);
        editText.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(v.getContext() instanceof Activity) {
					refreshFileListView((Activity)v.getContext(), v.getText().toString());
					return true;
				}
				return false;
			}
		});
        ((ListView)findViewById(R.id.FileListView)).setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(parent.getContext() instanceof Activity) {
					Map map = (Map)parent.getItemAtPosition(position);
					if(map.containsKey("isDir")) {
						refreshFileListView((Activity)parent.getContext(), (String)map.get("path"));
					} else {
						String name = (String)map.get("name");
						if(name.endsWith(".html")) {
							log((String)map.get("path"));
							log(Uri.encode((String)map.get("path"), "/"));
							try {
								log(URLEncoder.encode((String)map.get("path"), "utf-8"));
								log(URLEncoder.encode((String)map.get("path"), "gbk"));
							} catch(UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							Uri uri = Uri.parse("file://"+Uri.encode((String)map.get("path"), "/"));
							log(uri.getEncodedPath());
							log(uri.getPath());
							((Activity)parent.getContext()).startActivity(new Intent(Intent.ACTION_VIEW, uri));
						} else {
							try {
								String path = new MHT((String)map.get("path")).save();
								toast(parent.getContext(), "MHT文件转换后已保存到当前目录");
								Uri uri = Uri.parse("file://"+Uri.encode(path, "/"));
								((Activity)parent.getContext()).startActivity(new Intent(Intent.ACTION_VIEW, uri));
							} catch(IOException e) {
								Log.e("MHT View", "save mht error", e);
								toast(parent.getContext(), "处理MHT文件时出错");
							}
						}
					}
				} else {
					toast(parent.getContext(), "无法显示选择的文件");
				}
			}});
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK && !"/".equals(currentPath)) {
    		gotoParentPath(this, currentPath);
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    
    private void log(String msg) {
    	Log.i("MHT View", msg);
    }
    
    private void toast(Context context, String msg) {
    	Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    
    private void gotoParentPath(Activity activity, String currentPath) {
    	if(currentPath==null) {
    		toast(activity.getBaseContext(), "输入的路径无效");
    		return;
    	}
    	refreshFileListView(activity, currentPath.equals("/")?"/":new File(currentPath).getParent());
    }
    
    private void refreshFileListView(Activity activity, String filePath) {
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
        ((ListView)findViewById(R.id.FileListView)).setAdapter(new SimpleAdapter(this, fileList, android.R.layout.simple_list_item_1, new String[]{"name"}, new int[]{android.R.id.text1}));
//        ((ListView)findViewById(R.id.FileListView)).setAdapter(new ArrayAdapter<File>(this, android.R.layout.simple_list_item_1, files));
        currentPath = dir.getAbsolutePath();
        ((EditText)activity.findViewById(R.id.PathEditText)).setText(currentPath);
    }
    
}