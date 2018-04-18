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
	//��һ�ֳ�ʼ��	
       int[] array1=new int[5];

 //�������
       Arrays.fill(array1,5);
       CTestArrays.output(array1);
 //�������2��3��Ԫ�ظ�ֵΪ8
       Arrays.fill(array1,2,4,8);
       CTestArrays.output(array1);
       
 //�ڶ��ֳ�ʼ��
       int array2[]={7,8,3,2,12,6,3,5,4};
  //������ĵ�2������6����������
       Arrays.sort(array2,2,7);
       CTestArrays.output(array2);
  //�����������������
       Arrays.sort(array2);
       CTestArrays.output(array2);
  //�Ƚ�����Ԫ���Ƿ���ͬ     
       System.out.println(Arrays.equals(array1, array2));//---false
       int[] array3=array1.clone();
       System.out.println(Arrays.equals(array1, array3));
  //ʹ�ö��������㷨����ָ��Ԫ�����ڵ��±�
       Arrays.sort(array1);
       System.out.println(Arrays.binarySearch(array1, 3));
       //��������ڣ����ظ���(����ĸ���)
       System.out.println(Arrays.binarySearch(array1, 9));
       
  //copyof() ��String[]���鸴�Ƴ�һ����String
       String[] stra1={"12","23","ni"};
       String[] strarray=Arrays.copyOf(stra1, stra1.length);  //
  //String toString() ��һ������ת��Ϊ�ַ���
       System.out.println(Arrays.toString(strarray));
       
       //�����ֳ�ʼ��
       int[] A2=new int[]{1,2,3,4,5};
  //ѭ������Arrays�ķ���
       for(int i=0;i<stra1.length;i++){
    	   System.out.println(stra1[i]);
       }
       
       System.out.println(new Date()); //Date()��ʱ����
     
  //int[]����       
       int[] intArray = { 1, 2, 3, 4, 5 }; //int[]ת��ΪString[]
       String intArrayString = Arrays.toString(intArray);    
       System.out.println(intArrayString);
       for(int i=0;i<5;i++){
       	System.out.println(intArray[i]);
       }
 // ��������int[]����
	// ArrayUtils��Ҫorg.apache.commons.lang_2.6.0.v201404270220.jar
		int[] intArray2 = { 6, 7, 8, 9, 10 };
		int[] combinedIntArray = ArrayUtils.addAll(intArray, intArray2);
 //����һ������ 
		ArrayUtils.reverse(intArray);
		System.out.println(Arrays.toString(intArray));
 //�Ƴ������е�Ԫ�� 
		int[] removed = ArrayUtils.removeElement(intArray, 3);//create a new array
		System.out.println(Arrays.toString(removed));
		
       System.exit(0);  //����Ϊ�˽���ض����������
	}

}
