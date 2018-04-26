package AsyncHttpClientPackage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.message.BasicNameValuePair;

public class AsyncClientService {
	public HttpGet httpget;
	public HttpPost httpPost;
	public static CloseableHttpAsyncClient hc=HttpClientFactory.getInstance().
			getHttpAsyncClientPool().getAsyncHttpClient();
	
	public AsyncClientService(){
		
	}
	
	public void sendHttpGet(String url){
		httpget=null;
		httpget=new HttpGet(url);
	}
	
	public void sendHttpPost(String url){
		httpPost=null;
		httpPost=new HttpPost(url);
	}
	
	public void setHttpPostBody(List<BasicNameValuePair> postBody){
		if (null != postBody) {
			UrlEncodedFormEntity entity;
			try {
				entity = new UrlEncodedFormEntity(postBody, "UTF-8");
				((HttpPost) httpPost).setEntity(entity);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	public HttpGet getHttpGetInstance(){
		return httpget;
	}
	
	public HttpPost getHttpPostInstance(){
		return httpPost;
	}
	
	public void execAsyncHttpGetForSessionId(){
		final CountDownLatch latch = new CountDownLatch(1);
	//	final HttpResponse[] res = new HttpResponse[1];
		hc.execute(httpget, new FutureCallback<HttpResponse>() {
			public void completed(final HttpResponse response) {
				httpget.releaseConnection();
		//		res[0] = response;
				latch.countDown();
			}

			public void failed(final Exception ex) {
				latch.countDown();
			}

			public void cancelled() {
				latch.countDown();
			}
		});
		//获得信息方式一	
		try {	
		    latch.await();
//			Header[] headers = res[0].getAllHeaders();
//			String _sessionId=getSessionId(headers);
//			if(!StringUtils.isEmpty(_sessionId)){
//				recordProperties.sessionId=_sessionId;
//				System.out.println("record login:"+_sessionId);
//				recordProperties.errorValue=recordProperties.NORMAL;			
//			}else{//没有sessionId
//				recordProperties.errorValue=recordProperties.Login_NO_SESSIONID;
//			}
		} catch (InterruptedException e) {
			System.out.println("V2200HttpClient login InterruptedException");
		}
	}
	
	
	public void execAsyncSearchRequest(){
		final CountDownLatch latch = new CountDownLatch(1);
		hc.execute(httpPost, new FutureCallback<HttpResponse>() {
			public void completed(final HttpResponse response) {
				httpPost.releaseConnection();
				//解析Json的方式
		//		JSONObject jsonObj = getResponseContent(response);
		//		parseAsyncSearchContent(jsonObj);
				latch.countDown();
			}
			public void failed(final Exception ex) {
				latch.countDown();
			}
			public void cancelled() {
				latch.countDown();
			}
		});
//
		try {
			latch.await();
	
		} catch (InterruptedException e) {
			System.out.println(" InterruptedException");
		}
		
	}
	
	public void getAsyncSearchResult(){
		List<BasicNameValuePair> postBody = new ArrayList<BasicNameValuePair>();	
		AsyncSearchResultPostBody(postBody);
	//	sendHttpPost(recordProperties.getAsyncSearchResult);
		setHttpPostBody(postBody);
		setPostAsyncSearchHeader();
	//	hc.execute(httpPost, new responseCall());	
	}
	
	private void AsyncSearchResultPostBody(List<BasicNameValuePair> postBody){
	//	postBody.add(new BasicNameValuePair("timeout", recordProperties.AsyncSearch_TimeOut));
	//	postBody.add(new BasicNameValuePair("searchId", recordProperties.AsyncSearch_SearchId));
	}
	
	private void setPostAsyncSearchHeader(){
		httpPost.setHeader("Accept", "*/*");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
	//	httpPost.setHeader("Referer", "http://10.82.25.251/webclient.html");
		httpPost.setHeader("Accept-Language", "zh-CN");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");	
		httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
		httpPost.setHeader("Connection","Keep-Alive");
		httpPost.setHeader("Pragma","no-cache");
	//	httpPost.setHeader("Cookie",recordProperties.getAsyncSearchCookie());
	}

}
