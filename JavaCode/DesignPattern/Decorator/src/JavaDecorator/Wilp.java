package JavaDecorator;

public class Wilp extends CondimentDecorator{

  Beverage beverage; //这是为了知道哪种类型的咖啡
      //所以有父类

   public Wilp(Beverage beverage){
      this.beverage=beverage;
   }

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return beverage.getDescription()+", Wilp";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
	   return 0.3+beverage.cost();
	}
}
