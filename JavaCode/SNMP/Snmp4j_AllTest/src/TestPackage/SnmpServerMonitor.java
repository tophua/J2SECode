package TestPackage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;


public class SnmpServerMonitor {
	  Log log=LogFactory.getLog(SnmpServerMonitor.class);
	   private static Snmp snmp = null;     
	   private static TransportMapping transport = null;  
	   private static Address targetAddress = null;   
	   private static PDU responsepdu = new PDU();  
	   private static CommunityTarget target = null;  
	   private static String ipStr = "";  
	   
	   /** 
	    * ���ü�ط�������ַ 
	    *  
	    * @author linwang 
	    * @createTime 2014-6-30 
	    * @param ipStr 
	    */  
	   public static void setIpStr(String ipStr) {  
		   SnmpServerMonitor.ipStr = "udp:" + ipStr + "/161";   
	       try {  
	           initComm();  
	       } catch (Exception e) {  
	           e.printStackTrace();  
	       }  
	   }  
	 
	   /** 
	    * ��ȡ��ǰ��������ص�IP��ַ 
	    *  
	    * @author linwang 
	    * @createTime 2014-7-8 
	    * @return 
	    */  
	   public static String getIpStr() {  
	       if (StringUtils.isNotEmpty(ipStr) && ipStr.indexOf(":") != -1) {  
	           ipStr = ipStr.split(":")[1].split("/161")[0];  
	       }  
	       return ipStr;  
	   }  
	 
	   /** 
	    * ��ʼ��snmp�����Ϣ 
	    *  
	    * @author linwang 
	    * @createTime 2014-6-30 
	    * @throws IOException 
	    */  
	   public static synchronized void initComm() throws IOException {  
	 
	       // ����Agent������Э�鲢�򿪼���  
	       if (null != transport)  
	           transport.close();  
	       if (null != snmp)  
	           snmp.close();  
	       transport = new DefaultUdpTransportMapping();  
	       snmp = new Snmp(transport);  
	       snmp.listen();  
	       if (!transport.isListening())  
	           transport.listen();  
	 
	       // ����target  
	       target = new CommunityTarget();  
	       target.setCommunity(new OctetString("public"));  
	       targetAddress = GenericAddress.parse(ipStr);  
	       target.setAddress(targetAddress);  
	       target.setRetries(2);  
	       target.setTimeout(1500);  
	       target.setVersion(SnmpConstants.version1);  
	   }  
	 
	   /** 
	    * ��ȡCPU������ �ٷֱ� 
	    *  
	    * @author linwang 
	    * @createTime 2014-6-30 
	    * @return 
	    * @throws IOException 
	    */  
	   public static Map<String, Object> getCpuUse() throws Exception {  
	       Map<String, Object> temMap = new HashMap<String, Object>();  
	       try {  
	           PDU responsepdu = new PDU();  
	           //1.3.6.1.4.1.9600.1.1.5.1.5.6.95.84.111.116.97.108
	           responsepdu.add(new VariableBinding(new OID(  
	                   "1.3.6.1.4.1.2021.11.52.0")));  
	           responsepdu.setType(PDU.GET);  
	           ResponseEvent response = snmp.send(responsepdu, target);  
	           if (response.getResponse() == null) {  
	           } else {  
	               responsepdu = response.getResponse();  
	               /* 
	                * //System.out.println("Received Value is: "+ 
	                * responsepdu.getVariableBindings()); 
	                */  
	           }  
	 
	           String tem = responsepdu.getVariableBindings().toString();  
	           int temInt = 0;  
	           if (tem.indexOf("Null") != -1) {  
	               temInt = -1;  
	           } else {  
	               tem = tem.split("=")[1].split("]")[0].trim();  
	               if (isNum(tem)) {  
	                   temInt = Integer.parseInt(tem);  
	               }  
	           }  
	 
	           temMap.put("data", "");  
	           temMap.put("cpuLoad", temInt);  
	 
	            System.out.println("CPU�����ʣ�" + temInt);  
	       } catch (Exception e) {  
	         //   throw new BusinessException("��ȡ" + ipStr + "��������CPUʧ��");  
	       }  
	       return temMap;  
	   }  
	 
