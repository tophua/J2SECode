package Test_Provide_Consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

	private BlockingQueue<Data> queue;
	//随机对象
	private static Random r =new Random();
	
	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	     while(true){
	       try {
	            //获取数据
	            Data data = this.queue.take();
	            //进行数据处理。休眠0 - 1000毫秒模拟耗时
	            Thread.sleep(r.nextInt(1000));
	            System.out.println("当前消费线程：" + 
	            Thread.currentThread().getName() +
	            "， 消费成功，消费数据为id: " + data.getId());
	        } catch (InterruptedException e) {
	              e.printStackTrace();
	       }
	     }
	}

}
