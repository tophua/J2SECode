package com.Task;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test_ScheduledExecutorSample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
       long initialDelay1 = 1;
       long period1 = 1;
       //从现在开始1秒钟之后，每隔1秒钟执行一次job1
       service.scheduleAtFixedRate(
    		   new ScheduledExecutorSample("job1"),
    		   initialDelay1, 
    		   period1,
    		   TimeUnit.SECONDS);
       
       long initialDelay2 = 1;
       long delay2 =1;
       //从现在开始2秒钟之后，每隔2秒钟执行一次job1
       service.scheduleWithFixedDelay(
    		   new ScheduledExecutorSample("job2"),
    		   initialDelay2, 
    		   delay2,
    		   TimeUnit.SECONDS);            
	}
}