	   /** 
	    * ��ȡ�ڴ������� 
	    *  
	    * @author linwang 
	    * @createTime 2014-6-30 
	    * @return 
	    * @throws IOException 
	    */  
	   public static Map<String, Object> getMemoryUse() throws Exception {  
	       Map<String, Object> temMap = new HashMap<String, Object>();  
	       try {  
	           int total = 0;  
	           int used = 0;  
	           int temInt = 0;  
	 
	           PDU responsepdu = new PDU();  
	           responsepdu  
	                   .add(new VariableBinding(new OID(".1.3.6.1.2.1.25.2.2.0")));  
	           responsepdu.setType(PDU.GET);  
	           ResponseEvent response = snmp.send(responsepdu, target);  
	           if (response.getResponse() == null) {  
	           } else {  
	               responsepdu = response.getResponse();  
	               // //System.out.println("ȫ����: "+  
	               // responsepdu.getVariableBindings());  
	               String tem = responsepdu.getVariableBindings().toString();  
	               total = Integer  
	                       .parseInt(tem.split("=")[1].split("]")[0].trim());  
	               System.out.println("��ȡ�ڴ��С:"+total);
	           }  
	           responsepdu.clear();  
	           //1.3.6.1.4.1.9600.1.1.2.2.0
	           responsepdu.add(new VariableBinding(new OID(  
	                   ".1.3.6.1.4.1.2021.4.3.0")));  
	           responsepdu.setType(PDU.GET);  
	           response = snmp.send(responsepdu, target);  
	           if (response.getResponse() == null) {  
	           } else {  
	               responsepdu = response.getResponse();  
	               // //System.out.println("���õ�: " +  
	               // responsepdu.getVariableBindings());  
	               String tem = responsepdu.getVariableBindings().toString();  
	               used = Integer.parseInt(tem.split("=")[1].split("]")[0].trim());  
	               System.out.println("Total Swap Size(�����ڴ�):"+total);
	           }  
	           if (total == 0) {  
	               temInt = 0;  
	           }  
	           if (total != 0) {  
	               temInt = (total - used) * 100 / total;  
	               temInt = Math.abs(temInt);  
	           }  
	 
	           // System.out.println("�ڴ������ʣ�" + temInt);  
	 
	           temMap.put("data", "");  
	           temMap.put("memoryLoad", temInt);  
	       } catch (Exception e) {  
	       //    throw new BusinessException("��ȡ" + ipStr + "���������ڴ�ʧ��");  
	       }  
	       return temMap;  
	   }  
	 
	   /** 
	    * ȡ��Ӳ������ 
	    *  
	    * @author linwang 
	    * @createTime 2014-6-30 
	    * @return 
	    * @throws IOException 
	    */  
	   public static List<Map<String, Object>> getHardpan() throws IOException {  
	 
	       // 1.3.6.1.2.1.25.2.3.1.3 �������ͣ�Ӳ�̣�c��d..),�ڴ桢�����ڴ棩  
	       List<String> labelss = new ArrayList<String>();  
	       // 1.3.6.1.2.1.25.2.3.1.4���䵥Ԫ  
	       List<Integer> units = new ArrayList<Integer>();  
	       // 1.3.6.1.2.1.25.2.3.1.5 �洢����  
	       List<Integer> size = new ArrayList<Integer>();  
	       // 1.3.6.1.2.1.25.2.3.1.6��ʹ������  
	       List<Integer> used = new ArrayList<Integer>();  
	 
	       responsepdu = new PDU();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.25.2.3.1.3")));  
	       responsepdu.setType(PDU.GETNEXT);  
	       getHardpanIteration(labelss, "1.3.6.1.2.1.25.2.3.1.4.1", 1);  
	 
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.25.2.3.1.4")));  
	       getHardpanIteration(units, "1.3.6.1.2.1.25.2.3.1.5.1", 2);  
	 
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.25.2.3.1.5")));  
	       getHardpanIteration(size, "1.3.6.1.2.1.25.2.3.1.6.1", 2);  
	 
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.25.2.3.1.6")));  
	       getHardpanIteration(used, "1.3.6.1.2.1.25.2.3.1.7.1", 2);  
	 
	       List<Map<String, Object>> temList = new ArrayList<Map<String, Object>>();  
	 
	       int i = 0;  
	       for (String label : labelss) {  
	           Map<String, Object> temMap = new HashMap<String, Object>();  
	           if ("Virtual Memory".equals(label)  
	                   || "Physical Memory".equals(label) || size.get(i) == 0) {  
	               i++;  
	               continue;  
	           }  
	           String usedPercent = "";  
	           String total = "";  
	           String usedStr = "";  
	           String freeStr = "";  
	           int a = used.get(i);// ��ʹ������  
	           int b = size.get(i);// �洢����  
	           int u = units.get(i);// ���䵥Ԫ  
	 
	           // ����̷�����  
	           temMap.put("name", label.split(":")[0]);  
	 
	           // ����̷�������  
	           float c = (float) b * (float) u / 1024 / 1024 / 1024;  
	           DecimalFormat df = new DecimalFormat("0");// ��ʽ��С��������Ĳ�0  
	           total = df.format(c);// ���ص���String���͵�  
	           temMap.put("total", total);  
	 
	           // ����̷���ʹ�ðٷֱ�  
	           if (size.get(i) != 0) {  
	               // java��������������õ�С���㲢������λС���ķ���--�Ƿ�ѡ����������  
	               c = (float) a / (float) b * 100;  
	               df = new DecimalFormat("0.00");// ��ʽ��С��������Ĳ�0  
	               usedPercent = df.format(c);// ���ص���String���͵�  
	           }  
	           temMap.put("usedPercent", usedPercent);  
	 
	           // ����̷���ʹ��  
	           c = (float) a * (float) u / 1024 / 1024 / 1024;  
	           df = new DecimalFormat("0.00");// ��ʽ��С��������Ĳ�0  
	           usedStr = df.format(c);// ���ص���String���͵�  
	           temMap.put("used", usedStr);  
	 
	           // ����̷�δʹ������  
	           c = ((float) ((b - a)) / 1024 / 1024 / 1024 * u);  
	           freeStr = df.format(c);// ���ص���String���͵�  
	           temMap.put("free", freeStr);  
	           i++;  
	           temList.add(temMap);  
	       }  
	 
	       // System.out.println("��ȡ��Ӳ����Ϣ��" + json);  
	 
	       return temList;  
	   }  
	 
