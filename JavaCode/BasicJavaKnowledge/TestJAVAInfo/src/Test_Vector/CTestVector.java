package Test_Vector;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class CTestVector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//Vector�Ĵ��� 
		//ʹ��Vector�Ĺ��췽�����д��� 
		Vector v = new Vector(4); 
		//��Vector�����Ԫ�� 
		//ʹ��add����ֱ�����Ԫ�� 
		v.add("Test0"); 
		v.add("Test1"); 
		v.add("Test0"); 
		v.add("Test2"); 
		v.add("Test2"); 
	//��Vector��ɾ��Ԫ�� 
		v.remove("Test0"); //ɾ��ָ�����ݵ�Ԫ�� 
		v.remove(0); //����������ɾ��Ԫ�� 
	//���Vector������Ԫ�صĸ��� 
		int size = v.size(); 
		System.out.println("size:" + size); 
	//��1�ַ�������Vector�е�Ԫ�� 
		for(int i = 0;i < v.size();i++){ 
		System.out.println(v.get(i));  //���������Ž������
		} 
	//��2�ַ���������
		Iterator iter1=v.iterator();
		while(iter1.hasNext()){
			System.out.println(iter1.next());		
		}
	///��3�ַ��� Enumerationѭ��(Enumeration��ö�����ݼ���)
		String strV="";
		Enumeration enu=v.elements();
		while(enu.hasMoreElements()){
			strV=(String)enu.nextElement();
		}
  
   ////�������������ӡ�ɾ��������Ȳ���
		Vector Vtest= new Vector();
		Vtest.addElement("one"); //���齨��������β������С��1��������1
		Vtest.addElement("two");
		Vtest.addElement("three");
		Vtest.addElement("one");
		Vtest.insertElementAt("zero", 0); //���������ָ�����������˺���������ƶ�1����λ
		Vtest.insertElementAt("oop", 1);
		Vtest.setElementAt("three", 3);   //�޸Ķ�Ӧλ���ϵ�ֵ
		Vtest.setElementAt("four", 4);
		Vtest.removeAllElements();   //ɾ�����У���СΪ0
	}

}
