package JavaDecorator;

public class DarkRoast extends Beverage{

	//子类继承父类，其子类可以调用父类public的
	//函数、变量
	public DarkRoast(){
		description="DarkRoast";
	}
	
	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 1.99;
	}

}