	   /** 
	    * ������ȡ������Ϣ 
	    *  
	    * @author linwang 
	    * @createTime 2014-7-12 
	    * @param listType 
	    *            Ӳ�������ϢList 
	    * @param endOid 
	    *            ��ֹOID�ַ��� 
	    * @param flag 
	    *            flag 1:ȡ�ַ���list��2��ȡ����list 
	    * @throws IOException 
	    */  
	   @SuppressWarnings({ "rawtypes", "unchecked" })  
	   public static void getHardpanIteration(List listType, String endOid,  
	           int flag) throws IOException {  
	       ResponseEvent response = snmp.getBulk(responsepdu, target);  
	       if (response.getResponse() == null) {  
	           return;  
	       } else {  
	           Vector<VariableBinding> recVBs = (Vector<VariableBinding>) response.getResponse()  
	                   .getVariableBindings();  
	           String oid = recVBs.elementAt(0).getOid().toString();  
	           if (endOid.equals(oid)) {  
	               return;  
	           }  
	           if (!oid.contains(endOid) && (flag == 3 || flag == 4)) {  
	               return;  
	           }  
	           responsepdu = response.getResponse();  
	           // System.out.println("����ȡӲ����Ϣ��Received Value is: " +  
	           // responsepdu.getVariableBindings());  
	           String tem = responsepdu.getVariableBindings().toString();  
	           tem = tem.split("=")[1].split("]")[0].trim();  
	           if (flag == 2 || flag == 3) {  
	               if (isNum(tem)) {  
	                   listType.add(Integer.parseInt(tem));  
	               }  
	           } else {  
	               listType.add(tem);  
	           }  
	           getHardpanIteration(listType, endOid, flag);  
	       }  
	   }  
	 
