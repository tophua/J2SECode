package Test_TreeMap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class CTestTreeMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  //创建一个自定义比较器  
	MyComparator comparator = new MyComparator();  
   //初始化TreeMap
	Map<TestTemp,String> map1=new TreeMap<TestTemp,String>(comparator); 
	map1.put(new TestTemp("电动",2000),"001");  
	map1.put(new TestTemp("王五",4000),"002");  
	map1.put(new TestTemp("天天",7000),"003");  
	map1.put(new TestTemp("事实",29000),"004");  
	map1.put(new TestTemp("哦哦",29000),"009");  
    System.out.println(map1);  	
    
///遍历TreeMap的键值对
	//第一步：根据entrySet()获取TreeMap的“键值对”的Set集合。
    //第二步：通过Iterator迭代器遍历“第一步”得到的集合。	
    String strteg ="";  
    Iterator iter = map1.entrySet().iterator();  
    while(iter.hasNext()) {  
        Map.Entry entry = (Map.Entry)iter.next();  
        // 获取key  
        TestTemp  key = (TestTemp)entry.getKey();  
        // 获取value  
        strteg = (String)entry.getValue();  
        } 
    
//遍历TreeMap的键	
    //第一步：根据keySet()获取TreeMap的“键”的Set集合。
    //第二步：通过Iterator迭代器遍历“第一步”得到的集合。
    TestTemp key2 = null;  
    String strteg2 ="";   
    Iterator iter2 = map1.keySet().iterator();  
    while (iter2.hasNext()) {  
       // 获取key  
        key2 = (TestTemp)iter2.next();  
       // 根据key，获取value  
        strteg2 = (String)map1.get(key2);  
       }  
 
 ///遍历TreeMap的值
    //第一步：根据value()获取TreeMap的“值”的集合。
    //第二步：通过Iterator迭代器遍历“第一步”得到的集合。   
    String strvalue = "";  
    Collection c = map1.values();  
    Iterator iter3= c.iterator();  
    while (iter3.hasNext()) {  
    	strvalue = (String)iter3.next();  
   }  
     
	}

}
