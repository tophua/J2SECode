
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
			System.out.println("无法连接到服务器");
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
//			  //专门用于接收数据
//			  getReponse();
//		  }
//	  }
	}
	
	public Socket getClientSocket() {
	    return clientSocket;
	} 
	
	 //发送请求
    public void sendRequest(String  request){
        out.println(request);//向Socket的输出流中写数据
        System.out.println("Client发送信息："+request);

    }
   
    public void getReponse(){
        String  str=new  String();
        try{
            str=in.readLine();//从Socket的输入流中读取数据
            System.out.println("Client收到Server返回："+str);
        }catch(IOException  e){
        }
        Recv.AddSocketMessage(str);
    }
}
