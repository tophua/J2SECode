
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RecvSocketThread extends Thread{
	Map<Integer,String> mapMessage=new HashMap<Integer,String>();
	public RecvSocketThread(){
		super();
	}
	
	public void AddSocketMessage(String value){		
		mapMessage.put(mapMessage.size()+1,value);			
	}
	
	public String RemoveMessage(){
		String result="";
		if(mapMessage.size()>0){
			Iterator<Integer> iter=mapMessage.keySet().iterator();
			while(iter.hasNext()){
				Integer key=iter.next();
				result=mapMessage.get(key);
				if(key>0){
					mapMessage.remove(key);
				}
				break;
			}
//			Iterator<Map.Entry<Integer, String>> it = mapMessage.entrySet().iterator();  
//			while(it.hasNext()){  
//			   Map.Entry<Integer, String> entry = it.next();  
//			   result=entry.getValue();
//			   it.remove();//使用迭代器的remove()方法删除元素  
//			   break;
//			}
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
//			System.out.println("RecvSocketThread: ");
			strValue=RemoveMessage();
			if(!strValue.equals("")){
				ProcessMessage(strValue);
			}		
		}
	}
}
