import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class TSP {
	private static Map<String, Integer> cityIdMap = new HashMap<String, Integer>();
	static {
		cityIdMap.put("Hefei", 1);
		cityIdMap.put("Beijing", 2);
		cityIdMap.put("Shanghai", 4);
		cityIdMap.put("Guangzhou", 8);
		cityIdMap.put("Chengdu", 16);
	}
	private static Map<Integer,Integer> cityDistanceMap = new HashMap<Integer, Integer>();
	static {
		cityDistanceMap.put(1|2, 1044);
		cityDistanceMap.put(1|4, 472);
		cityDistanceMap.put(1|8, 1257);
		cityDistanceMap.put(1|16, 1615);
		cityDistanceMap.put(2|4, 1244);
		cityDistanceMap.put(2|8, 2174);
		cityDistanceMap.put(2|16, 1854);
		cityDistanceMap.put(4|8, 1529);
		cityDistanceMap.put(4|16, 2095);
		cityDistanceMap.put(8|16, 1954);
	}
	private static String[] city = new String[]{"Beijing","Shanghai","Guangzhou","Chengdu"};
	private static int[] index = new int[]{0,1,2,3};
	private static int length = index.length;
	private static String start = "Hefei";
	private static int min = Integer.MAX_VALUE;
	private static int[] minindex = null;
	private static List<int[]> list = new LinkedList<int[]>();
	// 存储结果的堆栈
	private static Stack<Integer> stack = new Stack<Integer>();
	
	public static void main(String[] args) {
		enumerating();
		StringBuilder sb = new StringBuilder();
		sb.append("min:");
		sb.append(min);
		sb.append("--");
		sb.append(start);
		for(int i:minindex) {
			sb.append("-->");
			sb.append(city[i]);
		}
		sb.append("-->");
		sb.append(start);
		System.out.println(sb.toString());
	}
	
	private static void enumerating() {
		getSequence(0, length);
		label:for(int i = 0; i < list.size(); i++) {
			int sum = 0;
			int[] index = list.get(i);
			int length = index.length;
			int last = cityIdMap.get(start);
			for(int j = 0; j < length; j++) {
				int temp = cityIdMap.get(city[index[j]]);
				sum += cityDistanceMap.get(last|temp);
				if(sum>min) continue label;
				last = temp;
			}
			sum += cityDistanceMap.get(last|cityIdMap.get(start));
//			StringBuilder sb = new StringBuilder();
//			sb.append(sum);
//			sb.append("--");
//			sb.append(start);
//			for(int ii:index) {
//				sb.append("-->");
//				sb.append(city[ii]);
//			}
//			System.out.println(sb.toString());
			if(sum<min) {
				min = sum;
				minindex = index;
			}
		}
	}
	
	/**
	 * 获得指定数组从指定开始的指定数量的数据组合<br>
	 * @param arr 指定的数组
	 * @param begin 开始位置
	 * @param num 获得的数量
	 */
	public static void getSequence(int begin, int num) {
		if(num == 0) {
			// 找到一个结果
			int[] temp = new int[stack.size()];
			for(int i = 0; i<temp.length; i++) {
				temp[i] = (Integer)stack.get(i);
			}
			list.add(temp);
//			System.out.println(stack);
		} else {
			// 循环每个可用的元素
			for(int i = begin; i < index.length; i++) {
				// 当前位置数据放入结果堆栈
				stack.push(index[i]);
				// 将当前数据与起始位置数据交换
				swap(begin, i);
				// 从下一个位置查找其余的组合
				getSequence(begin + 1, num - 1);
				// 交换回来
				swap(begin, i);
				// 去除当前数据       
				stack.pop();
			}
		}
	}
	
	/**
	 * 交换数组的2个元素
	 * @param arr 数组
	 * @param from 位置1
	 * @param to 位置2
	 */
	public static void swap(int from, int to) {
		if(from == to) {
			return;
		}
		int tmp = index[from];
		index[from] = index[to];
		index[to] = tmp;
	}
}
