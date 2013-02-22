import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import limingdiguo.BarrackBean;
import limingdiguo.BarrackBean.CorpsBean;
import limingdiguo.BuildingBean;
import limingdiguo.GiftBean;
import limingdiguo.LookoutTowerBean;
import limingdiguo.LookoutTowerBean.InvaderBean;
import limingdiguo.QuestListBean;
import limingdiguo.QuestListBean.QuestBean;
import limingdiguo.ResourceBean;
import limingdiguo.TaskListBean;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientParamBean;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParamBean;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;


public class LiMingDiGuo {
	private static HttpClient client;
	private static HttpContext context;
	private static String sid;
	private static String vid;
	private static Map<String, List<Integer>> buildMap;
//	仓库:[27]
//	王宫:[24]
//	磨坊:[21]
//	城镇中心:[23]
//	石匠屋:[19]
//	礼堂:[29]
//	废墟:[22]
//	武器铺:[37]
//	农场:[2, 4, 5, 12, 13, 15]
//	建设部:[34]
//	大学:[31]
//	军事大厅:[25]
//	铁矿场:[6, 9, 10, 14]
//	护具屋:[36]
//	英雄会馆:[30]
//	兵营:[33]
//	粮仓:[28]
//	市场:[32]
//	铁匠铺:[20]
//	军工厂:[35]
//	城墙:[39]
//	木材厂:[18]
//	采石场:[0, 1, 16, 17]
//	伐木场:[3, 7, 8, 11]
//	马厩:[38]
//	嘉年华:[26]
	static{
		HttpParams params = new BasicHttpParams();
		
		ClientParamBean clientParam = new ClientParamBean(params);
		clientParam.setCookiePolicy(CookiePolicy.RFC_2109);
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8,text/vnd.wap.wml;q=0.6"));
		clientParam.setDefaultHeaders(headers);
		
		HttpProtocolParamBean httpProtocolParam = new HttpProtocolParamBean(params);
		httpProtocolParam.setContentCharset(HTTP.UTF_8);
		httpProtocolParam.setHttpElementCharset(HTTP.UTF_8);
		httpProtocolParam.setUserAgent("Mozilla/5.0 (Windows NT 5.1; rv:10.0.2) Gecko/20100101 Firefox/10.0.2");
		httpProtocolParam.setUseExpectContinue(true);
		
        client = new DefaultHttpClient(params);
        
        context = new BasicHttpContext();
		CookieStore cookieStore = new BasicCookieStore();
		context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	}
	
