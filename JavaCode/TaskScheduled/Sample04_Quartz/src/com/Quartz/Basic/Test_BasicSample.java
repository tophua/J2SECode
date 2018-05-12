package com.Quartz.Basic;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

public class Test_BasicSample {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   try {
	            // ����һ��Scheduler
	            SchedulerFactory schedFact = 
	            new org.quartz.impl.StdSchedulerFactory();
	            Scheduler sched = schedFact.getScheduler();
	            sched.start();
	            // ����һ��JobDetail��ָ��name��groupname���Լ������Job������
	            //��Job��������Ҫִ������
	            JobDetail jobDetail = new JobDetail(
	            		"myJob", "myJobGroup",QuartzBasicSample.class);
	            jobDetail.getJobDataMap().put("type", "FULL");
	            // ����һ��ÿ�ܴ�����Trigger��ָ�����ڼ����㼸��ִ��
	            Trigger trigger = TriggerUtils.makeWeeklyTrigger(5, 11, 26);
	            trigger.setGroup("myTriggerGroup");
	            // �ӵ�ǰʱ�����һ�뿪ʼִ��
	            trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));
	            // ָ��trigger��name
	            trigger.setName("myTrigger");
	            // ��scheduler��JobDetail��Trigger������һ�𣬿�ʼ��������
	            sched.scheduleJob(jobDetail, trigger);
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
