package ClonePackage;

class CNewClone implements Cloneable{
	@Override
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();  //ֻ��ǳ����
	}
}
 
public class Test_Clone {
		
	public static void main(String[] args) throws CloneNotSupportedException{
		// TODO Auto-generated method stub
		CNewClone p=new CNewClone();		
		CNewClone p1=p;
		CNewClone p2=(CNewClone) p.clone();
		System.out.println(p);  //��ַ��ͬ������ͬһ��������p1�൱����p�ı���
		System.out.println(p1);
		System.out.println(p2);  //˵��p2��һ��ȫ�µ��ڴ�Ķ���
	}
}
