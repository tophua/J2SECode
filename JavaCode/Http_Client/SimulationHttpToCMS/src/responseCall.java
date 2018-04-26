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
	 * 请求完成后调用该函数
	 */
	@Override
	public void completed(HttpResponse response) {

		System.out.println(response.getStatusLine().getStatusCode());
		//如果发送url成功，下面getHttpContent()来获取返回值
	//	System.out.println(getHttpContent(response));
	//	processHttpRespose(response);
		JSONObject jsonObj = getResponseContent(response);
		parseResponseContent(jsonObj);
	//	parseOrgNumContent(jsonObj,uri,attr,isAttrValList);
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
	
	/**
	 * 通过请求的url获取返回的content内容
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
				//说明是没有登录login
				System.out.println("401错误，AsyncSearchResult");
				return; //直接退出
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
//				JSONArray sJson=JSONArray.fromObject(listS); //数量小的话，可以这样转换
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
