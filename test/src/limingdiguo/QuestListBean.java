package limingdiguo;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuestListBean {
	public List<QuestListBean.QuestBean> questList = new ArrayList<QuestListBean.QuestBean>();
	public List<QuestListBean.QuestBean> completeList = new ArrayList<QuestListBean.QuestBean>();
	
	public QuestListBean(String json) throws JSONException {
		JSONObject object = new JSONObject(json);
		System.out.println(object);
		JSONArray msg = (JSONArray)object.get("msg");
//		System.out.println(msg);
		for(int i = 0; i<msg.length(); i++) {
			object = msg.getJSONObject(i);
			QuestBean quest = new QuestBean();
			quest.id = object.getInt("id");
			quest.name = object.getString("name");
			quest.isComplete = "FINISHEDQUEST".equals(object.getString("type"));
			questList.add(quest);
			if(quest.isComplete) completeList.add(quest);
		}
	}
	
	public static class QuestBean {
		public int id;
		public String name;
		public boolean isComplete;
	}
}
