package JavaStrategy;

public abstract class Duck {
	//非共同行为
    FlyBehavior flyBehavior;  //飞行接口
    QuackBehavior quackBehavior;  //叫接口
    public Duck(){             
    	
    }  
    ////共同行为
    public abstract void display();  //显示函数
    public void swim(){     //游泳函数
    	System.out.println("All ducks is Swim!");
    }
    
  //这是将通过这里来调用不同行为的子类中去  
    public void performFly(){
    	flyBehavior.fly();
    }
    public void performQuack(){
    	quackBehavior.quack();
    }
  //这是切换行为的不同方式  
    public void setFlyBehavior(FlyBehavior fb){
    	flyBehavior=fb;
    }  
    public void setQuackBehavior(QuackBehavior qb){
    	quackBehavior=qb;
    }  
}
