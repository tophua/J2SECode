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
	 * ������ɺ���øú���
	 */
	@Override
	public void completed(HttpResponse response) {

		System.out.println(response.getStatusLine().getStatusCode());
		//�������url�ɹ�������getHttpContent()����ȡ����ֵ
	//	System.out.println(getHttpContent(response));
		processHttpRespose(response);
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

	/// ת��Ϊutf-8��ʽ�������
	protected String getHttpContent(HttpResponse response) {

		HttpEntity entity = response.getEntity();
		String body = null;
		if (entity == null) {
			return null;
		}
		try {
			//body�Ƿ��ص�ֵ
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
