package Test_HashSet;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CTestHashSet {
	
	public static char FindFirstRepeat(String strValue, Integer nSize){
		char[] a=strValue.toCharArray(); //String转换为数组存储
		HashSet hTest= new HashSet();
		for(int i=0;i<nSize;i++){
			if(!hTest.add(a[i])){
				return a[i];
			}
		}	
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    //添加元素和遍历
		HashSet hSet=new HashSet();
		hSet.add("1st");
		hSet.add("2nd");
		hSet.add(new Integer(3)); //整型
		hSet.add(new Double(4.0));
		hSet.add("2nd");            //重复元素，未被添加
		hSet.add(new Integer(3));      //重复元素，未被添加
		hSet.add(new Date());
		hSet.add(null);  //可以是null
		System.out.println("开始：size="+hSet.size());
		Iterator it=hSet.iterator();
		while(it.hasNext())
		{
		   Object o=it.next();
		   System.out.println(o);
		}
		hSet.remove("2nd");
		System.out.println("移除元素后：size="+hSet.size());
		System.out.println(hSet);
   
	///向HashSet中添加一个数组
		int[] nArray={10,11,12};
		hSet.add(nArray);
	//向HashSet中添加一个自定义对象
		//Cat cat1=new Cat("asd",2);
		//hSet.add(cat1);
	//可设计出高效查找重复元素的算法	
	System.out.println(FindFirstRepeat("qweyerww24d34sq23s3",19));	
	
	///HashSet继承Set接口
	Set<String> SetA=(Set<String>)new HashSet();
	SetA.add(new String("abc"));
	SetA.add("ww");
	//SetA.add(new Integer(2));  这样不行()中应该是String
	//遍历该HashSet的过程
	Iterator<String> ite=SetA.iterator();
	while(ite.hasNext()){
		System.out.println(ite.next());
	}
	
	}

}
