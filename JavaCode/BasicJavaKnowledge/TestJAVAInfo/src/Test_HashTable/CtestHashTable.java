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
/////ht实际上存储的是：{6=xuexiang, 5=yangsisi, 4=jinjia, 3=mahong, 2=wupan, 1=handandan, 0=cuihao}
	      System.out.println("用循环输出的效果如下：");
	      int length = ht.size();  //Hashtable的规模
	      for(int i = 0; i < length; ++ i)
	      {
	         System.out.println(i + ": " + ht.get(i));
	      }

	      System.out.println("-----------------------------------------");
	      System.out.println("使用toString()函数输出效果如下：");
	      System.out.println(ht.toString());
	      System.out.println("------删除所有元素-----------------------------------");
	  //删除一定要注意！  这里之前写的是i < ht.size(); size是变化的，因此，
	  //删除后打印时只打印了4个，这是错误的！
	      for(int i = 0; i < length; ++ i)                           
	      {
	         System.out.println("delete obj[" + i + "]");
	         ht.remove(i);
	      }
	      System.out.println("--------删---除---后---------------------------");
	      System.out.println(ht.toString());
	}

}
