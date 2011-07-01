package org.hld.fab;

import static org.hld.fab.MiscUtil.closeOutputStream;
import static org.hld.fab.MiscUtil.closeReader;
import static org.hld.fab.MiscUtil.err;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BalanceLogManage {
	private static final File BALANCE_DATA_DIR = new File(PreferencesManage.getDataDir(), "balance");
	
	public static File getBalanceDataDir() {
		BALANCE_DATA_DIR.mkdirs();
		return BALANCE_DATA_DIR;
	}
	
	/**
	 * 获取保存记录的文件
	 * @param date yyyyMM格式的日期
	 * @return
	 */
	public static File getDataFile(String date) {
		return new File(getBalanceDataDir(), date.substring(0, 6)+".dat");
	}
	
	/**
	 * 保存记录
	 * @param date yyyyMMdd格式的日期
	 * @param balance
	 * @return 成功返回true，失败返回false
	 */
	public static boolean saveLog(String date, Double balance) {
		OutputStream out = null;
		try {
			DecimalFormat format = new DecimalFormat();
			format.setGroupingUsed(false);
			format.setDecimalSeparatorAlwaysShown(false);
			File file = getDataFile(date);
			file.getParentFile().mkdirs();
			out = new FileOutputStream(file, true);
			out.write(date.getBytes("UTF-8"));
			out.write('\t');
			out.write(format.format(balance).getBytes());
			out.write('\r');
			out.write('\n');
			return true;
		} catch(Exception e) {
			err("保存记录时出现意外错误", e);
			return false;
		} finally {
			closeOutputStream(out);
		}
	}
	
	/**
	 * 保存记录
	 * @param file
	 * @param data
	 * @return 成功返回true，失败返回false
	 */
	public static boolean saveLog(File file, Map<String, Double> data) {
		if(data==null || data.isEmpty()) return file.delete();
		OutputStream out = null;
		try {
			DecimalFormat format = new DecimalFormat();
			format.setGroupingUsed(false);
			format.setDecimalSeparatorAlwaysShown(false);
			file.getParentFile().mkdirs();
			out = new FileOutputStream(file);
			Iterator<Entry<String, Double>> iterator = data.entrySet().iterator();
			while(iterator.hasNext()) {
				Entry<String, Double> entry = iterator.next();
				out.write(entry.getKey().getBytes("UTF-8"));
				out.write('\t');
				out.write(format.format(entry.getValue()).getBytes());
				out.write('\r');
				out.write('\n');
			}
			return true;
		} catch(Exception e) {
			err("保存记录时出现意外错误", e);
			return false;
		} finally {
			closeOutputStream(out);
		}
	}
	
	/**
	 * 读取记录
	 * @param file
	 * @return Map<日期, 余额>
	 */
	public static Map<String, Double> loadLog(File file) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"), 1024);
			Map<String, Double> map = new TreeMap<String, Double>(new Comparator<String>() {
				@Override
				public int compare(String object1, String object2) {
					return -object1.compareTo(object2);
				}
			});
			String line;
			while((line=in.readLine())!=null) {
				String[] s = line.split("\\t");
				if(s.length==2) map.put(s[0], Double.valueOf(s[1]));
			}
			return map;
		} catch(Exception e) {
			err("读取记录时出现意外错误", e);
			return null;
		} finally {
			closeReader(in);
		}
	}
	
	/**
	 * 读取记录
	 * @param file
	 * @return Map<日期, 余额>
	 */
	public static void loadLog(File file, Map<String, String> result) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"), 1024);
			String line;
			while((line=in.readLine())!=null) {
				String[] s = line.split("\\t");
				if(s.length==2) result.put(s[0], s[1]);
			}
		} catch(Exception e) {
			err("读取记录时出现意外错误", e);
		} finally {
			closeReader(in);
		}
	}
	
	/**
	 * 删除指定日期的记录
	 * @param date yyyyMMdd格式的日期
	 * @return true为删除成功，false为删除失败
	 */
	public static boolean deleteLog(String date) {
		File file = getDataFile(date);
		BufferedReader in = null;
		boolean flag = false;
		List<String> list = new LinkedList<String>();
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"), 1024);
			String line;
			date += '\t';
			while((line=in.readLine())!=null) {
				if(line.startsWith(date)) flag = true;
				else list.add(line);
			}
		} catch(Exception e) {
			err("读取记录时出现意外错误", e);
		} finally {
			closeReader(in);
		}
		OutputStream out = null;
		if(flag) {
			if(list.isEmpty()) file.delete();
			else {
				try {
					out = new FileOutputStream(file);
					for(String line:list) {
						out.write(line.getBytes("UTF-8"));
						out.write('\r');
						out.write('\n');
					}
				} catch(Exception e) {
					err("保存记录时出现意外错误", e);
				} finally {
					closeOutputStream(out);
				}
			}
		}
		return flag;
	}
	
}
