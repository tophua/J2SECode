package GenericPackage;

public class GenClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BasicGenPa<String> strClass=new BasicGenPa<String>("Hello");
		BasicGenPa<Double> doClass=new BasicGenPa<Double>(new Double("33"));
		BasicGenPa<Object> objClass=new BasicGenPa<Object>(new Object());
		System.out.println("strClass= "+objClass.getOb());
		System.out.println("doClass= "+doClass.getOb());
		System.out.println("objClass= "+objClass.getOb());
		/*
		   strClass= java.lang.Object@626b2d4a
           doClass= 33.0
           objClass= java.lang.Object@626b2d4a
		 */
	}

}
