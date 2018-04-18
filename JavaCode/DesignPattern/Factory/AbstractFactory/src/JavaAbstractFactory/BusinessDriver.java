package JavaAbstractFactory;

public class BusinessDriver extends Driver3{

	@Override
	public BenzCar createBenzCar(String car) throws Exception {
		// TODO Auto-generated method stub
		return new BenzBusinessCar();
	}

	@Override
	public BmwCar createBmwCar(String car) throws Exception {
		// TODO Auto-generated method stub
		return new BmwBusinessCar();
	}

}
