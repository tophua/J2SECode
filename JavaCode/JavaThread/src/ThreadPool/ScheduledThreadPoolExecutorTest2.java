package ThreadPool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import ThreadPool.ScheduledThreadPoolExecutorTest1.task;

public class ScheduledThreadPoolExecutorTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		///new一个5个线程容量的线程池
		ScheduledExecutorService excutor=Executors.newScheduledThreadPool(5);
		task myTask=new task();
		//第一个参数表示周期线执行的任务，第二个参数表示第一次执行前的延迟时间，
		//第三个参数表示任务启动间隔时间，第四个参数表示时间单位。
		
		//使用scheduleAtFixedRate()方法每隔2秒启动一个线程，获取ScheduledFuture对象，
		//通过该对象的getDelay()方法获取离下一次执行的时间。
		ScheduledFuture<?> resultFuture=excutor.scheduleAtFixedRate(myTask, 1, 2, TimeUnit.SECONDS);
		for(int i=0;i<10;i++){
			System.out.println(resultFuture.getDelay(TimeUnit.MILLISECONDS));
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		excutor.shutdown();
	}
	
	public static class task implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String result=Thread.currentThread().getName()+":"+new Date().toString();
			System.out.println(result);
		}	
	}
}