	   /** 
	    * ��ȡ�������� 
	    *  
	    * @author linwang 
	    * @createTime 2014-1-2 
	    * @return 
	    * @throws IOException 
	    */  
	   public static Map<String, Object> GetProcessesCount() throws IOException {  
	 
	       PDU responsepdu = new PDU();  
	       Map<String, Object> temMap = new HashMap<String, Object>();  
	       try {  
	           responsepdu  
	                   .add(new VariableBinding(new OID("1.3.6.1.2.1.25.1.6.0")));  
	           responsepdu.setType(PDU.GET);  
	           ResponseEvent response = snmp.send(responsepdu, target);  
	           if (response.getResponse() == null) {  
	               temMap.put("success", "false");  
	               temMap.put("msg", "0");  
	           } else {  
	               responsepdu = response.getResponse();  
	           }  
	           String tem = responsepdu.getVariableBindings().toString();  
	           tem = tem.split("=")[1].split("]")[0].trim();  
	           temMap.put("success", "true");  
	           temMap.put("ProcessesCount", tem);  
	           // System.out.println("��������" + tem);  
	       } catch (Exception e) {  
	           e.printStackTrace();  
	           temMap.put("success", "false");  
	           temMap.put("msg", e.getMessage());  
	           // System.out.println("��ȡ����ʧ��");  
	       }  
	 
	       return temMap;  
	   }  
	 
	   /** 
	    * ��ȡ�����б� 
	    *  
	    * @author linwang 
	    * @createTime 2014-1-2 
	    * @return 
	    * @throws IOException 
	    */  
	   public static List<Map<String, Object>> GetProcessesRunInfo()  
	           throws IOException {  
	       List<Map<String, Object>> temMapList = new ArrayList<Map<String, Object>>();  
	       Map<String, Object> temMap = new HashMap<String, Object>();  
	       List<String> runNameList = new ArrayList<String>();  
	       List<String> runPathList = new ArrayList<String>();  
	       List<String> runTypeList = new ArrayList<String>();  
	       List<String> perfMemList = new ArrayList<String>();  
	 
	       // ���� PDU  
	       PDU pdu = new PDU();  
	       pdu.setType(PDU.GETBULK);  
	       responsepdu = pdu;  
	       pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.25.4.2.1.2")));  
	       getProcessesListIteration(runNameList, "1.3.6.1.2.1.25.4.2.1.2");  
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.25.4.2.1.4")));  
	       getProcessesListIteration(runPathList, "1.3.6.1.2.1.25.4.2.1.4");  
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.25.4.2.1.6")));  
	       getProcessesListIteration(runTypeList, "1.3.6.1.2.1.25.4.2.1.6");  
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.25.5.1.1.2")));  
	       getProcessesListIteration(perfMemList, "1.3.6.1.2.1.25.5.1.1.2");  
	 
	       int i = 0;  
	       if (runNameList.size() > 0 && runPathList.size() > 0  
	               && perfMemList.size() > 0 && runTypeList.size() > 0) {  
	           for (String kvp : runNameList) {  
	               temMap = new HashMap<String, Object>();  
	               temMap.put("runName", kvp);  
	               temMap.put("runPath", runPathList.get(i));  
	               temMap.put("runType", runTypeList.get(i));  
	               temMap.put("perfMem", perfMemList.get(i));  
	               temMapList.add(temMap);  
	               i++;  
	           }  
	       }  
	       return temMapList;  
	   }  
	 
	   /** 
	    * ������ȡ�����б� 
	    *  
	    * @author linwang 
	    * @createTime 2014-7-12 
	    * @param listType 
	    *            Ӳ�������ϢList 
	    * @param endOid 
	    *            ��ֹOID�ַ��� 
	    * @throws IOException 
	    */  
	   @SuppressWarnings({ "rawtypes", "unchecked" })  
	   public static void getProcessesListIteration(List listType, String endOid)  
	           throws IOException {  
	       ResponseEvent response = snmp.getBulk(responsepdu, target);  
	 
	       if (response.getResponse() == null) {  
	           return;  
	       } else {  
	           Vector<VariableBinding> recVBs = (Vector<VariableBinding>)response.getResponse().getVariableBindings();  
	           String oid = recVBs.elementAt(0).getOid().toString();  
	 
	           // ������ֹOID�Ǹ��ڵ㣬�����ǰ������oid���������ڵ�ǰ׺���˳�����  
	           if (!oid.contains(endOid)) {  
	               return;  
	           }  
	           responsepdu = response.getResponse();  
	           // System.out.println("����ȡ�����б�Received Value is: " +  
	           // responsepdu.getVariableBindings());  
	           String tem = responsepdu.getVariableBindings().toString();  
	           tem = tem.split("=")[1].split("]")[0].trim();  
	           listType.add(tem);  
	 
	           getProcessesListIteration(listType, endOid);  
	       }  
	   }  
	 
