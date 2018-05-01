package ThreadBasicWay;

public class demo_yield {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		Thread_yield y1 = new Thread_yield("y1");
		Thread_yield y2 = new Thread_yield("y2");
		
		y1.start();
		y2.start();
		for(int i=0; i<=5;i++){
			System.out.println("I am main Thread");
		}
	}

}
