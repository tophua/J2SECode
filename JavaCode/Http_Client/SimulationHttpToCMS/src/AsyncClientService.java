import java.util.concurrent.CountDownLatch;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

import org.apache.commons.lang.StringUtils;

public class AsyncClientService {

	public HttpRequestBase httpMethod;
	public HttpGet httpget;
	/// 这相当于调用了异步HttpClient
	 //HttpClientFactory.getInstance()会先New一个HttpClientFactory对象(static),getInstance()会获得对象句柄
	 //
	public static CloseableHttpAsyncClient hc=HttpClientFactory.getInstance().
			getHttpAsyncClientPool().getAsyncHttpClient();
	
	public AsyncClientService(){
		
	}
	
	public void sendHttpGet(String url){
		httpget=new HttpGet(url);
	}
	
	public void setGetHeader(){
		httpget.setHeader(V2200Properties.AUTHSTR,V2200Properties.authorization);
	}
	
	public void execAsyncClient(FutureCallback callback){
		hc.execute(httpget, callback);
	}
	
	public void execAsyncForSessionId(){
		final CountDownLatch latch = new CountDownLatch(1);
		final HttpResponse[] res = new HttpResponse[1];
		hc.execute(httpget, new FutureCallback<HttpResponse>() {
			public void completed(final HttpResponse response) {
				httpget.releaseConnection();
				res[0] = response;
				latch.countDown();
			}

			public void failed(final Exception ex) {
				latch.countDown();
			}

			public void cancelled() {
				latch.countDown();
			}
		});
		//获得sessionId
		try {
			latch.await();
			Header[] headers = res[0].getAllHeaders();
			String _sessionId=getSessionId(headers);
			if(!StringUtils.isEmpty(_sessionId)){
			//	sessionId =_sessionId;
				System.out.println(_sessionId);
				
			}
		} catch (InterruptedException e) {
			System.out.println("V2200HttpClient login InterruptedException");
		}
	}
	
	private String getSessionId(Header[] headers) {
		if (headers != null && headers.length > 0) {

			for (Header header : headers) {
				String name = header.getName();
				if (V2200Properties.SET_COOKIE.equals(name)) {
					String value = header.getValue();
					int start = value.indexOf(V2200Properties.JSESSION_ID);
					if (start > -1) {
						String temp = value.substring(start);
						int end = temp.indexOf(V2200Properties.SEMICOLON);
						if (end > -1) {
							return temp.substring(V2200Properties.JSESSION_ID_LEN, end);
						} else {
							return temp.substring(V2200Properties.JSESSION_ID_LEN);
						}
					}
				}

			}

		}
		return null;
	}
}
