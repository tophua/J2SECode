package JavaSimpleFactory;

public class TestSimpleFactory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�����ϰ�˵����ʲô��
        Car car=Driver.createCar("Benz");
        car.setName("Benz");
        //Ȼ��ֱ��˵������������
        car.drive();
	}

}
