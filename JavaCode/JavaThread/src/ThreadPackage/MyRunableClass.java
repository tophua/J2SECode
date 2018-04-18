package ThreadPackage;

public class MyRunableClass implements Runnable{
	private int ticket=5;
	
	///也可以是public void run()
	//synchronized这个必须需要，相当于加锁，同步机制
	@Override
	public synchronized void run(){
		for(int i=0;i<10;i++){
			if(this.ticket>0){
				System.out.println("线程开始:"+Thread.currentThread().getName()+"卖票:i="+this.ticket);
				ticket--;
			}
		}
	}
}
