package hld.coins.view;

import hld.coins.R;
import hld.coins.constants.GameStatusConstants.Status;
import hld.coins.manager.BitmapManager;
import hld.coins.manager.GameStatusManger;
import hld.coins.util.LogUnit;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Image;
import hld.coins.wrapper.Images;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

@SuppressWarnings("unused")
public class CourseView extends MainView {
	private Image bg;
	private Images menu;
	private Images again;
	private Point menuPoint;
	private Point againPoint;
	private Rect menuRect;
	private Rect againRect;
	private boolean pressMenu;
	private boolean pressAgain;
	private Point[] points = new Point[]{coinaPoint, coinbPoint, coincPoint, coindPoint, coinePoint};
	private Graphics graphics;
	private List<Action> actionList;
	private Pattern pattern;
	private int location = -1;
	private int frames;
	private int restframes;
	private long timeout;
	private long resttimeout;
	private long time;
	
	public CourseView(Activity activity) {
		super(false);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(activity.getAssets().open("script/course")));
			actionList = new LinkedList<Action>();
			pattern = Pattern.compile("^-?[0-9]+");
			for(String s = in.readLine(); s!=null; s = in.readLine()) {
				if(s.length()>0) {
					actionList.add(new Action(s));
				}
			}
		} catch(IOException e) {
			LogUnit.e("CourseView read BufferedReader", e);
			disable();
			hide();
			GameStatusManger.getInstance().setStatusCurrent(Status.MENU);
			return;
		} finally {
			if(in!=null) {
				try {
					in.close();
				} catch(IOException e) {
					LogUnit.w("CourseView close BufferedReader", e);
				}
			}
		}
		bg = BitmapManager.getInstance().getViewScaledImage(getClass(), R.drawable.howtoplaybg, scale, false);
		menu = BitmapManager.getInstance().getViewScaledImages(getClass(), scale, false, R.drawable.menua0000, R.drawable.menua0001);
		again = BitmapManager.getInstance().getViewScaledImages(getClass(), scale, false, R.drawable.againa0000, R.drawable.againa0001);
		menuPoint = new Point(offsetX(195),offsetY(90));
		againPoint = new Point(offsetX(193),offsetY(196));
		menuRect = new Rect(menuPoint.x, menuPoint.y, menuPoint.x+menu.getWidth(), menuPoint.y+menu.getHeight());
		againRect = new Rect(againPoint.x, againPoint.y, againPoint.x+again.getWidth(), againPoint.y+again.getHeight());
		bestStr = null;
		isShowHelp = false;
		currentLevel = 3;
		rules();
		clear();
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		super.onDraw(graphics);
		if(actionList.size()!=location+1) {
			this.graphics = graphics;
			if(restframes>0) {
				actionList.get(location).invoke(this);
				restframes--;
			} else if(resttimeout>0) {
				actionList.get(location).invoke(this);
				resttimeout = timeout-(System.currentTimeMillis()-time);
			} else {
				Action action = actionList.get(++location);
				frames = restframes = action.frames;
				timeout = resttimeout = action.timeout;
				if(timeout>0) time = System.currentTimeMillis();
				action.invoke(this);
			}
			this.graphics = null;
		} else {
			goTime = false;
			graphics.drawImage(bg.imgae, bgPoint);
			graphics.drawImage(menu, menuPoint, pressMenu);
			graphics.drawImage(again, againPoint, pressAgain);
		}
	}
	
	@Override
	public boolean onTouchListener(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			int x = (int)event.getX();
			int y = (int)event.getY();
			if(menuRect.contains(x, y)) {
				pressMenu = true;
			} else if(againRect.contains(x, y)) {
				pressAgain = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			if(pressMenu && menuRect.contains((int)event.getX(), (int)event.getY())) {
				disable();
				hide();
				GameStatusManger.getInstance().setStatusCurrent(Status.MENU);
			} else if(pressAgain && againRect.contains((int)event.getX(), (int)event.getY())) {
				isShowHelp = false;
				currentStage = 1;
				location = frames = restframes = -1;
				time = timeout = resttimeout = -1L;
				rules();
				clear();
				goTime = true;
			}
			pressMenu = pressAgain = false;
			break;
		}
		return false;
	}
	
	private void rect(int x, int y, int width, int height) {
		graphics.drawRect(x, y, width, height, Color.RED);
	}
	
	private void move(int id, int x, int y) {
		Images coin = coins[id];
		Point point = points[id];
		x = offsetX(x)-coin.getWidth()/2;
		y = offsetY(y)-coin.getHeight()/2;
		if(frames>0) {
			if(restframes<=0) {
				x = point.x;
				y = point.y;
			} else {
				float offset = restframes/(float)frames;
				x = (int)(x+(point.x-x)*offset);
				y = (int)(y+(point.y-y)*offset);
			}
		} else if(timeout>0) {
			if(resttimeout<=0) {
				x = point.x;
				y = point.y;
			} else {
				float offset = resttimeout/(float)timeout;
				x = (int)(x+(point.x-x)*offset);
				y = (int)(y+(point.y-y)*offset);
			}
		}
		graphics.drawImage(coin, x, y, isShowHelp?1:0);
	}
	
	private void down(int id, int x, int y) {
		Images images = coinxs[id];
		coinList.add(images);
		int w = images.getWidth() / 2;
		int h = images.getHeight() / 2;
		coinRectList.add(new Rect(x - w, y - h, x + w, y + h));
		coinShowList.add(coinShowList.size());
		currentAmount += coinsAmount[id];
		currentAmount = new BigDecimal(currentAmount, new MathContext(2)).floatValue();
	}
	
	private void delay() {}
	
	private void clear() {
		clearAll();
		targetAmount = 0.4f;
	}
	
	private void help() {
		isShowHelp = !isShowHelp;
	}
	
	private void pass() {
		currentStage++;
	}
	
	private class Action {
		private Method method;
		private Object[] args;
		int frames;
		long timeout;
		public Action(String action) {
			String[] parameter = action.split(" +", 3);
			try {
				switch(parameter.length) {
				case 1:
					if(action.endsWith("L") || action.endsWith("l")) {
						String temp = action.substring(0, action.length()-1);
						if(pattern.matcher(temp).matches()) {
							method = CourseView.class.getDeclaredMethod("delay");
							timeout = Long.parseLong(temp);
						}
					} else if(pattern.matcher(action).matches()) {
						method = CourseView.class.getDeclaredMethod("delay");
						frames = Integer.parseInt(action);
					}
					if(method==null) method = CourseView.class.getDeclaredMethod(action);
					break;
				case 2:
					String temp = parameter[0];
					if(temp.endsWith("L") || temp.endsWith("l")) {
						temp = temp.substring(0, temp.length()-1);
						if(pattern.matcher(temp).matches()) {
							timeout = Long.parseLong(temp);
						}
					} else if(pattern.matcher(temp).matches()) {
						frames = Integer.parseInt(temp);
					}
					if(frames>0 || timeout>0) {
						method = CourseView.class.getDeclaredMethod(parameter[1]);
					} else {
						String[] s = parameter[1].split(",");
						args = new Object[s.length];
						Class[] parameterTypes = new Class[s.length];
						for(int i = 0; i < s.length; i++) {
							String arg = s[i];
							if(arg.endsWith("L") || arg.endsWith("l")) {
								temp = arg.substring(0, arg.length()-1);
								if(pattern.matcher(temp).matches()) {
									args[i] = Long.valueOf(temp);
									parameterTypes[i] = long.class;
								}
							} else if(pattern.matcher(arg).matches()) {
								args[i] = Integer.valueOf(arg);
								parameterTypes[i] = int.class;
							}
							if(args[i]==null) {
								args[i] = arg;
								parameterTypes[i] = String.class;
							}
						}
						method = CourseView.class.getDeclaredMethod(parameter[0], parameterTypes);
					}
					break;
				case 3:
					temp = parameter[0];
					if(temp.endsWith("L") || temp.endsWith("l")) {
						temp = temp.substring(0, temp.length()-1);
						if(pattern.matcher(temp).matches()) {
							timeout = Long.parseLong(temp);
						}
					} else if(pattern.matcher(temp).matches()) {
						frames = Integer.parseInt(temp);
					}
					String methodName = parameter[1];
					String[] s = parameter[2].split(",");
					args = new Object[s.length];
					Class[] parameterTypes = new Class[s.length];
					for(int i = 0; i < s.length; i++) {
						String arg = s[i];
						if(arg.endsWith("L") || arg.endsWith("l")) {
							temp = arg.substring(0, arg.length()-1);
							if(pattern.matcher(temp).matches()) {
								args[i] = Long.valueOf(temp);
								parameterTypes[i] = long.class;
							}
						} else if(pattern.matcher(arg).matches()) {
							args[i] = Integer.valueOf(arg);
							parameterTypes[i] = int.class;
						}
						if(args[i]==null) {
							args[i] = arg;
							parameterTypes[i] = String.class;
						}
					}
					method = CourseView.class.getDeclaredMethod(methodName, parameterTypes);
					break;
				}
			} catch(Exception e) {
				LogUnit.e("Action:"+action, e);
			}
		}
		
		public void invoke(CourseView receiver) {
			try {
				if(method!=null) {
					method.setAccessible(true);
					if(args==null) method.invoke(receiver);
					else method.invoke(receiver, args);
				}
			} catch(Exception e) {
				if(e instanceof InvocationTargetException) e = (Exception)((InvocationTargetException)e).getTargetException();
				LogUnit.e("Action invoke "+method.getName()+" "+Arrays.toString(args), e);
				receiver.disable();
				receiver.hide();
				GameStatusManger.getInstance().setStatusCurrent(Status.MENU);
			}
		}
	}
}
