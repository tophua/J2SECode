
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class SimpleClient {
	//客户端输入输出流
	PrintStream out;
	BufferedReader in;
	DataInputStream inNew;
	//构造方法
	public SimpleClient(String serverName,int port){
		  //根据服务器端名和端口号，连接服务器
        try {
            Socket  clientSocket=new Socket(serverName, port);
            
            //获取Socket的输入输出流
            out=new PrintStream(clientSocket.getOutputStream());
            inNew = new DataInputStream(clientSocket.getInputStream());
//            byte[] buffer = new byte[10000];  //缓冲区的大小
//            in.read(buffer);               //处理接收到的报文，转换成字符串
            
     //       in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("无法连接到服务器");
        }
	}
	
	 //发送请求
    public void sendRequest(byte[]  request){
   // 	String SendV=request.toString();
    	try {
			out.write(request);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    //    out.println(SendV);//向Socket的输出流中写数据
        System.out.println("Client发送请求："+request);
    }

	 //发送请求
    public void sendRequest1(CloudHead cloud){
   // 	String SendV=request.toString();
    	try {
			//out.write(request);
    		out.write(cloud.getcStartCode());
    		out.write(cloud.getDwCmdType());
    		out.write(ByteConvert.toLH((int)cloud.getsPacketSN()));
    		out.write(ByteConvert.toLH((int)cloud.getsPacketNum()));
    		out.write(ByteConvert.toLH((int)cloud.getnDataSize()));   		
    		out.write(cloud.getcReserved());
    		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    //    out.println(SendV);//向Socket的输出流中写数据
        System.out.println("Client发送请求：");

    }
    
    public String  getReponse(){
        String  str="";
        try{
        	int nLen;
        	byte [] reCode = new byte[3];	//获取
        	inNew.read(reCode,0,3);	
         	String strcode=new String(reCode);  //对于只占有一个字节的char，可以直接new成String        	
        	//strcode="INF"
        	reCode = new byte[1];	//获取
        	inNew.read(reCode,0,1);	 
        	strcode=ByteConvert.bytes2Hex(reCode); //一个字节的byte流，转换为16进制
        	//strcode="c3"
        	reCode = new byte[2];	//获取
        	inNew.read(reCode,0,2);	 
        	strcode=ByteConvert.Netbytes2Hex(reCode);
        	nLen=Integer.parseInt(strcode,16);
        	//nLen=1
        	reCode = new byte[2];	//获取
        	inNew.read(reCode,0,2);	 
        	strcode=ByteConvert.Netbytes2Hex(reCode);
        	nLen=Integer.parseInt(strcode,16);
        	//nLen=1
        	byte [] reDataSize = new byte[4];	//获取大小
        	inNew.read(reDataSize,0,4);	        	
        	String strHead=ByteConvert.Netbytes2Hex(reDataSize);
        	nLen=Integer.parseInt(strHead,16);
        	//nLen=521
        	inNew.read(reDataSize,0,4);	     
        	
        	byte [] receiveValue = new byte[nLen];		
        	inNew.read(receiveValue,0,nLen);
        	str=new String(receiveValue).trim();	
//			int recLength = in.read();
//			byte [] receiveArray = new byte[recLength];				
//			int x = in.read();
//			int i = 0;
//			while(x != 0) {					
//				receiveArray[i] = (byte) x;
//				i ++;
//				x = in.read();
//			} 
			
   //         str=in.readLine();//从Socket的输入流中读取数据
            System.out.println("Client收到Server返回："+str);
        }catch(IOException   e){
        }
        return  str;
    }
}
