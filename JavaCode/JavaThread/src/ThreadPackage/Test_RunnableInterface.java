package ThreadPackage;

//感觉有点问题
class MyRunnable implements Runnable{
	private int ticket=5;
	
	///也可以是public void run()
	@Override
	public synchronized void run(){
		for(int i=0;i<5;i++){
			if(this.ticket>0){
				System.out.println("线程开始:"+Thread.currentThread().getName()+"卖票:i="+this.ticket);
				this.ticket--;
			}
		}
	}
}

public class Test_RunnableInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable r=new MyRunnable();
	//	Runnable r=new MyRunableClass();
		Thread t3=new Thread(r,"线程1");
		Thread t4=new Thread(r,"线程2");
		t3.start(); //start()方法只在Thread中有，Thread构造函数接受Runnable的子类实例,
		            //所有可通过Thread类启动Runnable实现多线程。
		t4.start();
	}

}
