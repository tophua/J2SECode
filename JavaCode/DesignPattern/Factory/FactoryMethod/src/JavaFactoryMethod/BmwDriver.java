package JavaFactoryMethod;

public class BmwDriver extends Driver{

	@Override
	public Car createCar(String car) throws Exception {
		// TODO Auto-generated method stub
		return new Bmw();
	}
}
