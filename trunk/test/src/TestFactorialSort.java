import java.util.Arrays;


/**
 * 用数组[1,2,3,4,5,6]组合出不同的数字
 * 使用的数字不能重复
 * 求出第10大的数字组合
 */
public class TestFactorialSort {
	private static int[] array = new int[6];
	private static int length = array.length;
	private static int target = 10;
	private static int[] factorialArray = new int[10];
	static {
		int i = 0;
		while(i<length) array[i++] = i;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(array));
		for(int i = 0; i<length; i++) sortArray(i);
		System.out.println(Arrays.toString(array));
//		factorial(9);
//		System.out.println(Arrays.toString(factorialArray));
		while(target>1) {
			for(int i = 1; i<factorialArray.length; i++) {
				int num = factorial(i);
				if(num>=target) {
					target -= factorial(i-1);
					i = length-i;
					int temp = array[i];
					array[i] = array[++i];
					array[i] = temp;
					break;
				}
			}
		}
		System.out.println(Arrays.toString(array));
	}
	
	/**
	 * 排序数组
	 * @param index
	 */
	private static void sortArray(int index) {
		for(int i = 0; i<length; i++) {
			if(index!=i) {
				int temp1 = array[index];
				int temp2 = array[i];
				if(temp2<temp1) {
					array[index] = temp2;
					array[i] = temp1;
				}
			}
		}
	}
	
	/**
	 * 获取9!以内的阶乘结果
	 * @param num
	 * @return
	 */
	private static int factorial(int num) {
		if(num+1>factorialArray.length) {
			throw new IllegalArgumentException("数字过大无法计算~");
		}
		int result = factorialArray[num];
		if(result>0) return result;
		if(num<0) {
			throw new IllegalArgumentException("阶乘的数字必须是非负数~");
		} else if(num==0) {
			return factorialArray[0] = 1;
		}
		return factorialArray[num] = factorial(num-1)*num;
	}
	
	/**
	 * 判断某数是在哪个阶乘里面
	 * @param num
	 * @return
	 */
	private static int contain(int num) {
		if(num<1) {
			throw new IllegalArgumentException("数字过小无法判断~");
		}
		for(int i = 1; i<factorialArray.length; i++) {
			if(factorial(i)>=num) return i;
		}
		throw new IllegalArgumentException("数字过大无法判断~");
	}
}
