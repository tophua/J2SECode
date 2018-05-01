package ThreadPool_ThreadPoolExecutor;

import ThreadPool.SimpleTask;
import ThreadPool.ThreadPoolExecutorExtensionManager;

public class demo_ThreadPoolExcutor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutorExtensionManager pool=new ThreadPoolExecutorExtensionManager();
		new Thread(pool).start();
		//下面是具体添加任务的过程
		SimpleTask task=null;
		for(int i=0;i<10;i++){
			task=new SimpleTask();
			pool.addTask(task);
		}
		
	}

}
