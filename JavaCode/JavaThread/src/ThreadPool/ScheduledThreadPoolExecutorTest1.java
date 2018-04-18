package ThreadPool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * ������ʱִ��
 */
public class ScheduledThreadPoolExecutorTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		///newһ��5���߳��������̳߳�
		ScheduledExecutorService excutor=Executors.newScheduledThreadPool(5);
		
		for(int i=0;i<10;i++){
			//��һ�����������񣬵ڶ���������ִ������ǰ�ȴ���ʱ�䣬������������ʱ�䵥λ
			//������ÿ�������ǰһ�������ӳ�һ��ִ��
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
