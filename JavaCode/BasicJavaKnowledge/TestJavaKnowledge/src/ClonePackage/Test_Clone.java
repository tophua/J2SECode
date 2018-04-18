package ClonePackage;

class CNewClone implements Cloneable{
	@Override
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();  //只是浅拷贝
	}
}
 
public class Test_Clone {
		
	public static void main(String[] args) throws CloneNotSupportedException{
		// TODO Auto-generated method stub
		CNewClone p=new CNewClone();		
		CNewClone p1=p;
		CNewClone p2=(CNewClone) p.clone();
		System.out.println(p);  //地址相同，则是同一个对象，则p1相当于是p的别名
		System.out.println(p1);
		System.out.println(p2);  //说明p2是一个全新的内存的对象
	}
}
