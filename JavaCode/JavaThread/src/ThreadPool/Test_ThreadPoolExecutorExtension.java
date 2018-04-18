package ThreadPool;

public class Test_ThreadPoolExecutorExtension {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//ThreadPoolExecutorExtensionManager是一个继承Runnable接口，那么需要下面两行代码形式进行
    //启动这个线程
		
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
