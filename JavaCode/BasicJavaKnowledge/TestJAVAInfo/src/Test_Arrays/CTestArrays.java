package Test_Arrays;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;

public class CTestArrays {
	
	public static void output(int[] arr){
		if(arr !=null){
			for(int i=0;i<arr.length;i++){
				System.out.println(arr[i]+" ");
			}
		}			
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//第一种初始化	
       int[] array1=new int[5];

 //填充数组
       Arrays.fill(array1,5);
       CTestArrays.output(array1);
 //将数组第2和3个元素赋值为8
       Arrays.fill(array1,2,4,8);
       CTestArrays.output(array1);
       
 //第二种初始化
       int array2[]={7,8,3,2,12,6,3,5,4};
  //对数组的第2个到第6个进行排序
       Arrays.sort(array2,2,7);
       CTestArrays.output(array2);
  //对整个数组进行排序
       Arrays.sort(array2);
       CTestArrays.output(array2);
  //比较数组元素是否相同     
       System.out.println(Arrays.equals(array1, array2));//---false
       int[] array3=array1.clone();
       System.out.println(Arrays.equals(array1, array3));
  //使用二分搜索算法查找指定元素所在的下标
       Arrays.sort(array1);
       System.out.println(Arrays.binarySearch(array1, 3));
       //如果不存在，返回负数(任意的负数)
       System.out.println(Arrays.binarySearch(array1, 9));
       
  //copyof() 将String[]数组复制成一个新String
       String[] stra1={"12","23","ni"};
       String[] strarray=Arrays.copyOf(stra1, stra1.length);  //
  //String toString() 将一个数组转换为字符串
       System.out.println(Arrays.toString(strarray));
       
       //第三种初始化
       int[] A2=new int[]{1,2,3,4,5};
  //循环访问Arrays的方法
       for(int i=0;i<stra1.length;i++){
    	   System.out.println(stra1[i]);
       }
       
       System.out.println(new Date()); //Date()是时间类
     
  //int[]数组       
       int[] intArray = { 1, 2, 3, 4, 5 }; //int[]转换为String[]
       String intArrayString = Arrays.toString(intArray);    
       System.out.println(intArrayString);
       for(int i=0;i<5;i++){
       	System.out.println(intArray[i]);
       }
 // 连接两个int[]数组
	// ArrayUtils需要org.apache.commons.lang_2.6.0.v201404270220.jar
		int[] intArray2 = { 6, 7, 8, 9, 10 };
		int[] combinedIntArray = ArrayUtils.addAll(intArray, intArray2);
 //逆向一个数组 
		ArrayUtils.reverse(intArray);
		System.out.println(Arrays.toString(intArray));
 //移除数组中的元素 
		int[] removed = ArrayUtils.removeElement(intArray, 3);//create a new array
		System.out.println(Arrays.toString(removed));
		
       System.exit(0);  //这是为了解决重定向输出问题
	}

}
