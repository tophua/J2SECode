import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.message.BasicNameValuePair;

public class demoV2200 {
    public static AsyncClientService ser=new AsyncClientService();
    public static Thread processResultThread =null;
    //在整个过程之前就new一个处理线程
    static{
    	processResultThread =new Thread(new processAsyncSearchResult());
    	processResultThread.start();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loginOperate();
		System.out.println("loginError: "+V2200Properties.errorValue+" "+V2200Properties.sessionId);
//		//通过Login来获取sessionId
//		ser.sendHttpGet(V2200Properties.loginUrl);
//		ser.setLoginHeader();
//	//	ser.execAsyncClient(new responseCall());//登陆时直接获得sessionId
//	    ser.execAsyncForSessionId();
		if(V2200Properties.errorValue==10001){
//			getOrgOperate();
//			System.out.println("OrgError: "+V2200Properties.errorValue);
			if(V2200Properties.sessionId.equals("")){
				loginOperate();
			}
			getAsyncSearch();
		} 

	}

	public static void loginOperate(){
		//通过Login来获取sessionId
		ser.sendHttpGet(V2200Properties.loginUrl);
		ser.setLoginHeader();
	//	ser.execAsyncClient(new responseCall());//登陆时直接获得sessionId
		ser.execAsyncForSessionId();
	}
	
	public static void getOrgOperate(){
		//在获取组织数量
		String[] reqUrl = { V2200Properties.getOrgByPageUrl + "limit=0&page=1" };
		ser.Orgrequest(reqUrl,"pageInfo", false);
	}
	
	public static void getAsyncSearch(){
		List<BasicNameValuePair> postBody = new ArrayList<BasicNameValuePair>();	
		postBody.add(new BasicNameValuePair("searchId", "123"));
//		postBody.add(new BasicNameValuePair("cameraId", "0b62c727f02947428431489b9f24e1e0"));
		postBody.add(new BasicNameValuePair("cameraId", "8113aaa311404d708f9f4ee2b937a72b"));
		postBody.add(new BasicNameValuePair("beginDateTime", "1524400196000"));	
		postBody.add(new BasicNameValuePair("endDateTime", "1524500996000"));		
		postBody.add(new BasicNameValuePair("recordType", "all"));
		postBody.add(new BasicNameValuePair("playbackType", "0"));
		
	    Date begin = new Date(1524400196000L);  
	    Date end = new Date(1524500996000L);  
	    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String strbegin = sdf.format(begin);  
	    String strend = sdf.format(end);  
	    
		processAsyncSearchResult.getQueryTime(strbegin,strend);
		ser.AsyncSearchRequest(V2200Properties.getAsyncSearch,postBody);
	
		
	}
}
