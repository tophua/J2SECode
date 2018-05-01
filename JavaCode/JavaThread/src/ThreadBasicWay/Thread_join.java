package ThreadBasicWay;

public class Thread_join extends Thread{
	
	Thread_join(String s){
		super(s);
	/*
	* 使用super关键字调用父类的构造方法 
	* 父类Thread的其中一个构造方法：“public Thread(String name)” 
	* 通过这样的构造方法可以给新开辟的线程命名，便于管理线程
	*/
	}
	
	public void run(){
		for(int i=0;i<=5;i++){
			System.out.println("I am "+ getName());
		}
		try{
			sleep(1000);
		}catch(InterruptedException e){
			return;
		}
	}
}
