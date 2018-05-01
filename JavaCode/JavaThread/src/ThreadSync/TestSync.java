package ThreadSync;


public class TestSync implements Runnable{

	 public Timer timer = new Timer();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      Thread t1 = new Thread(new TestSync());
      Thread t2 = new Thread(new TestSync());
      t1.setName("t1");
      t2.setName("t2");
      t1.start();
      t2.start();
      
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		timer.add(Thread.currentThread().getName());
	}

}
