package com.Task;

public class ScheduledExecutorSample implements Runnable{

	private String jobName = "";
	public ScheduledExecutorSample(String jobName) {
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
