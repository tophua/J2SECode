package Test_JSON_lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestJsonlib {

	public void ClassConvertJsonString(){
		
        FrontCloudInfo FrontInfo=new FrontCloudInfo();		
		CloudConfigInfo[] CloudInfo = new CloudConfigInfo[2]; //相当于数组
		for(int i=0;i<2;i++){
			CloudInfo[i]=new CloudConfigInfo();
			CloudInfo[i].setCloudIP("");
			CloudInfo[i].setCloudPort("9857");
			CloudInfo[i].setCloudstart("1");
			CloudInfo[i].setInterval("25");
			CloudInfo[i].setOmsIP("10.82.27.18");
			CloudInfo[i].setOmsPort("10080");
		}
		FrontInfo.setState("OK");
		FrontInfo.setCloud(CloudInfo);
		String strOut=JSONObject.fromObject(FrontInfo).toString();
		System.out.println(strOut);
		
		//如何去解析strOut?
		JSONObject json=JSONObject.fromObject(strOut);
		Object obState=json.get("state");
		JSONArray jsonArray=json.getJSONArray("cloud");
	//	CloudConfigInfo[] CloudArray = new CloudConfigInfo[2]; //相当于数组
		List<CloudConfigInfo> listCloud=new ArrayList<CloudConfigInfo>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject ObCloud=jsonArray.getJSONObject(i);
//			String clouIP=ObCloud.getString("cloudIP").toString();
//			CloudArray[i].setCloudIP(clouIP);
//			CloudArray[i].setCloudPort(ObCloud.get("cloudPort").toString());
//			CloudArray[i].setCloudstart(ObCloud.optString("cloudstart"));
//			CloudArray[i].setInterval(ObCloud.get("interval").toString());
//			CloudArray[i].setOmsIP(ObCloud.get("omsIP").toString());
//			CloudArray[i].setOmsPort(ObCloud.get("omsPort").toString());
			
			CloudConfigInfo cloudInfo=new CloudConfigInfo();
			cloudInfo.setCloudIP(ObCloud.getString("cloudIP"));
			cloudInfo.setCloudPort(ObCloud.get("cloudPort").toString());
			cloudInfo.setCloudstart(ObCloud.optString("cloudstart"));
			cloudInfo.setInterval(ObCloud.get("interval").toString());
			cloudInfo.setOmsIP(ObCloud.get("omsIP").toString());
			cloudInfo.setOmsPort(ObCloud.get("omsPort").toString());
			listCloud.add(cloudInfo);
		}
		///输出的结果是
		/*
		 * {
       "cloud": [
        {
            "cloudIP": "",
            "cloudPort": "",
            "cloudstart": "",
            "interval": "",
            "omsIP": "",
            "omsPort": ""
        },
        {
            "cloudIP": "",
            "cloudPort": "",
            "cloudstart": "",
            "interval": "",
            "omsIP": "",
            "omsPort": ""
        }
        ],
        "state": "OK"
        }
		 */
	}
	
	
	public void MapConvertJsonString(){
		Map<String,String> mapOut=new HashMap<String,String>();
		mapOut.put("key1", "v1");
		mapOut.put("key2", "v2");
		String strOut=JSONObject.fromObject(mapOut).toString();
		System.out.println(strOut);
		/*
		 * {
            "key1": "v1",
            "key2": "v2"
           }
		 */
	}
	
	public void parseJSONObject1(){
//		String strValue="{'code':'0','desc':'success','msg':{'searchId':'123',"
//				+ "'cameraId':'0b62c727f02947428431489b9f24e1e0','recordFile':"
//				+ "[{'serverUrl':'nvr://10.82.25.247:5003','fileName':'5ad9d331.IFV',"
//				+ "'beginDateTime':'2018-04-23T00:36:57','endDateTime':'2018-04-23T00:40:54',"
//				+ "'recordType':'auto','fileSize':'115','cameraId':null},"
//				+ "{'serverUrl':'nvr://10.82.25.247:5003','fileName':'5add2b96.IFV',"
//				+ "'beginDateTime':'2018-04-23T00:40:54','endDateTime':'2018-04-23T00:51:08',"
//				+ "'recordType':'auto','fileSize':'299','cameraId':null}]}}";
		
//		String strValue="{'code':'0','desc':'success','msg':{'searchId':'123',"
//		+ "'cameraId':null,'recordFile':null}}";
		
//		String strValue="{'code':'0','desc':'success','msg':{'searchId':'123',"
//		+ "'cameraId':'0b62c727f02947428431489b9f24e1e0','recordFile':null}}";
		
		String strValue="{'code':'0','desc':'success','msg':{'searchId':'123',"
		+ "'cameraId':'0b62c727f02947428431489b9f24e1e0','recordFile':[]}}";
		//如何去解析strOut?
		JSONObject json=JSONObject.fromObject(strValue);
		if(json!=null){
			Object Obdesc=json.get("desc");
			Object Obcode=json.get("code");
			json=json.getJSONObject("msg");
			if(json!=null){
				if(json.getString("cameraId").toString().equals("null")){
					return; //这是判断"cameraId":null
				}
//				if(json.getJSONObject("recordFile").toString().equals("null")){
//					return; //这是判断"recordFile":null
//				}
				JSONArray jsonArray=json.getJSONArray("recordFile");
				if(jsonArray.isEmpty()){
					return; //这是判断"recordFile":[]
				}
				
				List<recordFile> listRecordFile=new ArrayList<recordFile>();
				for(int i=0;i<jsonArray.size();i++){
					JSONObject jsonRe=jsonArray.getJSONObject(i);
					recordFile reF=new recordFile();
					reF.setBeginDateTime(jsonRe.get("beginDateTime").toString());
					reF.setEndDateTime(jsonRe.get("endDateTime").toString());
					reF.setFileName(jsonRe.get("fileName").toString());
					reF.setFileSize(jsonRe.get("fileSize").toString());
					reF.setRecordType(jsonRe.get("recordType").toString());
					reF.setServerUrl(jsonRe.get("serverUrl").toString());
					listRecordFile.add(reF);
			}
		}
	 }
	}
	
}
