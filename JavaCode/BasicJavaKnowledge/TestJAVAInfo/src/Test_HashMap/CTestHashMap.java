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
    ///hashMap�Ǽ̳�Map�ӿ�
		 Map map=new HashMap();                
         map.put("a", "aaa");    
         map.put("b", "bbb");    
         map.put("c", "ccc");    
         map.put("d", "ddd");    
     //�������hashMap������   ---Ч�ʵ�    
         //�ʺ���keyֵ���ұ�����
         Iterator iterator = map.keySet().iterator();                
         while (iterator.hasNext()) {    
          Object key = iterator.next();  
          Object val = map.get(key);
          System.out.println("map.get(key) is :"+map.get(key));    
         }  
       //��Ч�ı�������
         Iterator iter = map.entrySet().iterator();
         while (iter.hasNext()) {
         Map.Entry entry = (Map.Entry) iter.next();
         Object key = entry.getKey();
         Object val = entry.getValue();
         }
             
         
  ///HashMap��ʹ��
         HashMap<String, String> hashMap = new HashMap<String, String>();  
         hashMap.put("cn", "�й�");  
         hashMap.put("jp", "�ձ�");  
         hashMap.put("fr", "����");  
           
         System.out.println(hashMap);  
         System.out.println("cn:" + hashMap.get("cn"));  //��key�õ�Valueֵ
         System.out.println(hashMap.containsKey("cn")); //�ж��Ƿ������keyֵ  
         System.out.println(hashMap.keySet());  //keyֵ�ļ���
         System.out.println(hashMap.isEmpty());  
           
         hashMap.remove("cn");  
         System.out.println(hashMap.containsKey("cn"));  
        
     //��for-eachѭ����ʹ��entries������
         for(Map.Entry<String, String> entry1 : hashMap.entrySet()){
        	 System.out.println("Key = " + entry1.getKey() + ", Value = " + entry1.getValue());  
         }
         
     //��for-eachѭ���б���keys��values
     //(�÷�����entrySet�������������Ժã�����10%�������Ҵ�����Ӹɾ�)
         //����map�еļ�  
        for (String key : hashMap.keySet()) {  
              System.out.println("Key = " + key);  
         }  
         //����map�е�ֵ  
         for (String value : hashMap.values()) {  
            System.out.println("Value = " + value);  
         } 
  
     //ʹ��Iterator����
         //ʹ�÷���
        Iterator<Map.Entry<String, String>> entries = hashMap.entrySet().iterator();  
        while (entries.hasNext()) {  
            Map.Entry<String, String> entry = entries.next();  
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
        }  
         //��ʹ�÷���
        Iterator entries1 = hashMap.entrySet().iterator();  
        while (entries1.hasNext()) {  
           Map.Entry entry = (Map.Entry) entries1.next();  
           String key = (String)entry.getKey();  
           String value = (String)entry.getValue();  
           System.out.println("Key = " + key + ", Value = " + value);  
        }  
 
    //ͨ������ֵ������Ч�ʵͣ�
       for (String key : hashMap.keySet()) {  
    	   String value = hashMap.get(key);  
           System.out.println("Key = " + key + ", Value = " + value);  
        }
                 
    //����Iterator����HashMap  
         Iterator it = hashMap.keySet().iterator();  
         while(it.hasNext()) {  
             String key = (String)it.next();  
             System.out.println("key:" + key);  
             System.out.println("value:" + hashMap.get(key));  
         }  
     //����key����value
       Object bmap=hashMap.isEmpty();
       Object bValue=hashMap.get("32");  //�൱��C++��find()���������û�����key������null
       Object bVa=hashMap.containsValue("�й�"); 
       
    //����HashMap����һ������  
         Set<Entry<String, String>> sets = hashMap.entrySet();  
         for(Entry<String, String> entry : sets) {  
             System.out.print(entry.getKey() + ", ");  
             System.out.println(entry.getValue());  
         }  
    //hashMapʵ��ͬ��
         // create map
         Map<String,String> map2 = new HashMap<String,String>();

         // populate the map
         map2.put("1","ALIVE ");
         map2.put("2","IS");
         map2.put("3","AWESOME");
   //��һ��Map����׷�ӵ���һ��map��
         map2.putAll(hashMap);
         // create a synchronized map
         Map<String,String> syncMap = Collections.synchronizedMap(map2);          
         System.out.println("Synchronized map :"+syncMap);   
         
         
  //HashMap��list��ϵ�����
   /** 
          * �ڲ�����ѧ���������£��Ӽ�������n��ѧ����Ϣ(ѧ�ţ����������䣩�� 
          * ������ɺ󣬴�ӡ������ѧ����Ϣ 
          * @author ccna_zhang 
          * 
   */      
         //���屣��ѧ����Ϣ��List��Ԫ������ΪHashMap  
//         List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();  
//         Scanner input = new Scanner(System.in);  
//           
//         System.out.println("������ѧ������Ϣ,y��ʾ������n��ʾ�˳�");  
//         while("y".equals(input.next())) {  
//             HashMap<String, Object> map1 = new HashMap<String, Object>();  
//             System.out.println("������ѧ��");  
//             map1.put("studentno", input.next());  
//             System.out.println("����������");  
//             map1.put("name", input.next());  
//             System.out.println("����������");  
//             map1.put("age", input.nextInt());  
//             list.add(map1);  
//             System.out.println("���������ѧ������Ϣ,y��ʾ������n��ʾ�˳�");  
//         }  
//           
//         System.out.println("�����ѧ����ϢΪ:");  
//         System.out.println("ѧ������Ϊ:" + list.size());  
//           
//         Iterator<HashMap<String, Object>> it1 = list.iterator();  
//         int i = 1;  
//         while(it.hasNext()) {  
//             HashMap<String, Object> stuMap = it1.next();  
//             System.out.print("��" + i + "��ѧ������ϢΪ");  
//             System.out.println("ѧ��:" + stuMap.get("studentno") + " ,����:" + stuMap.get("name") + " ,����:" + stuMap.get("age"));  
//         }       
//         
         
	}

}
