package ThreadPool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/*
 * ThreadPoolExecutorExtensionManager���൱��һ�����̣߳���ô�ڹ��캯���У�����һ��
 *�̳߳�ThreadPoolExecutorExtension����run()�з��֣��������̳߳ػ�û���κ�task����
 *���룬������߳��ڵȴ���ֱ�������߳�ͨ������ notify ����/ notifyAll ����֪ͨ�ڴ˶���
 *�ļ������ϵȴ����߳�������
 * ���ͨ��addTask()����������в�������ÿ��������ʵҲ�Ǽ̳�Runnable���൱��ÿ���̡߳�
 * ��������һ�������ͨ��notify()����֪ͨ���߳��������ˡ����߳�ͨ��pool.execute(tasks.poll())
 * ���������񶪵��̳߳��н��д���
 * ��ˣ������������߳����̳߳�ִ�������Ƕ����ģ����߲���ء�
 */

public class ThreadPoolExecutorExtensionManager implements Runnable{

	public Queue<Runnable> tasks=new LinkedList<Runnable>(); ///��������Ķ���
	private ExecutorService pool =null; ///��Ӧһ���̳߳ؾ��	
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
				pool.execute(tasks.poll());//poll()��ÿ�����룬Ȼ���tasks���Ƴ�
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
