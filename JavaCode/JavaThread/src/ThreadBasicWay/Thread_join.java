package ThreadBasicWay;

public class Thread_join extends Thread{
	
	Thread_join(String s){
		super(s);
	/*
	* ʹ��super�ؼ��ֵ��ø���Ĺ��췽�� 
	* ����Thread������һ�����췽������public Thread(String name)�� 
	* ͨ�������Ĺ��췽�����Ը��¿��ٵ��߳����������ڹ����߳�
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
