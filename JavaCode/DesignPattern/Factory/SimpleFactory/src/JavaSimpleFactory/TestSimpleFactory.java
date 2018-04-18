package JavaSimpleFactory;

public class TestSimpleFactory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//事先老板说了坐什么车
        Car car=Driver.createCar("Benz");
        car.setName("Benz");
        //然后直接说“开车”就行
        car.drive();
	}

}
