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
			//	��ִ�е�i�ܱ�2����ʱ��ǰִ�е��߳̾��ó�������һ��
			//  ��ִ��run()�������߳�������ִ��
			}
		}
	}
}
