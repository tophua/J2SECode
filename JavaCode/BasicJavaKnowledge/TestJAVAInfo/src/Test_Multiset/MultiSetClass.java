package Test_Multiset;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class MultiSetClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<String> wordList=new ArrayList<String>();
    //统计List中某个字符串的个数       
		HashMultiset<String> mulset=HashMultiset.create();
		mulset.addAll(wordList);
		Integer count=mulset.count("this"); ///统计“this”的个数
	
	//初始化等操作
		Multiset<String> multiset=HashMultiset.create();
		 multiset.add("a");  
		 multiset.add("b");  
		 multiset.add("c");  
		 multiset.add("d");  
		 multiset.add("a");  
		 multiset.add("b");  
		 multiset.add("c");  
		 multiset.add("b");  
		 multiset.add("b");  
		 multiset.add("b"); //存储的结果是[a x 2, b x 5, c x 2, d]
         Integer icount=multiset.count("b");
         Integer iSize=multiset.size();
         //遍历，用set输出的不是重复的
         Set<String> set=multiset.elementSet();//set为[a, b, c, d]
         for(String s:set){
        	 System.out.println(s);
         }
         
         //循环迭代， 这样输出的是所有的信息，会重复的
		 Iterator<String> iter1=multiset.iterator();
		 while(iter1.hasNext()){
			 System.out.println(iter1.next());
		 }
		 
		 //遍历，这样遍历会获得每个元素且元素个数
		 for (Multiset.Entry<String> entry : multiset.entrySet())  
		 {  
			 System.out.println("Element: "+entry.getElement() +
					 ", Occurrence(s): " + entry.getCount());              
		 } 

		 multiset.remove("b",2);  //这是移除2个b元素
		 icount=multiset.count("b");
		 
	}

}