	public static void main(String[] args) throws Exception {
		login("暂时没想到", "112233", "18521");
//		login("112233", "112233", "27635");
		System.out.println(sid+":"+vid);
		if(sid!=null && vid!=null) {
			unknows();
			online();
//			troopListTouch();
//			loopProduction();
//			loopRecruit();
		}
//		loop();
//		{"code":0,"msg":{"upkeep":0,"hasEffect":false,"buildingUsage":41,"prestige":24,"cropUsage":41,"totalCrop":70,"synRes":[[440,84,901,1254,41,135],[101400,101400,101400,101400,70,135]],"kuo":[0,0,0,0,1],"perHour":[20,20,15,29]}}
//		{"code":0,"msg":[{"id":2002,"questType":1,"name":"外交关系","type":"FINISHEDQUEST","mainType":20},{"id":1017,"questType":1,"name":"存放资源","type":"UNFINISHEDQUEST","mainType":1},{"id":2001,"questType":1,"name":"信使","type":"UNFINISHEDQUEST","mainType":20},{"id":1013,"questType":1,"name":"皇家建筑","type":"UNFINISHEDQUEST","mainType":1},{"id":1014,"questType":1,"name":"军政机构","type":"UNFINISHEDQUEST","mainType":1}]}
//		{"code":0,"msg":{"flag":true,"items":[{"icon":204,"num":3,"name":"木材箱"},{"icon":205,"num":3,"name":"石料箱"},{"icon":207,"num":3,"name":"粮食箱"},{"icon":206,"num":3,"name":"铁矿箱"}],"name":"一小时礼包","leftTime":3600,"online":3700}}
//		String text = "{\"code\":0,\"msg\":{\"upkeep\":0,\"hasEffect\":false,\"buildingUsage\":27,\"prestige\":17,\"cropUsage\":27,\"totalCrop\":35,\"synRes\":[[1427,1386,1530,1793,27,95],[101400,101400,101400,101400,35,95]],\"kuo\":[0,0,0,0,1],\"perHour\":[20,20,15,8]}}";
//		ResourceBean resource = new ResourceBean(text);
//		System.out.println(resource.woodOutput);
//		System.out.println(resource.woodMax);
//		System.out.println(resource.woodNow);
//		System.out.println(resource.stoneOutput);
//		System.out.println(resource.stoneMax);
//		System.out.println(resource.stoneNow);
//		System.out.println(resource.foodOutput);
//		System.out.println(resource.foodMax);
//		System.out.println(resource.foodNow);
//		System.out.println(resource.ironOutput);
//		System.out.println(resource.ironMax);
//		System.out.println(resource.ironNow);
//		String text = "{\"code\":0,\"msg\":[{\"id\":2002,\"questType\":1,\"name\":\"外交关系\",\"type\":\"FINISHEDQUEST\",\"mainType\":20},{\"id\":1017,\"questType\":1,\"name\":\"存放资源\",\"type\":\"UNFINISHEDQUEST\",\"mainType\":1},{\"id\":2001,\"questType\":1,\"name\":\"信使\",\"type\":\"UNFINISHEDQUEST\",\"mainType\":20},{\"id\":1013,\"questType\":1,\"name\":\"皇家建筑\",\"type\":\"UNFINISHEDQUEST\",\"mainType\":1},{\"id\":1014,\"questType\":1,\"name\":\"军政机构\",\"type\":\"UNFINISHEDQUEST\",\"mainType\":1}]}";
//		QuestListBean questList = new QuestListBean(text);
//		System.out.println(questList.questList.size());
//		for(QuestBean quest:questList.completeList) {
//			System.out.println(quest.id+":"+quest.name);
//		}
//		String text = "{\"code\":0,\"msg\":{\"flag\":true,\"items\":[{\"icon\":204,\"num\":3,\"name\":\"木材箱\"},{\"icon\":205,\"num\":3,\"name\":\"石料箱\"},{\"icon\":207,\"num\":3,\"name\":\"粮食箱\"},{\"icon\":206,\"num\":3,\"name\":\"铁矿箱\"}],\"name\":\"一小时礼包\",\"leftTime\":3600,\"online\":3700}}";
//		GiftBean gift = new GiftBean(text);
//		System.out.println(gift.allowGet);
//		System.out.println(gift.requireTime);
//		System.out.println(gift.onlineTime);
	}
	
	private static void login(String name, String password, String uid) throws Exception {
		execute("http://limingdiguo.com/");
		
		Map<String, String> pair = new HashMap<String, String>();
		pair.put("name", name);
		pair.put("password", password);
		execute("http://limingdiguo.com/login.action", pair);
		
		execute("http://limingdiguo.com/selectServer.action?uid="+uid+"&server=2&tegert=login");
		HttpUriRequest req = (HttpUriRequest)context.getAttribute(ExecutionContext.HTTP_REQUEST);
		URI uri = req.getURI();
		System.out.println(uri);
		for(String p:uri.getQuery().split("&")) {
			if(p.startsWith("sid=")) {
				sid = p.substring(4);
			} else if(p.startsWith("vid=")) {
				vid = p.substring(4);
			}
		}
	}
	
	private static void online() throws Exception {
		int i = 0;
		while(true) {
			try {
				Thread.sleep(60*1000L);
			} catch(InterruptedException e) {
				e.printStackTrace();
				return;
			}
			switch(i++) {
			case 0:
				checkGift();
				break;
			case 1:
				questNotify();
				break;
			case 2:
				troopListTouch();
				break;
			default:
				i = 0;
				getResource();
				break;
			}
		}
	}
	
