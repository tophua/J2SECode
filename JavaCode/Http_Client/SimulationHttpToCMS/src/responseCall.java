import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;

public class responseCall implements FutureCallback<HttpResponse>{

	/**
	 * 请求完成后调用该函数
	 */
	@Override
	public void completed(HttpResponse response) {

		System.out.println(response.getStatusLine().getStatusCode());
		//如果发送url成功，下面getHttpContent()来获取返回值
	//	System.out.println(getHttpContent(response));
		processHttpRespose(response);
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

	/// 转换为utf-8格式进行输出
	protected String getHttpContent(HttpResponse response) {

		HttpEntity entity = response.getEntity();
		String body = null;
		if (entity == null) {
			return null;
		}
		try {
			//body是返回的值
			body = EntityUtils.toString(entity, "utf-8");
		} catch (ParseException e) {

		} catch (IOException e) {

		}
		return body;
	}
	
	public void processHttpRespose(HttpResponse response){
		getHttpContent(response);
		Header[] headers = response.getAllHeaders();
		System.out.println(response);
	}
}
