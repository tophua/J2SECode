package TestFuture;

public class Client {
	
	public Data request(final String queryStr){
		
		final FutureData future = new FutureData();
		//开启一个新的线程来构造真实数据
		new Thread(){
			public void run(){
				RealData realData = new RealData(queryStr);
				future.setRealData(realData);			}
		}.start();
		
	//先返回一个futureData对象，不让主方法阻塞，然后再让这个引用去得到耗时的操作的结果
		return future;
	}

}
