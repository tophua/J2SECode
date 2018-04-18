package GenericPackage;

public class GenParameter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       //定义泛型类BasicGenPa 的一个Interger版本
		BasicGenPa<Integer> intOb=new BasicGenPa<Integer>(100);
		intOb.showType();
		int i=intOb.getOb();
		System.out.println("value= "+i);
		
		// 定义泛型类BasicGenPa 的一个String版本
		BasicGenPa<String> strOb = new BasicGenPa<String>("Hello");
		strOb.showType();
		String s = strOb.getOb();
		System.out.println("value= " + s);
		/*
		 * T实际类型：java.lang.Integer
           value= 100
           T实际类型：java.lang.String
           value= Hello
		 */
	}

}
