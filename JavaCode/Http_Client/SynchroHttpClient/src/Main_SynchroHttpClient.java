
public class Main_SynchroHttpClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SynchroHttpClient_Test1 test=new SynchroHttpClient_Test1();
		String url="http://10.82.17.15:8081/yftCloud1/DeviceMac=324523546&DeviceState=2";
		test.sendHttpGet(url);
	}

}
