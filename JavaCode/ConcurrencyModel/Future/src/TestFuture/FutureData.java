package TestFuture;


/**
 * 是对RealData的一个包装
 *
 */
public class FutureData implements Data{

	protected RealData realData = null;
	protected boolean isReady = false;
	//线程见将通过wait()和notifyAll()进行通信
	public synchronized void setRealData(RealData realData){
		if(isReady){
			return;
		}
		this.realData=realData;
		isReady=true;
		notifyAll();	
	}
	@Override
	public synchronized  String getResult() {
		// TODO Auto-generated method stub
        while(!isReady){  
           try{  
                wait();  
           }catch (Exception e){  
            }  
       }  
       return realData.result;  
	}
}
