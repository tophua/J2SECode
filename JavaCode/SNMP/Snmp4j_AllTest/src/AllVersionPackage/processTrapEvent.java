package AllVersionPackage;

import java.util.ArrayList;
import java.util.List;

import org.snmp4j.CommandResponderEvent;

public class processTrapEvent implements Runnable{

	public List<CommandResponderEvent> listEvent;
	
	public processTrapEvent(){
		listEvent = new ArrayList<CommandResponderEvent>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub	
		while(true){
			//循环从listEvent中取数据并处理
		}
		
	}

	public processTrapEvent getInstance(){
		return this;
	}
	
	public void InsertEventToList(CommandResponderEvent event){
		listEvent.add(event);
	}
	
}
