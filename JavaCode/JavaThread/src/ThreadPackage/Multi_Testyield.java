package ThreadPackage;

class ThreadYield extends Thread{  
    public ThreadYield(String name) {  
        super(name);  
    }  
   
    @Override  
    public void run() {  
        for (int i = 1; i <= 50; i++) {  
            System.out.println("" + this.getName() + "-----" + i);  
            // ��iΪ30ʱ�����߳̾ͻ��CPUʱ���õ��������������Լ����߳�ִ�У�Ҳ����˭������˭ִ�У�  
            if (i ==30) {  
                this.yield();  
            }  
        }       
    }  
}  

public class Multi_Testyield {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ThreadYield yt1 = new ThreadYield("����");  
	     ThreadYield yt2 = new ThreadYield("����");  
	     yt1.start();  
	     yt2.start(); 
	}

}
