
public class TestClientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URLConnectionClient clientTemp=new URLConnectionClient();
		String url="http://10.82.17.15:8081/yftCloud1";
		String param="DeviceMac=324523546&DeviceState=2";
		clientTemp.SendHttpGet(url,param);
	}

}
