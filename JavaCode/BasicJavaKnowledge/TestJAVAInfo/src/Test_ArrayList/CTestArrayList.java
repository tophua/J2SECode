package Test_ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CTestArrayList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 ///ArrayList������������
		ArrayList list1= new ArrayList();
		//����������10��Ԫ��
		for(int i=0;i<10;i++){
			list1.add(i+1);
		}
        //�Ƴ���6��Ԫ��
		list1.remove(5);
	    //�ٴ�����3��Ԫ��
		for(int j=0;j<3;j++){
			list1.add(j+12);
		}

///ArrayList����������
		// ����һ���յ������������list��list�������String���͵�����
		ArrayList<String> list2= new ArrayList<String>();
		//����Ԫ�ص�list2������
		list2.add("Item1");
		list2.add("Item2");
		list2.add(2, "Item3"); // ������佫��ѡ�Item3���ַ������ӵ�list�ĵ�3��λ�á�
		list2.add("Item4");
		// ��ʾ���������е�����
		 System.out.println("The arraylist elements: "+ list2);
		
		//���Ԫ�ص�λ��
		 int pos = list2.indexOf("Item2");
		// ������������Ƿ�Ϊ��
		 boolean check = list2.isEmpty(); 
		// ��ȡ����Ĵ�С 
		 int size = list2.size();
		// ��������������Ƿ����ĳԪ��
		 boolean element = list2.contains("Item5");
		// ��ȡָ��λ���ϵ�Ԫ��
		 String item = list2.get(0); 
		// ����arraylist�е�Ԫ��	 
		// ��1�ַ���: ѭ��ʹ��Ԫ�ص�����������Ĵ�С
		 for (int i = 0; i < list2.size(); i++) {
			System.out.println("Index: " + i + " - Item: " + list2.get(i));
			}
		// ��2�ַ���:ʹ��for eachѭ��
		 for (String str : list2) {
			System.out.println("Item is: " + str);
			}
		 // ��3�ַ���:ʹ�õ����� 
		 //hasNext(): ����true��ʾ���������л���Ԫ��
		 // next(): ������һ��Ԫ��
		   for (Iterator<String> it = list2.iterator(); it.hasNext();) {
			 System.out.println("Item is: " + it.next());
			 }
		// �滻Ԫ��
		   list2.set(1, "NewItem");
		// �Ƴ�Ԫ��
		// �Ƴ���1��λ���ϵ�Ԫ��   
		   list2.remove(1);  
		// �Ƴ���һ���ҵ��� "Item3"Ԫ��   
		   list2.remove("Item3");  
		   
	// ת�� ArrayList Ϊ String[]   
	ArrayList<String> list3 = new ArrayList<String>();
	 //����Ԫ�ص�list2������
	list3.add("Item1");
	list3.add("Item2");
	list3.add(2, "Item3"); // ������佫��ѡ�Item3���ַ������ӵ�list�ĵ�3��λ�á�
	list3.add("Item4");
	String[] simpleArray = list3.toArray(new String[list3.size()]);
	System.out.println("The array is: "+ Arrays.toString(simpleArray));	   
	
 //String[]ת��ΪList��ArrayList
	  String[] s={"1","2","3","5","6"};
      List<String> listA = Arrays.asList(s); //[1, 2, 3, 5, 6]
    //listA.add(3,"4"); //������listA���������   
      /*
           ��ʹ��Arrays.asList()�����add��remove��Щmethodʱ����java.lang.UnsupportedOperationException�쳣��
           ��������Arrays.asList() ����java.util.Arrays$ArrayList�� ������ArrayList��Arrays$ArrayList��ArrayList
           ���Ǽ̳�AbstractList��remove��add�� method��AbstractList����Ĭ��throw UnsupportedOperationException��
           �Ҳ����κβ�����ArrayList override��Щmethod����list���в���������Arrays$ArrayListû��override remove()
           ��add()�ȣ�����throw UnsupportedOperationException
      */
      List<String> listB = new ArrayList<String>(listA);
      listB.add(3,"4");  //�����������
      for(String temp:listB){
          System.out.println(temp);
      }

	
	
	//List�����ֱ�������
	List<String> list = new ArrayList<String>();
	list.add("aaa");
	list.add("bbb");
	list.add("ccc");
	 //����һ������forѭ������
	for(String attribute : list) {
	  System.out.println(attribute);
	}
	 //������������ArrayList��˵�ٶȱȽϿ�, ��forѭ��, ��sizeΪ��������:
	for(int i = 0 ; i < list.size() ; i++) {
		System.out.println(list.get(i));
	}
	 //���������������ͨ�ñ�����ʽ, �Ӻ���İ汾����, �õ���������
	Iterator it = list.iterator();
	while(it.hasNext()) {
	  System.out.println(it.next());
	}
	
	}

}
