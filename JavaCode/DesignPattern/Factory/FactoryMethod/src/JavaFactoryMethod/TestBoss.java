package JavaFactoryMethod;

public class TestBoss {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	//˾���ҵ�����Benz���ˣ���ȷ����Ӧ�ĳ���Ȼ��Ϳ��ԡ�������
		Driver d=new BenzDriver();
		Car c=d.createCar("");//���ﴫֵû���ã�dȷ����
		                      //��ô��ƷҲ���Ӧȷ��
		c.setName("Benz");
		c.drive();
	}

}
