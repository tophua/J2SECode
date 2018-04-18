package Test_HashSet;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CTestHashSet {
	
	public static char FindFirstRepeat(String strValue, Integer nSize){
		char[] a=strValue.toCharArray(); //Stringת��Ϊ����洢
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
    //���Ԫ�غͱ���
		HashSet hSet=new HashSet();
		hSet.add("1st");
		hSet.add("2nd");
		hSet.add(new Integer(3)); //����
		hSet.add(new Double(4.0));
		hSet.add("2nd");            //�ظ�Ԫ�أ�δ�����
		hSet.add(new Integer(3));      //�ظ�Ԫ�أ�δ�����
		hSet.add(new Date());
		hSet.add(null);  //������null
		System.out.println("��ʼ��size="+hSet.size());
		Iterator it=hSet.iterator();
		while(it.hasNext())
		{
		   Object o=it.next();
		   System.out.println(o);
		}
		hSet.remove("2nd");
		System.out.println("�Ƴ�Ԫ�غ�size="+hSet.size());
		System.out.println(hSet);
   
	///��HashSet�����һ������
		int[] nArray={10,11,12};
		hSet.add(nArray);
	//��HashSet�����һ���Զ������
		//Cat cat1=new Cat("asd",2);
		//hSet.add(cat1);
	//����Ƴ���Ч�����ظ�Ԫ�ص��㷨	
	System.out.println(FindFirstRepeat("qweyerww24d34sq23s3",19));	
	
	///HashSet�̳�Set�ӿ�
	Set<String> SetA=(Set<String>)new HashSet();
	SetA.add(new String("abc"));
	SetA.add("ww");
	//SetA.add(new Integer(2));  ��������()��Ӧ����String
	//������HashSet�Ĺ���
	Iterator<String> ite=SetA.iterator();
	while(ite.hasNext()){
		System.out.println(ite.next());
	}
	
	}

}
