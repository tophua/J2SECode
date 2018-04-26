import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;

import JavaBeanClass.recordAsyncSearch;
import JavaBeanClass.recordFile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

public class responseCall implements FutureCallback<HttpResponse>{

	/**
	 * ������ɺ���øú���
	 */
	@Override
	public void completed(HttpResponse response) {

		System.out.println(response.getStatusLine().getStatusCode());
		//�������url�ɹ�������getHttpContent()����ȡ����ֵ
	//	System.out.println(getHttpContent(response));
	//	processHttpRespose(response);
		JSONObject jsonObj = getResponseContent(response);
		parseResponseContent(jsonObj);
	//	parseOrgNumContent(jsonObj,uri,attr,isAttrValList);
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
	
	private void parseResponseContent(JSONObject jsonObj){
		if(jsonObj !=null){	
			Object code=jsonObj.get("code");
			if("-401".equals(code)){
				//˵����û�е�¼login
				System.out.println("401����AsyncSearchResult");
				return; //ֱ���˳�
			}
			recordAsyncSearch CameraAsyncSearchResult = getJSONsFromRep(jsonObj);		
			if(CameraAsyncSearchResult.getCameraId()!=null){
				processAsyncSearchResult.addRecordInfoToMap(CameraAsyncSearchResult);
			}
		}
	}
	
	public recordAsyncSearch getJSONsFromRep(JSONObject jsonObj) {
		
		recordAsyncSearch reSeTemp=null;
		List<JSONObject> reJsons = new ArrayList<JSONObject>();
		String cameraId="";
		if (jsonObj != null) {
			Object isSuccess = jsonObj.get("desc");
			if ("success".equals(isSuccess)) {
				reSeTemp=new recordAsyncSearch();
				jsonObj = jsonObj.getJSONObject("msg");
			//	Object ObCam=(Object)jsonObj.getString("cameraId");
				if((jsonObj.getString("cameraId")).toString().equals("null")){
					return reSeTemp;
				}
				cameraId=(String)(Object)jsonObj.getString("cameraId");
				JSONArray jsonArr=jsonObj.getJSONArray("recordFile");
				if(jsonArr.isEmpty()){ 
					return reSeTemp;
				}
				List<recordFile> listRecordFile=new ArrayList<recordFile>();
				/////
//				List<recordFile> listS=new ArrayList<recordFile>();
//				for(int i=0;i<3;i++){
//					recordFile reF=new recordFile();
//					reF.setBeginDateTime("313");
//					reF.setEndDateTime("353");
//					listS.add(reF);
//				}
//				JSONArray sJson=JSONArray.fromObject(listS); //����С�Ļ�����������ת��
//				List<recordFile> slist=JSONArray.toList(sJson, recordFile.class);
				for(int i=0;i<jsonArr.size();i++){
					JSONObject jsonRe=jsonArr.getJSONObject(i);
					recordFile reF=new recordFile();
					reF.setBeginDateTime(jsonRe.get("beginDateTime").toString());
					reF.setEndDateTime(jsonRe.get("endDateTime").toString());
					reF.setFileName(jsonRe.get("fileName").toString());
					reF.setFileSize(jsonRe.get("fileSize").toString());
					reF.setRecordType(jsonRe.get("recordType").toString());
					reF.setServerUrl(jsonRe.get("serverUrl").toString());
					listRecordFile.add(reF);
				}	
				reSeTemp.setCameraId(cameraId);
				reSeTemp.setListRecordFile(listRecordFile);				
			}
		}
		return reSeTemp;
	}
	
}
