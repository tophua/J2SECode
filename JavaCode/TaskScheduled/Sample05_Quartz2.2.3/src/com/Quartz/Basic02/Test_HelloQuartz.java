package com.Quartz.Basic02;

import static org.quartz.DateBuilder.newDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class Test_HelloQuartz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// 创建scheduler
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// 定义一个Trigger
			Trigger trigger = newTrigger().withIdentity("trigger1", "group1") // 定义name/group
					.startNow()// 一旦加入scheduler，立即生效
					.withSchedule(simpleSchedule() // 使用SimpleTrigger
					.withIntervalInSeconds(1) // 每隔一秒执行一次
					.repeatForever()) // 一直执行，奔腾到老不停歇
					.build();

			// 定义一个JobDetail
			// 定义Job类为HelloQuartz类，这是真正的执行逻辑所在
			JobDetail job = newJob(HelloQuartz.class)
					.withIdentity("job1", "group1")// 定义name/group
					.usingJobData("name", "quartz")// 定义属性
					.build();

			// 加入这个调度
			scheduler.scheduleJob(job, trigger);
			// 启动之
			scheduler.start();
			// 运行一段时间后关闭
			Thread.sleep(10000);
			scheduler.shutdown(true);
		} catch (Exception e) {
			e.printStackTrace();
		}			
		System.exit(0); //这个是需要的
	}
}
