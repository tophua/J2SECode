package Test_HashTable;

import java.util.Hashtable;

public class CtestHashTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	       Hashtable ht = new Hashtable(10);
	        ht.put(0, "cuihao");
	        ht.put(1, "handandan");
	        ht.put(2, "wupan");
	        ht.put(3, "mahong");
	        ht.put(4, "jinjia");
	        ht.put(5, "yangsisi");
	        ht.put(6, "xuexiang");
/////htʵ���ϴ洢���ǣ�{6=xuexiang, 5=yangsisi, 4=jinjia, 3=mahong, 2=wupan, 1=handandan, 0=cuihao}
	      System.out.println("��ѭ�������Ч�����£�");
	      int length = ht.size();  //Hashtable�Ĺ�ģ
	      for(int i = 0; i < length; ++ i)
	      {
	         System.out.println(i + ": " + ht.get(i));
	      }

	      System.out.println("-----------------------------------------");
	      System.out.println("ʹ��toString()�������Ч�����£�");
	      System.out.println(ht.toString());
	      System.out.println("------ɾ������Ԫ��-----------------------------------");
	  //ɾ��һ��Ҫע�⣡  ����֮ǰд����i < ht.size(); size�Ǳ仯�ģ���ˣ�
	  //ɾ�����ӡʱֻ��ӡ��4�������Ǵ���ģ�
	      for(int i = 0; i < length; ++ i)                           
	      {
	         System.out.println("delete obj[" + i + "]");
	         ht.remove(i);
	      }
	      System.out.println("--------ɾ---��---��---------------------------");
	      System.out.println(ht.toString());
	}

}
