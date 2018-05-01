package ThreadSync;

public class Timer {
	  private static int num = 0;

	 //public synchronized void add(String name)
	 // 在声明方法时加入synchronized时表示在执行这个方法的过程之中当前对象被锁定
	  public void add(String name) {

	        synchronized (this) {
	            /*
	             * 使用synchronized(this)来锁定当前对象，
	             * 这样就不会再出现两个不同的线程同时访问同一个对象资源的问题了
	             * 只有当一个线程访问结束后才会轮到下一个线程来访问
	             */
	            num++;
	            try {
	                Thread.sleep(1);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println(name + "：你是第" + num + "个使用timer的线程");
	        }
	 }
}
