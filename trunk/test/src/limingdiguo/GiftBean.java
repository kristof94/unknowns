package limingdiguo;

import org.json.JSONException;
import org.json.JSONObject;

public class GiftBean {
	public String name;
	public boolean allowGet;
	
	public GiftBean(String json) throws JSONException {
		JSONObject object = new JSONObject(json);
//		System.out.println(object);
		JSONObject msg = (JSONObject)object.get("msg");
		name = msg.getString("name");
		allowGet = msg.getBoolean("flag");
	}
}
