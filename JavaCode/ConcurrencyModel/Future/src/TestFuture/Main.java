package TestFuture;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = new Client();
		//理解返回一个FutureData
		Data data = client.request("name");
		System.out.println("请求完毕！");
		
		try{		
			//处理其他业务
			//这个过程中，真实数据RealData组装完成，重复利用等待时间
			Thread.sleep(2000);						
		    }catch (Exception e){			
		 }
		
		//真实数据
		System.out.println("数据 = "+ data.getResult());
	}

}
