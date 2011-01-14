package org.hld.mht;

import java.io.IOException;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class TaskManage {
	
	public static void showMHT(Activity activity, String mhtPath) {
		new ShowMHTAsyncTask(activity, mhtPath).execute(null);
	}

	private static class ShowMHTAsyncTask extends AsyncTask<Void, Void, String> {
		private Activity activity;
		private String mhtPath;
		
		public ShowMHTAsyncTask(Activity activity, String mhtPath) {
			super();
			this.activity = activity;
			this.mhtPath = mhtPath;
		}

		@Override
		protected void onPreExecute() {
			MiscUtil.toast(activity, "正在处理MHT文件……");
		}
		
		@Override
		protected String doInBackground(Void... params) {
			try {
				String path = new MHT(mhtPath).save();
				MiscUtil.log("MHT save to "+path);
				return path;
			} catch(IOException e) {
				Log.e("MHT View", "save mht error", e);
				MiscUtil.toast(activity, "这个真的是MHT文件么……");
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			if(result!=null && activity instanceof MHTActivity) {
				MiscUtil.log("activity.isShow():"+((MHTActivity)activity).isShow());
				if(((MHTActivity)activity).isShow()) {
					MiscUtil.showHtml(activity, result);
				} else {
					NotificationManager notificationManager = (NotificationManager)activity.getSystemService(Context.NOTIFICATION_SERVICE);
					PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, MiscUtil.createShowHtmlIntent(activity, result), PendingIntent.FLAG_CANCEL_CURRENT);
					Notification notification = new Notification(R.drawable.icon, "MHT转换完毕", System.currentTimeMillis());
					notification.flags |= Notification.FLAG_AUTO_CANCEL;
					notification.setLatestEventInfo(activity, "MHT转换完毕", mhtPath.subSequence(mhtPath.lastIndexOf('/')+1, mhtPath.length()), pendingIntent);
					notificationManager.cancel(R.string.app_name);
					notificationManager.notify(R.string.app_name, notification);
				}
			}
		}
	}
}
