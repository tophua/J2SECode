
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SocketClient extends Thread{
	RecvThread Recv=null;
	public Socket clientSocket=null;
	public PrintStream out;
	public BufferedReader in;
	public String SerName;
	public int nPort;
	
	public SocketClient(){
		super();
	}
	
	public void InitSocket(String serverName,int port,RecvThread recv){
		Recv=recv;
		SerName=serverName;
		nPort=port;
	}
	
	@Override
	public void run(){
		
	  try {
			clientSocket = new Socket(SerName, nPort);
			out=new PrintStream(clientSocket.getOutputStream());
			in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�޷����ӵ�������");
		}
//	  while(true){
//		  if(clientSocket==null){
//			  try {
//				clientSocket.close();
//				break;
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		  }else{
//			  //ר�����ڽ�������
//			  getReponse();
//		  }
//	  }
	}
	
	public Socket getClientSocket() {
	    return clientSocket;
	} 
	
	 //��������
    public void sendRequest(String  request){
        out.println(request);//��Socket���������д����
        System.out.println("Client������Ϣ��"+request);

    }
   
    public void getReponse(){
        String  str=new  String();
        try{
            str=in.readLine();//��Socket���������ж�ȡ����
            System.out.println("Client�յ�Server���أ�"+str);
        }catch(IOException  e){
        }
        Recv.AddSocketMessage(str);
    }
}