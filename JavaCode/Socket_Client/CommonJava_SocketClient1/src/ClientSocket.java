
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientSocket extends Thread{
	public Socket clientSocket=null;
	public PrintStream out;
//	public BufferedReader in;
	public String ServerName;
	public int Port;
	
	protected String message;
	RecvSocketThread Recv=null; 
	public ClientSocket(String serverName,int port,RecvSocketThread recv){
		this.ServerName=serverName;
		this.Port=port;
		this.Recv=recv;
		InitSocket();
	}
	
	public void InitSocket(){
		
		  try {
				clientSocket = new Socket(ServerName, Port);
				//创建输入流和输出流
				out=new PrintStream(clientSocket.getOutputStream());
	//			in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("无法连接到服务器");
			}
	}
	
	public void run(){
		
		while(true){
	        try {
	      //  	sendRequest("2ww3re3");
	            //接受数据，但不允许有中文，因为会乱码
	            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
	            byte[] buffer = new byte[10000];  //缓冲区的大小
	            in.read(buffer);               //处理接收到的报文，转换成字符串
	            /**
	             * C++传递过来的中文字，需要转化一下。C++默认使用GBK。
	             * GB2312是GBK的子集，只有简体中文。因为数据库用GB2312，所以这里直接转为GB2312
	             * */
	            message = new String(buffer, "GB2312").trim();
	            
	            if(message!=null||!message.equals("")){
	            	 Recv.AddSocketMessage(message);
	            }
	           
	    //        System.out.println("来自客户端的消息："+message);

	        } catch (IOException ex) {
	            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
	            try {
	                clientSocket.close();
	            } catch (IOException ex1) {
	                Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex1);
	            }
	        } 
		}
 
	}
	
	public Socket getClientSocket() {
	    return clientSocket;
	} 
	
	 //发送请求
    public void sendRequest(String  request){
        out.println(request);//向Socket的输出流中写数据
        System.out.println("Client发送信息："+request);

    }
   
    //获得数据
//    public String getReponse(){
//        String  str=new  String();
//        try{
//            str=in.readLine();//从Socket的输入流中读取数据
//            System.out.println("Client收到Server返回："+str);
//        }catch(IOException  e){
//        }
//        return str;
//     //   Recv.AddSocketMessage(str);
//    }
}
