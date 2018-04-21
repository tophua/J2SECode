
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * http client 业务逻辑处理类
 */

public class HttpClientService {

	public 	HttpRequestBase httpMethod;
	/// 这相当于调用了异步HttpClient
	 //HttpClientFactory.getInstance()会先New一个HttpClientFactory对象(static),getInstance()会
	 //获得对象句柄
	public static CloseableHttpAsyncClient hc=HttpClientFactory.getInstance().
			getHttpAsyncClientPool().getAsyncHttpClient();
	
	protected void exeAsyncReq(String baseUrl, boolean isPost, List<BasicNameValuePair> urlParams,
			List<BasicNameValuePair> postBody, FutureCallback callback) throws Exception {

		if (baseUrl == null) {
			throw new Exception("missing base url");
		}
		try {
			HttpClientContext localContext = HttpClientContext.create();
			BasicCookieStore cookieStore = new BasicCookieStore();

			if (isPost) { // Post方式
				sendHttpPost(baseUrl,urlParams,postBody);
			} else { // Get方式
				sendHttpGet(baseUrl,urlParams);
			}

			System.out.println("exeAsyncReq getparams:" + httpMethod.getURI());
			localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			/// 构建好我们需要的url形式，然后execute()进行发送,结果是通过callback进行回调
			hc.execute(httpMethod, localContext, callback);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void sendHttpGet(String url,List<BasicNameValuePair> urlParams) throws Exception{
		httpMethod = new HttpGet(url);
		if (null != urlParams) {
			String getUrl;
			try {
				getUrl = EntityUtils.toString(new UrlEncodedFormEntity(urlParams));
				httpMethod.setURI(new URI(httpMethod.getURI().toString() + "?" + getUrl));
				// baseUrl为“http://10.82.17.15:8082/yftCloud”
				// 例如构建成功后的是“GET http://10.82.17.15:8082/yftCloud?appid=2
				// HTTP/1.1”
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public void sendHttpPost(String url,List<BasicNameValuePair> urlParams,
			List<BasicNameValuePair> postBody) throws Exception{
		httpMethod = new HttpPost(url);
		if (null != postBody) {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postBody, "UTF-8");
			((HttpPost) httpMethod).setEntity(entity);
		}
		if (null != urlParams) {
			String getUrl = EntityUtils.toString(new UrlEncodedFormEntity(urlParams));
			httpMethod.setURI(new URI(httpMethod.getURI().toString() + "?" + getUrl));
		}
		
	}
	/// 转换为utf-8格式进行输出
	protected String getHttpContent(HttpResponse response) {

		HttpEntity entity = response.getEntity();
		String body = null;

		if (entity == null) {
			return null;
		}
		try {

			body = EntityUtils.toString(entity, "utf-8");

		} catch (ParseException e) {

		} catch (IOException e) {

		}
		return body;
	}
}
