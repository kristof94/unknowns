package org.hld.mht;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MiscUtil.log("WebViewActivity onCreate("+savedInstanceState+")");
		setContentView(R.layout.web);
		Intent intent = getIntent();
		if(intent==null) {
			showMessage("啥都没啊……");
		} else {
			String path = intent.getExtras().getString("path");
			if(path==null) {
				showMessage("不知道要去哪找文件……");
			} else if(!new File(path).isFile()) {
				showMessage(path+"文件无效");
			} else {
				WebView webView = (WebView)findViewById(R.id.WebView);
				webView.getSettings().setJavaScriptEnabled(true);
				webView.loadUrl("file://"+path);
				setTitle(webView.getTitle());
			}
		}
	}
	
	private void showMessage(String msg) {
		WebView webView = (WebView)findViewById(R.id.WebView);
		webView.getSettings().setDefaultTextEncodingName("UTF-8");
		webView.loadData(Uri.encode(msg, "UTF-8"), "text/plain", "UTF-8");
	}
}
