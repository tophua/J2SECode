package GenericPackage;

public class GenParameter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       //���巺����BasicGenPa ��һ��Interger�汾
		BasicGenPa<Integer> intOb=new BasicGenPa<Integer>(100);
		intOb.showType();
		int i=intOb.getOb();
		System.out.println("value= "+i);
		
		// ���巺����BasicGenPa ��һ��String�汾
		BasicGenPa<String> strOb = new BasicGenPa<String>("Hello");
		strOb.showType();
		String s = strOb.getOb();
		System.out.println("value= " + s);
		/*
		 * Tʵ�����ͣ�java.lang.Integer
           value= 100
           Tʵ�����ͣ�java.lang.String
           value= Hello
		 */
	}

}
