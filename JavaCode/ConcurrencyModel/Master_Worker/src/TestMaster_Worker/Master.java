package TestMaster_Worker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

	//ʢ�����������
	private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();
    //ʢ��worker�ļ���
	private HashMap<String,Thread> workers = new HashMap<String,Thread>();
	//ʢ��ÿһ��workerִ������Ľ������
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
    
	//���췽��
	public Master(Worker worker , int workerCount){
		worker.setWorkQueue(this.workQueue);
		worker.setResultMap(this.resultMap);
		
		for(int i=0;i<workerCount;i++){
			this.workers.put(Integer.toString(i), new Thread(worker));
		}
	}
	
	//��Ҫһ���ύ����ķ���
	 public void submit(Task task){
	      this.workQueue.add(task);
	 }

	//��Ҫ��һ��ִ�еķ������������е�worker����ȥִ������
	 public void execute(){
	     for(Map.Entry<String, Thread> me : workers.entrySet()){
	         me.getValue().start();
	     }
	 }

	// �ж��Ƿ����н����ķ���
	 public boolean isComplete() {
	    for(Map.Entry<String, Thread> me : workers.entrySet()){
	       if(me.getValue().getState() != Thread.State.TERMINATED){
	           return false;
	       }
	    }       
	    return true;
	 }
	//����������
	 public int getResult() {
	     int priceResult = 0;
	     for(Map.Entry<String, Object> me : resultMap.entrySet()){
	         priceResult += (Integer)me.getValue();
	     }
	     return priceResult;
	 }

}
