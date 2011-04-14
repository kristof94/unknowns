package cn.gov.ydstats.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TypeUtil {
	private static LinkedHashMap<String, String[]> typeMap = new LinkedHashMap<String, String[]>();
	private static HashMap<String, String> typeName = new HashMap<String, String>();
	private static File file = null;
	private static long lastUpdateTime = 0L;
	static {
		try {
			file = new File(TypeUtil.class.getClassLoader().getResource("type.txt").toURI());
		} catch(URISyntaxException e) {
			e.printStackTrace();
		}
		update();
	}

	public static Map getTypeMap() {
		update();
		return typeMap;
	}

	public static Map getTypeName() {
		update();
		return typeName;
	}
	
	public static String[] getSlaveType(String masterType) {
		update();
		return typeMap.get(masterType);
	}
	
	public static boolean containsName(String type) {
		update();
		return typeName.containsKey(type);
	}
	
	public static String getName(String type) {
		update();
		return typeName.get(type);
	}
	
	private static void update() {
		BufferedReader in = null;
		try {
			long lastModified = file.lastModified();
			if(lastUpdateTime!=lastModified) {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				LinkedHashMap<String, String[]> tempTypeMap = new LinkedHashMap<String, String[]>();
				HashMap<String, String> tempTypeName = new HashMap<String, String>();
				String root = in.readLine();
				String[] empty = new String[]{};
				for(String type:root.split(",")) tempTypeMap.put(type, empty);
				String temp;
				while((temp=in.readLine())!=null) {
					if(temp.startsWith("=")) break;
					String[] s = temp.split("=", 2);
					tempTypeMap.put(s[0], s[1].split(","));
				}
				while((temp=in.readLine())!=null) {
					String[] s = temp.split("=", 2);
					if(s.length==2) tempTypeName.put(s[0], s[1]);
				}
				typeMap = tempTypeMap;
				typeName = tempTypeName;
				lastUpdateTime = lastModified;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in!=null) try {in.close();} catch(IOException e) {}
		}
	}
	
}
