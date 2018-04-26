package HttpURLConnectionPackage;

import net.sf.json.JSONObject;

public class demoHttpURL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="http://10.82.27.171:22080/videoqs/api/";
		//传入Post的参数内容[json格式的字符串]
//		String jsonParameter="{'action':'stopVQDTask','request':"
//				+ "{'taskId':'fdd49672c752525f81e50c1d6b77bc0ba8b02767'}}";  
		String jsonParameter="{'action':'startVQDTask','request':{'items':"
				+ "[{'cameraId':'0672f0c3719e4708bc593b8f517584b8','configId':'default',"
				+ "'streamSetup':{'encoding':'H264','videoEncoder':'major/minor'},"
				+ "'streamUri':'smt://10.82.27.171:5001'}],'keepAliveTime':'PT2M'}}";
        JSONObject object=JSONObject.fromObject(jsonParameter);
        jsonParameter=object.toString();
		HttpURLConnectionClient urlClient=new HttpURLConnectionClient();
		urlClient.HttpURLInit(url);
		urlClient.setURLConnectionProperty();
		urlClient.Connect();
		urlClient.URLOutputStream(jsonParameter);	
		String result=urlClient.URLInputStream();
		System.out.println(result);
		urlClient.disConnect();		
	}
}
