package limingdiguo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TaskListBean {
	public int length;
	public boolean empty;
	
	public TaskListBean(String json) throws JSONException {
//		{"code":0,"msg":{"total":1,"cp":1,"task":[{"id":1167524,"canCancel":true,"time":"1313","over":false,"canPlus":true,"name":"[建造] 伐木场(Lv7)","iconId":"q1"},{"id":1167526,"canCancel":true,"time":"3478","over":false,"canPlus":true,"name":"[建造] 采石场(Lv7)","iconId":"q1"},{"id":1167533,"canCancel":true,"time":"4008","over":false,"canPlus":true,"name":"[建造] 城镇中心(Lv13)","iconId":"q1"},{"id":1167534,"canCancel":true,"time":"4344","over":false,"canPlus":true,"name":"[建造] 城墙(Lv2)","iconId":"q1"}],"type":0}}
		JSONObject object = new JSONObject(json);
//		System.out.println(object);
		JSONObject msg = (JSONObject)object.get("msg");
//		System.out.println(msg);
		JSONArray taskList = msg.getJSONArray("task");
		length = taskList.length();
		empty = length==0;
	}
	
}
