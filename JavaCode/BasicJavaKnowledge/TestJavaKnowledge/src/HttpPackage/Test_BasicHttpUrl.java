package HttpPackage;

public class Test_BasicHttpUrl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		//测试bundle之间能否相互发送http
    	String url="http://10.82.17.15:10080/yftCloud";
		String param="DeviceMac=324523546&DeviceState=2";
		BasicHttpUrl.SendHttpGet(url,param);
////		SendHttpPost(url,param);  //该方法此架构不可行
	}

}
