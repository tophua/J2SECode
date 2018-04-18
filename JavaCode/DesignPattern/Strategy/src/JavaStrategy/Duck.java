package JavaStrategy;

public abstract class Duck {
	//�ǹ�ͬ��Ϊ
    FlyBehavior flyBehavior;  //���нӿ�
    QuackBehavior quackBehavior;  //�нӿ�
    public Duck(){             
    	
    }  
    ////��ͬ��Ϊ
    public abstract void display();  //��ʾ����
    public void swim(){     //��Ӿ����
    	System.out.println("All ducks is Swim!");
    }
    
  //���ǽ�ͨ�����������ò�ͬ��Ϊ��������ȥ  
    public void performFly(){
    	flyBehavior.fly();
    }
    public void performQuack(){
    	quackBehavior.quack();
    }
  //�����л���Ϊ�Ĳ�ͬ��ʽ  
    public void setFlyBehavior(FlyBehavior fb){
    	flyBehavior=fb;
    }  
    public void setQuackBehavior(QuackBehavior qb){
    	quackBehavior=qb;
    }  
}
