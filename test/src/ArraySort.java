import java.util.Arrays;
import java.util.Random;

public class ArraySort {
	private static Random random = new Random();
	
	/**
	 * 获取随机整数数组
	 * @param arrayLength
	 * @param maxNum
	 * @return
	 */
    private static int[] getRandomArray(int arrayLength, int maxNum){
    	int[] array = new int[arrayLength];
        for(int i=0;i<array.length;i++){     
        	array[i] = random.nextInt(maxNum);      
        }
        return array;
    }
	
    /**
     * 打印数组
     * @param array
     */
    private static void printArray(int[] array){
//    	for(int i=0;i<array.length;i++){
//            System.out.println(i+1 + ":" + array[i]);
//        }
    	System.out.println(Arrays.toString(array));
    }
    
    /**
     * 交换数值
     * @param array
     * @param i
     * @param j
     */
    private static void swap(int[] array,int i, int j){
    	int temp = array[i];
    	array[i] = array[j];
    	array[j] = temp;
    }
    
	/**
	 * 插入排序
	 * @param array
	 * @return
	 */
    private static int[] insertSort(int[] array){
    	//与前边排序好的子序列比较，向前依次比较相邻元素，将元素推到正确位置
    	for(int i=1;i<array.length;i++){
    		for(int j=i;j>0 && array[j]<array[j-1];j--){
    			swap(array,j,j-1);
    		}
    	}
    	return array;
    }
    
	/**
	 * 冒泡排序
	 * @param array
	 * @return
	 */
    private static int[] bubbleSort(int[] array){
    	//从头开始向后依次比较相邻元素，将最大值推到数组尾部
    	for(int i=0;i<array.length-1;i++){
    		for(int j=0;j<array.length-i-1;j++){
    			if(array[j]>array[j+1]){
    				swap(array,j,j+1);
    			}
    		}
    	}
    	return array;
    }
    
	/**
	 * 选择排序
	 * @param array
	 * @return
	 */
    private static int[] selectionSort(int[] array){
    	//每次循环找出相对最小值，并交换到头部
    	for(int i=0;i<array.length-1;i++){
    		int lowIndex = i;
    		for(int j=i;j<array.length;j++){
    			if(array[j]<array[lowIndex])
    				lowIndex = j;
    		}
    		swap(array,lowIndex,i);
    	}
    	return array;
    }
    
    /**
     * 快速排序
     * @param array
     * @return
     */
    private static int[] quickSort(int[] array) {
    	//选一个基点，小的放前面大的放后面，再从两边区域各找一个基点重复判断下去
    	quickSortPartition(array, 0, array.length-1);
    	return array;
    }
    
    private static void quickSortPartition(int[] array, int begin, int end) {
    	if(end>begin) {
    		//随机指定基数
//    		int partition = random.nextInt(end-begin+1)+begin;
    		//以中间的值做为基数
    		int partition = (end-begin)/2+begin;
    		//以开始的值做为基数
//    		int partition = begin;
    		int temp = array[partition];
    		swap(array, partition, end);
    		partition = end;
    		for(int i = begin; i<partition; i++) {
    			if(array[i]>=temp) {
    				swap(array, i--, --partition);
    			}
    		}
    		swap(array, partition, end);
    		quickSortPartition(array, begin, partition-1);
    		quickSortPartition(array, partition+1, end);
    	}
    }
    
    public static void main(String[] args) {
		int[] array = ArraySort.getRandomArray(20,1000);
		printArray(array);
		System.out.println("------------------------");
    	System.out.println("以上为初始数组");
    	System.out.println("------------------------");
    	compare(array);
	}
    
    private static void compare(int[] array) {
		//插入排序
    	int[] insertArray = array.clone();
    	long time = System.nanoTime();
		insertSort(insertArray);
		time = System.nanoTime()-time;
		System.out.println("------------------------");
		printArray(insertArray);
		System.out.println("insertSort耗时：" + time);
    	System.out.println("------------------------");
    	
    	//冒泡排序
    	int[] bubbleArray = array.clone();
    	time = System.nanoTime();
		bubbleSort(bubbleArray);
		time = System.nanoTime()-time;
		System.out.println("------------------------");
		printArray(bubbleArray);
		System.out.println("bubbleSort耗时：" + time);
    	System.out.println("------------------------");
    	
    	//选择排序
    	int[] selectionArray = array.clone();
    	time = System.nanoTime();
		selectionSort(selectionArray);
		time = System.nanoTime()-time;
		System.out.println("------------------------");
		printArray(selectionArray);
		System.out.println("selectionSort耗时：" + time);
    	System.out.println("------------------------");
    	
    	//快速排序
    	int[] quickArray = array.clone();
    	time = System.nanoTime();
    	quickSort(quickArray);
		time = System.nanoTime()-time;
		System.out.println("------------------------");
		printArray(quickArray);
		System.out.println("quickSort耗时：" + time);
    	System.out.println("------------------------");
    }
}
