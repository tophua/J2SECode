

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RecvThread extends Thread{
	Map<Integer,String> mapMessage=new HashMap<Integer,String>();
	public RecvThread(){
		super();
	}
	
	public void AddSocketMessage(String value){		
		mapMessage.put(mapMessage.size()+1,value);			
	}
	
	public String RemoveMessage(){
		String result="";
		if(mapMessage.size()>0){
			Iterator<Map.Entry<Integer, String>> it = mapMessage.entrySet().iterator();  
			while(it.hasNext()){  
			   Map.Entry<Integer, String> entry = it.next();  
			   result=entry.getValue();
			   it.remove();//ʹ�õ�������remove()����ɾ��Ԫ��  
			   break;
			}
		}
	  return result;	
	}
	
	public void ProcessMessage(String value){
		System.out.println("value: "+value);
	}
	
	@Override
	public void run(){
		
		String strValue="";
		while(true){
			strValue=RemoveMessage();
			if(!strValue.equals("")){
				ProcessMessage(strValue);
			}		
		}
//		try {
//			// �������ݣ��������������ģ���Ϊ������
//			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
//			byte[] buffer = new byte[10000]; // �������Ĵ�С
//			in.read(buffer); // ������յ��ı��ģ�ת�����ַ���
//			/**
//			 * C++���ݹ����������֣���Ҫת��һ�¡�C++Ĭ��ʹ��GBK��
//			 * GB2312��GBK���Ӽ���ֻ�м������ġ���Ϊ���ݿ���GB2312����������ֱ��תΪGB2312
//			 */
//			message = new String(buffer, "GB2312").trim();
//			// System.out.println("���Կͻ��˵���Ϣ��"+message);
//			this.clientSocket = clientSocket;
//			// �����������
//			out = new PrintStream(clientSocket.getOutputStream());
//		} catch (IOException ex) {
//			Logger.getLogger(EchoServerThread.class.getName()).log(Level.SEVERE, null, ex);
//			try {
//				clientSocket.close();
//			} catch (IOException ex1) {
//				Logger.getLogger(EchoServerThread.class.getName()).log(Level.SEVERE, null, ex1);
//			}
//		}
	}
}
