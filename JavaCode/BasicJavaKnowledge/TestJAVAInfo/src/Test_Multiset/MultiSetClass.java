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
    //ͳ��List��ĳ���ַ����ĸ���       
		HashMultiset<String> mulset=HashMultiset.create();
		mulset.addAll(wordList);
		Integer count=mulset.count("this"); ///ͳ�ơ�this���ĸ���
	
	//��ʼ���Ȳ���
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
		 multiset.add("b"); //�洢�Ľ����[a x 2, b x 5, c x 2, d]
         Integer icount=multiset.count("b");
         Integer iSize=multiset.size();
         //��������set����Ĳ����ظ���
         Set<String> set=multiset.elementSet();//setΪ[a, b, c, d]
         for(String s:set){
        	 System.out.println(s);
         }
         
         //ѭ�������� ��������������е���Ϣ�����ظ���
		 Iterator<String> iter1=multiset.iterator();
		 while(iter1.hasNext()){
			 System.out.println(iter1.next());
		 }
		 
		 //������������������ÿ��Ԫ����Ԫ�ظ���
		 for (Multiset.Entry<String> entry : multiset.entrySet())  
		 {  
			 System.out.println("Element: "+entry.getElement() +
					 ", Occurrence(s): " + entry.getCount());              
		 } 

		 multiset.remove("b",2);  //�����Ƴ�2��bԪ��
		 icount=multiset.count("b");
		 
	}

}
