package LockPackage;

public class ReentrantLockJob implements Runnable{
	
	private MyReentrantLock myReen;
	
	public ReentrantLockJob(MyReentrantLock MyReenL){
		this.myReen=MyReenL;
	}
	
	@Override
	public synchronized void run(){
		myReen.printJob(new Object());
	}

}
