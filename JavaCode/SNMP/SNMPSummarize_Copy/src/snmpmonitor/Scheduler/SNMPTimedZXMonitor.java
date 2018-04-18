package snmpmonitor.Scheduler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import snmpmonitor.Common.ISiteviewApi;
import snmpmonitor.MonitorObject.OperatorTable;
import snmpmonitor.MonitorObject.OpticalObject;

public class SNMPTimedZXMonitor {

	public static int ZXScheduler=0;
	public SNMPTimedZXMonitor() {
		// TODO Auto-generated constructor stub
	}

	public String run(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
	//	System.out.println("��ʱ����");
		ZXScheduler=1;  //�����е���ʱ��trap��Ϣ������,ǰ̨����Ҳ������
		ISiteviewApi api = (ISiteviewApi) params.get("_CURAPI_"); //����Ϊ�˲������ݿ���Ҫ
		Map<String,String> mapChassis=new HashMap<String,String>();
		OperatorTable.GetAllChassisIPFromTable(api,mapChassis);
		if(mapChassis.size()>0){
			Iterator iter1 = mapChassis.keySet().iterator();
			for (; iter1.hasNext();) {
				String keyIP = (String) iter1.next();
				String valueCommunity= mapChassis.get(keyIP);
				OpticalObject.UpdateOperatorZXOptical(api,keyIP,valueCommunity);
			}			
		}else
			System.out.println("û��ZX��˻���Ҫ����");
		
		ZXScheduler=0;
		return null;
	}

}
