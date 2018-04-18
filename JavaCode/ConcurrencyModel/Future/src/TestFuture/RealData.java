package TestFuture;

public class RealData implements Data{
   
	protected  String  result;	
	public RealData(String para){
		 //构造比较慢
		StringBuffer sb= new StringBuffer();
		for(int i=0;i<10;i++){
			sb.append(para);
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				
			}
			result= sb.toString();
		}
	}
	
	@Override
	public String getResult() {

		return result;
	}

}
