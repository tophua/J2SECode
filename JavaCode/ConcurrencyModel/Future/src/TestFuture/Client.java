package TestFuture;

public class Client {
	public Data request(final String queryStr){
		final FutureData future = new FutureData();
		//����һ���µ��߳���������ʵ����
		new Thread(){
			public void run(){
				RealData realData = new RealData(queryStr);
				future.setRealData(realData);			}
		}.start();
		return future;
	}

}
