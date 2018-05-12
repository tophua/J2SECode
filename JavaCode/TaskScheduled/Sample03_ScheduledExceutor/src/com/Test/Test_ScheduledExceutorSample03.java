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
	      //获取当前时间
        Calendar currentDate = Calendar.getInstance();
        long currentDateLong = currentDate.getTime().getTime();
        System.out.println("Current Date = " + currentDate.getTime().toString());
        //计算满足条件的最近一次执行时间
        Calendar earliestDate = test
                .getEarliestDate(currentDate, 3, 16, 38, 10);
        long earliestDateLong = earliestDate.getTime().getTime();
        System.out.println("Earliest Date = "
                + earliestDate.getTime().toString());
        //计算从当前时间到最近一次执行时间的时间间隔
        long delay = earliestDateLong - currentDateLong;
        //计算执行周期为一星期
        long period = 7 * 24 * 60 * 60 * 1000;
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        //从现在开始delay毫秒之后，每隔一星期执行一次job1
        service.scheduleAtFixedRate(test, delay, period,
                TimeUnit.MILLISECONDS);

		
		
		
	}

}
