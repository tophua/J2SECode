import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Lookup;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.KerberosSchemeFactory;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.ssl.SSLContexts;

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
	
//	private static int socketTimeout = 1000;// ���õȴ����ݳ�ʱʱ��5���� ����ҵ�����
//	private static int connectTimeout = 2000;// ���ӳ�ʱ
//	private static int poolSize = 3000;// ���ӳ����������
//	private static int maxPerRoute = 1500;// ÿ�������Ĳ������ֻ��1500
//
//	// http������ز���
//	private static String host = "";
//	private static int port = 0;
//	private static String username = "";
//	private static String password = "";
//
//	// �첽httpclient
//	private static CloseableHttpAsyncClient asyncHttpClient=null;
//	// �첽�Ӵ����httpclient
//	private static CloseableHttpAsyncClient proxyAsyncHttpClient=null;
//	
//	static {
//		try {
//		//	asyncHttpClient = createAsyncClient(true); //������ݺ�����
//			asyncHttpClient = createBasicAsyncClient();
//	//		proxyAsyncHttpClient = createAsyncClient(true); //������ݺ�����
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public HttpAsyncClient() {
//		asyncHttpClient.start();
//	//	proxyAsyncHttpClient.start();
//	}
//
//	public static CloseableHttpAsyncClient createAsyncClient(boolean proxy)
//			throws KeyManagementException, UnrecoverableKeyException,
//			NoSuchAlgorithmException, KeyStoreException,
//			MalformedChallengeException, IOReactorException {
//
//		RequestConfig requestConfig = RequestConfig.custom()
//				.setConnectTimeout(connectTimeout)
//				.setSocketTimeout(socketTimeout).build();
//		
//		SSLContext sslcontext = SSLContexts.createDefault();
//
//		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
//				username, password);
//
//		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//		credentialsProvider.setCredentials(AuthScope.ANY, credentials);
//
//		// ����Э��http��https��Ӧ�Ĵ���socket���ӹ����Ķ���
//		Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder
//				.<SchemeIOSessionStrategy> create()
//				.register("http", NoopIOSessionStrategy.INSTANCE)
//				.register("https", new SSLIOSessionStrategy(sslcontext))
//				.build();
//
//		// ����io�߳�
//		IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
//				.setIoThreadCount(Runtime.getRuntime().availableProcessors())
//				.build();
//		// �������ӳش�С
//		ConnectingIOReactor ioReactor;
//		ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
//		PoolingNHttpClientConnectionManager conMgr = new PoolingNHttpClientConnectionManager(
//				ioReactor, null, sessionStrategyRegistry, null);
//
//		if (poolSize > 0) {
//			conMgr.setMaxTotal(poolSize);
//		}
//
//		if (maxPerRoute > 0) {
//			conMgr.setDefaultMaxPerRoute(maxPerRoute);
//		} else {
//			conMgr.setDefaultMaxPerRoute(10);
//		}
//
//		ConnectionConfig connectionConfig = ConnectionConfig.custom()
//				.setMalformedInputAction(CodingErrorAction.IGNORE)
//				.setUnmappableInputAction(CodingErrorAction.IGNORE)
//				.setCharset(Consts.UTF_8).build();
//
//		Lookup<AuthSchemeProvider> authSchemeRegistry = RegistryBuilder
//				.<AuthSchemeProvider> create()
//				.register(AuthSchemes.BASIC, new BasicSchemeFactory())
//				.register(AuthSchemes.DIGEST, new DigestSchemeFactory())
//				.register(AuthSchemes.NTLM, new NTLMSchemeFactory())
//				.register(AuthSchemes.SPNEGO, new SPNegoSchemeFactory())
//				.register(AuthSchemes.KERBEROS, new KerberosSchemeFactory())
//				.build();
//		conMgr.setDefaultConnectionConfig(connectionConfig);
//
//		if (proxy) {
//			return HttpAsyncClients.custom().setConnectionManager(conMgr)
//					.setDefaultCredentialsProvider(credentialsProvider)
//					.setDefaultAuthSchemeRegistry(authSchemeRegistry)
//					.setProxy(new HttpHost(host, port))
//					.setDefaultCookieStore(new BasicCookieStore())
//					.setDefaultRequestConfig(requestConfig).build();
//		} else {
//			return HttpAsyncClients.custom().setConnectionManager(conMgr)
//					.setDefaultCredentialsProvider(credentialsProvider)
//					.setDefaultAuthSchemeRegistry(authSchemeRegistry)
//					.setDefaultCookieStore(new BasicCookieStore()).build();
//		}
//
//	}
//
//	public static CloseableHttpAsyncClient createBasicAsyncClient(){
//		return HttpAsyncClientBuilder.create().setMaxConnTotal(poolSize)
//				.setMaxConnPerRoute(maxPerRoute).build();
//	}
//	
//	public CloseableHttpAsyncClient getAsyncHttpClient() {
//		return asyncHttpClient;
//	}
//
//	public CloseableHttpAsyncClient getProxyAsyncHttpClient() {
//		return proxyAsyncHttpClient;
//	}
}
