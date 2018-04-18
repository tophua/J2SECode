package snmpmonitor.Extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import snmpmonitor.Common.ISiteviewApi;
import snmpmonitor.MonitorObject.OpticalObject;

public class ClickShelfDevice {

	ISiteviewApi api=null;  //Ϊ���ܽ���Ҫ���½�һ����
	public static Map<String, String> cmap = new ConcurrentHashMap<String, String>(1);	
	
	public List<Map<String, String>> ProcessEventFromFront(Map<String, String> inputParamMap){

		List<Map<String, String>> listOut = new ArrayList<Map<String, String>>();

		String strChasId = "", strShelfId = "";
		// ��ǰ̨ҳ���ȡ����
		String Data = inputParamMap.get("Data") == null ? "" : inputParamMap.get("Data").toString().trim();
		JSONArray jsonArray = JSONArray.fromObject(Data);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject Jsearch = jsonArray.getJSONObject(i);
			strChasId = (String) Jsearch.get("chassisId");
			strShelfId = (String) Jsearch.get("shelfId");
		}

		cmap.clear();
		cmap.put("Result", OpticalObject.ClickShelfDeviceObject(api, strChasId, strShelfId).get("Result"));
		// if(SNMPTimedZXMonitor.ZXScheduler==1){ //��ֹ�͵��ȳ�ͻ
		// cmap.put("Result", "Scheduler");
		// }else{
		//
		// }
		listOut.add(cmap);
		return listOut;
	}
	
}
