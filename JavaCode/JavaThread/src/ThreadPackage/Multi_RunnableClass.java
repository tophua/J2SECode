package ThreadPackage;

class Thread2 implements Runnable{  
    private String name;   
    
    public Thread2(String name) {  
        this.name=name;  
    }  
  
    @Override  
    public void run() {  
      for (int i = 0; i < 5; i++) {  
         System.out.println(name + "����  :  " + i);  
         try {  
              Thread.sleep((int) Math.random() * 10);  
         } catch (InterruptedException e) {  
              e.printStackTrace();  
        }  
     }          
   }       
} 

public class Multi_RunnableClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  new Thread(new Thread2("C")).start();  
	  new Thread(new Thread2("D")).start();  
//��ͨ��Thread��Ĺ��췽��Thread(Runnable target) ���������
//Ȼ�����Thread�����start()���������ж��̴߳��롣
	}

}
