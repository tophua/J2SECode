package Test_HashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class CTestHashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    ///hashMap是继承Map接口
		 Map map=new HashMap();                
         map.put("a", "aaa");    
         map.put("b", "bbb");    
         map.put("c", "ccc");    
         map.put("d", "ddd");    
     //迭代输出hashMap的内容   ---效率低    
         //适合由key值查找遍历的
         Iterator iterator = map.keySet().iterator();                
         while (iterator.hasNext()) {    
          Object key = iterator.next();  
          Object val = map.get(key);
          System.out.println("map.get(key) is :"+map.get(key));    
         }  
       //高效的遍历方法
         Iterator iter = map.entrySet().iterator();
         while (iter.hasNext()) {
         Map.Entry entry = (Map.Entry) iter.next();
         Object key = entry.getKey();
         Object val = entry.getValue();
         }
             
         
  ///HashMap的使用
         HashMap<String, String> hashMap = new HashMap<String, String>();  
         hashMap.put("cn", "中国");  
         hashMap.put("jp", "日本");  
         hashMap.put("fr", "法国");  
           
         System.out.println(hashMap);  
         System.out.println("cn:" + hashMap.get("cn"));  //由key得到Value值
         System.out.println(hashMap.containsKey("cn")); //判断是否包含该key值  
         System.out.println(hashMap.keySet());  //key值的集合
         System.out.println(hashMap.isEmpty());  
           
         hashMap.remove("cn");  
         System.out.println(hashMap.containsKey("cn"));  
        
     //在for-each循环中使用entries来遍历
         for(Map.Entry<String, String> entry1 : hashMap.entrySet()){
        	 System.out.println("Key = " + entry1.getKey() + ", Value = " + entry1.getValue());  
         }
         
     //在for-each循环中遍历keys或values
     //(该方法比entrySet遍历在性能上稍好（快了10%），而且代码更加干净)
         //遍历map中的键  
        for (String key : hashMap.keySet()) {  
              System.out.println("Key = " + key);  
         }  
         //遍历map中的值  
         for (String value : hashMap.values()) {  
            System.out.println("Value = " + value);  
         } 
  
     //使用Iterator遍历
         //使用泛型
        Iterator<Map.Entry<String, String>> entries = hashMap.entrySet().iterator();  
        while (entries.hasNext()) {  
            Map.Entry<String, String> entry = entries.next();  
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
        }  
         //不使用泛型
        Iterator entries1 = hashMap.entrySet().iterator();  
        while (entries1.hasNext()) {  
           Map.Entry entry = (Map.Entry) entries1.next();  
           String key = (String)entry.getKey();  
           String value = (String)entry.getValue();  
           System.out.println("Key = " + key + ", Value = " + value);  
        }  
 
    //通过键找值遍历（效率低）
       for (String key : hashMap.keySet()) {  
    	   String value = hashMap.get(key);  
           System.out.println("Key = " + key + ", Value = " + value);  
        }
                 
    //采用Iterator遍历HashMap  
         Iterator it = hashMap.keySet().iterator();  
         while(it.hasNext()) {  
             String key = (String)it.next();  
             System.out.println("key:" + key);  
             System.out.println("value:" + hashMap.get(key));  
         }  
     //根据key查找value
       Object bmap=hashMap.isEmpty();
       Object bValue=hashMap.get("32");  //相当于C++的find()操作，如果没有这个key，返回null
       Object bVa=hashMap.containsValue("中国"); 
       
    //遍历HashMap的另一个方法  
         Set<Entry<String, String>> sets = hashMap.entrySet();  
         for(Entry<String, String> entry : sets) {  
             System.out.print(entry.getKey() + ", ");  
             System.out.println(entry.getValue());  
         }  
    //hashMap实现同步
         // create map
         Map<String,String> map2 = new HashMap<String,String>();

         // populate the map
         map2.put("1","ALIVE ");
         map2.put("2","IS");
         map2.put("3","AWESOME");
   //将一个Map对象追加到另一个map中
         map2.putAll(hashMap);
         // create a synchronized map
         Map<String,String> syncMap = Collections.synchronizedMap(map2);          
         System.out.println("Synchronized map :"+syncMap);   
         
         
  //HashMap与list结合的例子
   /** 
          * 在不创建学生类的情况下，从键盘输入n个学生信息(学号，姓名，年龄）， 
          * 输入完成后，打印出各个学生信息 
          * @author ccna_zhang 
          * 
   */      
         //定义保存学生信息的List，元素类型为HashMap  
//         List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();  
//         Scanner input = new Scanner(System.in);  
//           
//         System.out.println("请输入学生的信息,y表示继续，n表示退出");  
//         while("y".equals(input.next())) {  
//             HashMap<String, Object> map1 = new HashMap<String, Object>();  
//             System.out.println("请输入学号");  
//             map1.put("studentno", input.next());  
//             System.out.println("请输入姓名");  
//             map1.put("name", input.next());  
//             System.out.println("请输入年龄");  
//             map1.put("age", input.nextInt());  
//             list.add(map1);  
//             System.out.println("请继续输入学生的信息,y表示继续，n表示退出");  
//         }  
//           
//         System.out.println("输入的学生信息为:");  
//         System.out.println("学生数量为:" + list.size());  
//           
//         Iterator<HashMap<String, Object>> it1 = list.iterator();  
//         int i = 1;  
//         while(it.hasNext()) {  
//             HashMap<String, Object> stuMap = it1.next();  
//             System.out.print("第" + i + "个学生的信息为");  
//             System.out.println("学号:" + stuMap.get("studentno") + " ,姓名:" + stuMap.get("name") + " ,年龄:" + stuMap.get("age"));  
//         }       
//         
         
	}

}
