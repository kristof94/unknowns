package org.hld.fab;

import static org.hld.fab.MiscUtil.closeReader;
import static org.hld.fab.MiscUtil.err;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.TreeSet;
import org.achartengine.ChartFactory;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;

public class TaskManage {
	
	public static void showChart(Activity activity, String type, String title, String[] dirs) {
		new ShowChartAsyncTask(activity, type, title, dirs).execute();
	}

	private static class ShowChartAsyncTask extends AsyncTask<Void, Void, Intent> {
		private Activity activity;
		private String type;
		private String title;
		private String[] dirs;
		
		public ShowChartAsyncTask(Activity activity, String type, String title, String[] dirs) {
			super();
			this.activity = activity;
			this.type = type;
			this.title = title;
			this.dirs = dirs;
		}

		@Override
		protected void onPreExecute() {
			MiscUtil.toast(activity, "正在统计，请稍候……");
		}
		
		@Override
		protected Intent doInBackground(Void... params) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			//设置图表数据
			XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
			TimeSeries typeSeries = new TimeSeries(title);
			int i = 0;
			double max = 0;
			Set<String> yearSet = new TreeSet<String>();
			for(String year:dirs) yearSet.add(year);
			for(String year:yearSet) {
				for(String month:PreferencesManage.MONTH) {
					String date = year+File.separator+month;
					File dir = new File(PreferencesManage.getDataDir(), date);
					if(dir.isDirectory()) {
						Set<String> daySet = new TreeSet<String>();
						for(String day:dir.list()) daySet.add(day);
						for(String day:daySet) {
							File file = new File(PreferencesManage.getDataDir(), date+File.separator+day);
							BufferedReader in = null;
							try {
								in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"), 1024);
								String s;
								double count = 0;
								while((s=in.readLine())!=null) {
									int index = s.lastIndexOf('\t');
									if(index>-1) {
										double money = Double.parseDouble(s.substring(index+1));
										if(s.startsWith(type))count+=money;
									}
								}
								if(count>0) {
								MiscUtil.log(year+month+day+":"+count);
									typeSeries.add(sdf.parse(year+month+day.substring(0, 2)), count);
									i++;
									if(count>max) max = count;
								}
							} catch(Exception e) {
								err("读取"+file.getAbsolutePath()+"时出错", e);
							} finally {
								closeReader(in);
							}
						}
					}
				}
			}
			if(typeSeries.getItemCount()==0) return null;
			dataset.addSeries(typeSeries);
			//设置图表样式
			XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
			renderer.setAxisTitleTextSize(16);
		    renderer.setChartTitleTextSize(20);
		    renderer.setLabelsTextSize(15);
		    renderer.setLegendTextSize(15);
		    
		    renderer.setChartTitle(title+"历史数据比较");
		    renderer.setXTitle("日期");
		    renderer.setYTitle("金额");
		    renderer.setYAxisMin(0);
		    renderer.setYAxisMax(max);
		    renderer.setAxesColor(Color.GRAY);
		    renderer.setLabelsColor(Color.LTGRAY);
		    
		    renderer.setDisplayChartValues(false);
		    renderer.setBarSpacing(0.5);
		    
		    XYSeriesRenderer typeRenderer = new XYSeriesRenderer();
		    typeRenderer.setColor(Color.BLUE);
		    renderer.addSeriesRenderer(typeRenderer);
		    
		    Intent intent = ChartFactory.getTimeChartIntent(activity, dataset, renderer, null);
		    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			return intent;
		}
		
		@Override
		protected void onPostExecute(Intent intent) {
			if(activity instanceof CompareActivity) {
				if(intent!=null) {
					if(((CompareActivity)activity).isShow()) {
						activity.startActivity(intent);
					} else {
						NotificationManager notificationManager = (NotificationManager)activity.getSystemService(Context.NOTIFICATION_SERVICE);
						PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
						Notification notification = new Notification(R.drawable.icon, "数据统计完毕", System.currentTimeMillis());
						notification.flags |= Notification.FLAG_AUTO_CANCEL;
						notification.setLatestEventInfo(activity, "数据统计完毕", title+"的数据已统计完毕，请点击查看", pendingIntent);
						notificationManager.cancel(R.string.app_name);
						notificationManager.notify(R.string.app_name, notification);
					}
				} else {
					if(((CompareActivity)activity).isShow()) {
						MiscUtil.toast(activity, "没有查到"+title+"相关的数据");
					} else {
						NotificationManager notificationManager = (NotificationManager)activity.getSystemService(Context.NOTIFICATION_SERVICE);
						PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, activity.getIntent(), PendingIntent.FLAG_CANCEL_CURRENT);
						Notification notification = new Notification(R.drawable.icon, "数据统计完毕", System.currentTimeMillis());
						notification.flags |= Notification.FLAG_AUTO_CANCEL;
						notification.setLatestEventInfo(activity, "数据统计完毕", "没有查到"+title+"相关的数据", pendingIntent);
						notificationManager.cancel(R.string.app_name);
						notificationManager.notify(R.string.app_name, notification);
					}
				}
			}
		}
	}
}
