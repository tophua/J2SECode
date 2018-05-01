package ThreadPool_SelfRealization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



public class MyThreadExcutor {

	//����
	private volatile boolean RUNNING=true;
	//�������񶼷��ڶ����У��ù����߳�������
	private static BlockingQueue<Runnable> queue=null;
	private final HashSet<Worker> workers=new HashSet<Worker>();
	private final List<Thread> threadList=new ArrayList<Thread>();
    //�����߳���(ʵ�ʹ�����)
	int poolSize=0;
	//�����߳���(�ܹ�ͬʱ�ṩ��)
	int coreSize=0;
	boolean shutdown=false;
	
	public MyThreadExcutor(int poolSize){
		this.poolSize=poolSize;
		queue=new LinkedBlockingQueue<Runnable>(poolSize);
	}
	
	//���̳߳ش����������
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
	
	///�����߳�ֹͣ�˹����̣߳�����һ����ʶ�����߹����̣߳�
		//���ڽ�����Ȼ��֪ͨ�����߳̿���interrupt()��
		//�������߳�ֹͣ��ǵð����߳�Ҳֹͣ��
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
	 * �����߳�,�ڲ���
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
                   task = getTask(); //�Ӷ�����ȡ���񣬲�ִ��
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