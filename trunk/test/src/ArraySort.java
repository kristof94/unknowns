import java.util.Arrays;

public class ArraySort {
	private static long swapcount, loopcount, time;
	
	//获取随机整数数组
    private static int[] getRandomArray(int arrayLength, int maxNum){
    	int[] array = new int[arrayLength];
        for(int i=0;i<array.length;i++){     
        	array[i] = (int)(Math.random()*maxNum);      
        }
        return array;
    }   
	
    //打印数组
    private static void printArray(int[] array){
//    	for(int i=0;i<array.length;i++){
//            System.out.println(i+1 + ":" + array[i]);
//        }
    	System.out.println(Arrays.toString(array));
    }
    
    //交换数值
    private static void swap(int[] array,int i, int j){
    	int temp = array[i];
    	array[i] = array[j];
    	array[j] = temp;
    }
    
    private static void resetCount(){
    	swapcount = loopcount = time = 0;
    }
    
	//插入排序
    private static int[] insertSort(int[] array){
    	//与前边排序好的子序列比较，向前依次比较相邻元素，将元素推到正确位置
    	for(int i=0;i<array.length;i++){
    		for(int j=i;j>0 && array[j]<array[j-1];j--){
    			swap(array,j,j-1);
    			loopcount++;
    			swapcount++;
    		}
    	}
    	return array;
    }
    
	//冒泡排序
    private static int[] bubbleSort(int[] array){
    	//从头开始向后依次比较相邻元素，将最大值推到数组尾部
    	for(int i=0;i<array.length-1;i++){
    		for(int j=0;j<array.length-i-1;j++){
    			if(array[j]>array[j+1]){
    				swap(array,j,j+1);
    				swapcount++;
    			}
    			loopcount++;
    		}
    	}
    	return array;
    }
    
	//选择排序
    private static int[] selectionSort(int[] array){
    	//每次循环找出相对最小值，并交换到头部
    	for(int i=0;i<array.length-1;i++){
    		int lowIndex = i;
    		for(int j=i;j<array.length;j++){
    			if(array[j]<array[lowIndex])
    				lowIndex = j;
    			loopcount++;
    		}
    		swap(array,lowIndex,i);
    		swapcount++;
    	}
    	return array;
    }
    public static void main(String[] args) {
		int[] array = ArraySort.getRandomArray(60,1000);
		ArraySort.printArray(array);
		System.out.println("------------------------");
    	System.out.println("以上为初始数组");
    	System.out.println("------------------------");
		
		//插入排序
    	int[] insertArray = array.clone();
    	ArraySort.resetCount();
    	time = System.nanoTime();
		ArraySort.insertSort(insertArray);
		time = System.nanoTime()-time;
		ArraySort.printArray(insertArray);
		System.out.println("------------------------");
		System.out.println("insertSort耗时：" + time);
    	System.out.println("insertSort交换次数为：" + swapcount);
    	System.out.println("insertSort循环次数为：" + loopcount);
    	System.out.println("------------------------");
    	
    	//冒泡排序
    	int[] bubbleArray = array.clone();
    	ArraySort.resetCount();
    	time = System.nanoTime();
		ArraySort.bubbleSort(bubbleArray);
		time = System.nanoTime()-time;
		ArraySort.printArray(bubbleArray);
		System.out.println("------------------------");
		System.out.println("bubbleSort耗时：" + time);
    	System.out.println("bubbleSort交换次数为：" + swapcount);
    	System.out.println("bubbleSort循环次数为：" + loopcount);
    	System.out.println("------------------------");
    	
    	//选择排序
    	int[] selectionArray = array.clone();
    	ArraySort.resetCount();
    	time = System.nanoTime();
		ArraySort.selectionSort(selectionArray);
		time = System.nanoTime()-time;
		ArraySort.printArray(selectionArray);
		System.out.println("------------------------");
		System.out.println("selectionSort耗时：" + time);
    	System.out.println("selectionSort交换次数为：" + swapcount);
    	System.out.println("selectionSort循环次数为：" + loopcount);
    	System.out.println("------------------------");
	}
}
