package ThreadPackage;

class MyThread extends Thread{
	private String name;
	private int ticket=5;
	public MyThread(String name){
		this.name=name;
	}
	@Override
	public synchronized void run(){
		for(int i=0;i<10;i++){
			if(this.ticket>0){
				System.out.println("线程开始:"+this.name+"卖票:i="+this.ticket);
				ticket--;
			}
		}
	}
}

public class Test_ExtendsThreadClass{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//调用run()方法
//	Thread t1=new MyThread("线程a");
//	Thread t2=new MyThread("线程b");
//	t1.run(); //只调用run()方法，这种方法其实并没有多线程思想
//	t2.run();		

//调用start()方法
		Thread t3=new MyThread("线程c");
		Thread t4=new MyThread("线程d");
		t3.start(); //start()方法新建一个线程取执行Run()方法，当资源不共享
		t4.start();
	}

}
