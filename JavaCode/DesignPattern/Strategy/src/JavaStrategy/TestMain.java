package JavaStrategy;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Duck mall=new MallardDuck();
       mall.performQuack();
       mall.performFly();
       mall.display();
       
       Duck model=new ModelDuck();
       model.performQuack();
       model.performFly();//ԭ����Ϊ
       //��Ϊ�޸�ĳ����Ϊ�������ı�������Ϊ
       model.setFlyBehavior(new FlyRocketPowered());
       model.performFly();//����Ϊ
       model.display();
	}

}
