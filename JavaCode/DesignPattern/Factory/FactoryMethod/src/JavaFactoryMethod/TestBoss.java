package JavaFactoryMethod;

public class TestBoss {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	//司机找到管理Benz的人，在确定对应的车，然后就可以“开车”
		Driver d=new BenzDriver();
		Car c=d.createCar("");//这里传值没作用，d确定了
		                      //那么产品也会对应确认
		c.setName("Benz");
		c.drive();
	}

}
