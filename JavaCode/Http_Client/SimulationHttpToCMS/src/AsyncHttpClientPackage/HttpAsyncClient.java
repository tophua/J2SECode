package AsyncHttpClientPackage;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;

public class HttpAsyncClient {
	private static int socketTimeout = 1000;// 设置等待数据超时时间5秒钟 根据业务调整
	private static int connectTimeout = 2000;// 连接超时
	private static int poolSize = 3000;// 连接池最大连接数
	private static int maxPerRoute = 1500;// 每个主机的并发最多只有1500

	// 异步httpclient
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
