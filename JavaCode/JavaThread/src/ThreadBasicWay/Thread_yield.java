package ThreadBasicWay;

public class Thread_yield extends Thread{
	Thread_yield(String s){
	   super(s);	
	}
	
	public void run(){
		for(int i=1;i<=5;i++){
			System.out.println(getName()+":"+i);
			if(i%2==0){
				yield();
			//	当执行到i能被2整除时当前执行的线程就让出来让另一个
			//  在执行run()方法的线程来优先执行
			}
		}
	}
}
