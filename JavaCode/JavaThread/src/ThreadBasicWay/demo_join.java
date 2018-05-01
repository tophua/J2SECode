package ThreadBasicWay;

public class demo_join {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread_join thread_join = new Thread_join("mythread");
		thread_join.start();
		
		try{
			thread_join.join();
			//调用join()方法合并线程，将子线程mythread合并到主线程里面
			//合并线程后，也就是先执行子线程且执行完后再继续主线程。
		}catch(InterruptedException e){
			e.printStackTrace();			
		}
		for(int i=0;i<=5;i++){
			System.out.println("I am main Thread");
		}
	}

}
