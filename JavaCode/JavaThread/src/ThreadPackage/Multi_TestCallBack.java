package ThreadPackage;

class Data   
{   
    public int value = 0;   
}   
class Work   
{   
    public void process(Data data, int num1,int num2,int num3){   
    	data.value += num1;  
    	data.value += num2; 
    	data.value += num3; 
//       for (int n : numbers){   
//              data.value += n;   
//         }   
    }   
}   


public class Multi_TestCallBack extends Thread{

	private Work work;   
	public Multi_TestCallBack(Work work)   
	{   
	this.work = work;   
	}   
	public void run()   
	{   
		java.util.Random random = new java.util.Random();   
		Data data = new Data();   
		int n1 = random.nextInt(1000);   
		int n2 = random.nextInt(2000);   
		int n3 = random.nextInt(3000);   
		work.process(data, n1,n2,n3); // 使用回调函数   
		System.out.println(String.valueOf(n1) + "+" + String.valueOf(n2) + "+"   
		+ String.valueOf(n3) + "=" + data.value);   
	}   
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new Multi_TestCallBack(new Work());   
		thread.start();   
	}

}
