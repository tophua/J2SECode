package ThreadPool;

public class SimpleTask implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("�̣߳�"+Thread.currentThread().getName()+" ID:"+Thread.currentThread().getId());
		try{
			///���������
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
