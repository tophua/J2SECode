import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import JavaBeanClass.recordAsyncSearch;
import JavaBeanClass.recordFile;
import JavaBeanClass.videoRecordAlarm;
import JavaDao.videoRecordDao;

public class processAsyncSearchResult implements Runnable{

	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static Map<String,recordAsyncSearch> mapTemp=new HashMap<String,recordAsyncSearch>();
	public static List<videoRecordAlarm> listVideoAlarm=new ArrayList<videoRecordAlarm>();
	
	public static String queryStart="";  //形如2018-04-24 09:14:03
	public static String queryEnd="";
	public static long lqueryStart=0; //精确到秒,但秒的差别不考虑
	public static long lqueryEnd=0;
	public static videoRecordDao videoDao=new videoRecordDao();
	public processAsyncSearchResult(){
		System.out.println("(processAsyncSearchResult)处理线程已启动...");
		//连接数据库
		try {
			videoDao.InitConnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){

			recordAsyncSearch recordVale=removeRecordInfoFromMap();
			if(recordVale!=null){//说明是有值
				processRecordResult(recordVale);
				updateRecordResult();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
	}
	
	public synchronized static void getQueryTime(String start,String end){
		queryStart=start;
		queryEnd=end;
	    try {
				Date dateBegin = (Date) formatter.parse(queryStart);
		//		dateBegin.setSeconds(0);
				Date dateEnd = (Date) formatter.parse(queryEnd);
		//		dateEnd.setSeconds(0);
				lqueryStart=dateBegin.getTime()/1000;
				lqueryEnd=dateEnd.getTime()/1000;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	}
	
	public synchronized static void addRecordInfoToMap(recordAsyncSearch reTemp){
		String cameraId=reTemp.getCameraId();
		mapTemp.put(cameraId, reTemp);
	}

	public synchronized recordAsyncSearch removeRecordInfoFromMap(){
		recordAsyncSearch recordSe=null;
		Iterator<Entry<String, recordAsyncSearch>> iter=mapTemp.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String, recordAsyncSearch> entry=(Map.Entry<String, recordAsyncSearch>) iter.next();
			Object key= entry.getKey();
			recordSe= (recordAsyncSearch)entry.getValue();
			mapTemp.remove(key);
			break;
		}
		return recordSe;	
	}
	
	public void processRecordResult(recordAsyncSearch temp){
		listVideoAlarm.clear();
		//由RecId获得videoFlag
		String videoFlag="";
	//	videoFlag=videoRecordDao.getVideoFlagFromPointInfo(temp.getCameraId()); //这种调用方法必须用static修饰
		videoFlag=videoDao.getVideoFlagFromPointInfo(temp.getCameraId());
		//
		if(videoFlag.equals("")){
			return;
		}

		Map<String,String> mapTime=new HashMap<String,String>();		
		for(int i=0;i<temp.getListRecordFile().size();i++){
			processOnlyRecordFile(temp.getListRecordFile().get(i),mapTime);
		//	mapTime.put(String.valueOf(lBegin), String.valueOf(lEnd));
		}
		//对时间值进行排序
		Map<String,String> resultMap=sortMapByKey(mapTime);
        Iterator iter=resultMap.entrySet().iterator();
      
        if(iter.hasNext()){  //先处理第一个时间点
        	Iterator itFirst=iter;
        	Map.Entry<String, String> CurFirst=(Entry<String, String>) itFirst.next();
        	long lDValue=Long.parseLong((String)CurFirst.getKey())-lqueryStart;
        	if(lDValue>2){
        		//
        		Date date1 = new Date(lqueryStart * 1000);  
        		Date date2 = new Date(Long.parseLong((String)CurFirst.getKey()) * 1000);  
        		String str1=formatter.format(date1);
        		String str2=formatter.format(date2);
        		produceVideoRecord(lqueryStart,Long.parseLong((String)CurFirst.getKey()),videoFlag);
        	}
        }
        while(iter.hasNext()){//确定有
        	Map.Entry<String, String> CurEntry=(Entry<String, String>) iter.next();//移动
    		Iterator itNext=iter;
        	if(itNext.hasNext()){
        		Map.Entry<String, String> NextEntry=(Entry<String, String>) itNext.next();
        		long lDValue=Long.parseLong((String)NextEntry.getKey())-Long.parseLong((String)CurEntry.getValue());
        		if(lDValue>2){//大于2秒说明丢失
        			produceVideoRecord(Long.parseLong((String)CurEntry.getValue()),
        					Long.parseLong((String)NextEntry.getKey()),videoFlag);
        		}     		        		
        	} else{//表示是最后一个时间点，与查询的时间进行比较
        		long lDValue=lqueryEnd-Long.parseLong((String)CurEntry.getValue());
        		if(lDValue>2){
        			produceVideoRecord(Long.parseLong((String)CurEntry.getValue()),lqueryEnd,videoFlag);
        		}
        	}
        }		
	}
	
	private void produceVideoRecord(long lstart,long lend,String videoFlag){
		//对于跨了天的时间需要拆分
		splitRecordAlarmTime(lstart,lend,videoFlag);
	}
	
	private void processOnlyRecordFile(recordFile recordFileTemp,Map<String,String> mapTemp){
		long lbegin=0,lend=0;
		String recordTemp_begin=recordFileTemp.getBeginDateTime();
		String recordTemp_end=recordFileTemp.getEndDateTime();
		recordTemp_begin=recordTemp_begin.replace("T"," ");
		recordTemp_end=recordTemp_end.replace("T"," ");
//		long lrecordBegin=0,lrecordEnd=0;
		
	    try {
			Date dateBegin = (Date) formatter.parse(recordTemp_begin);
	//		dateBegin.setSeconds(0);
			Date dateEnd = (Date) formatter.parse(recordTemp_end);
		//	dateEnd.setSeconds(0);
			lbegin=dateBegin.getTime()/1000;
			lend=dateEnd.getTime()/1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		//这个时间需要增大8小时
	    lbegin=lbegin+8*60*60;
	    lend=lend+8*60*60; 	
	    mapTemp.put(String.valueOf(lbegin), String.valueOf(lend));
	}

    private Map<String,String> sortMapByKey(Map<String,String> map){
    	 if (map == null || map.isEmpty()) {
             return null;
         }
         Map<String, String> sortMap = new TreeMap<String, String>(
                 new MapKeyComparator());

         sortMap.putAll(map);
         return sortMap;
    }
    
    private void splitRecordAlarmTime(long lstart,long lend,String videoflag){
        long day1=(lend-lstart)/(24*60*60);//相差几天
        if(lend>lstart){
        	day1=day1+1;
        }
    	if(day1>0){
    		Date dateS=new Date(lstart*1000);
    	//	Date dateE=new Date(lend*1000);
    		Date dateNext=new Date(lstart*1000);
    		dateNext.setHours(23);
    		dateNext.setMinutes(59);
    		dateNext.setSeconds(59);
    		long lNext=dateNext.getTime()/1000+1;//新的一天,从00:00:00开始
    		if(lNext<lend){//说明没超过
        		videoRecordAlarm videoAlarm=new videoRecordAlarm();
        		videoAlarm.setVideoFlag(videoflag);
        		videoAlarm.setLostStart(formatter.format(dateS));
        		videoAlarm.setLostEnd(formatter.format(dateNext));
        		listVideoAlarm.add(videoAlarm);
        		
        		splitRecordAlarmTime(lNext,lend,videoflag);
    		}else{
    			Date dateE=new Date(lend*1000);
        		videoRecordAlarm videoAlarm=new videoRecordAlarm();
        		videoAlarm.setVideoFlag(videoflag);
        		videoAlarm.setLostStart(formatter.format(dateS));
        		videoAlarm.setLostEnd(formatter.format(dateE));
        		listVideoAlarm.add(videoAlarm);
    		}
    	}
    }
    
    public void updateRecordResult(){
    	if(listVideoAlarm.size()>0){
    		for(int i=0;i<listVideoAlarm.size();i++){
    			videoDao.InsertAlarmToRecordResult(listVideoAlarm.get(i));
    		}
    	}
    }
}