	private static void checkBuilding() throws Exception {
		File file = new File("lmdg_building_"+vid);
		if(file.isFile()) {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			buildMap = (Map<String, List<Integer>>)in.readObject();
			in.close();
		}
		if(buildMap==null || buildMap.containsKey("空地")) {
			buildMap = new HashMap<String, List<Integer>>();
			for(int i = 0; i<40; i++) {
				if(i>0) {
					try {
						Thread.sleep(1000L);
					} catch(InterruptedException e) {
						e.printStackTrace();
						return;
					}
				}
				BuildingBean building = updatebuilding(i);
				System.out.println(building.id+":"+building.type);
				if(building.type!=null) {
					List<Integer> list = buildMap.get(building.type);
					if(list==null) {
						list = new ArrayList<Integer>();
						buildMap.put(building.type, list);
					}
					list.add(building.id);
				}
			}
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(buildMap);
			out.close();
		}
	}
	
	private static void loopProduction() throws Exception {
		checkBuilding();
		while(true) {
			production();
		}
	}
	
	private static void production() throws Exception {
		ResourceBean resource = getResource();
		List<Integer> list;
		if(resource.foodOutput<100) {
			list = buildMap.get("磨坊");
			list.addAll(buildMap.get("农场"));
		} else {
			if(resource.woodOutput<(Math.min(resource.stoneOutput, resource.ironOutput))) {
				list = buildMap.get("木材厂");
				list.addAll(buildMap.get("伐木场"));
			} else if(resource.stoneOutput<resource.ironOutput) {
				list = buildMap.get("石匠屋");
				list.addAll(buildMap.get("采石场"));
			} else {
				list = buildMap.get("铁匠铺");
				list.addAll(buildMap.get("铁矿场"));
			}
		}
		update:while(true) {
			for(Integer id:list) {
				BuildingBean building = updatebuilding(id);
				if(building.allowUpdate) {
					System.out.println("升级"+building.type);
					build(building.id, building.bid);
					break update;
				}
				Thread.sleep(1000L*60);
			}
			Thread.sleep(1000L*60*10);
		}
	}
	
	private static void loopRecruit() throws Exception {
		checkBuilding();
		while(true) {
			recruit();
		}
	}
	
	private static void recruit() throws Exception {
		ResourceBean resource = getResource();
		while(resource.foodOutput<500) {
			List<Integer> list = buildMap.get("磨坊");
			list.addAll(buildMap.get("农场"));
			update:while(true) {
				for(Integer id:list) {
					BuildingBean building = updatebuilding(id);
					if(building.allowUpdate) {
						System.out.println("升级"+building.type);
						build(building.id, building.bid);
						break update;
					}
					Thread.sleep(1000L*60);
				}
				Thread.sleep(1000L*60*10);
			}
			resource = getResource();
		}
		List<Integer> list = buildMap.get("马厩");
		if(list==null) {
			System.err.println("请先建造马厩！！！");
			return;
		}
		BarrackBean barrack = updatebarrack(list.get(0));
		CorpsBean corps = barrack.corpsMap.get("重骑士");
		if(corps==null) {
			System.err.println("请先研究重骑士！！！");
			return;
		}
		int num = Math.min(Math.min(resource.woodNow/corps.useWood, resource.stoneNow/corps.useStone), Math.min(resource.ironNow/corps.useIron, resource.foodNow/corps.useFood));
		long time = num==0?60*10:num*corps.time;
		if(num>0) {
			System.out.println("训练"+num+"重骑士");
			barracksTrain(barrack.id, corps.id, num);
		}
		time = System.currentTimeMillis()+1000*time;
		while(System.currentTimeMillis()<time) {
			Thread.sleep(1000L*60*5);
			troopListTouch();
		}
	}
	
