package limingdiguo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LookoutTowerBean {
	private static final String[] unitsType = new String[]{"轻步兵","重步兵","轻骑兵","侦查兵","重骑士","圣骑士","弩车","投石车","传教士","拓荒者","英雄"};
	public int count;
	public List<InvaderBean> invaderList = new ArrayList<LookoutTowerBean.InvaderBean>();
	
	public LookoutTowerBean(String json) throws JSONException {
//		{"code":0,"msg":[[{"v1":"暂时没想到的村庄","account1":"暂时没想到","v2":"暂时没想到的村庄","tid":172211,"units":[0,0,0,10,54,0,0,0,0,0,1]}],[],[],[{"v1":"慧慧城","account1":"hyh","v2":"暂时没想到的村庄","target":"v1","back":false,"left":"1:44:15","type":1,"in":true,"units":[0,0,0,0,0,0,0,0,0,0,0],"end":"18:50:09","y":3,"x":-71}],[],[]]}
//		{"code":0,"msg":[[{"v1":"暂时没想到的村庄","account1":"暂时没想到","v2":"暂时没想到的村庄","tid":172211,"units":[0,0,0,10,57,0,0,0,0,0,0]}],[],[],[{"v1":"慧慧城","account1":"hyh","v2":"暂时没想到的村庄","target":"v1","back":false,"left":"0:54:19","type":1,"in":true,"units":[0,0,0,0,0,0,0,0,0,0,0],"end":"18:50:09","y":3,"x":-71},{"v1":"暂时没想到的村庄","account1":"暂时没想到","v2":"212140的村庄","back":false,"type":1,"units":[0,0,0,0,0,0,0,0,0,0,1],"target":"v2","left":4802,"tid":211045,"end":"19:15:52","y":-17,"out":true,"x":-83}],[],[]]}
		JSONObject object = new JSONObject(json);
//		System.out.println(object);
		JSONArray jsonArray = object.getJSONArray("msg").getJSONArray(3);
		for(int i = 0; i<jsonArray.length(); i++) {
//			{"v1":"慧慧城","account1":"hyh","v2":"暂时没想到的村庄","target":"v1","back":false,"left":"1:44:15","type":1,"in":true,"units":[0,0,0,0,0,0,0,0,0,0,0],"end":"18:50:09","y":3,"x":-71}
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			InvaderBean invader = new InvaderBean();
			invader.name = jsonObject.getString("account1");
			invader.from = jsonObject.getString("v1");
			invader.to = jsonObject.getString("v2");
			invader.arriveTime = jsonObject.getString("end");
			invader.x = jsonObject.getInt("x");
			invader.y = jsonObject.getInt("y");
			invader.unknows = true;
			invader.units = new HashMap<String, Integer>();
			JSONArray units = jsonObject.getJSONArray("units");
			for(int j = 0; j<units.length(); j++) {
				int num = units.getInt(j);
				if(num>0) {
					if(invader.unknows) invader.unknows = false;
					invader.units.put(unitsType[j], num);
				}
			}
			invaderList.add(invader);
		}
		count = invaderList.size();
	}
	
	public static class InvaderBean {
		public String name;
		public String from;
		public String to;
		public String arriveTime;
		public int x;
		public int y;
		public boolean unknows;
		public Map<String, Integer> units;
	}
}
