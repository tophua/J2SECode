package snmpmonitor.Extension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import snmpmonitor.Common.FrontSearchStructure;
import snmpmonitor.Common.ISiteviewApi;
import snmpmonitor.MonitorObject.OpticalObject;

public class SNMPMonitorAddDevice {

	
	ISiteviewApi api=null;  //为了总结需要，新建一个类
	public static Map<String, String> cmap = new ConcurrentHashMap<String, String>(1);	
	
	public List<Map<String, String>> ProcessEventFromFront(Map<String, String> inputParamMap){
		List<Map<String, String>> listOut = new ArrayList<Map<String,String>>();
		Map<String,FrontSearchStructure> mapSearch= new HashMap<String,FrontSearchStructure>();
		
		//模拟获取参数
		GetFrontAddMap(mapSearch,inputParamMap);
		cmap.clear();
		cmap.put("Result", OpticalObject.ParseAddDeviceObject(api,mapSearch).get("Result"));

		listOut.add(cmap);
		return listOut;
	}
	
	public void GetFrontAddMap(Map<String,FrontSearchStructure> mapAddTemp,Map<String, String>mapInTemp){
		
		 String Data = mapInTemp.get("Data") == null ? "" : mapInTemp.get("Data").toString().trim();
		 JSONArray jsonArray = JSONArray.fromObject(Data);  
	     for(int i=0;i<jsonArray.size();i++){
	    	 JSONObject Jsearch = jsonArray.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
	//    	 System.out.println(job.get("Start"));
	//    	 System.out.println(job.get("End")); // 得到 每个对象中的属性值
	 		String strIP = (String) Jsearch.get("ChassisIP");
	 		String strBrand=(String) Jsearch.get("Brand");
	 		String strCommunity=(String) Jsearch.get("Community");
	 		String strCivilcode=(String) Jsearch.get("Civilcode");
	 		
	 		FrontSearchStructure frontSearch=new FrontSearchStructure();		
			frontSearch.setBrand(strBrand);
			frontSearch.setCommunity(strCommunity);
			frontSearch.setCivilcode(strCivilcode);
			frontSearch.setChassisIP(strIP);		
			mapAddTemp.put(strIP,frontSearch);
	     }
	}
	
}