	   /** 
	    * �鿴TCP�˿� 
	    *  
	    * @author linwang 
	    * @createTime 2014-1-2 
	    * @return 
	    * @throws IOException 
	    */  
	   public static List<Map<String, Object>> GetTcpInfo() throws IOException {  
	 
	       List<String> connStateList = new ArrayList<String>();  
	       List<String> localAddressList = new ArrayList<String>();  
	       List<String> localPortList = new ArrayList<String>();  
	       List<String> remoteAddressList = new ArrayList<String>();  
	       List<String> remotePortList = new ArrayList<String>();  
	 
	       // ���� PDU  
	       PDU pdu = new PDU();  
	       pdu.setType(PDU.GETBULK);  
	       responsepdu = pdu;  
	       pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.6.13.1.1")));  
	       getTCPIteration(connStateList, "1.3.6.1.2.1.6.13.1.1");  
	 
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.6.13.1.2")));  
	       getTCPIteration(localAddressList, "1.3.6.1.2.1.6.13.1.2");  
	 
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.6.13.1.3")));  
	       getTCPIteration(localPortList, "1.3.6.1.2.1.6.13.1.3");  
	 
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.6.13.1.4")));  
	       getTCPIteration(remoteAddressList, "1.3.6.1.2.1.6.13.1.4");  
	 
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.6.13.1.5")));  
	       getTCPIteration(remotePortList, "1.3.6.1.2.1.6.13.1.5");  
	 
	       int i = 0;  
	       Map<String, Object> temMap = new HashMap<String, Object>();  
	       List<Map<String, Object>> temMapList = new ArrayList<Map<String, Object>>();  
	       String[] state = { "", "closed", "listen", "synSent", "synReceived",  
	               "established", " finWait1", "finWait2", "closeWait", "lastAck",  
	               "closing", "timeWait", "" };  
	       for (String kvp : connStateList) {  
	           temMap = new HashMap<String, Object>();  
	           temMap.put("connState", state[toInteger(kvp)]);  
	           temMap.put("localAddress", localAddressList.get(i));  
	           temMap.put("localPort", localPortList.get(i));  
	           temMap.put("remoteAddress", remoteAddressList.get(i));  
	           temMap.put("remotePort", remotePortList.get(i));  
	           temMapList.add(temMap);  
	           i++;  
	       }  
	 
	       return temMapList;  
	   }  
	 
	   /** 
	    * ������ȡTCP�˿� 
	    *  
	    * @author linwang 
	    * @createTime 2013-12-26 
	    * @param listType 
	    * @param endOid 
	    * @throws IOException 
	    */  
	   @SuppressWarnings({ "rawtypes", "unchecked" })  
	   public static void getTCPIteration(List listType, String endOid)  
	           throws IOException {  
	 
	       ResponseEvent response = snmp.getBulk(responsepdu, target);  
	 
	       if (response.getResponse() == null) {  
	           return;  
	       } else {  
	           Vector<VariableBinding> recVBs = (Vector<VariableBinding>) response.getResponse()  
	                   .getVariableBindings();  
	           String oid = recVBs.elementAt(0).getOid().toString();  
	 
	           // ������ֹOID�Ǹ��ڵ㣬�����ǰ������oid���������ڵ�ǰ׺���˳�����  
	           if (!oid.contains(endOid)) {  
	               return;  
	           }  
	           responsepdu = response.getResponse();  
	           // System.out.println("����ȡTCP�˿ڡ�Received Value is: " +  
	           // responsepdu.getVariableBindings());  
	           String tem = responsepdu.getVariableBindings().toString();  
	           tem = tem.split("=")[1].split("]")[0].trim();  
	           listType.add(tem);  
	 
	           getTCPIteration(listType, endOid);  
	       }  
	   }  
	 
	   /** 
	    * ��ȡUDP�˿� 
	    *  
	    * @author linwang 
	    * @createTime 2014-1-2 
	    * @return 
	    * @throws IOException 
	    */  
	   public static List<Map<String, Object>> GetUdpInfo() throws IOException {  
	 
	       List<String> localAddressList = new ArrayList<String>();  
	       List<String> localPortList = new ArrayList<String>();  
	 
	       // ���� PDU  
	       PDU pdu = new PDU();  
	       pdu.setType(PDU.GETBULK);  
	       responsepdu = pdu;  
	       pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.7.5.1.1")));  
	       getUDPIteration(localAddressList, "1.3.6.1.2.1.7.5.1.1");  
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.7.5.1.2")));  
	       getUDPIteration(localPortList, "1.3.6.1.2.1.7.5.1.2");  
	 
	       int i = 0;  
	       Map<String, Object> temMap = new HashMap<String, Object>();  
	       List<Map<String, Object>> temMapList = new ArrayList<Map<String, Object>>();  
	       for (String kvp : localAddressList) {  
	           temMap = new HashMap<String, Object>();  
	           temMap.put("localAddress", kvp);  
	           temMap.put("localPort", localPortList.get(i));  
	           temMapList.add(temMap);  
	           i++;  
	       }  
	 
	       return temMapList;  
	   }  
	 
