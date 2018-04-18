
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;


import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;  
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger; 
//import org.slf4j.LoggerFactory;

public class HttpSyncClient {
//	private Logger logger = LoggerFactory.getLogger(HttpSyncClient.class);
	private static int socketTimeout = 1000;// ���õȴ����ݳ�ʱʱ��5���� ����ҵ�����

	private static int connectTimeout = 2000;// ���ӳ�ʱ

	private static int maxConnNum = 4000;// ���ӳ����������

	private static int maxPerRoute = 1500;// ÿ�������Ĳ������ֻ��1500

	private static PoolingClientConnectionManager cm;

	private static HttpParams httpParams;

	private static final String DEFAULT_ENCODING = Charset.defaultCharset()
			.name();

	// proxy�����������
	private String host = "";
	private int port = 0;
	private String username = "";
	private String password = "";

	private DefaultHttpClient httpClient;

	private DefaultHttpClient proxyHttpClient;

	// Ӧ��������ʱ���Ӧ��ִ�еķ���
	public HttpSyncClient() {

		this.httpClient = createClient(false);

		this.proxyHttpClient = createClient(true);
	}

	public DefaultHttpClient createClient(boolean proxy) {

		SchemeRegistry sr = new SchemeRegistry();
		sr.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));
		SSLSocketFactory sslFactory;
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL");
			X509TrustManager tm = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			sslContext.init(null, new TrustManager[] { tm },
					new java.security.SecureRandom());
			sslFactory = new SSLSocketFactory(sslContext,
					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			sr.register(new Scheme("https", 443, sslFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ʼ�����ӳ�
		cm = new PoolingClientConnectionManager(sr);
		cm.setMaxTotal(maxConnNum);
		cm.setDefaultMaxPerRoute(maxPerRoute);
		httpParams = new BasicHttpParams();
		httpParams.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
				HttpVersion.HTTP_1_1);
		httpParams.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				connectTimeout);// ����ʱʱ��
		httpParams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
				socketTimeout);// ��ȡ���ݳ�ʱʱ��
		// ���������NoDelay���ԣ�httpclient��վ��֮�䴫������ʱ���ᾡ���ܼ�ʱ�ؽ����ͻ������е����ݷ��ͳ�ȥ�����������������������ʣ���������ʺ϶�ʵʱ��Ҫ��ߵĳ���
		httpParams.setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true);
		httpParams.setBooleanParameter(
				CoreConnectionPNames.STALE_CONNECTION_CHECK, true);

		DefaultHttpClient httpclient = new DefaultHttpClient(cm, httpParams);

		if (proxy) {
			httpclient.getCredentialsProvider().setCredentials(
					new AuthScope(host, port),
					new UsernamePasswordCredentials(username, password));
		}

		return httpclient;

	}

	public DefaultHttpClient getHttpClient() {
		return httpClient;
	}

	public DefaultHttpClient getProxyClient() {
		return proxyHttpClient;
	}

	public String httpGet(String url, List<BasicNameValuePair> parameters) {

		DefaultHttpClient client = getHttpClient();// Ĭ�ϻᵽ���в�ѯ���õ����ӣ����û�о��½�
		HttpGet getMethod = null;
		String returnValue = "";
		try {
			getMethod = new HttpGet(url);

			if (null != parameters) {
				String params = EntityUtils.toString(new UrlEncodedFormEntity(
						parameters, DEFAULT_ENCODING));
				getMethod.setURI(new URI(getMethod.getURI().toString() + "?"
						+ params));
	//			logger.debug("httpGet-getUrl:{}", getMethod.getURI());
			}

			HttpResponse response = client.execute(getMethod);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == 200) {
				HttpEntity he = response.getEntity();
				returnValue = new String(EntityUtils.toByteArray(he),
						DEFAULT_ENCODING);
				return returnValue;
			}

		} catch (UnsupportedEncodingException e) {
	//		logger.error(Thread.currentThread().getName()
	//				+ "httpGet Send Error,Code error:" + e.getMessage());
		} catch (ClientProtocolException e) {
	//		logger.error(Thread.currentThread().getName()
	//				+ "httpGet Send Error,Protocol error:" + e.getMessage());
		} catch (IOException e) {
	//		logger.error(Thread.currentThread().getName()
	//				+ "httpGet Send Error,IO error:" + e.getMessage());
		} catch (URISyntaxException e) {
	//		logger.error(Thread.currentThread().getName()
	//				+ "httpGet Send Error,IO error:" + e.getMessage());
		} finally {// �ͷ�����,�����ӷŻص����ӳ�
			getMethod.releaseConnection();

		}
		return returnValue;

	}

	public String httpPost(String url, List<BasicNameValuePair> parameters,
			String requestBody) {

		DefaultHttpClient client = getHttpClient();// Ĭ�ϻᵽ���в�ѯ���õ����ӣ����û�о��½�
		HttpPost postMethod = null;
		String returnValue = "";
		try {
			postMethod = new HttpPost(url);

			if (null != parameters) {
				String params = EntityUtils.toString(new UrlEncodedFormEntity(
						parameters, DEFAULT_ENCODING));
				postMethod.setURI(new URI(postMethod.getURI().toString() + "?"
						+ params));
		//		logger.debug("httpPost-getUrl:{}", postMethod.getURI());
			}

//			if (StringUtils.isNotBlank(requestBody)) {
//				StringEntity se = new StringEntity(requestBody,
//						DEFAULT_ENCODING);
//				postMethod.setEntity(se);
//			}

			HttpResponse response = client.execute(postMethod);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == 200) {
				HttpEntity he = response.getEntity();
				returnValue = new String(EntityUtils.toByteArray(he),
						DEFAULT_ENCODING);
				return returnValue;
			}

		} catch (UnsupportedEncodingException e) {
	//		logger.error(Thread.currentThread().getName()
	//				+ "httpPost Send Error,Code error:" + e.getMessage());
		} catch (ClientProtocolException e) {
	//		logger.error(Thread.currentThread().getName()
	//				+ "httpPost Send Error,Protocol error:" + e.getMessage());
		} catch (IOException e) {
	//		logger.error(Thread.currentThread().getName()
	//				+ "httpPost Send Error,IO error:" + e.getMessage());
		} catch (URISyntaxException e) {
	//		logger.error(Thread.currentThread().getName()
	//				+ "httpPost Send Error,IO error:" + e.getMessage());
		} finally {// �ͷ�����,�����ӷŻص����ӳ�
			postMethod.releaseConnection();
			// �ͷų����еĿ�������
			// client.getConnectionManager().closeIdleConnections(30L,
			// TimeUnit.MILLISECONDS);
		}
		return returnValue;

	}
}
