package JavaDecorator;

public class Milk extends CondimentDecorator{
    Beverage beverage; //这是为了知道哪种类型的咖啡
                       //所以有父类
    
    public Milk(Beverage beverage){
    	this.beverage=beverage;
    }
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return beverage.getDescription()+", Milk";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 0.2+beverage.cost();
	}
}
