import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;

public class demoV2200 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AsyncClientService ser=new AsyncClientService();
		ser.sendHttpGet(V2200Properties.loginUrl);
		ser.setGetHeader();
	//	ser.execAsyncClient(new responseCall());//登陆时直接获得sessionId
	    ser.execAsyncForSessionId();
	}

}