	private static void loop() throws Exception {
		if(sid!=null && vid!=null) {
			unknows();
			checkGift();
			try {
				Thread.sleep(3000L);
			} catch(InterruptedException e) {
				e.printStackTrace();
				return;
			}
			getResource();
			try {
				Thread.sleep(3000L);
			} catch(InterruptedException e) {
				e.printStackTrace();
				return;
			}
			getTaskList();
			try {
				Thread.sleep(3000L);
			} catch(InterruptedException e) {
				e.printStackTrace();
				return;
			}
			questNotify();
			try {
				Thread.sleep(3000L);
			} catch(InterruptedException e) {
				e.printStackTrace();
				return;
			}
//			questListTouch("1");
//			try {
//				Thread.sleep(3000L);
//			} catch(InterruptedException e) {
//				e.printStackTrace();
//				return;
//			}
//			questListTouch("20");
//			try {
//				Thread.sleep(3000L);
//			} catch(InterruptedException e) {
//				e.printStackTrace();
//				return;
//			}
			troopListTouch();
			for(int i = 0; i<40; i++) {
				try {
					Thread.sleep(3000L);
				} catch(InterruptedException e) {
					e.printStackTrace();
					return;
				}
				updatebuilding(i);
			}
			Iterator<Entry<String, List<Integer>>> iterator = buildMap.entrySet().iterator();
			while(iterator.hasNext()) {
				Entry<String, List<Integer>> entry = iterator.next();
				System.out.println(entry.getKey()+":"+entry.getValue());
			}
		}
	}
	
	private static void unknows() throws Exception {
		execute("http://s1.limingdiguo.com/getMainPageInfo.do", pair("sid", sid), pair("vid", vid));
		execute("http://s1.limingdiguo.com/getValleyCenter.do", pair("sid", sid), pair("vid", vid));
	}
	
	private static void checkGift() throws Exception {
		GiftBean gift = new GiftBean(execute("http://s1.limingdiguo.com/checkGift.do", pair("sid", sid)));
		if(gift.allowGet) {
			System.out.println("领取礼包："+gift.name);
			getGift();
		}
	}
	
	private static void getGift() throws Exception {
		GiftBean gift = new GiftBean(execute("http://s1.limingdiguo.com/getGift.do", pair("sid", sid)));
		if(gift.allowGet) {
			System.out.println("领取礼包："+gift.name);
			getGift();
		}
	}
	
	private static ResourceBean getResource() throws Exception {
		ResourceBean resource = new ResourceBean(execute("http://s1.limingdiguo.com/getResource.do", pair("sid", sid), pair("vid", vid)));
		System.out.println("木材："+resource.woodNow+"["+resource.woodOutput+"]/"+resource.woodMax);
		System.out.println("石料："+resource.stoneNow+"["+resource.stoneOutput+"]/"+resource.stoneMax);
		System.out.println("铁矿："+resource.ironNow+"["+resource.ironOutput+"]/"+resource.ironMax);
		System.out.println("粮食："+resource.foodNow+"["+resource.foodOutput+"]/"+resource.foodMax);
		return resource;
	}
	
	private static BuildingBean updatebuilding(int id) throws Exception {
//		点击建筑
//		http://s1.limingdiguo.com/updatebuilding.do
//		sid=aaaJ_hLAuzp_S_UwtdZyt&vid=55667&index=23
		BuildingBean building = new BuildingBean(execute("http://s1.limingdiguo.com/updatebuilding.do", pair("sid", sid), pair("vid", vid), pair("index", String.valueOf(id))));
		return building;
	}
	
	private static void build(int id, int bid) throws Exception {
//		升级建筑
//		http://s1.limingdiguo.com/build.do
//		sid=aaaJ_hLAuzp_S_UwtdZyt&vid=55667&index=10&bid=10306
		execute("http://s1.limingdiguo.com/build.do", pair("sid", sid), pair("vid", vid), pair("index", String.valueOf(id)), pair("bid", String.valueOf(bid)));
	}
	
	private static BarrackBean updatebarrack(int id) throws Exception {
		BarrackBean barrack = new BarrackBean(execute("http://s1.limingdiguo.com/updatebuilding.do", pair("sid", sid), pair("vid", vid), pair("index", String.valueOf(id))));
		return barrack;
	}
	
