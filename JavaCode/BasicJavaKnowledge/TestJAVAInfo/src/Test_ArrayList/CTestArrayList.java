package Test_ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CTestArrayList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 ///ArrayList具有数组特性
		ArrayList list1= new ArrayList();
		//给数组增加10个元素
		for(int i=0;i<10;i++){
			list1.add(i+1);
		}
        //移除第6个元素
		list1.remove(5);
	    //再次增加3个元素
		for(int j=0;j<3;j++){
			list1.add(j+12);
		}

///ArrayList的链表属性
		// 创建一个空的数组链表对象list，list用来存放String类型的数据
		ArrayList<String> list2= new ArrayList<String>();
		//增加元素到list2对象中
		list2.add("Item1");
		list2.add("Item2");
		list2.add(2, "Item3"); // 此条语句将会把“Item3”字符串增加到list的第3个位置。
		list2.add("Item4");
		// 显示数组链表中的内容
		 System.out.println("The arraylist elements: "+ list2);
		
		//检查元素的位置
		 int pos = list2.indexOf("Item2");
		// 检查数组链表是否为空
		 boolean check = list2.isEmpty(); 
		// 获取链表的大小 
		 int size = list2.size();
		// 检查数组链表中是否包含某元素
		 boolean element = list2.contains("Item5");
		// 获取指定位置上的元素
		 String item = list2.get(0); 
		// 遍历arraylist中的元素	 
		// 第1种方法: 循环使用元素的索引和链表的大小
		 for (int i = 0; i < list2.size(); i++) {
			System.out.println("Index: " + i + " - Item: " + list2.get(i));
			}
		// 第2种方法:使用for each循环
		 for (String str : list2) {
			System.out.println("Item is: " + str);
			}
		 // 第3种方法:使用迭代器 
		 //hasNext(): 返回true表示链表链表中还有元素
		 // next(): 返回下一个元素
		   for (Iterator<String> it = list2.iterator(); it.hasNext();) {
			 System.out.println("Item is: " + it.next());
			 }
		// 替换元素
		   list2.set(1, "NewItem");
		// 移除元素
		// 移除第1个位置上的元素   
		   list2.remove(1);  
		// 移除第一次找到的 "Item3"元素   
		   list2.remove("Item3");  
		   
	// 转换 ArrayList 为 String[]   
	ArrayList<String> list3 = new ArrayList<String>();
	 //增加元素到list2对象中
	list3.add("Item1");
	list3.add("Item2");
	list3.add(2, "Item3"); // 此条语句将会把“Item3”字符串增加到list的第3个位置。
	list3.add("Item4");
	String[] simpleArray = list3.toArray(new String[list3.size()]);
	System.out.println("The array is: "+ Arrays.toString(simpleArray));	   
	
 //String[]转换为List或ArrayList
	  String[] s={"1","2","3","5","6"};
      List<String> listA = Arrays.asList(s); //[1, 2, 3, 5, 6]
    //listA.add(3,"4"); //不能在listA后面加数据   
      /*
           在使用Arrays.asList()后调用add，remove这些method时出现java.lang.UnsupportedOperationException异常。
           这是由于Arrays.asList() 返回java.util.Arrays$ArrayList， 而不是ArrayList。Arrays$ArrayList和ArrayList
           都是继承AbstractList，remove，add等 method在AbstractList中是默认throw UnsupportedOperationException而
           且不作任何操作。ArrayList override这些method来对list进行操作，但是Arrays$ArrayList没有override remove()
           ，add()等，所以throw UnsupportedOperationException
      */
      List<String> listB = new ArrayList<String>(listA);
      listB.add(3,"4");  //可以正常添加
      for(String temp:listB){
          System.out.println(temp);
      }

	
	
	//List的三种遍历方法
	List<String> list = new ArrayList<String>();
	list.add("aaa");
	list.add("bbb");
	list.add("ccc");
	 //方法一：超级for循环遍历
	for(String attribute : list) {
	  System.out.println(attribute);
	}
	 //方法二：对于ArrayList来说速度比较快, 用for循环, 以size为条件遍历:
	for(int i = 0 ; i < list.size() ; i++) {
		System.out.println(list.get(i));
	}
	 //方法三：集合类的通用遍历方式, 从很早的版本就有, 用迭代器迭代
	Iterator it = list.iterator();
	while(it.hasNext()) {
	  System.out.println(it.next());
	}
	
	}

}
