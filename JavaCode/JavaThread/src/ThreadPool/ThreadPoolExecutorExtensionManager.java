package ThreadPool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/*
 * ThreadPoolExecutorExtensionManager类相当于一个主线程，那么在构造函数中，创建一个
 *线程池ThreadPoolExecutorExtension，在run()中发现，如果这个线程池还没有任何task任务
 *加入，这个主线程在等待，直到其他线程通过调用 notify 方法/ notifyAll 方法通知在此对象
 *的监视器上等待的线程醒来；
 * 外界通过addTask()函数向队列中插入任务，每个任务其实也是继承Runnable，相当于每个线程。
 * 当增加了一个任务后，通过notify()方法通知主线程来任务了。主线程通过pool.execute(tasks.poll())
 * 方法将任务丢到线程池中进行处理
 * 因此，添加任务和主线程让线程池执行任务是独立的，两者不相关。
 */

public class ThreadPoolExecutorExtensionManager implements Runnable{

	public Queue<Runnable> tasks=new LinkedList<Runnable>(); ///接收任务的队列
	private ExecutorService pool =null; ///对应一个线程池句柄	
	private static Object lock=new Object();
	
	public ThreadPoolExecutorExtensionManager(){
		pool=new ThreadPoolExecutorExtension(4,50,0L,
				TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
	}
		
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(tasks.size()>0){
				pool.execute(tasks.poll());//poll()将每个传入，然后从tasks中移除
			}else{
				try{
					synchronized (lock) {
					   lock.wait();	
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}

	public void executeTasks(){
		int size=tasks.size();
		for(int i=0;i<size;i++){
			pool.execute(tasks.poll());
			System.out.println("-----------"+i);
		}
	}
	
	public void shutDown(){
		pool.shutdown();
		System.out.println("----shut Down---");
	}
	
	public Queue<Runnable> getTasks(){
		return tasks;
	}
	
	public void addTask(Runnable task){
		this.tasks.add(task);
		synchronized (lock) {
		 lock.notify();	
		}
	}

}
