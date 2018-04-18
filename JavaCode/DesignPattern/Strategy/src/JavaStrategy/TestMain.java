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
       model.performFly();//原先行为
       //人为修改某种行为，进而改变这种行为
       model.setFlyBehavior(new FlyRocketPowered());
       model.performFly();//新行为
       model.display();
	}

}
