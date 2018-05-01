package Test_Provide_Consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

	private BlockingQueue<Data> queue;
	//�������
	private static Random r =new Random();
	
	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	     while(true){
	       try {
	            //��ȡ����
	            Data data = this.queue.take();
	            //�������ݴ�������0 - 1000����ģ���ʱ
	            Thread.sleep(r.nextInt(1000));
	            System.out.println("��ǰ�����̣߳�" + 
	            Thread.currentThread().getName() +
	            "�� ���ѳɹ�����������Ϊid: " + data.getId());
	        } catch (InterruptedException e) {
	              e.printStackTrace();
	       }
	     }
	}

}
