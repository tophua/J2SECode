package LockPackage;

public class Test_ReentrantLock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyReentrantLock myReenLock=new MyReentrantLock();
		
		Thread thread[]=new Thread[10];
		for(int i=0;i<10;i++){
			thread[i]=new Thread(new ReentrantLockJob(myReenLock),""+i);
		}
	}

}
