package AsyncHttpClientPackage;

public class HttpClientFactory {
	//�첽http
		private static HttpAsyncClient httpAsyncClient = new HttpAsyncClient();

		private HttpClientFactory() {
		}

		private static HttpClientFactory httpClientFactory = new HttpClientFactory();

		public static HttpClientFactory getInstance() {

			return httpClientFactory;
		}

		public HttpAsyncClient getHttpAsyncClientPool() {
			return httpAsyncClient;
		}
}
