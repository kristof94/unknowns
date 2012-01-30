import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class FamilyAccountBookUtil {
	
	private static Map<String, String[]> map = new HashMap<String, String[]>();
	static {
		add("早餐", "伙食", "早餐");
		add("早餐 ", "伙食", "早餐");
		add("中餐", "伙食", "中餐");
		add("午餐", "伙食", "中餐");
		add("晚餐", "伙食", "晚餐");
		add("夜宵", "伙食", "宵夜");
		add("宵夜", "伙食", "宵夜");
		add("押金", "缴费", "押金");
		add("房租", "缴费", "房租水电");
		add("房租 ", "缴费", "房租水电");
		add("居住证", "缴费", "居住证");
		add("手机话费", "缴费", "话费");
		add("话费", "缴费", "话费");
		add("班费", "缴费", "班费");
		add("宽带", "缴费", "宽带");
		add("GPRS", "缴费", "GPRS");
		add("学费", "缴费", "学费");
//		add("羊城通", "缴费", "羊城通");
		add("水果", "日常生活", "水果");
		add("零食", "日常生活", "零食");
		add("糖水", "日常生活", "零食");
		add("文具", "日常生活", "文具");
		add("笔", "日常生活", "文具");
		add("报纸", "日常生活", "报纸");
		add("水", "日常生活", "水");
		add("水 ", "日常生活", "水");
		add("水  ", "日常生活", "水");
		add("日常用品", "日常生活", "");
		add("生活用品", "日常生活", "");
		add("鞋子", "日常生活", "鞋子");
		add("衣服", "日常生活", "衣服");
		add("剪头发", "日常生活", "剪头发");
		add("擦鞋", "日常生活", "擦鞋");
		add("看病", "日常生活", "医药费");
		add("医药费", "日常生活", "医药费");
		add("药费", "日常生活", "医药费");
		add("挂号", "日常生活", "医药费");
		add("药", "日常生活", "医药费");
		add("路费", "路费", "");
		add("车票", "路费", "火车");
		add("车费", "路费", "公交");
		add("的士", "路费", "的士");
		add("打的", "路费", "的士");
		add("汽车票", "路费", "汽车");
		add("火车票", "路费", "火车");
		add("公交 ", "路费", "公交");
		add("公交", "路费", "公交");
		add("坐车", "路费", "公交");
		add("摩托", "路费", "摩托");
		add("地铁", "路费", "地铁");
		add("三国杀", "娱乐", "三国杀");
		add("电影", "娱乐", "电影");
		add("书", "娱乐", "");
		add("娱乐", "娱乐", "");
		add("打机", "娱乐", "机室");
		add("漫展", "娱乐", "漫展");
		add("门票", "娱乐", "漫展");
		add("看电影", "娱乐", "电影");
		add("游戏", "娱乐", "游戏");
		add("上网", "娱乐", "网吧");
		add("点卡", "娱乐", "魔兽世界");
		add("点卡 ", "娱乐", "魔兽世界");
		add("买手机", "数码", "手机");
		add("转接线", "数码", "转接线");
		add("耳塞", "数码", "耳塞");
		add("机箱", "数码", "机箱");
		add("音箱", "数码", "音箱");
		add("刻录碟", "数码", "刻录碟");
		add("耳麦", "数码", "耳麦");
		add("电池", "数码", "手机电池");
		add("鼠标", "数码", "鼠标");
		add("电脑", "数码", "电脑");
		add("捐钱", "人情", "捐钱");
		add("利是", "人情", "利是");
		add("年货", "人情", "年货");
		add("敬老", "人情", "敬老");
		add("请客", "人情", "请客");
		add("转经轮", "人情", "转经轮");
		add("复印", "其它", "复印");
		add("配眼镜", "其它", "配眼镜");
		add("转账", "其它", "转账");
		add("照相", "其它", "照相");
		add("打印", "其它", "打印");
		add("修相机", "其它", "修相机");
		add("修手机", "其它", "修手机");
	}
	
	private static void add(String key, String... value) {
		map.put(key, value);
	}
	
	public static void main(String[] args) throws Throwable {
//		File dir = new File("E:\\reckoning");
//		System.out.println(getType(dir));
//		System.out.println(getTypeMap(dir));
//		System.out.println(getTotalMap(dir));
//		convertFile(dir, new File("E:\\target"));
		File file = new File("E:\\M.txt");
		convertBalanceFile(file);
	}
	
	private static Set<String> getType(File dir) throws Exception {
		Set<String> type = new HashSet<String>();
		for(File f:dir.listFiles()) {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "GBK"));
			String line;
			while((line = in.readLine()) != null) {
				String[] s = line.split("\\t+");
				if(s.length == 2) {
					type.add(s[0]);
				}
			}
			in.close();
		}
		return type;
	}
	
	private static Map<String, Float> getTypeMap(File dir) throws Exception {
		Map<String, Float> map = new HashMap<String, Float>();
		for(File f:dir.listFiles()) {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "GBK"));
			String line;
			while((line = in.readLine()) != null) {
				String[] s = line.split("\\t+");
				if(s.length == 2) {
					Float count = map.get(s[0]);
					if(count == null) map.put(s[0], Float.valueOf(s[1]));
					else map.put(s[0], count + Float.parseFloat(s[1]));
				}
			}
			in.close();
		}
		return map;
	}
	
	private static Map<String, Float> getTotalMap(File dir) throws Exception {
		Map<String, Float> map = new LinkedHashMap<String, Float>();
		for(File f:dir.listFiles()) {
			BigDecimal total = BigDecimal.ZERO;
			BigDecimal count = total;
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "GBK"));
			String line;
			while((line = in.readLine()) != null) {
				String[] s = line.split("\\t+");
				if(s.length == 2) {
					if("合计".equals(s[0])) total.add(new BigDecimal(s[1]));
					else count.add(new BigDecimal(s[1]));
				}
			}
			in.close();
			if(total.compareTo(count)!=0) map.put(f.getName(), total.subtract(count).floatValue());
		}
		return map;
	}
	
	private static void convertFile(File srcDir, File targetDir) throws Exception {
		for(File src:srcDir.listFiles()) {
			File dateDir = new File(targetDir, src.getName().substring(10, 14)+File.separator+src.getName().substring(14, 16));
			dateDir.mkdirs();
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(src), "GBK"));
			String line;
			File target = null;
			while((line = in.readLine()) != null) {
				if(line.matches("^\\d+\\.\\d+$")) {
					String day = line.substring(line.indexOf('.')+1);
					if(day.length()==1) day = "0"+day+".dat";
					else day = day+".dat";
					target = new File(dateDir, day);
				} else {
					String[] s = line.split("\\t+");
					if(s.length == 2) {
						String[] type = map.get(s[0]);
						if(type!=null) {
							OutputStream out = new FileOutputStream(target, true);
							out.write(type[0].getBytes("UTF-8"));
							out.write('\t');
							out.write(type[1].getBytes("UTF-8"));
							out.write('\t');
							out.write(s[1].getBytes());
							out.write('\r');
							out.write('\n');
							out.close();
						}
					}
				}
			}
			in.close();
		}
	}
	
	private static void convertBalanceFile(File src) throws Exception {
		File dateDir = new File(src.getParentFile(), "balance");
		dateDir.mkdirs();
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(src), "GBK"));
		String line;
		Map<String, String> map = new TreeMap<String, String>();
		while((line = in.readLine()) != null) {
			String[] s = line.split(":", 2);
			if(s.length==2) {
				map.put(s[0], s[1]);
			}
		}
		in.close();
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String date = entry.getKey();
			OutputStream out = new FileOutputStream(new File(dateDir, date.substring(0, 6)+".dat"), true);
			out.write(date.getBytes());
			out.write('\t');
			out.write(entry.getValue().getBytes());
			out.write('\r');
			out.write('\n');
			out.close();
		}
	}
}
