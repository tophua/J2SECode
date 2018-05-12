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
	            // 创建一个Scheduler
	            SchedulerFactory schedFact = 
	            new org.quartz.impl.StdSchedulerFactory();
	            Scheduler sched = schedFact.getScheduler();
	            sched.start();
	            // 创建一个JobDetail，指明name，groupname，以及具体的Job类名，
	            //该Job负责定义需要执行任务
	            JobDetail jobDetail = new JobDetail(
	            		"myJob", "myJobGroup",QuartzBasicSample.class);
	            jobDetail.getJobDataMap().put("type", "FULL");
	            // 创建一个每周触发的Trigger，指明星期几几点几分执行
	            Trigger trigger = TriggerUtils.makeWeeklyTrigger(5, 11, 26);
	            trigger.setGroup("myTriggerGroup");
	            // 从当前时间的下一秒开始执行
	            trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));
	            // 指明trigger的name
	            trigger.setName("myTrigger");
	            // 用scheduler将JobDetail与Trigger关联在一起，开始调度任务
	            sched.scheduleJob(jobDetail, trigger);
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
