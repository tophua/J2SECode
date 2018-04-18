package ThreadPool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * 任务延时执行
 */
public class ScheduledThreadPoolExecutorTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		///new一个5个线程容量的线程池
		ScheduledExecutorService excutor=Executors.newScheduledThreadPool(5);
		
		for(int i=0;i<10;i++){
			//第一个参数是任务，第二个参数是执行任务前等待的时间，第三个参数是时间单位
			//这里是每个任务比前一个任务延迟一秒执行
			excutor.schedule(new task(i), 1+i, TimeUnit.SECONDS);
		}
		excutor.shutdown();
		while(!excutor.isTerminated()){
			
		}
	}
	
	public static class task implements Runnable{
        public int ID=0;
		public task(int Id){
			this.ID=Id;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String result="ID:"+ID+","+Thread.currentThread().getName()+":"+new Date().toString();
			System.out.println(result);
		}	
	}

}
