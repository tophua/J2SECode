package JavaDecorator;

public class TestCoffee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//µ¥¶ÀDarkRoast¿§·È²»ÐèÒªÅäÁÏ
       Beverage be1=new DarkRoast();
       System.out.println(be1.getDescription()+" $"+be1.cost());
       
    //DarkRoast¿§·È+Ë«±¶Milk+Whip
       Beverage be2=new DarkRoast();
       be2=new Milk(be2);
       be2=new Milk(be2);
       be2=new Wilp(be2);
       System.out.println(be2.getDescription()+" $"+be2.cost());
    
     //Decaf¿§·È+Milk+Whip
       Beverage be3=new Decaf();
       be3=new Milk(be3);
       be3=new Wilp(be3);
       System.out.println(be3.getDescription()+" $"+be3.cost());      
	}

}
