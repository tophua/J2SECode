package snmpmonitor.Extension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import snmpmonitor.Common.FrontSearchStructure;
import snmpmonitor.Common.ISiteviewApi;
import snmpmonitor.MonitorObject.OpticalObject;

public class RenovateShowDevice {
	ISiteviewApi api=null;  //Ϊ���ܽ���Ҫ���½�һ����
	public static Map<String, String> cmap = new ConcurrentHashMap<String, String>(1);	
	
	public List<Map<String, String>> ProcessEventFromFront(Map<String, String> inputParamMap){
	List<Map<String, String>> listOut = new ArrayList<Map<String,String>>();	
		
		//��ǰ̨ҳ���ȡ����		
		cmap.clear();
		if(GetFrontRenovatedMap(inputParamMap)){
			cmap.put("Result", OpticalObject.RenovateShowDeviceObject(api).get("Result"));
		}else
			cmap.put("Result", "error");
		listOut.add(cmap);
		return listOut;
	}
	
	public boolean GetFrontRenovatedMap(Map<String, String>mapInTemp){
		
		 String Data = mapInTemp.get("Data") == null ? "" : mapInTemp.get("Data").toString().trim();
		 if(Data.equals("Renovate")){
			 return true;
		 }else
			 return false;
	}
	
}
