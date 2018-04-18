package Test_Vector;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class CTestVector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//Vector的创建 
		//使用Vector的构造方法进行创建 
		Vector v = new Vector(4); 
		//向Vector中添加元素 
		//使用add方法直接添加元素 
		v.add("Test0"); 
		v.add("Test1"); 
		v.add("Test0"); 
		v.add("Test2"); 
		v.add("Test2"); 
	//从Vector中删除元素 
		v.remove("Test0"); //删除指定内容的元素 
		v.remove(0); //按照索引号删除元素 
	//获得Vector中已有元素的个数 
		int size = v.size(); 
		System.out.println("size:" + size); 
	//第1种方法遍历Vector中的元素 
		for(int i = 0;i < v.size();i++){ 
		System.out.println(v.get(i));  //按照索引号进行输出
		} 
	//第2种方法迭代器
		Iterator iter1=v.iterator();
		while(iter1.hasNext()){
			System.out.println(iter1.next());		
		}
	///第3种方法 Enumeration循环(Enumeration是枚举数据集合)
		String strV="";
		Enumeration enu=v.elements();
		while(enu.hasMoreElements()){
			strV=(String)enu.nextElement();
		}
  
   ////对向量经常增加、删除、插入等操作
		Vector Vtest= new Vector();
		Vtest.addElement("one"); //将组建加入向量尾部，大小加1，容量加1
		Vtest.addElement("two");
		Vtest.addElement("three");
		Vtest.addElement("one");
		Vtest.insertElementAt("zero", 0); //将组件插入指定索引处，此后内容向后移动1个单位
		Vtest.insertElementAt("oop", 1);
		Vtest.setElementAt("three", 3);   //修改对应位置上的值
		Vtest.setElementAt("four", 4);
		Vtest.removeAllElements();   //删除所有，大小为0
	}

}
