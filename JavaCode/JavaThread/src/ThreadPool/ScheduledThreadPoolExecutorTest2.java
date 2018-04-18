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
		///newһ��5���߳��������̳߳�
		ScheduledExecutorService excutor=Executors.newScheduledThreadPool(5);
		task myTask=new task();
		//��һ��������ʾ������ִ�е����񣬵ڶ���������ʾ��һ��ִ��ǰ���ӳ�ʱ�䣬
		//������������ʾ�����������ʱ�䣬���ĸ�������ʾʱ�䵥λ��
		
		//ʹ��scheduleAtFixedRate()����ÿ��2������һ���̣߳���ȡScheduledFuture����
		//ͨ���ö����getDelay()������ȡ����һ��ִ�е�ʱ�䡣
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
