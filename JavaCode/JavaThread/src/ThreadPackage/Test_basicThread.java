package ThreadPackage;

public class Test_basicThread implements Runnable{

	@Override
	public synchronized void run(){
		for(int i=0;i<3;i++){
			System.out.println(Thread.currentThread().getName()+"run...");
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       for(int i=0;i<5;i++){
    	   new Thread(new Test_basicThread(), "Thread_"+i).start();
       }
	}

}
