import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Enumerating {
	public static void main(String[] args) {
		Map<String, Integer> cityIdMap = new HashMap<String, Integer>();
		cityIdMap.put("Hefei", 1);
		cityIdMap.put("Beijing", 2);
		cityIdMap.put("Shanghai", 4);
		cityIdMap.put("Guangzhou", 8);
		cityIdMap.put("Chengdu", 16);
		Map<Integer,Integer> cityDistanceMap = new HashMap<Integer, Integer>();
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
		String[] city = new String[]{"Beijing","Shanghai","Guangzhou","Chengdu"};
		String start = "Hefei";
		int min = Integer.MAX_VALUE;
		int[] minindex = null;
		int[][] indexs = getIndex(new int[]{0,1,2,3});
		label:for(int i = 0; i < indexs.length; i++) {
			int sum = 0;
			int[] index = indexs[i];
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
	
	private static int[][] getIndex(int... num) {
		int length = num.length;
		switch(length) {
		case 1:
			return new int[][]{{num[0]}};
		case 2:
			return new int[][]{{num[0],num[1]},{num[1],num[0]}};
		default:
			List<int[]> list = new ArrayList<int[]>();
			for(int i = 0; i<length; i++) {
				int[] next = new int[length-1];
				for(int j = 0, k = 0; k<next.length; j++) {
					if(i!=j) next[k++] = num[j];
				}
				int[][] temp = getIndex(next);
				for(int j = 0; j < temp.length; j++) {
					int[] a = new int[length];
					a[0] = num[i];
					System.arraycopy(temp[j], 0, a, 1, temp[j].length);
					list.add(a);
				}
			}
			int[][] result = new int[list.size()][length];
			for(int i = 0; i<list.size(); i++) {
				result[i] = list.get(i);
			}
			return result;
		}
	}
}
