package ClonePackage;

public class TestDemo {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub

		BasicClass ba1=new BasicClass("original data");
		StringBuffer strBuf=new StringBuffer("origin string buf");
		
		EasyClone org=new EasyClone(ba1,1.0,"old",strBuf);
		EasyClone copy=null;
		Object obj1=org.clone(); //浅拷贝，所以引用类型字段(BasicClass、StringBuffer)只是将引用给了新对象，但值不变，都是指向同一个
		if(obj1 instanceof EasyClone){ //确定obj1是否是EasyClone类的一个实例
			copy =(EasyClone) obj1;
		}
		
		System.out.println("copy == org? "+(copy==org));//返回值是false,虽然copy和org是不同对象实例，但字段的值是相同
		                                                //BasicClass、StringBuffer是引用类型字段，double、String是基本数据类型字段
		System.out.println("data of org: ");
		org.show();
		System.out.println();
		System.out.println("data of copy: ");
		copy.show();
		System.out.println();
		System.out.println("org.data1==copy.data1? "+(org.data1==copy.data1)); //下面四个相同，浅拷贝
		System.out.println("org.data2==copy.data2? "+(org.data2==copy.data2));
		System.out.println("org.data2==copy.data2? "+(org.data3==copy.data3));
		System.out.println("org.data2==copy.data2? "+(org.data4==copy.data4));
		
		///浅拷贝带来的后果,修改拷贝后copy的值
		  //从结果发现，引用类型字段的值改变时，对应的原对象的值也对应变化
		copy.data1.data="copy New data";
		copy.data2=2.0;
		copy.data3="New str";
		copy.data4.replace(0, copy.data4.length(), "New strBuf");
		
		System.out.println("New data of org: ");
		org.show();
		System.out.println();
		System.out.println("New data of copy: ");
		copy.show();
		
///////测试深拷贝
		System.out.println("-------------深拷贝--------------");
		BasicClass ba2=new BasicClass("original data2");
		StringBuffer strBuf2=new StringBuffer("origin string buf2");
		
		DeepClone org2=new DeepClone(ba2,4.0,"old2",strBuf2);
		DeepClone copy2=null;
		Object obj2=org2.clone(); 
		if(obj2 instanceof DeepClone){ //确定obj1是否是EasyClone类的一个实例
			copy2 =(DeepClone) obj2;
		}
		System.out.println("data of org2: ");
		org2.show();
		System.out.println();
		System.out.println("data of copy2: ");
		copy2.show();
		
		System.out.println();
		System.out.println("org2.data1==copy2.data1? "+(org2.data1==copy2.data1)); 
		System.out.println("org2.data2==copy2.data2? "+(org2.data2==copy2.data2));
		System.out.println("org2.data2==copy2.data2? "+(org2.data3==copy2.data3));
		System.out.println("org2.data2==copy2.data2? "+(org2.data4==copy2.data4));
		//修改拷贝后copy2的值
		copy2.data1.data="copy2 New data";
		copy2.data2=3.0;
		copy2.data3="New str2";
		copy2.data4.replace(0, copy.data4.length(), "New strBuf2");
		
		System.out.println("New data of org2: ");
		org2.show();
		System.out.println();
		System.out.println("New data of copy2: ");
		copy2.show();
	}

}
