package limingdiguo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResourceBean {
	public int woodNow;
	public int woodMax;
	public int woodOutput;
	public int stoneNow;
	public int stoneMax;
	public int stoneOutput;
	public int ironNow;
	public int ironMax;
	public int ironOutput;
	public int foodNow;
	public int foodMax;
	public int foodOutput;
	
	public ResourceBean(String json) throws JSONException {
		JSONObject object = new JSONObject(json);
//		System.out.println(object);
		JSONObject msg = (JSONObject)object.get("msg");
//		System.out.println(msg);
		JSONArray synRes = (JSONArray)msg.get("synRes");
		JSONArray jsonArray = synRes.getJSONArray(0);
		this.woodNow = jsonArray.getInt(0);
		this.stoneNow = jsonArray.getInt(1);
		this.ironNow = jsonArray.getInt(2);
		this.foodNow = jsonArray.getInt(3);
		jsonArray = synRes.getJSONArray(1);
		this.woodMax = jsonArray.getInt(0);
		this.stoneMax = jsonArray.getInt(1);
		this.ironMax = jsonArray.getInt(2);
		this.foodMax = jsonArray.getInt(3);
		jsonArray = (JSONArray)msg.get("perHour");
		this.woodOutput = jsonArray.getInt(0);
		this.stoneOutput = jsonArray.getInt(1);
		this.ironOutput = jsonArray.getInt(2);
		this.foodOutput = jsonArray.getInt(3);
	}
	
	
}
