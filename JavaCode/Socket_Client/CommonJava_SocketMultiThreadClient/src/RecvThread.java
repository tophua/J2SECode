

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
			   it.remove();//使用迭代器的remove()方法删除元素  
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
//			// 接受数据，但不允许有中文，因为会乱码
//			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
//			byte[] buffer = new byte[10000]; // 缓冲区的大小
//			in.read(buffer); // 处理接收到的报文，转换成字符串
//			/**
//			 * C++传递过来的中文字，需要转化一下。C++默认使用GBK。
//			 * GB2312是GBK的子集，只有简体中文。因为数据库用GB2312，所以这里直接转为GB2312
//			 */
//			message = new String(buffer, "GB2312").trim();
//			// System.out.println("来自客户端的消息："+message);
//			this.clientSocket = clientSocket;
//			// 获得输出输出流
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
