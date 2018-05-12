package com.Test;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.Task.ScheduledExecutorSample03;

public class Test_ScheduledExceutorSample03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScheduledExecutorSample03 test =new ScheduledExecutorSample03("job1");
	      //��ȡ��ǰʱ��
        Calendar currentDate = Calendar.getInstance();
        long currentDateLong = currentDate.getTime().getTime();
        System.out.println("Current Date = " + currentDate.getTime().toString());
        //�����������������һ��ִ��ʱ��
        Calendar earliestDate = test
                .getEarliestDate(currentDate, 3, 16, 38, 10);
        long earliestDateLong = earliestDate.getTime().getTime();
        System.out.println("Earliest Date = "
                + earliestDate.getTime().toString());
        //����ӵ�ǰʱ�䵽���һ��ִ��ʱ���ʱ����
        long delay = earliestDateLong - currentDateLong;
        //����ִ������Ϊһ����
        long period = 7 * 24 * 60 * 60 * 1000;
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        //�����ڿ�ʼdelay����֮��ÿ��һ����ִ��һ��job1
        service.scheduleAtFixedRate(test, delay, period,
                TimeUnit.MILLISECONDS);

		
		
		
	}

}
