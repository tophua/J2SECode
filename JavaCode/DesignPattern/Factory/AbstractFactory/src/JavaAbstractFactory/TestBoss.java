package JavaAbstractFactory;

public class TestBoss {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	   Driver3 d = new BusinessDriver();  
	   BenzCar car = d.createBenzCar("");  
	   car.drive();  

	}

}
