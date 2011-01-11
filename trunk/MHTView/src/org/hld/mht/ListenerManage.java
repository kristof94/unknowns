package org.hld.mht;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView.OnEditorActionListener;

public class ListenerManage {
	public static final OnClickListener ROOT_PATH_CLICK_LISTENER = new OnClickListener() {
		@Override
		public void onClick(View v) {
			MiscUtil.refreshFileListView((Activity)v.getContext(), PreferencesManage.getRootPath());
		}
	};
	
	public static final OnClickListener PARENT_PATH_CLICK_LISTENER = new OnClickListener() {
		@Override
		public void onClick(View v) {
			MiscUtil.gotoParentPath((Activity)v.getContext(), PreferencesManage.getCurrentPath());
		}
	};
	
	public static final OnClickListener SDCARD_PATH_CLICK_LISTENER = new OnClickListener() {
		@Override
		public void onClick(View v) {
			MiscUtil.refreshFileListView((Activity)v.getContext(), PreferencesManage.getSdcardPath());
		}
	};
	
	public static final OnEditorActionListener SUBMIT_EDITOR_LISTENER = new OnEditorActionListener() {
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			if(v.getContext() instanceof Activity) {
				MiscUtil.refreshFileListView((Activity)v.getContext(), v.getText().toString());
				return true;
			}
			return false;
		}
	};
	
	public static final OnItemClickListener ITEM_CLICK_LISTENER = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if(parent.getContext() instanceof Activity) {
				Map map = (Map)parent.getItemAtPosition(position);
				if(map.containsKey("isDir")) {
					MiscUtil.refreshFileListView((Activity)parent.getContext(), (String)map.get("path"));
				} else {
					String name = (String)map.get("name");
					if(name.endsWith(".html")) {
//						Uri uri = Uri.parse("file://"+Uri.encode((String)map.get("path"), "/"));
//						((Activity)parent.getContext()).startActivity(new Intent(Intent.ACTION_VIEW, uri));
						MiscUtil.showHtml((Activity)parent.getContext(), (String)map.get("path"));
					} else {
						try {
							String path = new MHT((String)map.get("path")).save();
							MiscUtil.toast(parent.getContext(), "MHT文件转换后已保存到当前目录");
//							Uri uri = Uri.parse("file://"+Uri.encode(path, "/"));
//							((Activity)parent.getContext()).startActivity(new Intent(Intent.ACTION_VIEW, uri));
							MiscUtil.showHtml((Activity)parent.getContext(), path);
						} catch(IOException e) {
							Log.e("MHT View", "save mht error", e);
							MiscUtil.toast(parent.getContext(), "这个真的是MHT文件么……");
						}
					}
				}
			} else {
				MiscUtil.toast(parent.getContext(), "选择的文件不让随便看啊……");
			}
		}};
}
