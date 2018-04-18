package snmpmonitor.Extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import snmpmonitor.Common.ISiteviewApi;
import snmpmonitor.MonitorObject.OpticalObject;

public class DeleteMonitorDevice {

	ISiteviewApi api=null;  //为了总结需要，新建一个类
	public static Map<String, String> cmap = new ConcurrentHashMap<String, String>(1);	
	
	public List<Map<String, String>> ProcessEventFromFront(Map<String, String> inputParamMap){
		List<Map<String, String>> listOut = new ArrayList<Map<String,String>>();
		cmap.clear();
		String type="",chasId="",shelfId="",slotId="";
		//从前台页面获取参数		
		String Data = inputParamMap.get("Data") == null ? "" : inputParamMap.get("Data").toString().trim();
		JSONArray jsonArray = JSONArray.fromObject(Data);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject Jsearch = jsonArray.getJSONObject(i);
			type = (String) Jsearch.get("type");
			switch(type){
			case "Chassis":
			{
				chasId=(String) Jsearch.get("chassisId");
				cmap.put("Result", OpticalObject.DeleteChassisDevice(api,chasId));
			}
				break;
			case "Shelf":
			{
				chasId=(String) Jsearch.get("chassisId");
				shelfId=(String) Jsearch.get("shelfId");
				cmap.put("Result", OpticalObject.DeleteShelfDevice(api,chasId,shelfId));
			}
				break;	
			case "Slot":
			{
				chasId=(String) Jsearch.get("chassisId");
				shelfId=(String) Jsearch.get("shelfId");
				slotId=(String) Jsearch.get("slotId");		
				cmap.put("Result", OpticalObject.DeleteSlotDevice(api,chasId,shelfId,slotId));
			}
				break;
			default:
				cmap.put("Result", "error");
				break;
			}
		}
	
		listOut.add(cmap);
		return listOut;		
	}
	
}
