package ThreadPackage;

class Thread2 implements Runnable{  
    private String name;   
    
    public Thread2(String name) {  
        this.name=name;  
    }  
  
    @Override  
    public void run() {  
      for (int i = 0; i < 5; i++) {  
         System.out.println(name + "运行  :  " + i);  
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
//先通过Thread类的构造方法Thread(Runnable target) 构造出对象，
//然后调用Thread对象的start()方法来运行多线程代码。
	}

}
