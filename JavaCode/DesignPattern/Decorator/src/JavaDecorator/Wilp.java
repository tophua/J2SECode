package JavaDecorator;

public class Wilp extends CondimentDecorator{

  Beverage beverage; //����Ϊ��֪���������͵Ŀ���
      //�����и���

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
