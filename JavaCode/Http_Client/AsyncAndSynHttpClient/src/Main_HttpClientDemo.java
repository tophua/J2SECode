
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
		//�����ǹ�������ֵ����
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
	 * ���ص��Ķ��󣬸��첽��httpclientʹ��
	 * �����ú��з��ؽ��ʱ�������������
	 * 
	 * */
	class GetConfCall implements FutureCallback<HttpResponse> {
		/**
		 * ������ɺ���øú���
		 */
		@Override
		public void completed(HttpResponse response) {

			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(getHttpContent(response));
			HttpClientUtils.closeQuietly(response);
		}

		/**
		 * ����ȡ������øú���
		 */
		@Override
		public void cancelled() {
			System.out.println("cancell ");
		}

		/**
		 * ����ʧ�ܺ���øú���
		 */
		@Override
		public void failed(Exception e) {
			System.out.println("failed ");
		}

	}
	
}
