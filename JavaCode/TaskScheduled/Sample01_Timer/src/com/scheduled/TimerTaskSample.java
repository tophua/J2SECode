package com.scheduled;

import java.util.TimerTask;

public class TimerTaskSample extends TimerTask{

	private String jobName = "";
	public TimerTaskSample(String jobName) {
		// TODO Auto-generated constructor stub
		super();
		this.jobName = jobName;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("execute " + jobName); 
	}

}
