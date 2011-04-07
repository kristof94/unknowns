package cn.gov.ydstats.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesUtil {
	private static HashMap<String, String> map;
	private static File file = null;
	private static long lastUpdateTime = 0L;
	static {
		try {
			file = new File(PropertiesUtil.class.getClassLoader().getResource("config.properties").toURI());
		} catch(URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		if(key==null) return null;
		update();
		return map.get(key);
	}
	
	private static void update() {
		FileInputStream in = null;
		try {
			long lastModified = file.lastModified();
			if(lastUpdateTime!=lastModified) {
				in = new FileInputStream(file);
				Properties p = new Properties();
				p.load(in);
				in.close();
				HashMap<String, String> temp = new HashMap<String, String>();
				Iterator iter = p.entrySet().iterator();
				while(iter.hasNext()) {
					Entry e = (Entry)iter.next();
					temp.put(e.getKey().toString(), e.getValue().toString());
				}
				map = temp;
				lastUpdateTime = lastModified;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in!=null) try {in.close();} catch(IOException e) {}
		}
	}
	
}
