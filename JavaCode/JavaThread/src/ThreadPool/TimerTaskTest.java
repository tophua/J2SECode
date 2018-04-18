package ThreadPool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerTaskTest {
	
	static class MyTimerTask extends TimerTask{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("ִ�е�ǰ�߳�"+Thread.currentThread().getName());
		}		
	}
	 /**
     * Timer�̲߳��Ჶ���쳣������TimerTask�׳���δ�����쳣����ֹtimer�̡߳�
     * ���Timer�߳��д��ڶ���ƻ���������һ���ƻ������׳�δ�����쳣��
     * �����������Timer�߳̽������Ӷ����������ƻ������޷��õ�����ִ�С�����
     * Timer�߳�ʱ���ھ���ʱ�䣬��˼ƻ������ϵͳ��ʱ��ĸı������еġ�
     * Timer�ǵ��̣߳����ĳ������ܺ�ʱ�����ܻ�Ӱ�������ƻ������ִ�С�
     * @param args
     * @throws ParseException
     * @throws InterruptedException 
     */
	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub
		Timer timer=new Timer();
		 /**
         * scheduleAtFixedRate��ʽ
         * �趨�����ִ������
         */
		timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 1000);
  //      SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
  //      Date time = dateFormatter.parse("2016/03/28 14:40:00");  
   //     timer.schedule(new MyTimerTask(), time);
		//����MyTimerTask1�̺߳����߳����������ӣ���MyTimerTask1�����ִ��ʱ��
        Thread.sleep(5000);
        //��ֹTimer�߳�
        timer.cancel();
	}

}
