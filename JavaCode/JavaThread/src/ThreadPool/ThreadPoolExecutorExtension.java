package ThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 继承线程池
 * BlockingQueue是任务等待队列，一般有三种队列SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue
 */
public class ThreadPoolExecutorExtension extends ThreadPoolExecutor{

	public ThreadPoolExecutorExtension(int corePoolSize, int maximumPoolSize, long keepAliveTime, 
			TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void beforeExecute(Thread t,Runnable r){
		super.beforeExecute(t, r);
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
	}
	
}
