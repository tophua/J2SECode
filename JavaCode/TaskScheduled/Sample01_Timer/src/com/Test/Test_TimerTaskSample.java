package com.Test;

import java.util.Timer;

import com.scheduled.TimerTaskSample;

public class Test_TimerTaskSample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Timer timer =new Timer();
        
        long delay1 = 1* 1000;
        long period1 = 1000;    
     // �����ڿ�ʼ 1 ����֮��ÿ�� 1 ����ִ��һ�� job1 
        timer.schedule(new TimerTaskSample("job1"), delay1,period1);
        
        long delay2 = 2 * 1000; 
        long period2 = 2000; 
     // �����ڿ�ʼ 2 ����֮��ÿ�� 2 ����ִ��һ�� job2 
        timer.schedule(new TimerTaskSample("job2"), delay2, period2);
	}
}