	   /** 
	    * ������ȡUDP�˿� 
	    *  
	    * @author linwang 
	    * @createTime 2013-12-26 
	    * @param listType 
	    * @param endOid 
	    * @throws IOException 
	    */  
	   @SuppressWarnings({ "rawtypes", "unchecked" })  
	   public static void getUDPIteration(List listType, String endOid)  
	           throws IOException {  
	 
	       ResponseEvent response = snmp.getBulk(responsepdu, target);  
	 
	       if (response.getResponse() == null) {  
	           return;  
	       } else {  
	           Vector<VariableBinding> recVBs = (Vector<VariableBinding>) response.getResponse()  
	                   .getVariableBindings();  
	           String oid = recVBs.elementAt(0).getOid().toString();  
	 
	           // ������ֹOID�Ǹ��ڵ㣬�����ǰ������oid���������ڵ�ǰ׺���˳�����  
	           if (!oid.contains(endOid)) {  
	               return;  
	           }  
	           responsepdu = response.getResponse();  
	           // System.out.println("����ȡUDP�˿ڡ�Received Value is: " +  
	           // responsepdu.getVariableBindings());  
	           String tem = responsepdu.getVariableBindings().toString();  
	           tem = tem.split("=")[1].split("]")[0].trim();  
	           listType.add(tem);  
	 
	           getUDPIteration(listType, endOid);  
	       }  
	   }  
	 
	   /** 
	    * ��ȡ����ͳ�� 
	    *  
	    * @author linwang 
	    * @createTime 2013-12-26 
	    * @return 
	    * @throws IOException 
	    */  
	   public static Map<String, Object> getNetWorkFlowCount() throws IOException {  
	 
	       // 1.3.6.1.4.1.9600.1.1.3.1.2 ��������  
	       List<Integer> receivedList = new ArrayList<Integer>();  
	       // 1.3.6.1.4.1.9600.1.1.3.1.3 ��������  
	       List<Integer> sendedList = new ArrayList<Integer>();  
	 
	       // ���� PDU  
	       PDU pdu = new PDU();  
	       VariableBinding vb = new VariableBinding();  
	       vb.setOid(new OID("1.3.6.1.4.1.9600.1.1.3.1.2"));  
	       pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.9600.1.1.3.1.2")));  
	       pdu.setType(PDU.GETBULK);  
	       responsepdu = pdu;  
	       getNetWorkFlowIteration(receivedList, "1.3.6.1.4.1.9600.1.1.3.1.2");  
	       responsepdu.clear();  
	       responsepdu.add(new VariableBinding(new OID(  
	               "1.3.6.1.4.1.9600.1.1.3.1.3")));  
	       getNetWorkFlowIteration(sendedList, "1.3.6.1.4.1.9600.1.1.3.1.3");  
	 
	       Map<String, Object> temMap = new HashMap<String, Object>();  
	       int receive = 0;  
	       String receiveStr = "";  
	       String sendStr = "";  
	       int send = 0;  
	       for (Integer kvp : receivedList) {  
	           receive += kvp;  
	       }  
	 
	       float c = (float) ((float) receive / 1024.0);  
	       DecimalFormat df = new DecimalFormat("0.00");// ��ʽ��С��������Ĳ�0  
	       receiveStr = df.format(c);// ���ص���String���͵�  
	       for (Integer kvp : sendedList) {  
	           send += kvp;  
	       }  
	 
	       c = (float) ((float) send / 1024.0);  
	       sendStr = df.format(c);// ���ص���String���͵�  
	 
	       temMap.put("receive", receiveStr);  
	       temMap.put("send", sendStr);  
	 
	       // System.out.println("��ȡ������Ϣ��" + temMap);  
	 
	       return temMap;  
	   }  
	 
