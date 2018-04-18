package Test_JSON_lib;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class TestJsonlib {

	public void ClassConvertJsonString(){
		
        FrontCloudInfo FrontInfo=new FrontCloudInfo();		
		CloudConfigInfo[] CloudInfo = new CloudConfigInfo[3]; //相当于数组
		for(int i=0;i<3;i++){
			CloudInfo[i]=new CloudConfigInfo();
		}
		FrontInfo.setState("OK");
		FrontInfo.setCloud(CloudInfo);
		String strOut=JSONObject.fromObject(FrontInfo).toString();
		System.out.println(strOut);
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
	
}
