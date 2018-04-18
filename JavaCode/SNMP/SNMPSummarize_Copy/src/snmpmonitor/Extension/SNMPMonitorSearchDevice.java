package snmpmonitor.Extension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import snmpmonitor.Common.CommonFunction;
import snmpmonitor.Common.FrontSearchStructure;
import snmpmonitor.Common.ISiteviewApi;
import snmpmonitor.MonitorObject.OpticalObject;


public class SNMPMonitorSearchDevice {

	ISiteviewApi api=null;  //Ϊ���ܽ���Ҫ���½�һ����
	public static Map<String, String> cmap = new ConcurrentHashMap<String, String>(1);	
	
	public List<Map<String, String>> ProcessEventFromFront(Map<String, String> inputParamMap){
		List<Map<String, String>> listOut = new ArrayList<Map<String,String>>();
		Map<String,FrontSearchStructure> mapSearch= new HashMap<String,FrontSearchStructure>();
		
		//ģ���ȡ����
		GetFrontSearchMap(mapSearch,inputParamMap);
		cmap.clear();
		cmap.put("Result", OpticalObject.ParseSearchObject(api,mapSearch).get("Result"));

		listOut.add(cmap);
		return listOut;
	}
	
	public void GetFrontSearchMap(Map<String,FrontSearchStructure> mapSearchTemp,Map<String, String>mapInTemp){
		
		 String Data = mapInTemp.get("Data") == null ? "" : mapInTemp.get("Data").toString().trim();
		 JSONArray jsonArray = JSONArray.fromObject(Data);  
		 //���ַ������ܽ���
//		 List<SearchDeviceFromfront> listData = (List) JSONArray.toCollection(jsonArray, SearchDeviceFromfront.class);  
//        for(int h=0;h<listData.size();h++){
//	         String startip=listData.get(h).getStart();
//	         System.out.println(startip);
//       }

	     for(int i=0;i<jsonArray.size();i++){
	    	 JSONObject Jsearch = jsonArray.getJSONObject(i); // ���� jsonarray ���飬��ÿһ������ת�� json ����
	//    	 System.out.println(job.get("Start"));
	//    	 System.out.println(job.get("End")); // �õ� ÿ�������е�����ֵ
	 		String strStart = (String) Jsearch.get("Start");
	 		String strEnd = (String) Jsearch.get("End");
	 		String strBrand=(String) Jsearch.get("Brand");
	 		String strCommunity=(String) Jsearch.get("Community");
	 		
	 		FrontSearchStructure frontSearch=new FrontSearchStructure();
			frontSearch.setBrand(strBrand);
			frontSearch.setCommunity(strCommunity);
			frontSearch.setOnlineState("2");
			
			long lStart=0,lEnd=0;
			lStart=CommonFunction.StringIPToLong(strStart);
			lEnd=CommonFunction.StringIPToLong(strEnd);
			for(long l=lStart;l<=lEnd;l++){
				String strIP=CommonFunction.LongToStringIP(l);
				frontSearch.setChassisIP(strIP);
				mapSearchTemp.put(strIP, frontSearch);
			} 	
	     }
		
//		Iterator iter1 = mapInTemp.keySet().iterator();
//		for (; iter1.hasNext();) {
//			String key = (String) iter1.next();
//			String value= mapInTemp.get(key);
//			String strStart="",strEnd="",strIP="";
//			long lStart=0,lEnd=0;
//			String strCivilcode="",strBrand="",strCommunity="";
//			FrontSearchStructure frontSearch=new FrontSearchStructure();
//		//	strCivilcode= value.substring(value.indexOf("Civilcode")+"Civilcode:".length(), value.indexOf(";"));
//		//	value=value.substring(value.indexOf(";")+1);
//			strBrand= value.substring(value.indexOf("Brand")+"Brand:".length(), value.indexOf(";"));
//			value=value.substring(value.indexOf(";")+1);
//			strCommunity= value.substring(value.indexOf("Conmunity")+"Conmunity:".length(), value.indexOf(";"));
//			value=value.substring(value.indexOf(";")+1);
//			
//		//	frontSearch.setCivilcode(strCivilcode);
//			frontSearch.setBrand(strBrand);
//			frontSearch.setCommunity(strCommunity);
//			frontSearch.setOnlineState("2");
//			
//			strStart=key.substring(key.indexOf("Start")+"Start:".length(), key.indexOf(";"));
//			key=key.substring(key.indexOf(";")+1);
//			strEnd=key.substring(key.indexOf("End")+"End:".length(), key.indexOf(";"));
//			key=key.substring(key.indexOf(";")+1);
//			
//			lStart=CommonFunction.StringIPToLong(strStart);
//			lEnd=CommonFunction.StringIPToLong(strEnd);
//			for(long l=lStart;l<=lEnd;l++){
//				strIP=CommonFunction.LongToStringIP(l);
//				frontSearch.setChassisIP(strIP);
//				mapSearchTemp.put(strIP, frontSearch);
//			}
//		}	
	}
	
}
