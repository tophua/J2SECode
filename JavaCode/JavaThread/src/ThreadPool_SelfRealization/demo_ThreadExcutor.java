package ThreadPool_SelfRealization;


public class demo_ThreadExcutor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThreadExcutor excutor=new MyThreadExcutor(3);
		for(int i=0;i<10;i++){
			excutor.exec(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName()+"is Runing");
				}
				
			});
		}
		excutor.shutdown();
	}

}
