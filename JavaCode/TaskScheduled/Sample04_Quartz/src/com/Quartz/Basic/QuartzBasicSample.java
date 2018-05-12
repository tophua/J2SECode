package com.Quartz.Basic;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//1.8�汾
public class QuartzBasicSample implements Job{

	//ʵ��ִ�е�����
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		 System.out.println("Generating report - "
		            + arg0.getJobDetail().getFullName()+", type = "
	                + arg0.getJobDetail().getJobDataMap().get("type"));
	        System.out.println(new Date().toString());
	}
}
