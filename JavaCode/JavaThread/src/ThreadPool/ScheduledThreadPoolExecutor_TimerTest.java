package ThreadPool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutor_TimerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	      //���ʵ��������������������Ϊ5��  
        ScheduledThreadPoolExecutor sExecutor=new ScheduledThreadPoolExecutor(5);
        MyTask task = new MyTask(); 
        //��2���ʼִ�����񣬲�������һ������ʼ���һ����ִ��һ��
//      sExecutor.scheduleWithFixedDelay(task, 2, 1, TimeUnit.SECONDS);  
        //��6���ִ��һ�Σ���ֻ��ִ��һ��
        sExecutor.schedule(task, 6, TimeUnit.SECONDS);
        /**
         * ��Timer���ƣ�Ҳ����ֱ���������run()�����е��õ��ȷ���ֹͣ
         * ���������ƽ���Ĺرյ��������ȴ������������
         */
        sExecutor.shutdown();      
	}
	
    static class MyTask implements Runnable{ 	 
        @Override
        public void run() {
            System.out.println("��ǰִ�е��߳�"+Thread.currentThread().getName()); 
        }
    }
}
