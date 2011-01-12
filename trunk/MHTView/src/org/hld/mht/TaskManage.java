package org.hld.mht;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.os.AsyncTask;

public class TaskManage {
	private static Timer timer = new Timer(R.string.app_name+" TaskManage", true);
	
	public static void showMHT(Activity activity, String mhtPath) {
	}
	
	private class ShowMHTAsyncTask extends AsyncTask<String, Void, Void> {
		private Activity activity;
		
		public ShowMHTAsyncTask(Activity activity) {
			super();
			this.activity = activity;
		}

		@Override
		protected Void doInBackground(String... params) {
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}
	}
}
