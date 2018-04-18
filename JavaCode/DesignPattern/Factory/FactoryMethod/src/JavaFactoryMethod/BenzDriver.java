package JavaFactoryMethod;

public class BenzDriver extends Driver{

	@Override
	public Car createCar(String car) throws Exception {
		// TODO Auto-generated method stub
		return new Benz();
	}

}
