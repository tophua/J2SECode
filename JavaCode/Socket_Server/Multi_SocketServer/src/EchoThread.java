
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  EchoServerThread���½��߳�������ͻ�������
 * EchoThread��Ϊ�����Ĵ����̵߳Ļ��࣬��Ҫ�ǰ�socketͨ�Ų����ٷ�װһ��
 * ��run�����аѿͻ����������Ϣ����ClientRequestHandler����������ֻ����socket����Ϣ���պͷ���
 */


public class EchoThread extends Thread{
	 protected PrintStream out;
	    protected String message;
	    protected Socket clientSocket;
	    DataInputStream in;
	    //������
	    public EchoThread(Socket clientSocket) {
	        super();
	        try {
	            //�������ݣ��������������ģ���Ϊ������
	            in = new DataInputStream(clientSocket.getInputStream());
	            
	            processCSocket();
//	            byte[] buffer = new byte[10000];  //�������Ĵ�С
//	            in.read(buffer);               //������յ��ı��ģ�ת�����ַ���
//	            /**
//	             * C++���ݹ����������֣���Ҫת��һ�¡�C++Ĭ��ʹ��GBK��
//	             * GB2312��GBK���Ӽ���ֻ�м������ġ���Ϊ���ݿ���GB2312����������ֱ��תΪGB2312
//	             * */
//	            message = new String(buffer, "utf-8").trim();
//	            System.out.println("���Կͻ��˵���Ϣ��"+message);
//	            
//	            this.clientSocket = clientSocket;
//	            // �����������
//	            out = new PrintStream(clientSocket.getOutputStream());
//	            System.out.println("��Ϣ��"+out);
	        } catch (IOException ex) {
	            Logger.getLogger(EchoServerThread.class.getName()).log(Level.SEVERE, null, ex);
	            try {
	                clientSocket.close();
	            } catch (IOException ex1) {
	                Logger.getLogger(EchoServerThread.class.getName()).log(Level.SEVERE, null, ex1);
	            }
	        }
	    }

	    //���Ƿ�,����
	    public void run() {
//	        try {
//	             /**
//	             * ������Ҫʹ�õײ��byte���������ݡ���Ϊ��ʹֱ��д�ַ��������ײ㻹�ǵ����˴���byte�ķ�����
//	             * �����漰���������⡣C++Ĭ��ʹ��GBK����GB2312��GBK���Ӽ���
//	             */
//
////	            byte[] responseBuffer = new ClientRequestHandler(message).response().getBytes("GB2312");
////	        	for(int i=0;i<10;i++){
////	        		 out.write(responseBuffer, 0, responseBuffer.length);
////	        		 Thread.sleep(2000);
////	        	}
//	     //       out.write(responseBuffer, 0, responseBuffer.length);
////	            String test = new ClientRequestHandler(message).response();
////	            out.print(test);//ֱ��UTF8��������յײ�ÿ��������3���ֽڴ���
////	            out.print(new String(test.getBytes(),"GBK"));//תGBKʧ�ܣ�ʵ��ÿ������������4��5���ֽڴ���
////	            out.print(new String(test.getBytes("GBK"),"GBK"));//תGBK�����ײ㻹��Ҫ����ֽ����飬��Ȼ���ջ��Ǹ�UTF8һ��
//
//	        } catch (Exception ex) {
//	            Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
//	        }
//	        try {
//	            clientSocket.close();
//	        } catch (IOException ex) {
//	            Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
//	        }
	    }

	    public String getMessage() {
	        return message;
	    }

	    /**
	     * ��ȡ�����
	     * @return
	     */
	    public PrintStream getOutputStream() {
	        return out;
	    }
	    
	    public String processCSocket(){
	        String  str="";
	        try{
	        	int nLen;
	        	byte [] reCode = new byte[3];	//��ȡ
	        	in.read(reCode,0,3);	
	         	String strcode=new String(reCode);  //����ֻռ��һ���ֽڵ�char������ֱ��new��String        	
	        	//strcode="INF"
	        	reCode = new byte[1];	//��ȡ
	        	in.read(reCode,0,1);	 
	        	strcode=ByteConvert.bytes2Hex(reCode); //һ���ֽڵ�byte����ת��Ϊ16����
	        	//strcode="c3"
	        	reCode = new byte[2];	//��ȡ
	        	in.read(reCode,0,2);	 
	        	strcode=ByteConvert.Netbytes2Hex(reCode);
	        	nLen=Integer.parseInt(strcode,16);
	        	//nLen=1
	        	reCode = new byte[2];	//��ȡ
	        	in.read(reCode,0,2);	 
	        	strcode=ByteConvert.Netbytes2Hex(reCode);
	        	nLen=Integer.parseInt(strcode,16);
	        	//nLen=1
	        	byte [] reDataSize = new byte[4];	//��ȡ��С
	        	in.read(reDataSize,0,4);	        	
	        	String strHead=ByteConvert.Netbytes2Hex(reDataSize);
	        	nLen=Integer.parseInt(strHead,16);
	        	//nLen=521
	        	in.read(reDataSize,0,4);	     
	        	
	        	byte [] receiveValue = new byte[nLen];		
	        	in.read(receiveValue,0,nLen);
	        	str=new String(receiveValue).trim();	
//				int recLength = in.read();
//				byte [] receiveArray = new byte[recLength];				
//				int x = in.read();
//				int i = 0;
//				while(x != 0) {					
//					receiveArray[i] = (byte) x;
//					i ++;
//					x = in.read();
//				} 
				
	   //         str=in.readLine();//��Socket���������ж�ȡ����
	            System.out.println("Client�յ�Server���أ�"+str);
	        }catch(IOException   e){
	        }
	        return  str;
	    }
}
