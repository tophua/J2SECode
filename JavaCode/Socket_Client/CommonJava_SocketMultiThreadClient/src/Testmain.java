
public class Testmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecvThread Recv=new RecvThread();
		Recv.start();
		
		CheckSocketConnect SoConnect=new CheckSocketConnect();
		SoConnect.InitConnect("10.82.17.15",8089,Recv);
		SoConnect.start();
		
		SoConnect.GetSocketHandle().sendRequest("send data");
	}

}
