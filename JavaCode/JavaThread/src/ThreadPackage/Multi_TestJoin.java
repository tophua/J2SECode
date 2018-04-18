package ThreadPackage;

class Thread3 extends Thread{  
    private String name;  
    public Thread3(String name) {  
        super(name);  
       this.name=name;  
    }  
    public void run() {  
        System.out.println(Thread.currentThread().getName() + " �߳����п�ʼ!");  
        for (int i = 0; i < 5; i++) {  
            System.out.println("���߳�"+name + "���� : " + i);  
            try {  
                sleep((int) Math.random() * 10);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
        System.out.println(Thread.currentThread().getName() + " �߳����н���!");  
    }  
}  

public class Multi_TestJoin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	       System.out.println(Thread.currentThread().getName()+"���߳����п�ʼ!");  
	        Thread3 mTh1=new Thread3("A");  
	        Thread3 mTh2=new Thread3("B");  
	        mTh1.start();  
	        mTh2.start();  
	///����join()
	        try {  
	            mTh1.join();  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        try {  
	            mTh2.join();  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println(Thread.currentThread().getName()+ "���߳����н���!");  
	}

}
