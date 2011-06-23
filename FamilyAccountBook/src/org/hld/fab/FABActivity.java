package org.hld.fab;

import android.os.Bundle;

public class FABActivity extends SaveActivity {
	private static boolean init = true;
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		if(init) {
			//初始化分类信息
			TypeManage.init(this);
			init = false;
		}
		super.onCreate(savedInstanceState);
	}
}