	   /** 
	    * ������ȡ������Ϣ 
	    *  
	    * @author linwang 
	    * @createTime 2013-12-26 
	    * @param target 
	    * @param listType 
	    * @param endOid 
	    * @throws IOException 
	    */  
	   @SuppressWarnings({ "rawtypes", "unchecked" })  
	   public static void getNetWorkFlowIteration(List listType, String endOid)  
	           throws IOException {  
	 
	       ResponseEvent response = snmp.getBulk(responsepdu, target);  
	 
	       if (response.getResponse() == null) {  
	           return;  
	       } else {  
	           Vector<VariableBinding> recVBs = (Vector<VariableBinding>) response.getResponse()  
	                   .getVariableBindings();  
	           String oid = recVBs.elementAt(0).getOid().toString();  
	           if (!oid.contains(endOid)) {  
	               return;  
	           }  
	           responsepdu = response.getResponse();  
	           // System.out.println("����ȡ�����š�Received Value is: " +  
	           // responsepdu.getVariableBindings());  
	           String tem = responsepdu.getVariableBindings().toString();  
	           tem = tem.split("=")[1].split("]")[0].trim();  
	           if (isNum(tem)) {  
	               listType.add(Integer.parseInt(tem));  
	           }  
	 
	           getNetWorkFlowIteration(listType, endOid);  
	       }  
	   }  
	 
	   /** 
	    * ��ȡ������ 
	    *  
	    * @author JianZC 
	    * @createTime 2014-7-4 
	    * @param ipStr 
	    *  
	    */  
	   public static Map<String, Object> getMaster() throws IOException {  
	 
	       PDU responsepdu = new PDU();  
	       responsepdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0")));  
	       responsepdu.setType(PDU.GET);  
	       ResponseEvent response = snmp.send(responsepdu, target);  
	 
	       if (response.getResponse() == null) {  
	       } else {  
	           responsepdu = response.getResponse();  
	       }  
	 
	       String tem = responsepdu.getVariableBindings().toString();  
	       tem = tem.split("=")[1].split("]")[0].trim();  
	 
	       Map<String, Object> temMap = new HashMap<String, Object>();  
	       temMap.put("master", tem);  
	       return temMap;  
	   }  
	 
	   /** 
	    * �������б����ip��ȡ��Ӧ�б���Ϣ 
	    *  
	    * @author JianZC 
	    * @createTime 2014-7-4 
	    * @param ipStr 
	    * @param 
	    */  
	   public static Map<String, Object> getMasterDetail() {  
	 
	       Map<String, Object> temMap = new HashMap<String, Object>();  
	 
	       try {  
	           Map<String, Object> map = SnmpServerMonitor.getCpuUse();  
	           int cpu = (Integer) map.get("cpuLoad");  
	           map = SnmpServerMonitor.getMemoryUse();  
	           int memory = (Integer) map.get("memoryLoad");  
	           map = SnmpServerMonitor.getMaster();  
	           String master = (String) map.get("master");  
	           int auditFlag = 1;  
	 
	           if (cpu == -1 || memory == 0) {  
	               auditFlag = 0;  
	           } else {  
	               auditFlag = 1;  
	           }  
	 
	           map.put("cpuLoad", cpu);  
	           map.put("memoryLoad", memory);  
	           map.put("ip", ipStr);  
	           map.put("operateIp", ipStr);  
	           map.put("masterName", master);  
	           map.put("auditFlag", auditFlag);  
	           temMap.putAll(map);  
	       } catch (Exception e) {  
	           e.printStackTrace();  
	       }  
	 
	       return temMap;  
	   }  
	 
	   /** 
	    * �ж��ַ����Ƿ������� 
	    *  
	    * @author linwang 
	    * @createTime 2013-12-26 
	    * @param str 
	    * @return 
	    */  
	   public static boolean isNum(String str) {  
	       return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");  
	   }  
	 
	   /** 
	    * Integer.parseInt���Ϸǿ��ж�-Ϊnull������Ϊ0 
	    *  
	    * @author linwang 
	    * @createTime 2014-7-12 
	    * @param str 
	    * @return 
	    */  
	   public static Integer toInteger(String str) {  
	       Integer integer = 0;  
	       if (str == null || !isNum(str)) {  
	           integer = 0;  
	       } else {  
	           integer = new Integer(str.trim());  
	       }  
	       return integer;  
	   }   
}
