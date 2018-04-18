package ThreadPackage;

class Thread1 extends Thread{
	private String name;
	public Thread1(String name){
		this.name=name;
	}
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(name+"运行:"+i);
			try{
//Thread.sleep()方法调用目的是不让当前线程独自霸占
//该进程所获取的CPU资源，以留出一定时间给其他线程执行的机会。
				sleep((int) Math.random() * 10); 
			}catch(InterruptedException e){
				 e.printStackTrace();  
			}
		}
	}
}
public class Multi_threadClass {
///程序启动运行main时候，java虚拟机启动一个进程，主线程main在main()
//调用时候被创建。随着调用MitiSay的两个对象的start方法，
//另外两个线程也启动了，这样，整个应用就在多线程下运行。
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	Thread1 mt1=new Thread1("A");
	//	Thread1 mt2=new Thread1("B");
	//	mt1.start();
	//	mt2.start();
		new Thread1("A").start();
		new Thread1("B").start();
//start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态
//（Runnable），什么时候运行是由操作系统决定的。
	}
}
