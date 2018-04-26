package AsyncHttpClientPackage;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;

public class HttpAsyncClient {
	private static int socketTimeout = 1000;// ���õȴ����ݳ�ʱʱ��5���� ����ҵ�����
	private static int connectTimeout = 2000;// ���ӳ�ʱ
	private static int poolSize = 3000;// ���ӳ����������
	private static int maxPerRoute = 1500;// ÿ�������Ĳ������ֻ��1500

	// �첽httpclient
	private static CloseableHttpAsyncClient asyncHttpClient=null;
	static {
		try {
			asyncHttpClient = createBasicAsyncClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public HttpAsyncClient() {
		asyncHttpClient.start();
	}
	
	public static CloseableHttpAsyncClient createBasicAsyncClient(){
		return HttpAsyncClientBuilder.create().setMaxConnTotal(poolSize)
				.setMaxConnPerRoute(maxPerRoute).build();
	}
	
	public CloseableHttpAsyncClient getAsyncHttpClient() {
		return asyncHttpClient;
	}
}
