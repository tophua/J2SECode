
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.message.BasicNameValuePair;

public class Main_HttpClientDemo extends HttpClientService{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main_HttpClientDemo().getConfCall();
	}

	public void getConfCall() {

		String url = "http://10.82.17.15:8082/yftCloud";
		//下面是构建参数值内容
		List<BasicNameValuePair> urlParams = new ArrayList<BasicNameValuePair>();	
		urlParams.add(new BasicNameValuePair("appid", "2"));
		
		exeHttpReq(url, false, urlParams, null, new GetConfCall());
	}

	public void exeHttpReq(String baseUrl, boolean isPost,
			List<BasicNameValuePair> urlParams,
			List<BasicNameValuePair> postBody,
			FutureCallback<HttpResponse> callback) {

		try {
			System.out.println("enter exeAsyncReq");
			exeAsyncReq(baseUrl, isPost, urlParams, postBody, callback);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 被回调的对象，给异步的httpclient使用
	 * 当调用后有返回结果时，都会进入这里
	 * 
	 * */
	class GetConfCall implements FutureCallback<HttpResponse> {
		/**
		 * 请求完成后调用该函数
		 */
		@Override
		public void completed(HttpResponse response) {

			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(getHttpContent(response));
			HttpClientUtils.closeQuietly(response);
		}

		/**
		 * 请求取消后调用该函数
		 */
		@Override
		public void cancelled() {
			System.out.println("cancell ");
		}

		/**
		 * 请求失败后调用该函数
		 */
		@Override
		public void failed(Exception e) {
			System.out.println("failed ");
		}

	}
	
}
