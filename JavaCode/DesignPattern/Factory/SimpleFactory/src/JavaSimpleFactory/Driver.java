package JavaSimpleFactory;

public class Driver {
	//这里来确定什么类型的车
     public static Car createCar(String car){
    	 Car c=null;
    	 if("Benz".equalsIgnoreCase(car)){
    		 c=new Benz();
    	 }else if("Bmw".equalsIgnoreCase(car)){
    		 c=new Bmw();
    	 }
    	 return c;
     }
}
