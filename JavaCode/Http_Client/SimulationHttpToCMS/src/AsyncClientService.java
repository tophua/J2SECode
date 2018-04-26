import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.message.BasicNameValuePair;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class AsyncClientService {

	public HttpRequestBase httpMethod;
	public HttpGet httpget;
	public HttpPost httpPost;
	/// ���൱�ڵ������첽HttpClient
	 //HttpClientFactory.getInstance()����Newһ��HttpClientFactory����(static),getInstance()���ö�����
	 //
	public static CloseableHttpAsyncClient hc=HttpClientFactory.getInstance().
			getHttpAsyncClientPool().getAsyncHttpClient();
	
	public AsyncClientService(){
		
	}
	
	public void sendHttpGet(String url){
		httpget=null;
		httpget=new HttpGet(url);
	}
	
	public void setLoginHeader(){
		httpget.setHeader(V2200Properties.AUTHSTR,V2200Properties.authorization);
	}
	
	public void setGetHeader(){
		httpget.setHeader("Connection", "keep-alive");
		//����SessionId���ڻ�ʲô�����ֻҪ�е���login���κ�sessionId��û��
	//	httpget.setHeader("Cookie", V2200Properties.JSESSION_ID+"dswqwqq2112");
		httpget.setHeader("Cookie", V2200Properties.JSESSION_ID+V2200Properties.sessionId);
	}
	
	public void sendHttpPost(String url,List<BasicNameValuePair> postBody){
		httpPost=null;
		httpPost=new HttpPost(url);
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
	
	public void setPostAsyncSearchHeader(){
		httpPost.setHeader("Accept", "*/*");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
	//	httpPost.setHeader("Referer", "http://10.82.25.251/webclient.html");
		httpPost.setHeader("Accept-Language", "zh-CN");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");	
		httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
		httpPost.setHeader("Connection","Keep-Alive");
		httpPost.setHeader("Pragma","no-cache");
		httpPost.setHeader("Cookie",V2200Properties.getAsyncSearchCookie());
		
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
		//���sessionId
		try {
			latch.await();
			Header[] headers = res[0].getAllHeaders();
			String _sessionId=getSessionId(headers);
			if(!StringUtils.isEmpty(_sessionId)){
			//	sessionId =_sessionId;
				V2200Properties.sessionId=_sessionId;
				System.out.println(_sessionId);
				V2200Properties.errorValue=errorInfo.NORMAL;
				
			}else{//û��sessionId
				V2200Properties.errorValue=errorInfo.Login_NO_SESSIONID;
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

	public void AsyncSearchRequest(String url,List<BasicNameValuePair> postBody){

		final CountDownLatch latch = new CountDownLatch(1);
		sendHttpPost(url,postBody);
		setPostAsyncSearchHeader();
		hc.execute(httpPost, new FutureCallback<HttpResponse>() {
			public void completed(final HttpResponse response) {
				httpPost.releaseConnection();
				JSONObject jsonObj = getResponseContent(response);
				parseAsyncSearchContent(jsonObj);
				latch.countDown();
			}
			public void failed(final Exception ex) {
				latch.countDown();
			}
			public void cancelled() {
				latch.countDown();
			}
		});
		
		//������������������ֱ��getASyncSearchResult
		try {
			latch.await();
			if (V2200Properties.errorValue==10002) {
			//ֱ�ӵ�����һ���ӿ�
			getAsyncSearchResult();
			}		
		} catch (InterruptedException e) {
			System.out.println("AsyncSearchRequest InterruptedException");
		}
	}
	
	public void getAsyncSearchResult(){
		List<BasicNameValuePair> postBody = new ArrayList<BasicNameValuePair>();	
		AsyncSearchResultPostBody(postBody);
		sendHttpPost(V2200Properties.getAsyncSearchResult,postBody);
		setPostAsyncSearchHeader();
		hc.execute(httpPost, new responseCall());	
	}
	
	private void AsyncSearchResultPostBody(List<BasicNameValuePair> postBody){
		postBody.add(new BasicNameValuePair("timeout", "15000"));
		postBody.add(new BasicNameValuePair("searchId", "123"));
	}
	
	
	/**
	 * ���������url���鷵�ر������顣
	 * @param urisToGet
	 */
	public void Orgrequest(String[] urls, final String attr, final boolean isAttrValList)
	{
		int urlLen=urls.length;
		final CountDownLatch latch = new CountDownLatch(urlLen);
		for(int i=0;i<urlLen;i++){
			final String uri = urls[i];
			sendHttpGet(uri);
			setGetHeader();
			hc.execute(httpget, new FutureCallback<HttpResponse>() {
				public void completed(final HttpResponse response) {
					httpget.releaseConnection();
					JSONObject jsonObj = getResponseContent(response);
					parseOrgNumContent(jsonObj,uri,attr,isAttrValList);
					latch.countDown();
				}
				public void failed(final Exception ex) {
					latch.countDown();
				}
				public void cancelled() {
					latch.countDown();
				}
			});
			//�����֯����OrgNum
			try {
				latch.await();
				if (V2200Properties.reOrgJsons!=null&&!CollectionUtils.isEmpty(V2200Properties.reOrgJsons)) {
					V2200Properties.iOrgNum= V2200Properties.reOrgJsons.get(0).getInt("totalCount");
				    System.out.println(V2200Properties.iOrgNum);
				}		
			} catch (InterruptedException e) {
				System.out.println("V2200HttpClient login InterruptedException");
			}
		}
	}
	
	/**
	 * ͨ�������url��ȡ���ص�content����
	 * @param urisToGet
	 * @return
	 */
	private JSONObject getResponseContent(HttpResponse response) {
		BufferedReader bufferedReader = null;
		InputStream ins = null;
		try {
			StringBuffer buffer = new StringBuffer();
			ins = response.getEntity().getContent();
			bufferedReader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			JSONObject obj = JSONObject.fromObject(buffer.toString());
			return obj;
		} catch (Exception e) {
			System.out.println("V2200HttpClient getResponseContent Exception");
		} finally {
			IOUtils.closeQuietly(bufferedReader);
		}
		return null;
	}

	private void parseAsyncSearchContent(JSONObject jsonObj){
		if(jsonObj !=null){
			Object code=jsonObj.get("code");
			if("-401".equals(code)){
				System.out.println("û�е���login��401����AsyncSearch");
				V2200Properties.errorValue=errorInfo.AsynSearch_LOGIN_ERROR;
				V2200Properties.sessionId=""; //֮ǰ��sessinId�Ѿ�û�ã���Ҫ���»��
				return;
			}
			Object isSuccess = jsonObj.get("desc");
			if ("success".equals(isSuccess)) {
				System.out.println("AsyncSearch����");
				V2200Properties.errorValue=errorInfo.AsynSearch_SUCCESS;
			}else{
				V2200Properties.errorValue=errorInfo.AsynSearch_DESC_ERROR;
			}
		}
	}
   /*
    * ���������֯�����ķ��ر�������
    */
	private void parseOrgNumContent(JSONObject jsonObj,String uri,final String attr, final boolean isAttrValList){
	
		V2200Properties.reOrgJsons=null;
		if(jsonObj !=null){	
			Object code=jsonObj.get("code");
			if("-401".equals(code)){
				//˵����û�е�¼login
				System.out.println("û�е���login��401���󣬽����µ�¼");
				V2200Properties.errorValue=errorInfo.NO_LOGIN;
				//���µ�¼���sessionId
		//		demoV2200.loginOperate();
		//		String[] uri0 = { uri };
		//		Orgrequest(uri0, attr, isAttrValList);			
				return; //ֱ���˳�
			}
			V2200Properties.reOrgJsons = getJSONsFromRep(jsonObj, attr, isAttrValList);		
		}
	}
	
	/**
	 * ��������Ϣ�е�msg
	 * 
	 * @param reqUrl
	 * @param c
	 *            ����
	 * @param attr
	 *            ��Ҫ��ȡ������
	 * @param isAttrValList
	 *            ��Ҫ��ȡ�������Ƿ�Ϊ����
	 * @return
	 */
	public List<JSONObject> getJSONsFromRep(JSONObject jsonObj, String attr, boolean isAttrValList) {
		List<JSONObject> reJsons = new ArrayList<JSONObject>();
		if (jsonObj != null) {
			Object isSuccess = jsonObj.get("desc");
			if ("success".equals(isSuccess)) {
				if (attr == null) {
					if (isAttrValList) {
						return jsonObj.getJSONArray("msg");

					} else {
						reJsons.add(jsonObj.getJSONObject("msg"));

					}
				} else {
					jsonObj = jsonObj.getJSONObject("msg");
					if (isAttrValList) {
						reJsons.addAll(jsonObj.getJSONArray(attr));
					} else {
						reJsons.add(jsonObj.getJSONObject(attr));
					}
				}
			}
		}
		return reJsons;
	}
}
