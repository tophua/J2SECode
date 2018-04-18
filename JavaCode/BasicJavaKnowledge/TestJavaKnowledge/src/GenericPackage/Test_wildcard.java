package GenericPackage;

import java.util.ArrayList;
import java.util.List;

class Parent{
	//
}

class Child extends Parent{
	//
}

class Child1 extends Parent{
	//
}

public class Test_wildcard {

	public static void doTest(List<? extends Parent> list){
		//?表示运行是确定具体类型
		System.out.println("list :"+list);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<Parent> pList=new ArrayList<Parent>();
        List<Child>  cList=new ArrayList<Child>();
        List<Child1>  cList1=new ArrayList<Child1>();
        
        doTest(pList);
        doTest(cList);
        doTest(cList1);
	}

}