	private static void getTaskList() throws Exception {
		TaskListBean taskList = new TaskListBean(execute("http://s1.limingdiguo.com/getTaskList.do", pair("sid", sid), pair("vid", vid), pair("cp", "1"), pair("type", "0")));
		if(taskList.empty) {
			System.out.println("现在没有建筑任务！");
		}
	}
	
	private static void troopListTouch() throws Exception {
		LookoutTowerBean lookoutTower = new LookoutTowerBean(execute("http://s1.limingdiguo.com/troopListTouch.do", pair("sid", sid), pair("vid", vid)));
		if(lookoutTower.count>0) {
			System.err.println("有战斗部队"+lookoutTower.count+"队！！！");
			List<InvaderBean> invaderList = lookoutTower.invaderList;
			for(InvaderBean invader:invaderList) {
				System.err.println(invader.name+"从"+invader.from+"["+invader.x+","+invader.y+"]攻击"+invader.to+"，预计到达时间是"+invader.arriveTime);
				if(invader.unknows) {
					System.err.println("无法探查部队数量！！！");
				} else {
					System.err.println("派出的部队有：");
					Iterator<Entry<String, Integer>> iterator = invader.units.entrySet().iterator();
					while(iterator.hasNext()) {
						Entry<String, Integer> entry = iterator.next();
						System.err.println(entry.getKey()+":"+entry.getValue());
					}
				}
			}
			System.err.println("=====================================");
		}
	}
	
	private static void questNotify() throws Exception {
		QuestListBean questList = new QuestListBean(execute("http://s1.limingdiguo.com/questNotify.do", pair("sid", sid), pair("vid", vid)));
		for(QuestBean quest:questList.completeList) {
			System.out.println("完成任务："+quest.name);
			questCompleteTouch(String.valueOf(quest.id));
		}
	}
	
	private static void questCompleteTouch(String id) throws Exception {
		execute("http://s1.limingdiguo.com/questCompleteTouch.do", pair("sid", sid), pair("vid", vid), pair("qid", id));
	}
	
	private static void questListTouch(String type) throws Exception {
		execute("http://s1.limingdiguo.com/questListTouch.do", pair("sid", sid), pair("vid", vid), pair("type", type));
	}
	
	private static void barracksTrain(int id, int uid, int num) throws Exception {
//		招兵
//		http://s1.limingdiguo.com/barracksTrain.do
//		sid=aaavSS8S_78VDcuyeG9yt&vid=79508&index=38&uid=105&num=1
		execute("http://s1.limingdiguo.com/barracksTrain.do", pair("sid", sid), pair("vid", vid), pair("index", String.valueOf(id)), pair("uid", String.valueOf(uid)), pair("num", String.valueOf(num)));
	}
	
	private static NameValuePair pair(String name, String value) {
		return new BasicNameValuePair(name, value);
	}
	
	private static void execute(String uri) throws Exception {
		HttpGet get = new HttpGet(uri);
		HttpResponse response = client.execute(get, context);
		System.out.println(uri+":"+response.getStatusLine());
//		System.out.println(EntityUtils.toString(response.getEntity()));
		get.abort();
	}
	
	private static void execute(String uri, Map<String, String> pair) throws Exception {
		HttpPost post = new HttpPost(uri);
		Iterator<Entry<String, String>> iterator = pair.entrySet().iterator();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		while(iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
		entity.setContentEncoding(HTTP.UTF_8);
		entity.setContentType("application/x-www-form-urlencoded");
		post.setEntity(entity);
		HttpResponse response = client.execute(post, context);
		System.out.println(uri+":"+response.getStatusLine());
//		System.out.println(EntityUtils.toString(response.getEntity()));
		post.abort();
	}
	
	private static String execute(String uri, NameValuePair... pair) throws Exception {
		HttpPost post = new HttpPost(uri);
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(Arrays.asList(pair));
		entity.setContentEncoding(HTTP.UTF_8);
		entity.setContentType("application/x-www-form-urlencoded");
		post.setEntity(entity);
		HttpResponse response = client.execute(post, context);
		System.out.println(uri+":"+response.getStatusLine());
		String result = EntityUtils.toString(response.getEntity());
//		System.out.println(result);
		post.abort();
		return result;
	}
}
