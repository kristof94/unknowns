package limingdiguo;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BuildingBean {
	private static Map<Integer, String> typeMap = new HashMap<Integer, String>();
	static {
//		modelArgs.enums.buildingType.
//		typeMap.put(800, "constructing");
		typeMap.put(21, "空地");
		typeMap.put(41, "兵营");
		typeMap.put(22, "城镇中心");
		typeMap.put(42, "马厩");
		typeMap.put(43, "军工厂");
		typeMap.put(45, "大学");
		typeMap.put(10, "仓库");
		typeMap.put(11, "粮仓");
//		typeMap.put(62, "工厂");
		typeMap.put(53, "城墙");
		typeMap.put(44, "军事大厅");
		typeMap.put(27, "行宫");
		typeMap.put(28, "王宫");
		typeMap.put(30, "建设部");
		typeMap.put(52, "英雄会馆");
		typeMap.put(46, "武器铺");
		typeMap.put(47, "护具屋");
		typeMap.put(23, "大使馆");
		typeMap.put(26, "礼堂");
		typeMap.put(25, "市场");
		typeMap.put(12, "藏宝阁");
		typeMap.put(60, "嘉年华");
		typeMap.put(59, "废墟");
		typeMap.put(1, "伐木场");
		typeMap.put(2, "采石场");
		typeMap.put(3, "铁矿场");
		typeMap.put(4, "农场");
		typeMap.put(5, "木材厂");
		typeMap.put(6, "石匠屋");
		typeMap.put(7, "铁匠铺");
		typeMap.put(8, "磨坊");
//		typeMap.put(2E3, "城市");
//		typeMap.put(2001, "城市");
//		typeMap.put(2002, "空地");
//		typeMap.put(9, "bakery");
	}
	
	public int id;
	public int bid;
	public String type;
	public String status;
	public boolean allowUpdate;
	public int useWood;
	public int useStone;
	public int useIron;
	public int useFood;
	
	public BuildingBean(String json) throws JSONException {
//		buildingType:{constructing:800,empty:21,barracks:41,townCenter:22,horseStable:42,machineShop:43,university:45,warehouse:10,granary:11,factory:62,cityWall:53,castle:44,temporaryPalace:27,imperialPalace:28,stonemason:30,heroicClubhouse:52,blacksmith:46,armoury:47,embassy:23,assemblyHall:26,market:25,tibet:12,activity:60,ruins:59,woodCutter:1,clayPit:2,ironMine:3,cropLand:4,sawmill:5,brickyard:6,ironFoundry:7,grainMill:8,resource2city:2E3,resourceVillage:2001,resourceEmpty:2002,bakery:9}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[80,30,60,45,2],"index":0,"status":"建筑队列已满","enhanceNextLV":6,"tp":2,"synRes":[[127,719,3269,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":3,"time":47,"nextBid":10201,"updateQueue":[],"resPH":[69,69,95,35],"bid":10200}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[80,30,60,45,2],"index":1,"status":"建筑队列已满","enhanceNextLV":6,"tp":2,"synRes":[[127,719,3269,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":3,"time":47,"nextBid":10201,"updateQueue":[],"resPH":[69,69,95,35],"bid":10200}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[2535,3260,2535,725,1],"index":2,"status":"建筑队列已满","enhanceNextLV":120,"tp":4,"synRes":[[127,719,3269,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":7,"code":0,"enhance":86,"time":3149,"nextBid":10408,"updateQueue":[],"resPH":[69,69,95,35],"bid":10407}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[790,2110,1450,1315,2],"index":3,"status":"正在升级中","enhanceNextLV":86,"tp":1,"synRes":[[127,719,3269,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":6,"code":0,"enhance":60,"time":2164,"nextBid":10107,"updateQueue":[1167524,"伐木场",7,1288,"12:24",10107,3,1],"resPH":[69,69,95,35],"bid":10106}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[195,250,195,55,0],"index":4,"status":"建筑队列已满","enhanceNextLV":18,"tp":4,"synRes":[[127,719,3269,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":2,"code":0,"enhance":11,"time":154,"nextBid":10403,"updateQueue":[],"resPH":[69,69,95,35],"bid":10402}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[195,250,195,55,0],"index":5,"status":"建筑队列已满","enhanceNextLV":18,"tp":4,"synRes":[[127,719,3269,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":2,"code":0,"enhance":11,"time":154,"nextBid":10403,"updateQueue":[],"resPH":[69,69,95,35],"bid":10402}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[80,55,30,50,3],"index":6,"status":"建筑队列已满","enhanceNextLV":6,"tp":3,"synRes":[[127,719,3269,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":3,"time":57,"nextBid":10301,"updateQueue":[],"resPH":[69,69,95,35],"bid":10300}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[30,80,55,50,2],"index":7,"status":"建筑队列已满","enhanceNextLV":6,"tp":1,"synRes":[[127,719,3269,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":3,"time":47,"nextBid":10101,"updateQueue":[],"resPH":[69,69,95,35],"bid":10100}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[30,80,55,50,2],"index":8,"status":"建筑队列已满","enhanceNextLV":6,"tp":1,"synRes":[[127,719,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":3,"time":47,"nextBid":10101,"updateQueue":[],"resPH":[69,69,95,35],"bid":10100}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[80,55,30,50,3],"index":9,"status":"建筑队列已满","enhanceNextLV":6,"tp":3,"synRes":[[128,719,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":3,"time":57,"nextBid":10301,"updateQueue":[],"resPH":[69,69,95,35],"bid":10300}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[3635,2500,1365,2270,4],"index":10,"status":"建筑队列已满","enhanceNextLV":120,"tp":3,"synRes":[[128,719,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":7,"code":0,"enhance":86,"time":4476,"nextBid":10308,"updateQueue":[],"resPH":[69,69,95,35],"bid":10307}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[30,80,55,50,2],"index":11,"status":"建筑队列已满","enhanceNextLV":6,"tp":1,"synRes":[[128,719,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":3,"time":47,"nextBid":10101,"updateQueue":[],"resPH":[69,69,95,35],"bid":10100}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[115,150,115,35,0],"index":12,"status":"建筑队列已满","enhanceNextLV":11,"tp":4,"synRes":[[128,719,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":1,"code":0,"enhance":6,"time":77,"nextBid":10402,"updateQueue":[],"resPH":[69,69,95,35],"bid":10401}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[115,150,115,35,0],"index":13,"status":"建筑队列已满","enhanceNextLV":11,"tp":4,"synRes":[[128,719,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":1,"code":0,"enhance":6,"time":77,"nextBid":10402,"updateQueue":[],"resPH":[69,69,95,35],"bid":10401}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[80,55,30,50,3],"index":14,"status":"建筑队列已满","enhanceNextLV":6,"tp":3,"synRes":[[128,720,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":3,"time":57,"nextBid":10301,"updateQueue":[],"resPH":[69,69,95,35],"bid":10300}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[115,150,115,35,0],"index":15,"status":"建筑队列已满","enhanceNextLV":11,"tp":4,"synRes":[[128,720,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":1,"code":0,"enhance":6,"time":77,"nextBid":10402,"updateQueue":[],"resPH":[69,69,95,35],"bid":10401}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[80,30,60,45,2],"index":16,"status":"建筑队列已满","enhanceNextLV":6,"tp":2,"synRes":[[128,720,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":3,"time":47,"nextBid":10201,"updateQueue":[],"resPH":[69,69,95,35],"bid":10200}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[2110,790,1580,1185,2],"index":17,"status":"正在升级中","enhanceNextLV":86,"tp":2,"synRes":[[128,720,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":6,"code":0,"enhance":60,"time":2164,"nextBid":10207,"updateQueue":[1167526,"采石场",7,3409,"13:00",10207,17,1],"resPH":[69,69,95,35],"bid":10206}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[400,350,400,100,5],"index":18,"status":"建筑队列已满","enhanceNextLV":5,"nbs":[[1,10]],"tp":5,"synRes":[[128,720,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":0,"time":737,"nextBid":10501,"updateQueue":[],"bid":10500}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[400,475,300,75,5],"index":19,"status":"建筑队列已满","enhanceNextLV":5,"nbs":[[2,10]],"tp":6,"synRes":[[128,720,3270,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":0,"time":670,"nextBid":10601,"updateQueue":[],"bid":10600}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[200,400,500,150,6],"index":20,"status":"建筑队列已满","enhanceNextLV":5,"nbs":[[3,10]],"tp":7,"synRes":[[128,720,3271,6721,105,245],[101900,101900,101900,102500,132,245]],"lv":0,"code":0,"enhance":0,"time":938,"nextBid":10701,"updateQueue":[],"bid":10700}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[950,855,570,2375,2],"index":21,"status":"建筑队列已满","enhanceNextLV":10,"nbs":[],"tp":8,"synRes":[[128,720,3271,6722,105,245],[101900,101900,101900,102500,132,245]],"lv":1,"code":0,"enhance":5,"time":1072,"nextBid":10802,"updateQueue":[],"bid":10801}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"index":22,"tp":59,"synRes":[[128,720,3271,6722,105,245],[101900,101900,101900,102500,132,245]],"code":0}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"buildingQueues":[[1167524,"伐木场",7,1224,"12:24",10107,3,1],[1167526,"采石场",7,3389,"13:00",10207,17,1],[1167533,"城镇中心",13,3919,"13:09",22213,23,0],[1167534,"城墙",2,4255,"13:14",35302,39,0]],"res":[1280,1050,1050,700,4],"index":23,"status":"正在升级中","tp":22,"synRes":[[128,720,3271,6722,105,245],[101900,101900,101900,102500,132,245]],"lv":12,"code":0,"time":4827,"nextBid":22213,"updateQueue":[1167533,"城镇中心",13,3919,"13:09",22213,23,0],"cutTimeNextLV":64,"cutTime":67,"bid":22212}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[720,1185,1270,505,1],"index":24,"status":"建筑队列已满","enhanceNextLV":78,"tp":28,"civNeed":500,"synRes":[[128,720,3271,6722,105,245],[101900,101900,101900,102500,132,245]],"lv":2,"code":0,"l1":100,"units":[{"id":110,"res":[5500,5000,7000,5000,1],"trainNum":0,"time":8448,"num":0,"name":"拓荒者","att":[0,80,80,5,3000],"msg":"王宫等级不足"}],"enhance":[88,88],"time":1156,"nextBid":22803,"affairs":[97,132],"updateQueue":[],"phCivTotal":60,"phCiv":60,"civTotal":64,"trainArray":[],"bid":22802,"loy":100}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[170,270,120,120,2],"atkQuene":[],"index":25,"status":"建筑队列已满","enhanceNextLV":1,"tp":44,"synRes":[[128,720,3271,6722,105,245],[101900,101900,101900,102500,132,245]],"lv":2,"code":0,"army":{"hero":0,"troops":[[101,"轻步兵",0],[102,"重步兵",0],[103,"轻骑士",0],[104,"侦察兵",0],[105,"重骑士",0],[106,"圣骑士",0],[107,"弩车",0],[108,"投石车",0],[109,"传教士",0],[110,"拓荒者",0]]},"time":419,"nextBid":34403,"updateQueue":[],"hasBarracks":true,"bid":34402}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"index":26,"tp":60,"synRes":[[129,720,3271,6722,105,245],[101900,101900,101900,102500,132,245]],"code":0}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[210,245,135,60,1],"index":27,"status":"建筑队列已满","tp":10,"synRes":[[129,720,3271,6722,105,245],[101900,101900,101900,102500,132,245]],"lv":2,"capacityNextLV":1700,"code":0,"time":509,"nextBid":11003,"updateQueue":[],"backoutQueue":[],"capacity":1100,"bid":11002}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[165,200,165,45,1],"index":28,"status":"建筑队列已满","tp":11,"synRes":[[129,720,3271,6722,105,245],[101900,101900,101900,102500,132,245]],"lv":3,"capacityNextLV":2500,"code":0,"time":566,"nextBid":11104,"updateQueue":[],"backoutQueue":[],"capacity":1700,"bid":11103}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[395,265,265,175,2],"index":29,"status":"建筑队列已满","enhanceNextLV":12,"tp":23,"inviteNum":0,"synRes":[[129,720,3271,6722,105,245],[101900,101900,101900,102500,132,245]],"lv":3,"code":0,"enhance":9,"time":747,"nextBid":22304,"updateQueue":[],"isExistingAlly":false,"backoutQueue":[],"bid":22303}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"index":30,"tp":21,"ub":[[{"n1":75,"time":402,"n5":4,"n4":75,"n3":105,"needBuilding":[[22,3,true],[10,1,true],[11,1,true]],"n2":70,"type":25,"bid":22501},{"n1":600,"time":291,"n5":2,"n4":300,"n3":500,"needBuilding":[[22,3,true],[44,1,true]],"n2":550,"type":52,"bid":35201}],[{"n1":180,"time":268,"n5":1,"n4":50,"n3":90,"needBuilding":[[22,3,true],[41,3,false]],"n2":150,"type":45,"bid":34501},{"n1":140,"time":268,"n5":3,"n4":120,"n3":360,"needBuilding":[[45,1,false]],"n2":200,"type":47,"bid":34701},{"n1":130,"time":268,"n5":4,"n4":120,"n3":400,"needBuilding":[[45,3,false]],"n2":170,"type":46,"bid":34601},{"n1":200,"time":291,"n5":4,"n4":130,"n3":200,"needBuilding":[[45,5,false],[46,3,false]],"n2":140,"type":42,"bid":34201},{"n1":130,"time":1072,"n5":3,"n4":80,"n3":120,"needBuilding":[[22,5,true],[28,3,false]],"n2":125,"type":30,"bid":23001},{"n1":400,"time":446,"n5":4,"n4":300,"n3":600,"needBuilding":[[22,5,true],[45,10,false]],"n2":450,"type":43,"bid":34301},{"n1":1200,"time":670,"n5":4,"n4":600,"n3":1200,"needBuilding":[[22,10,true],[45,10,false]],"n2":1000,"type":26,"bid":22601}]],"synRes":[[129,721,3271,6722,105,245],[101900,101900,101900,102500,132,245]],"code":0}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"index":31,"tp":21,"ub":[[{"n1":75,"time":402,"n5":4,"n4":75,"n3":105,"needBuilding":[[22,3,true],[10,1,true],[11,1,true]],"n2":70,"type":25,"bid":22501},{"n1":600,"time":291,"n5":2,"n4":300,"n3":500,"needBuilding":[[22,3,true],[44,1,true]],"n2":550,"type":52,"bid":35201}],[{"n1":180,"time":268,"n5":1,"n4":50,"n3":90,"needBuilding":[[22,3,true],[41,3,false]],"n2":150,"type":45,"bid":34501},{"n1":140,"time":268,"n5":3,"n4":120,"n3":360,"needBuilding":[[45,1,false]],"n2":200,"type":47,"bid":34701},{"n1":130,"time":268,"n5":4,"n4":120,"n3":400,"needBuilding":[[45,3,false]],"n2":170,"type":46,"bid":34601},{"n1":200,"time":291,"n5":4,"n4":130,"n3":200,"needBuilding":[[45,5,false],[46,3,false]],"n2":140,"type":42,"bid":34201},{"n1":130,"time":1072,"n5":3,"n4":80,"n3":120,"needBuilding":[[22,5,true],[28,3,false]],"n2":125,"type":30,"bid":23001},{"n1":400,"time":446,"n5":4,"n4":300,"n3":600,"needBuilding":[[22,5,true],[45,10,false]],"n2":450,"type":43,"bid":34301},{"n1":1200,"time":670,"n5":4,"n4":600,"n3":1200,"needBuilding":[[22,10,true],[45,10,false]],"n2":1000,"type":26,"bid":22601}]],"synRes":[[129,721,3272,6722,105,245],[101900,101900,101900,102500,132,245]],"code":0}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"index":32,"tp":21,"ub":[[{"n1":75,"time":402,"n5":4,"n4":75,"n3":105,"needBuilding":[[22,3,true],[10,1,true],[11,1,true]],"n2":70,"type":25,"bid":22501},{"n1":600,"time":291,"n5":2,"n4":300,"n3":500,"needBuilding":[[22,3,true],[44,1,true]],"n2":550,"type":52,"bid":35201}],[{"n1":180,"time":268,"n5":1,"n4":50,"n3":90,"needBuilding":[[22,3,true],[41,3,false]],"n2":150,"type":45,"bid":34501},{"n1":140,"time":268,"n5":3,"n4":120,"n3":360,"needBuilding":[[45,1,false]],"n2":200,"type":47,"bid":34701},{"n1":130,"time":268,"n5":4,"n4":120,"n3":400,"needBuilding":[[45,3,false]],"n2":170,"type":46,"bid":34601},{"n1":200,"time":291,"n5":4,"n4":130,"n3":200,"needBuilding":[[45,5,false],[46,3,false]],"n2":140,"type":42,"bid":34201},{"n1":130,"time":1072,"n5":3,"n4":80,"n3":120,"needBuilding":[[22,5,true],[28,3,false]],"n2":125,"type":30,"bid":23001},{"n1":400,"time":446,"n5":4,"n4":300,"n3":600,"needBuilding":[[22,5,true],[45,10,false]],"n2":450,"type":43,"bid":34301},{"n1":1200,"time":670,"n5":4,"n4":600,"n3":1200,"needBuilding":[[22,10,true],[45,10,false]],"n2":1000,"type":26,"bid":22601}]],"synRes":[[129,721,3272,6722,105,245],[101900,101900,101900,102500,132,245]],"code":0}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[260,155,310,155,2],"index":33,"status":"建筑队列已满","tp":41,"synRes":[[129,721,3272,6722,105,245],[101900,101900,101900,102500,132,245]],"lv":1,"code":0,"trainingQueues":[],"time":335,"nextBid":34102,"updateQueue":[],"backoutQueue":[],"cutTimeNextLV":88,"barracksObj":[{"id":101,"res":[100,70,150,30,1],"time":420,"num":0,"name":"轻步兵","att":[40,20,5,7,60]}],"cutTime":100,"bid":34101}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"index":34,"tp":21,"ub":[[{"n1":75,"time":402,"n5":4,"n4":75,"n3":105,"needBuilding":[[22,3,true],[10,1,true],[11,1,true]],"n2":70,"type":25,"bid":22501},{"n1":600,"time":291,"n5":2,"n4":300,"n3":500,"needBuilding":[[22,3,true],[44,1,true]],"n2":550,"type":52,"bid":35201}],[{"n1":180,"time":268,"n5":1,"n4":50,"n3":90,"needBuilding":[[22,3,true],[41,3,false]],"n2":150,"type":45,"bid":34501},{"n1":140,"time":268,"n5":3,"n4":120,"n3":360,"needBuilding":[[45,1,false]],"n2":200,"type":47,"bid":34701},{"n1":130,"time":268,"n5":4,"n4":120,"n3":400,"needBuilding":[[45,3,false]],"n2":170,"type":46,"bid":34601},{"n1":200,"time":291,"n5":4,"n4":130,"n3":200,"needBuilding":[[45,5,false],[46,3,false]],"n2":140,"type":42,"bid":34201},{"n1":130,"time":1072,"n5":3,"n4":80,"n3":120,"needBuilding":[[22,5,true],[28,3,false]],"n2":125,"type":30,"bid":23001},{"n1":400,"time":446,"n5":4,"n4":300,"n3":600,"needBuilding":[[22,5,true],[45,10,false]],"n2":450,"type":43,"bid":34301},{"n1":1200,"time":670,"n5":4,"n4":600,"n3":1200,"needBuilding":[[22,10,true],[45,10,false]],"n2":1000,"type":26,"bid":22601}]],"synRes":[[129,721,3272,6722,105,245],[101900,101900,101900,102500,132,245]],"code":0}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"index":35,"tp":21,"ub":[[{"n1":75,"time":402,"n5":4,"n4":75,"n3":105,"needBuilding":[[22,3,true],[10,1,true],[11,1,true]],"n2":70,"type":25,"bid":22501},{"n1":600,"time":291,"n5":2,"n4":300,"n3":500,"needBuilding":[[22,3,true],[44,1,true]],"n2":550,"type":52,"bid":35201}],[{"n1":180,"time":268,"n5":1,"n4":50,"n3":90,"needBuilding":[[22,3,true],[41,3,false]],"n2":150,"type":45,"bid":34501},{"n1":140,"time":268,"n5":3,"n4":120,"n3":360,"needBuilding":[[45,1,false]],"n2":200,"type":47,"bid":34701},{"n1":130,"time":268,"n5":4,"n4":120,"n3":400,"needBuilding":[[45,3,false]],"n2":170,"type":46,"bid":34601},{"n1":200,"time":291,"n5":4,"n4":130,"n3":200,"needBuilding":[[45,5,false],[46,3,false]],"n2":140,"type":42,"bid":34201},{"n1":130,"time":1072,"n5":3,"n4":80,"n3":120,"needBuilding":[[22,5,true],[28,3,false]],"n2":125,"type":30,"bid":23001},{"n1":400,"time":446,"n5":4,"n4":300,"n3":600,"needBuilding":[[22,5,true],[45,10,false]],"n2":450,"type":43,"bid":34301},{"n1":1200,"time":670,"n5":4,"n4":600,"n3":1200,"needBuilding":[[22,10,true],[45,10,false]],"n2":1000,"type":26,"bid":22601}]],"synRes":[[129,721,3272,6722,105,245],[101900,101900,101900,102500,132,245]],"code":0}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"index":36,"tp":21,"ub":[[{"n1":75,"time":402,"n5":4,"n4":75,"n3":105,"needBuilding":[[22,3,true],[10,1,true],[11,1,true]],"n2":70,"type":25,"bid":22501},{"n1":600,"time":291,"n5":2,"n4":300,"n3":500,"needBuilding":[[22,3,true],[44,1,true]],"n2":550,"type":52,"bid":35201}],[{"n1":180,"time":268,"n5":1,"n4":50,"n3":90,"needBuilding":[[22,3,true],[41,3,false]],"n2":150,"type":45,"bid":34501},{"n1":140,"time":268,"n5":3,"n4":120,"n3":360,"needBuilding":[[45,1,false]],"n2":200,"type":47,"bid":34701},{"n1":130,"time":268,"n5":4,"n4":120,"n3":400,"needBuilding":[[45,3,false]],"n2":170,"type":46,"bid":34601},{"n1":200,"time":291,"n5":4,"n4":130,"n3":200,"needBuilding":[[45,5,false],[46,3,false]],"n2":140,"type":42,"bid":34201},{"n1":130,"time":1072,"n5":3,"n4":80,"n3":120,"needBuilding":[[22,5,true],[28,3,false]],"n2":125,"type":30,"bid":23001},{"n1":400,"time":446,"n5":4,"n4":300,"n3":600,"needBuilding":[[22,5,true],[45,10,false]],"n2":450,"type":43,"bid":34301},{"n1":1200,"time":670,"n5":4,"n4":600,"n3":1200,"needBuilding":[[22,10,true],[45,10,false]],"n2":1000,"type":26,"bid":22601}]],"synRes":[[129,721,3272,6722,105,245],[101900,101900,101900,102500,132,245]],"code":0}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"index":37,"tp":21,"ub":[[{"n1":75,"time":402,"n5":4,"n4":75,"n3":105,"needBuilding":[[22,3,true],[10,1,true],[11,1,true]],"n2":70,"type":25,"bid":22501},{"n1":600,"time":291,"n5":2,"n4":300,"n3":500,"needBuilding":[[22,3,true],[44,1,true]],"n2":550,"type":52,"bid":35201}],[{"n1":180,"time":268,"n5":1,"n4":50,"n3":90,"needBuilding":[[22,3,true],[41,3,false]],"n2":150,"type":45,"bid":34501},{"n1":140,"time":268,"n5":3,"n4":120,"n3":360,"needBuilding":[[45,1,false]],"n2":200,"type":47,"bid":34701},{"n1":130,"time":268,"n5":4,"n4":120,"n3":400,"needBuilding":[[45,3,false]],"n2":170,"type":46,"bid":34601},{"n1":200,"time":291,"n5":4,"n4":130,"n3":200,"needBuilding":[[45,5,false],[46,3,false]],"n2":140,"type":42,"bid":34201},{"n1":130,"time":1072,"n5":3,"n4":80,"n3":120,"needBuilding":[[22,5,true],[28,3,false]],"n2":125,"type":30,"bid":23001},{"n1":400,"time":446,"n5":4,"n4":300,"n3":600,"needBuilding":[[22,5,true],[45,10,false]],"n2":450,"type":43,"bid":34301},{"n1":1200,"time":670,"n5":4,"n4":600,"n3":1200,"needBuilding":[[22,10,true],[45,10,false]],"n2":1000,"type":26,"bid":22601}]],"synRes":[[129,721,3272,6722,105,245],[101900,101900,101900,102500,132,245]],"code":0}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"index":38,"tp":21,"ub":[[{"n1":75,"time":402,"n5":4,"n4":75,"n3":105,"needBuilding":[[22,3,true],[10,1,true],[11,1,true]],"n2":70,"type":25,"bid":22501},{"n1":600,"time":291,"n5":2,"n4":300,"n3":500,"needBuilding":[[22,3,true],[44,1,true]],"n2":550,"type":52,"bid":35201}],[{"n1":180,"time":268,"n5":1,"n4":50,"n3":90,"needBuilding":[[22,3,true],[41,3,false]],"n2":150,"type":45,"bid":34501},{"n1":140,"time":268,"n5":3,"n4":120,"n3":360,"needBuilding":[[45,1,false]],"n2":200,"type":47,"bid":34701},{"n1":130,"time":268,"n5":4,"n4":120,"n3":400,"needBuilding":[[45,3,false]],"n2":170,"type":46,"bid":34601},{"n1":200,"time":291,"n5":4,"n4":130,"n3":200,"needBuilding":[[45,5,false],[46,3,false]],"n2":140,"type":42,"bid":34201},{"n1":130,"time":1072,"n5":3,"n4":80,"n3":120,"needBuilding":[[22,5,true],[28,3,false]],"n2":125,"type":30,"bid":23001},{"n1":400,"time":446,"n5":4,"n4":300,"n3":600,"needBuilding":[[22,5,true],[45,10,false]],"n2":450,"type":43,"bid":34301},{"n1":1200,"time":670,"n5":4,"n4":600,"n3":1200,"needBuilding":[[22,10,true],[45,10,false]],"n2":1000,"type":26,"bid":22601}]],"synRes":[[129,721,3272,6722,105,245],[101900,101900,101900,102500,132,245]],"code":0}
//		http://s1.limingdiguo.com/updatebuilding.do:HTTP/1.1 200 OK
//		{"res":[115,195,80,90,0],"index":39,"status":"正在升级中","enhanceNextLV":7,"tp":53,"lv":1,"synRes":[[129,721,3272,6722,105,245],[101900,101900,101900,102500,132,245]],"code":0,"enhance":4,"time":335,"nextBid":35302,"updateQueue":[1167534,"城墙",2,4204,"13:14",35302,39,0],"bid":35301}

		JSONObject object = new JSONObject(json);
//		System.out.println(object);
		id = object.getInt("index");
		bid = object.optInt("nextBid");
		int tp = object.getInt("tp");
		type = typeMap.get(tp);
		if(type==null) type = "unknows["+tp+"]";
		status = object.optString("status");
		allowUpdate = "Build_Ok".equals(status);
		JSONArray res = object.optJSONArray("res");
		if(res!=null) {
			useWood = res.getInt(0);
			useStone = res.getInt(1);
			useIron = res.getInt(2);
			useFood = res.getInt(3);
		}
	}
}
