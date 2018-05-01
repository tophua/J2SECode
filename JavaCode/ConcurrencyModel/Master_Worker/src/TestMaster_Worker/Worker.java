package TestMaster_Worker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable{

	//每个子worker线程需要执行的子任务
	private ConcurrentLinkedQueue<Task> workQueue;
	//子任务结果
	private ConcurrentHashMap<String, Object> resultMap;
	
	public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue){
		this.workQueue = workQueue;
	}
	
	public void setResultMap(ConcurrentHashMap<String, Object> resultMap){
		this.resultMap = resultMap;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			Task input = this.workQueue.poll();
			if(input == null){
				break;
			}
			Object output = handle(input);
			this.resultMap.put(Integer.toString(input.getId()), output);
		}
	}
	
	private Object handle(Task input){
		Object output =null;
		try{
			//处理任务的耗时
			Thread.sleep(500);
			output = input.getPrice();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return input;	
	}

}
