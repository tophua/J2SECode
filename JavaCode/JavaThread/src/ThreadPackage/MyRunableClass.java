package ThreadPackage;

public class MyRunableClass implements Runnable{
	private int ticket=5;
	
	///Ҳ������public void run()
	//synchronized���������Ҫ���൱�ڼ�����ͬ������
	@Override
	public synchronized void run(){
		for(int i=0;i<10;i++){
			if(this.ticket>0){
				System.out.println("�߳̿�ʼ:"+Thread.currentThread().getName()+"��Ʊ:i="+this.ticket);
				ticket--;
			}
		}
	}
}
