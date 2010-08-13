package hld.coins.view;

import hld.coins.constants.GameStatusConstants.Status;
import hld.coins.manager.GameStatusManger;
import hld.coins.util.LogUnit;
import hld.coins.wrapper.Graphics;
import hld.coins.wrapper.Images;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;

public class CourseView extends MainView {
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
		currentLevel = 3;
		rules();
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
				resttimeout = System.currentTimeMillis()-time;
			} else {
				Action action = actionList.get(++location);
				frames = restframes = action.frames;
				timeout = resttimeout = action.timeout;
				if(timeout>0) time = System.currentTimeMillis();
			}
			this.graphics = null;
		} else {
			disable();
			hide();
			GameStatusManger.getInstance().setStatusCurrent(Status.MENU);
		}
	}
	
	private void rect(int x, int y, int width, int height) {
		graphics.drawRect(x, y, width, height);
	}
	
	private void arc(int x, int y, int width, int height, int startAngle, int sweepAngle) {
		graphics.drawArc(x, y, width, height, startAngle, sweepAngle);
	}
	
	private void move(int id, int x, int y) {
		Images coin = coins[id];
		Point point = points[id];
		x = offsetX(x);
		y = offsetY(y);
		if(frames>0) {
			int offset = frames*(frames-restframes);
			x = x-(x-point.x)/offset;
			y = y-(y-point.y)/offset;
		} else if(timeout>0) {
			long offset = timeout*(timeout-resttimeout);
			x = x-(x-point.x)/(int)offset;
			y = y-(y-point.y)/(int)offset;
		}
		graphics.drawImage(coin, x-coin.getWidth()/2, y-coin.getHeight()/2, isShowHelp?1:0);
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
									parameterTypes[i] = Long.class;
								}
							} else if(pattern.matcher(arg).matches()) {
								args[i] = Integer.valueOf(arg);
								parameterTypes[i] = Integer.class;
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
								parameterTypes[i] = Long.class;
							}
						} else if(pattern.matcher(arg).matches()) {
							args[i] = Integer.valueOf(arg);
							parameterTypes[i] = Integer.class;
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
				LogUnit.e("Action invoke "+method.getName()+" "+Arrays.toString(args), e);
				receiver.disable();
				receiver.hide();
				GameStatusManger.getInstance().setStatusCurrent(Status.MENU);
			}
		}
	}
}
