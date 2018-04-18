package ThreadPool;

public class SimpleTask implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("线程："+Thread.currentThread().getName()+" ID:"+Thread.currentThread().getId());
		try{
			///具体的任务
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
