package ThreadBasicWay;

public class demo_join {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread_join thread_join = new Thread_join("mythread");
		thread_join.start();
		
		try{
			thread_join.join();
			//����join()�����ϲ��̣߳������߳�mythread�ϲ������߳�����
			//�ϲ��̺߳�Ҳ������ִ�����߳���ִ������ټ������̡߳�
		}catch(InterruptedException e){
			e.printStackTrace();			
		}
		for(int i=0;i<=5;i++){
			System.out.println("I am main Thread");
		}
	}

}
