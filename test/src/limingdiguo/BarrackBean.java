package limingdiguo;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BarrackBean extends BuildingBean {
	public Map<String, CorpsBean> corpsMap = new HashMap<String, BarrackBean.CorpsBean>();

	public BarrackBean(String json) throws JSONException {
//		{"res":[1630,1140,1630,1060,3],"index":38,"status":"Build_Ok","tp":42,"synRes":[[5612,5047,6732,6049,999,199],[100800,100800,100800,100800,1983,199]],"lv":8,"code":0,"trainingQueues":[],"time":1300,"nextBid":34209,"updateQueue":[],"backoutQueue":[],"cutTimeNextLV":37,"barracksObj":[{"id":106,"res":[550,600,650,200,4],"time":756,"num":0,"name":"圣骑士","att":[184,82,102,10,70]},{"id":105,"res":[550,400,320,130,3],"time":605,"num":44,"name":"重骑士","att":[41,112,168,12,80]},{"id":104,"res":[130,130,20,40,2],"time":202,"num":10,"name":"侦察兵","att":[0,20,10,16,0]},{"id":103,"res":[400,240,320,100,2],"time":252,"num":0,"name":"轻骑士","att":[61,31,20,20,50]}],"cutTime":42,"bid":34208}
		super(json);
		JSONObject object = new JSONObject(json);
		JSONArray barracksObj = object.optJSONArray("barracksObj");
		if(barracksObj!=null) {
			for(int i = 0; i<barracksObj.length(); i++) {
				JSONObject c = barracksObj.getJSONObject(i);
				CorpsBean corps = new CorpsBean();
				corps.id = c.getInt("id");
				corps.name = c.getString("name");
				corps.num = c.getInt("num");
				corps.time = c.getInt("time");
				JSONArray res = c.getJSONArray("res");
				corps.useWood = res.getInt(0);
				corps.useStone = res.getInt(1);
				corps.useIron = res.getInt(2);
				corps.useFood = res.getInt(3);
				corpsMap.put(corps.name, corps);
			}
		}
	}
	
	public static class CorpsBean {
		public int id;
		public String name;
		public int num;
		public int time;
		public int useWood;
		public int useStone;
		public int useIron;
		public int useFood;
	}
}
