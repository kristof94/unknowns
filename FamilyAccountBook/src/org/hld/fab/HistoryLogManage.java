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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HistoryLogManage {
	public static Map currentData = null;
	
	/**
	 * 获取保存记录的文件
	 * @param log
	 * @return
	 */
	public static File getDataFile(HistoryLog log) {
		String date = log.getDate();
		if(date==null) return null;
		return getDataFile(date);
	}
	
	/**
	 * 获取保存记录的文件
	 * @param date yyyyMMdd格式的日期
	 * @return
	 */
	public static File getDataFile(String date) {
		return new File(PreferencesManage.getDataDir(), date.substring(0, 4)+File.separator+date.substring(4, 6)+File.separator+date.substring(6)+".dat");
	}
	
	/**
	 * 保存记录
	 * @param log
	 * @return 成功返回true，失败返回false
	 */
	public static boolean saveLog(HistoryLog log) {
		OutputStream out = null;
		try {
			File file = getDataFile(log);
			file.getParentFile().mkdirs();
			out = new FileOutputStream(file, true);
			if(log.getMaster()!=null) out.write(log.getMaster().getBytes("UTF-8"));
			out.write('\t');
			if(log.getSlave()!=null) out.write(log.getSlave().getBytes("UTF-8"));
			out.write('\t');
			out.write(log.getMoney().toString().getBytes());
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
	public static boolean saveLog(File file, List<HistoryLog> data) {
		if(data==null || data.isEmpty()) return deleteLog(file);
		OutputStream out = null;
		try {
			file.getParentFile().mkdirs();
			out = new FileOutputStream(file);
			for(HistoryLog log:data) {
				if(log.getMaster()!=null) out.write(log.getMaster().getBytes("UTF-8"));
				out.write('\t');
				if(log.getSlave()!=null) out.write(log.getSlave().getBytes("UTF-8"));
				out.write('\t');
				out.write(log.getMoney().toString().getBytes());
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
	 * @return
	 */
	public static List<HistoryLog> loadLog(File file) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"), 1024);
			List<HistoryLog> list = new LinkedList<HistoryLog>();
			HistoryLog log;
			String line;
			while((line=in.readLine())!=null) {
				String[] s = line.split("\\t");
				if(s.length==3) {
					log = new HistoryLog();
					log.setMaster(s[0]);
					log.setSlave(s[1]);
					log.setMoney(Double.valueOf(s[2]));
					list.add(log);
				}
			}
			return list;
		} catch(Exception e) {
			err("读取记录时出现意外错误", e);
			return null;
		} finally {
			closeReader(in);
		}
	}
	
	/**
	 * 删除当天所有记录
	 * @param file
	 * @return 成功返回true，失败返回false
	 */
	public static boolean deleteLog(File file) {
		return file.delete();
	}
	
}
