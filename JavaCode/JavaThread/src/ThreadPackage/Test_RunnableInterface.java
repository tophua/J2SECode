package ThreadPackage;

//�о��е�����
class MyRunnable implements Runnable{
	private int ticket=5;
	
	///Ҳ������public void run()
	@Override
	public synchronized void run(){
		for(int i=0;i<5;i++){
			if(this.ticket>0){
				System.out.println("�߳̿�ʼ:"+Thread.currentThread().getName()+"��Ʊ:i="+this.ticket);
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
		Thread t3=new Thread(r,"�߳�1");
		Thread t4=new Thread(r,"�߳�2");
		t3.start(); //start()����ֻ��Thread���У�Thread���캯������Runnable������ʵ��,
		            //���п�ͨ��Thread������Runnableʵ�ֶ��̡߳�
		t4.start();
	}

}
