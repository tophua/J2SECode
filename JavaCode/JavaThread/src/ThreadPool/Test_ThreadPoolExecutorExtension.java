package ThreadPool;

public class Test_ThreadPoolExecutorExtension {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//ThreadPoolExecutorExtensionManager��һ���̳�Runnable�ӿڣ���ô��Ҫ�������д�����ʽ����
    //��������߳�
		
		ThreadPoolExecutorExtensionManager pool=new ThreadPoolExecutorExtensionManager();
		new Thread(pool).start();
		//�����Ǿ����������Ĺ���
		SimpleTask task=null;
		for(int i=0;i<10;i++){
			task=new SimpleTask();
			pool.addTask(task);
		}
		
	}
    
}
