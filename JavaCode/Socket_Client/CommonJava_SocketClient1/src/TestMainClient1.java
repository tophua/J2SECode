
public class TestMainClient1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecvSocketThread recv=new RecvSocketThread();
		recv.start();
		
		ClientSocket ClSocket=new ClientSocket("10.82.17.15",8089,recv);
		ClSocket.start();
		ClSocket.sendRequest("26t541");
	}

}
