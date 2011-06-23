package org.hld.fab;

import static org.hld.fab.MiscUtil.closeInputStream;
import static org.hld.fab.MiscUtil.closeOutputStream;
import static org.hld.fab.MiscUtil.err;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;
import android.content.Context;
import android.util.Xml;

public class TypeManage {
	private static Map<String, List<String>> typeMap = null;
	private static final String TYPE_FILE_NAME = "type.xml";
	
	public static void init(Context context) {
		File file = new File(PreferencesManage.getDataDir(), TYPE_FILE_NAME);
		if(file.isFile()) {
			BufferedInputStream in = null;
			try {
				in = new BufferedInputStream(new FileInputStream(file));
				XmlPullParser xml = XmlPullParserFactory.newInstance().newPullParser();
				xml.setInput(in, "UTF-8");
				loadType(xml);
			} catch(Exception e) {
				err("无法解析XML文件："+file.getAbsolutePath(), e);
			} finally {
				closeInputStream(in);
			}
		}
		if(typeMap==null) {
			loadType(context.getResources().getXml(R.xml.type));
		}
	}
	
	/**
	 * 获取所有分类信息
	 * @return
	 */
	public static Map<String, List<String>> getTypeMap() {
		return typeMap;
	}
	
	/**
	 * 加载分类文件
	 * @param context
	 * @return
	 */
	private static boolean loadType(XmlPullParser xml) {
		if(xml==null) return false;
		Map<String, List<String>> temp = new LinkedHashMap<String, List<String>>();
		try {
			String parent = null;
			loop:while(true) {
				switch(xml.next()) {
				case XmlPullParser.START_TAG:
					switch(xml.getDepth()) {
					case 2:
						parent = xml.getName();
						temp.put(parent, new LinkedList<String>());
						break;
					case 3:
						temp.get(parent).add(xml.getName());
						break;
					}
					break;
				case XmlPullParser.END_TAG:
					if(xml.getDepth()==2) temp.put(parent, Collections.unmodifiableList(temp.get(parent)));
					break;
				case XmlPullParser.END_DOCUMENT:
					break loop;
				}
			}
			typeMap = Collections.unmodifiableMap(temp);
			return true;
		} catch(Exception e) {
			err("无法解析XML文件："+xml, e);
		}
		return false;
	}
	
	/**
	 * 增加分类
	 * @return
	 */
	public static boolean addType(String master, String slave) {
		File file = new File(PreferencesManage.getDataDir(), TYPE_FILE_NAME);
		if(file.getParentFile().isDirectory()) {
			BufferedOutputStream out = null;
			try {
				out = new BufferedOutputStream(new FileOutputStream(file));
				XmlSerializer xml = Xml.newSerializer();
				xml.setOutput(out, "UTF-8");
				xml.startDocument("UTF-8", null);
				xml.startTag("", "typelist");
				Iterator<Entry<String, List<String>>> iterator = typeMap.entrySet().iterator();
				Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
				boolean flag = true;
				while(iterator.hasNext()) {
					Entry<String, List<String>> entry = iterator.next();
					String key = entry.getKey();
					List<String> list = new LinkedList<String>();
					map.put(key, list);
					xml.startTag("", key);
					for(String v:entry.getValue()) {
						list.add(v);
						xml.startTag("", v).endTag("", v);
					}
					if(flag && key.equals(master)) {
						list.add(slave);
						xml.startTag("", slave).endTag("", slave);
						flag = false;
					}
					xml.endTag("", key);
				}
				if(flag) {
					List<String> list = new LinkedList<String>();
					list.add(slave);
					map.put(master, list);
					xml.startTag("", master).startTag("", slave).endTag("", slave).endTag("", master);
				}
				xml.endTag("", "typelist");
				xml.endDocument();
				typeMap = Collections.unmodifiableMap(map);
				return true;
			} catch(Exception e) {
				err("保存分类文件时出错", e);
			} finally {
				closeOutputStream(out);
			}
		}
		return false;
	}
}
