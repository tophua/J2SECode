
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class SimpleClient {
	//�ͻ������������
	PrintStream out;
	BufferedReader in;
	DataInputStream inNew;
	//���췽��
	public SimpleClient(String serverName,int port){
		  //���ݷ����������Ͷ˿ںţ����ӷ�����
        try {
            Socket  clientSocket=new Socket(serverName, port);
            
            //��ȡSocket�����������
            out=new PrintStream(clientSocket.getOutputStream());
            inNew = new DataInputStream(clientSocket.getInputStream());
//            byte[] buffer = new byte[10000];  //�������Ĵ�С
//            in.read(buffer);               //������յ��ı��ģ�ת�����ַ���
            
     //       in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("�޷����ӵ�������");
        }
	}
	
	 //��������
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
    //    out.println(SendV);//��Socket���������д����
        System.out.println("Client��������"+request);
    }

	 //��������
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
    //    out.println(SendV);//��Socket���������д����
        System.out.println("Client��������");

    }
    
    public String  getReponse(){
        String  str="";
        try{
        	int nLen;
        	byte [] reCode = new byte[3];	//��ȡ
        	inNew.read(reCode,0,3);	
         	String strcode=new String(reCode);  //����ֻռ��һ���ֽڵ�char������ֱ��new��String        	
        	//strcode="INF"
        	reCode = new byte[1];	//��ȡ
        	inNew.read(reCode,0,1);	 
        	strcode=ByteConvert.bytes2Hex(reCode); //һ���ֽڵ�byte����ת��Ϊ16����
        	//strcode="c3"
        	reCode = new byte[2];	//��ȡ
        	inNew.read(reCode,0,2);	 
        	strcode=ByteConvert.Netbytes2Hex(reCode);
        	nLen=Integer.parseInt(strcode,16);
        	//nLen=1
        	reCode = new byte[2];	//��ȡ
        	inNew.read(reCode,0,2);	 
        	strcode=ByteConvert.Netbytes2Hex(reCode);
        	nLen=Integer.parseInt(strcode,16);
        	//nLen=1
        	byte [] reDataSize = new byte[4];	//��ȡ��С
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
			
   //         str=in.readLine();//��Socket���������ж�ȡ����
            System.out.println("Client�յ�Server���أ�"+str);
        }catch(IOException   e){
        }
        return  str;
    }
}
