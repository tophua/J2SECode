
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
				//�����������������
				out=new PrintStream(clientSocket.getOutputStream());
	//			in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�޷����ӵ�������");
			}
	}
	
	public void run(){
		
		while(true){
	        try {
	      //  	sendRequest("2ww3re3");
	            //�������ݣ��������������ģ���Ϊ������
	            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
	            byte[] buffer = new byte[10000];  //�������Ĵ�С
	            in.read(buffer);               //������յ��ı��ģ�ת�����ַ���
	            /**
	             * C++���ݹ����������֣���Ҫת��һ�¡�C++Ĭ��ʹ��GBK��
	             * GB2312��GBK���Ӽ���ֻ�м������ġ���Ϊ���ݿ���GB2312����������ֱ��תΪGB2312
	             * */
	            message = new String(buffer, "GB2312").trim();
	            
	            if(message!=null||!message.equals("")){
	            	 Recv.AddSocketMessage(message);
	            }
	           
	    //        System.out.println("���Կͻ��˵���Ϣ��"+message);

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
	
	 //��������
    public void sendRequest(String  request){
        out.println(request);//��Socket���������д����
        System.out.println("Client������Ϣ��"+request);

    }
   
    //�������
//    public String getReponse(){
//        String  str=new  String();
//        try{
//            str=in.readLine();//��Socket���������ж�ȡ����
//            System.out.println("Client�յ�Server���أ�"+str);
//        }catch(IOException  e){
//        }
//        return str;
//     //   Recv.AddSocketMessage(str);
//    }
}
