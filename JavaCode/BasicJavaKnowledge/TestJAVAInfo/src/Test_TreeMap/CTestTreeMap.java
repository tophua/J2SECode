package Test_TreeMap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class CTestTreeMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  //����һ���Զ���Ƚ���  
	MyComparator comparator = new MyComparator();  
   //��ʼ��TreeMap
	Map<TestTemp,String> map1=new TreeMap<TestTemp,String>(comparator); 
	map1.put(new TestTemp("�綯",2000),"001");  
	map1.put(new TestTemp("����",4000),"002");  
	map1.put(new TestTemp("����",7000),"003");  
	map1.put(new TestTemp("��ʵ",29000),"004");  
	map1.put(new TestTemp("ŶŶ",29000),"009");  
    System.out.println(map1);  	
    
///����TreeMap�ļ�ֵ��
	//��һ��������entrySet()��ȡTreeMap�ġ���ֵ�ԡ���Set���ϡ�
    //�ڶ�����ͨ��Iterator��������������һ�����õ��ļ��ϡ�	
    String strteg ="";  
    Iterator iter = map1.entrySet().iterator();  
    while(iter.hasNext()) {  
        Map.Entry entry = (Map.Entry)iter.next();  
        // ��ȡkey  
        TestTemp  key = (TestTemp)entry.getKey();  
        // ��ȡvalue  
        strteg = (String)entry.getValue();  
        } 
    
//����TreeMap�ļ�	
    //��һ��������keySet()��ȡTreeMap�ġ�������Set���ϡ�
    //�ڶ�����ͨ��Iterator��������������һ�����õ��ļ��ϡ�
    TestTemp key2 = null;  
    String strteg2 ="";   
    Iterator iter2 = map1.keySet().iterator();  
    while (iter2.hasNext()) {  
       // ��ȡkey  
        key2 = (TestTemp)iter2.next();  
       // ����key����ȡvalue  
        strteg2 = (String)map1.get(key2);  
       }  
 
 ///����TreeMap��ֵ
    //��һ��������value()��ȡTreeMap�ġ�ֵ���ļ��ϡ�
    //�ڶ�����ͨ��Iterator��������������һ�����õ��ļ��ϡ�   
    String strvalue = "";  
    Collection c = map1.values();  
    Iterator iter3= c.iterator();  
    while (iter3.hasNext()) {  
    	strvalue = (String)iter3.next();  
   }  
     
	}

}
