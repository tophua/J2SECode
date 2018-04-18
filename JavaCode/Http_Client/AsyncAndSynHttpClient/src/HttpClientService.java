
import java.io.IOException;
import java.net.URI;
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

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
/**
 * http client ҵ���߼�������
 */

public class HttpClientService {
	// private static Logger LOG = LoggerFactory
	// .getLogger(HttpClientService.class);

	protected void exeAsyncReq(String baseUrl, boolean isPost, List<BasicNameValuePair> urlParams,
			List<BasicNameValuePair> postBody, FutureCallback callback) throws Exception {

		if (baseUrl == null) {
			// LOG.warn("we don't have base url, check config");
			throw new Exception("missing base url");
		}

		HttpRequestBase httpMethod;
		CloseableHttpAsyncClient hc = null;

		try {
			/// ���൱�ڵ������첽HttpClient
			hc = HttpClientFactory.getInstance().getHttpAsyncClientPool().getAsyncHttpClient();

			hc.start();

			HttpClientContext localContext = HttpClientContext.create();
			BasicCookieStore cookieStore = new BasicCookieStore();

			if (isPost) { // Post��ʽ
				httpMethod = new HttpPost(baseUrl);

				if (null != postBody) {
					// LOG.debug("exeAsyncReq post postBody={}", postBody);
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postBody, "UTF-8");
					((HttpPost) httpMethod).setEntity(entity);
				}

				if (null != urlParams) {

					String getUrl = EntityUtils.toString(new UrlEncodedFormEntity(urlParams));

					httpMethod.setURI(new URI(httpMethod.getURI().toString() + "?" + getUrl));
				}

			} else { // Get��ʽ

				httpMethod = new HttpGet(baseUrl);

				if (null != urlParams) {

					String getUrl = EntityUtils.toString(new UrlEncodedFormEntity(urlParams));

					httpMethod.setURI(new URI(httpMethod.getURI().toString() + "?" + getUrl));
					// baseUrlΪ��http://10.82.17.15:8082/yftCloud��
					// ���繹���ɹ�����ǡ�GET http://10.82.17.15:8082/yftCloud?appid=2
					// HTTP/1.1��
				}
			}

			System.out.println("exeAsyncReq getparams:" + httpMethod.getURI());

			localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			/// ������������Ҫ��url��ʽ��Ȼ��execute()���з���,�����ͨ��callback���лص�
			hc.execute(httpMethod, localContext, callback);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/// ת��Ϊutf-8��ʽ�������
	protected String getHttpContent(HttpResponse response) {

		HttpEntity entity = response.getEntity();
		String body = null;

		if (entity == null) {
			return null;
		}

		try {

			body = EntityUtils.toString(entity, "utf-8");

		} catch (ParseException e) {

			// LOG.warn("the response's content inputstream is corrupt", e);
		} catch (IOException e) {

			// LOG.warn("the response's content inputstream is corrupt", e);
		}
		return body;
	}
}
