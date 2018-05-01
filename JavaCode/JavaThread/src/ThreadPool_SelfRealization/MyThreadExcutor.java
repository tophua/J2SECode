package ThreadPool_SelfRealization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



public class MyThreadExcutor {

	//创建
	private volatile boolean RUNNING=true;
	//所有任务都放在队列中，让工作线程来消费
	private static BlockingQueue<Runnable> queue=null;
	private final HashSet<Worker> workers=new HashSet<Worker>();
	private final List<Thread> threadList=new ArrayList<Thread>();
    //工作线程数(实际工作的)
	int poolSize=0;
	//核心线程数(能够同时提供的)
	int coreSize=0;
	boolean shutdown=false;
	
	public MyThreadExcutor(int poolSize){
		this.poolSize=poolSize;
		queue=new LinkedBlockingQueue<Runnable>(poolSize);
	}
	
	//向线程池存入任务队列
	public void exec(Runnable runnable) {
		if (runnable == null)
			throw new NullPointerException();
		if (coreSize < poolSize) {
			addThread(runnable);
		} else {
			// System.out.println("offer" + runnable.toString() + " " +
			// queue.size());
			try {
				queue.put(runnable);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addThread(Runnable runnable){
		   coreSize ++;
		   Worker worker = new Worker(runnable);
		   workers.add(worker);
		   Thread t = new Thread(worker);
		   threadList.add(t);
		   try {
		         t.start();
		   }catch (Exception e){
		         e.printStackTrace();
		   }
		}
	
	///是主线程停止了工作线程，先用一个标识符告诉工作线程，
		//不在接任务，然后通知工作线程可以interrupt()，
		//当所有线程停止后记得把主线程也停止掉
	public void shutdown() {
		RUNNING = false;
		if (!workers.isEmpty()) {
			for (Worker worker : workers) {
				worker.interruptIfIdle();
			}
		}
		shutdown = true;
		Thread.currentThread().interrupt();
	}

	
	/*
	 * 工作线程,内部类
	 */
	class Worker implements Runnable{

	   public Worker(Runnable runnable){
	       queue.offer(runnable);
	   }
	   
	   @Override
		public void run() {
			// TODO Auto-generated method stub
           while (true && RUNNING){
               if(shutdown == true){
                   Thread.interrupted();
               }
               Runnable task = null;
               try {
                   task = getTask(); //从队列中取任务，并执行
                   task.run();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
		}
       public Runnable getTask() throws InterruptedException {
           return queue.take();
       }

       public void interruptIfIdle() {
           for (Thread thread :threadList) {
               System.out.println(thread.getName() + " interrupt");
               thread.interrupt();
           }
       }
	}
	
}
