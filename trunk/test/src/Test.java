import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptEngineManager;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnConnectionPNames;
import org.apache.http.conn.params.ConnConnectionParamBean;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteInfo;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.RoutedRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXWriter;
import org.dom4j.io.XMLWriter;


class T implements Runnable {
	@Override
	public void run() {
		try {
			Thread.sleep(1000L);
		} catch(InterruptedException e1) {
			e1.printStackTrace();
		}
		for(int i = 0; i<100; i++) {
			try {
				URL url = new URL("http://192.168.1.139/num/uareveice?sid=123&cid=123&verifycode=12345678abcdef&pin=bW89JnVhPU9wZXJhJnA9YWM1ZmE0NzBjNDIxMDJkNGQ0NWM4N2NiOWI4NjZjNTA=");
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.connect();
				conn.getInputStream().close();
				conn.disconnect();
			} catch(MalformedURLException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}

public class Test {
	private static Pattern pattern = Pattern.compile(".*<hRet>(.*)</hRet>.*");

	private static void gbk2utf8(File dir, FileFilter filter) throws Exception {
		for(File file:dir.listFiles(filter)) {
			if(file.isDirectory()) {
				gbk2utf8(file, filter);
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
				StringBuilder sb = new StringBuilder();
				String line;
				while((line=in.readLine())!=null) {
					sb.append(line);
					sb.append("\r\n");
				}
				in.close();
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
				out.write(sb.toString());
				out.close();
			}
		}
	}
	
	private static void test(File file) throws Exception {
		System.out.println(file);
		sb.append(file.getName().replace("proxy-info-2011-09-20-", "").replace(".log", "点"));
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		int count = 0, success = 0, fail = 0;
		while((line=in.readLine())!=null) {
			if(line.contains("<msgType>BuyGameToolResp</msgType>")) {
				count++;
				Matcher m = pattern.matcher(line);
				if(m.find()) {
					String s = m.group(1);
					if("0".equals(s)) {
						success++;
					} else if("1".equals(s)) {
						fail++;
					}
				}
			}
//			Matcher m = pattern.matcher(line);
//			if(m.find()) {
//				String ip = m.group(1);
//				Long count = map.get(ip);
//				if(count==null) count = 0L;
//				map.put(ip, ++count);
//			}
		}
		in.close();
		sb.append("共计费");
		sb.append(count);
		sb.append("次，成功");
		sb.append(success);
		sb.append("次，失败");
		sb.append(fail);
		sb.append("\r\n");
	}
	

	private static Map<String, String> gm = new HashMap<String, String>();
	private static Map<String, String> om = new HashMap<String, String>();
	static {
		gm.put("g+i游戏", "搜狐");
		gm.put("g+暴风城", "动力创想");
		gm.put("g+电影大片", "掌中米格");
		gm.put("g+好又多", "盈正");
		gm.put("g+嘉年华", "摩瑞贝");
		gm.put("g+尽情玩吧", "兆荣联合");
		gm.put("g+经典游戏", "新浪");
		gm.put("g+开心畅游", "掌趣");
		gm.put("g+热门游戏-热舞天使", "空中信使");
		gm.put("g+热门游戏T", "空中信使");
		gm.put("g+热门游戏", "雷霆万钧");
		gm.put("g+私藏经典", "秦网");
		gm.put("g+旺旺包", "易动无限");
		gm.put("g+新潮流", "申达宏通");
		gm.put("g+游戏狂人", "丰尚佳诚");
		gm.put("g+游戏大作", "掌中地带");
		gm.put("g+游戏发烧包", "掌趣");
		gm.put("g+游戏盒子", "空中信使");
		gm.put("g+游戏达人", "摩讯");
		gm.put("g+游戏区", "因特莱斯");
		gm.put("g+最佳游戏", "中西网联");
		gm.put("g+游戏精选", "丰尚佳诚");
		gm.put("g+精选合集", "新浪");
		gm.put("g+休闲部落", "搜狐");
		gm.put("g+掌上乐园", "广州诠星网络科技");
		gm.put("g+游戏大本营", "广州蓝喜鹊");
	}
	
	private static StringBuilder sb = new StringBuilder();
	public strictfp static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\G+.csv"), "GBK"));
		Map<String, Long> map = new LinkedHashMap<String, Long>();
		String line;
		while((line=in.readLine())!=null) {
			String[] s = line.split(",");
			if(s.length==4) {
				if(!"0".equals(s[3])) {
					String key = s[1]+","+s[2];
					Long l = map.get(key);
					if(l==null) l = 0L;
					map.put(key, l+Long.parseLong(s[3]));
				}
			} else {
				System.out.println("无效记录："+line);
			}
		}
		in.close();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\G+收入.csv"), "GBK"));
		Iterator<Entry<String, Long>> i = map.entrySet().iterator();
		while(i.hasNext()) {
			Entry<String, Long> e = i.next();
			String[] s = e.getKey().split(",");
			out.write(s[0]);
    		if(gm.containsKey(s[0])) {
				out.write('（');
				out.write(gm.get(s[0]));
				out.write('）');
			}
    		out.write(',');
    		out.write(s[1]);
    		out.write(',');
    		out.write(e.getValue().toString());
    		out.write("\r\n");
		}
		out.close();
//		Map<String, List<String[]>> map = new TreeMap<String, List<String[]>>();
//		String line;
//		while((line=in.readLine())!=null) {
//			String[] s = line.split(",");
//			if(s.length==4) {
//				if(!"0".equals(s[3])) {
//					List<String[]> list = map.get(s[0]);
//					if(list==null) {
//						list = new ArrayList<String[]>(1000);
//						map.put(s[0], list);
//					}
//					list.add(s);
//				}
//			} else {
//				System.out.println("无效记录："+line);
//			}
//		}
//		in.close();
//		Map<String, Double> sum = new HashMap<String, Double>();
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\网游排序.csv"), "GBK"));
//		Iterator<Entry<String, List<String[]>>> i = map.entrySet().iterator();
//		while(i.hasNext()) {
//			Entry<String, List<String[]>> e = i.next();
//			String date = e.getKey();
//			Map<String, Double> data = new HashMap<String, Double>();
//			Map<String, List<Object[]>> log = new HashMap<String, List<Object[]>>();
//			List list = e.getValue();
//			Iterator<String[]> i2 = list.iterator();
//			while(i2.hasNext()) {
//				String[] s = i2.next();
//				String key = s[1];
//				List<Object[]> list2 = log.get(key);
//				if(list2==null) {
//					list2 = new ArrayList<Object[]>();
//					log.put(key, list2);
//				}
//				list2.add(new Object[]{s[2], Double.parseDouble(s[3])});
//				Double l = data.get(key);
//				if(l==null) l = 0D;
//				BigDecimal bd = new BigDecimal(s[3]);
//				data.put(key, bd.add(new BigDecimal(l.toString())).doubleValue());
//				Double t = sum.get(key);
//				if(t==null) t = 0D;
//				BigDecimal td = new BigDecimal(s[3]);
//				sum.put(key, td.add(new BigDecimal(t.toString())).doubleValue());
//			}
//			Object[][] o = new Object[data.size()][2];
//			Iterator<Entry<String, Double>> i3 = data.entrySet().iterator();
//			int l = 0;
//			while(i3.hasNext()) {
//				Entry<String, Double> e3 = i3.next();
//				o[l][0] = e3.getKey();
//				o[l][1] = e3.getValue();
//				l++;
//			}
//	    	for(l=0;l<o.length-1;l++){
//	    		for(int j=0;j<o.length-l-1;j++){
//	    			if((Double)o[j][1]<(Double)o[j+1][1]){
//	    				Object[] temp = o[j];
//	    				o[j] = o[j+1];
//	    				o[j+1] = temp;
//	    			}
//	    		}
//	    	}
//	    	StringBuilder sb = new StringBuilder();
//	    	sb.append(date);
//	    	sb.append(':');
//	    	sb.append('{');
//	    	for(l=0;l<o.length;l++) {
//	    		if(l>0) sb.append(',');
//	    		sb.append('[');
//	    		sb.append(o[l][0]);
//	    		sb.append(',');
//	    		sb.append(o[l][1]);
//	    		sb.append(']');
//	    		
//	    	}
//	    	sb.append('}');
//	    	System.out.println(sb.toString());
//	    	for(l=0;l<o.length;l++) {
//	    		Object[] d = o[l];
//	    		Iterator<Object[]> i4 = log.get(d[0]).iterator();
//	    		Object[][] sss = new Object[log.get(d[0]).size()][2];
//				int k = 0;
//				while(i4.hasNext()) {
//					Object[] ss = i4.next();
//					sss[k++] = ss;
//				}
//		    	for(k=0;k<sss.length-1;k++){
//		    		for(int j=0;j<sss.length-k-1;j++){
//		    			if((Double)sss[j][1]<(Double)sss[j+1][1]){
//		    				Object[] temp = sss[j];
//		    				sss[j] = sss[j+1];
//		    				sss[j+1] = temp;
//		    			}
//		    		}
//		    	}
//	    		out.write(date);
//	    		out.write(',');
//	    		out.write(d[0].toString());
//	    		if(gm.containsKey(d[0])) {
//	    			out.write('（');
//	    			out.write(gm.get(d[0]));
//	    			out.write('）');
//	    		}
//	    		out.write(',');
//	    		out.write("总计");
//	    		out.write(',');
//	    		out.write(data.get(d[0]).toString());
//	    		out.write("\r\n");
//		    	for(k=0;k<sss.length;k++) {
//	    			Object[] ss = sss[k];
//		    		out.write(date);
//		    		out.write(',');
//		    		out.write(d[0].toString());
//		    		if(gm.containsKey(d[0])) {
//		    			out.write('（');
//		    			out.write(gm.get(d[0]));
//		    			out.write('）');
//		    		}
//		    		out.write(',');
//		    		out.write(ss[0].toString());
//		    		out.write(',');
//		    		out.write(ss[1].toString());
//		    		out.write("\r\n");
//		    	}
//	    	}
//		}
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\网游总排序.csv"), "GBK"));
//		Iterator<Entry<String, Double>> ii = sum.entrySet().iterator();
//		while(ii.hasNext()) {
//			Entry<String, Double> e = ii.next();
//			out.write(e.getKey());
//			out.write(',');
//			out.write(e.getValue().toString());
//			out.write("\r\n");
//		}
//		out.close();
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("g+i游戏(搜狐)", "500231875000");
//		map.put("g+暴风城(动力创想)", "500231874000");
//		map.put("g＋电影大片(掌中米格)", "500231868000");
//		map.put("g+好又多(盈正)", "500231876000");
//		map.put("g+欢乐游戏包-动漫大赛", "500231894000");
//		map.put("g+嘉年华(摩瑞贝)", "500231878000");
//		map.put("g+尽情玩吧(兆荣联合)", "500231877000");
//		map.put("g+经典游戏(新浪)", "500231870000");
//		map.put("g+精选合集（新浪）", "500231911000");
//		map.put("g+开心畅游(掌趣)", "500231899000");
//		map.put("g+热门游戏(雷霆万钧)", "500231872000");
//		map.put("g+热门游戏T（空中信使）", "500231880000");
//		map.put("g+私藏经典(秦网)", "500231869000");
//		map.put("g+旺旺包(易动无限)", "500231873000");
//		map.put("g+新潮流(申达宏通)", "500231901000");
//		map.put("g+休闲部落（搜狐）", "700011993000");
//		map.put("g+游戏达人(摩讯)", "500231900000");
//		map.put("g+游戏大本营(广州蓝喜鹊)", "700011899000");
//		map.put("g+游戏大作(掌中地带)", "500231902000");
//		map.put("g+游戏发烧包(掌趣)", "500231881000");
//		map.put("g+游戏盒子(空中信使)", "500231898000");
//		map.put("g+游戏精选（丰尚佳诚）", "700012000000");
//		map.put("g+游戏狂人(丰尚佳诚)", "500231882000");
//		map.put("g+游戏区(因特莱斯)", "500231879000");
//		map.put("g+最佳游戏(中西网联)", "500231871000");
//		map.put("创业游戏包", "700013200000");
//		map.put("滚石30年游戏包", "500231907000");
//		map.put("游戏玩家", "500230544000");
//		map.put("游戏玩家动感版", "500231883000");
//		map.put("游戏玩家欢乐版", "500231905000");
//		map.put("游戏玩家至尊版", "500231906000");
//		Map<String,BufferedWriter> m = new HashMap<String, BufferedWriter>();
//		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
//		while(iterator.hasNext()) {
//			Entry<String, String> e = iterator.next();
//			m.put(e.getValue(), new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\G+\\"+e.getKey()+".csv"), "GBK")));
//		}
//		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\39576_20111207.csv"), "GBK"));
//		String line = null;
//		while((line=in.readLine())!=null) {
//			if(line.length()==0) continue;
//			String[] s = line.split(",");
//			BufferedWriter out = m.get(s[4]);
//			if(out==null) out = m.get(s[5]);
//			if(out!=null) {
//				out.write(s[0]);
//				out.write(',');
//				out.write(s[1]);
//				out.write(',');
//				out.write(s[2]);
//				out.newLine();
//			} else {
//				System.out.println("无法找到对应文件："+line);
//			}
//		}
//		in.close();
//		Iterator<Entry<String, BufferedWriter>> i = m.entrySet().iterator();
//		while(i.hasNext()) {
//			Entry<String, BufferedWriter> e = i.next();
//			e.getValue().close();
//		}
//		gbk2utf8(new File("D:\\workspaces\\Copy of spread\\src"), new FileFilter() {
//			@Override
//			public boolean accept(File pathname) {
//				return pathname.isDirectory() || pathname.getName().endsWith("java");
//			}
//		});
//		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\搜狗颜文字.txt"), "Unicode"));
//		String line = null;
//		Set<String> t = new TreeSet<String>();
//		while((line=in.readLine())!=null) {
//			if(line.length()==0) continue;
//			System.out.println(line.trim());
//			t.add(line.trim());
//		}
//		in.close();
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\颜文字.txt"), "UTF-8"));
//		Iterator<String> iterator = t.iterator();
//		while(iterator.hasNext()) {
//			out.write(iterator.next());
//			out.newLine();
//		}
//		out.close();
//		Pattern p = Pattern.compile("(江苏|河北|湖北|江西)");
//		Map<String, String> m = new HashMap<String, String>();
//		m.put("g+i游戏", "搜狐");
//		m.put("g+暴风城", "动力创想");
//		m.put("g+电影大片", "掌中米格");
//		m.put("g+好又多", "盈正");
//		m.put("g+嘉年华", "摩瑞贝");
//		m.put("g+尽情玩吧", "兆荣联合");
//		m.put("g+经典游戏", "新浪");
//		m.put("g+开心畅游", "掌趣");
//		m.put("g+热门游戏", "空中信使");
//		m.put("g+热门游戏T", "空中信使");
//		m.put("g+热门游戏", "雷霆万钧");
//		m.put("g+私藏经典", "秦网");
//		m.put("g+旺旺包", "易动无限");
//		m.put("g+新潮流", "申达宏通");
//		m.put("g+游戏狂人", "摩讯");
//		m.put("g+游戏大作", "掌中地带");
//		m.put("g+游戏发烧包", "掌趣");
//		m.put("g+游戏盒子", "空中信使");
//		m.put("g+游戏达人", "丰尚佳诚");
//		m.put("g+游戏区", "因特莱斯");
//		m.put("g+最佳游戏", "中西网联");
//		m.put("g+游戏精选", "丰尚佳诚");
//		m.put("g+精选合集", "新浪");
//		m.put("g+休闲部落", "搜狐");
//		m.put("g+掌上乐园", "广州诠星网络科技");
//		m.put("g+游戏大本营", "广州蓝喜鹊");
//		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\省.csv"), "GBK"));
//		String line = null;
//		Map<String, Long> map = new HashMap<String, Long>();
//		while((line=in.readLine())!=null) {
//			if(line.length()==0) continue;
//			String[] s = line.split(",", 4);
//			if(s.length==4 && "广东".equals(s[2])) {
//				String key = s[2]+","+s[3];
//				Long l = map.get(key);
//				if(l==null) l = 1L;
//				map.put(key, l+1);
//			}
//		}
//		in.close();
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\省统计.csv"), "GBK"));
//		Iterator<Entry<String, Long>> iterator = map.entrySet().iterator();
//		while(iterator.hasNext()) {
//			Entry<String, Long> entry = iterator.next();
//			out.write(entry.getKey());
//			String v = m.get(entry.getKey().substring(entry.getKey().indexOf(',')+1));
//			if(v!=null) {
//				out.write("（");
//				out.write(v);
//				out.write("）");
//			}
//			out.write(',');
//			out.write(entry.getValue().toString());
//			out.newLine();
//		}
//		out.close();
//		手机号,归属省份,归属地市,时间,游戏包名称,套餐ID,SP名称,SP代码,渠道代码,
//		500231876000]
//		广东 湖北 河北 江西 江苏  四川
//		Map<String, BufferedWriter> map = new HashMap<String, BufferedWriter>();
//		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\38253_20111109.csv"), "GBK"));
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\省.csv"), "GBK"));
//		String line = null;
//		while((line=in.readLine())!=null) {
//			if(line.length()==0) continue;
//			String[] s = line.split(",", 9);
//			if(s.length>6 && p.matcher(s[1]).matches()) {
//				BufferedWriter out = map.get(s[5]);
//				if(out==null) {
//					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\"+s[4]+"_"+s[5]+".csv"), "GBK"));
//					map.put(s[5], out);
//				}
//				out.write(s[0]);
//				out.write(',');
//				out.write(s[2]);
//				out.write(',');
//				out.write(s[1]);
//				out.write(',');
//				out.write(s[4]);
//				out.newLine();
//			}
//		}
//		in.close();
//		out.close();
//		System.out.println(map.size());
//		Iterator<BufferedWriter> iterator = map.values().iterator();
//		while(iterator.hasNext()) {
//			iterator.next().close();
//		}
//		File[] files = new File("E:\\rich").listFiles();
//		sb.append("拇指大富翁计费数据：");
//		sb.append("\r\n");
//		for(File file:files) {
//			test(file);
//		}
//		System.out.println(sb.toString());
//		System.out.println("write ip");
//		Iterator<Entry<String, Long>> i = map.entrySet().iterator();
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("F:\\ip.txt")));
//		while(i.hasNext()) {
//			Entry<String, Long> e = i.next();
//			out.write(e.getKey());
//			out.write('=');
//			out.write(e.getValue().toString());
//			out.newLine();
//		}
//		out.close();
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection c = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/test", "test", "test");
//		PreparedStatement p = c.prepareStatement("select province,city,isp from IP_INFO where start_ip<=inet_aton(?) and end_ip>=inet_aton(?) order by city desc,isp desc");
//		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\ip.txt")));
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("F:\\result.csv"), "GBK"));
//		String line;
////		long total = 0;
//		while((line=in.readLine())!=null) {
//			String s[] = line.split("=", 2);
//			String ip = s[0];
//			p.setString(1, ip);
//			p.setString(2, ip);
//			ResultSet r = p.executeQuery();
//			String q = null;
//			if(r.next()) {
//				q = r.getString(1)+","+r.getString(2)+","+r.getString(3);
//			}
//			if(q==null) out.write(ip+",未知,未知,未知,"+s[1]);
//			else out.write(ip+","+q+","+s[1]);
//			out.newLine();
//			r.close();
////			long l = ip2long(ip);
////			Iterator<long[]> iter = list.iterator();
////			System.out.println(l);
////			while(iter.hasNext()) {
////				long[] ll = iter.next();
////				if(l>=ll[0] && l<=ll[1]) {
////					String count = s[1];
////					System.out.println(ip+"="+count);
////					total+=Long.parseLong(count);
////					break;
////				}
////			}
//			
//		}
//		out.close();
//		in.close();
//		p.close();
//		c.close();
//		System.out.println("惠州："+total);
//		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\6.27-7.3-G+.csv"), "GBK"));
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\6.27-7.3-G+汇总.csv"), "GBK"));
//		List<String> l = Arrays.asList(new String[]{"辽宁","吉林","黑龙江","山东","山西","河南","河北","内蒙古","陕西"});
//		Map<String, String> match = new HashMap<String, String>();
//		match.put("g+i游戏", "搜狐");
//		match.put("g+暴风城", "动力创想");
//		match.put("g+电影大片", "掌中米格");
//		match.put("g+好又多", "盈正");
//		match.put("g+嘉年华", "摩瑞贝");
//		match.put("g+尽情玩吧", "兆荣联合");
//		match.put("g+经典游戏", "新浪");
//		match.put("g+开心畅游", "掌趣");
//		match.put("g+热门游戏-热舞天使", "空中信使");
//		match.put("g+热门游戏", "雷霆万钧");
//		match.put("g+私藏经典", "秦网");
//		match.put("g+旺旺包", "易动无限");
//		match.put("g+新潮流", "申达宏通");
//		match.put("g+游戏狂人", "摩讯");
//		match.put("g+游戏大作", "掌中地带");
//		match.put("g+游戏发烧包", "掌趣");
//		match.put("g+游戏盒子", "空中信使");
//		match.put("g+游戏达人", "丰尚佳诚");
//		match.put("g+游戏区", "因特莱斯");
//		match.put("g+最佳游戏", "中西网联");
//		Map<String, Long> map = new TreeMap<String, Long>();
//		Map<String, Double> dmap = new HashMap<String, Double>();
//		String line;
//		long total = 0L;
//		while((line=in.readLine())!=null) {if(line.length()==0) continue; total += Long.parseLong(line.substring(line.lastIndexOf(',')+1));}
//		in.close();
//		in = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\6.27-7.3-G+.csv"), "GBK"));
//		out.write("6.27-7.3-G+全部游戏包的总收入,");
//		out.write(Long.toString(total));
//		out.newLine();
//		while((line=in.readLine())!=null) {
//			if(line.length()==0) continue;
//			String[] s = line.split(",", 3);
//			if(l.contains(s[1])) {
//				Iterator<String> i = match.keySet().iterator();
//				while(i.hasNext()) {
//					String m = i.next();
//					if(s[0].startsWith(m)) {
//						s[0] = s[0]+"（"+match.get(m)+"）";
//						break;
//					}
//				}
//				String key = s[0]+","+s[1];
//				String key = s[1];
//				Long count = map.get(key);
//				if(count==null) count = 0L;
//				count = count+Long.parseLong(s[2]);
//				map.put(key, count);
//				dmap.put(key, count*100D/total);
//			}
//			if(!line.contains(",是,")) {
//				out.append(line);
//				out.newLine();
//			}
//			String[] s = line.split(",");
//			String key = s[0].trim()+","+s[1].trim()+","+s[14].trim();
//			Integer count = map.get(key);
//			if(count==null) {
//				count = 0;
//			}
//			map.put(key, count+1);
//			if(line.startsWith("\"500231876000\"")) {
//				out.write(line);
//				out.newLine();
//			}
//			if(line.contains("广东")) {
//				out.write(line);
//				out.newLine();
//			}
//			int index = line.lastIndexOf(',')+1;
//			if(index>0) {
//				String key = line.substring(0, index);
//				Double count = map.get(key);
//				if(count==null) count = 0d;
//				map.put(key, count+Double.parseDouble(line.substring(index)));
//			}
//			out.write(line.substring(0, 4));
//			out.write('-');
//			out.write(line.substring(4, 6));
//			out.write('-');
//			out.write(line.substring(6, 8));
//			out.write(' ');
//			out.write(line.substring(8, 10));
//			out.write(':');
//			out.write(line.substring(10, 12));
//			out.write(':');
//			out.write(line.substring(12, 14));
//			out.write(',');
//			out.write(line.substring(15));
//			out.newLine();
//		}
//		Iterator<Entry<String, Double>> iterator = map.entrySet().iterator();
//		while(iterator.hasNext()) {
//			Entry<String, Double> entry = iterator.next();
//			out.write(entry.getKey());
//			out.write(entry.getValue().toString());
//			out.newLine();
//		}
//		in.close();
//		Iterator<Entry<String, Long>> i = map.entrySet().iterator();
//		DecimalFormat f = new DecimalFormat("0.00");
//		while(i.hasNext()) {
//			Entry<String, Long> e = i.next();
//			out.write(e.getKey());
//			out.write(',');
//			out.write(e.getValue().toString());
//			out.write(',');
//			out.write(f.format(dmap.get(e.getKey())));
//			out.write('%');
//			out.newLine();
//		}
//		out.close();
//		Iterator<Entry<String, Integer>> i = map.entrySet().iterator();
//		while(i.hasNext()) {
//			Entry<String, Integer> e = i.next();
//			System.out.println(e.getKey()+","+e.getValue());
//		}
	}
	
	public static long ip2long(String ip) {  
	    String[] ips = ip.split("[.]");  
	    long num =  16777216L*Long.parseLong(ips[0]) + 65536L*Long.parseLong(ips[1]) + 256*Long.parseLong(ips[2]) + Long.parseLong(ips[3]);  
	    return num;  
	}  
	
	public static void bd() throws Exception {
		String path = "C:/Documents and Settings/Administrator/桌面/Civilization_T320X480";
		ClassPool cp = ClassPool.getDefault();
		cp.insertClassPath(path);
		CtClass cc = cp.get("bd");
		cc.addField(CtField.make("private long crack = System.currentTimeMillis();", cc));
		for(CtMethod cm:cc.getDeclaredMethods()) {
			String str = "{System.out.println(\"crack:"+Modifier.toString(cm.getModifiers())+ " "  + cm.getName() + cm.getMethodInfo().getDescriptor()+":\"+System.currentTimeMillis());}";
			cm.insertBefore(str);
			System.out.println(str);
			String methodInfo = cm.getMethodInfo().toString();
			System.out.println(methodInfo);
			if("a (Lcf;)Z".equals(methodInfo)) {
				StringBuilder sb = new StringBuilder();
				sb.append("long time = 2000 - System.currentTimeMillis() + crack;");
				sb.append("System.out.println(\"crack:\"+time);");
				sb.append("if(time>0) Thread.sleep(time);");
				sb.append("crack = System.currentTimeMillis();");
				cm.insertBefore(sb.toString());
				System.out.println(sb.toString());
			}
//			cm.insertBefore(str);
		}
//		cm.getMethodInfo().rebuildStackMap(cp);
		cc.writeFile();
	}
	
	
	public static void bb() throws Exception {
		String path = "C:/Documents and Settings/Administrator/桌面/Civilization_T320X480";
		ClassPool cp = ClassPool.getDefault();
		cp.insertClassPath(path);
		CtClass cc = cp.get("bb");
		cc.addField(CtField.make("private long crack = System.currentTimeMillis();", cc));
		for(CtMethod cm:cc.getDeclaredMethods()) {
			String str = "{System.out.println(\"crack:"+Modifier.toString(cm.getModifiers())+ " "  + cm.getName() + cm.getMethodInfo().getDescriptor()+":\"+System.currentTimeMillis());}";
			cm.insertBefore(str);
			System.out.println(str);
			String methodInfo = cm.getMethodInfo().toString();
			System.out.println(methodInfo);
//			if("a ()Z".equals(methodInfo) || "a (Z)Ljava/io/DataOutputStream;".equals(methodInfo)) {
//				StringBuilder sb = new StringBuilder();
//				sb.append("long time = 1000 - System.currentTimeMillis() + crack;");
//				sb.append("System.out.println(\"crack:\"+time);");
//				sb.append("if(time>0) Thread.sleep(time);");
//				sb.append("crack = System.currentTimeMillis();");
//				cm.insertBefore(sb.toString());
//				System.out.println(sb.toString());
//			}
//			cm.insertBefore(str);
		}
//		cm.getMethodInfo().rebuildStackMap(cp);
		cc.writeFile();
	}
	
	public static void testCalculator() {
		Calculator c = new Calculator();
		c.appendNumber(3);
		c.inverse();
		c.operator('+');
		c.appendNumber(2);
		System.out.println(c.equal());
	}
	
	public static void testClient() throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://cnota.cn/g.jsp?18500_895");
		HttpResponse response = client.execute(request);
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if(entity != null) {
			System.out.println(EntityUtils.toString(entity));
		}
		request.abort();
		client.getConnectionManager().shutdown();
	}
	
	public static void testPost() throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost("http://192.168.1.139:8080/activate_notify/log?gid=1&mt=3");
		HttpEntity entity = new StringEntity("900");
		request.setEntity(entity);
		client.execute(request);
	}
}

