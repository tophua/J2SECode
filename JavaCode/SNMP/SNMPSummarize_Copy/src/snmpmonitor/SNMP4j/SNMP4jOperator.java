package snmpmonitor.SNMP4j;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

import snmpmonitor.Common.ComInfo;

public class SNMP4jOperator {

	Snmp snmp = null;
	CommunityTarget target = null;
	TableUtils utils = null;

	public void InitSnmp(){
		try {
			snmp = new Snmp(new DefaultUdpTransportMapping());
			snmp.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void CreateTarget(String community,String IpAndPort){
		target = new CommunityTarget();
		target.setCommunity(new OctetString(community)); // 设置公共体
		target.setVersion(SnmpConstants.version2c);
		target.setAddress(new UdpAddress(IpAndPort));
		target.setTimeout(Integer.parseInt(ComInfo.Timeout)); // 3s //超时时间
		target.setRetries(Integer.parseInt(ComInfo.Retries)); // 通信不成功时的重试次数
	}
	
	public void CreateUtils(){
		utils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));// GETNEXT or GETBULK
		utils.setMaxNumRowsPerPDU(Integer.parseInt(ComInfo.maxrepetitions)); 
		// only for GETBULK,set max-repetitions,defaultis 10
	}
	
	public PDU createGetPdu(String strAll,int OidNum) {
		PDU pdu = new PDU();
		pdu.setType(PDU.GET);
		int nResult=0;
		String strOID[]=new String[OidNum];
		nResult=ParseOID(strOID,strAll);
		for(int i=0;i<nResult;i++){
			pdu.add(new VariableBinding(new OID(strOID[i])));
		}
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0")));
		// //sysUpTime
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0"))); //sysName
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5"))); //expect an
		// no_such_instance error
	//	pdu.add(new VariableBinding(new OID(stroid)));
		return pdu;
	}
	
	public PDU createGetNextPdu(String stroid) {
		PDU pdu = new PDU();
		pdu.setType(PDU.GETNEXT);
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3"))); //sysUpTime
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5"))); //sysName
		pdu.add(new VariableBinding(new OID(stroid)));
		return pdu;
	}
	
	public PDU createGetBulkPdu(String stroid) {
		PDU pdu = new PDU();
		pdu.setType(PDU.GETBULK);
		pdu.setMaxRepetitions(10); // must set it, default is 0
		pdu.setNonRepeaters(0);
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1"))); //system
		pdu.add(new VariableBinding(new OID(stroid)));
		return pdu;
	}
	
	public static int ParseOID(String[] strOid,String strValue){	
		int i=0;	
		while(!strValue.equals("")){
			strOid[i]= strValue.substring(0, strValue.indexOf(";")).toString();    
			strValue=strValue.substring(strValue.indexOf(";")+1);
			i++;
		}
		return i;
	}
	/*
	 * 如果输入的pdu不存在，返回noSuchObject,否则正常返回
	 */
	public String SendPDUAndRequest(PDU pdu, List<VariableBinding> listTemp){
		String strResult = "";
		// 向Agent发送PDU，并接收Response
		ResponseEvent respEvnt;
		try {
			respEvnt = snmp.send(pdu, target);
			// Thread.sleep(10); //停0.01s
			// 解析Response
			if (respEvnt != null && respEvnt.getResponse() != null) {
				Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getResponse().getVariableBindings();
				for (int i = 0; i < recVBs.size(); i++) {
					VariableBinding recVB = recVBs.elementAt(i);
					if (recVB.getVariable().equals("noSuchObject") || recVB.getVariable() == null) {
						strResult = "OIDError";
					} else {
						listTemp.add(recVB);
					}
				}
			} else if (respEvnt == null || respEvnt.getResponse() == null) {
				strResult = "IPError";
			} else {
				strResult = "UnknownError";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strResult;
	}
	
	//如果oid错误，listEvent是null
	public void SNMPWalkToBatch(String Oid,List<TableEvent> listEvent){
		TableUtils utils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));//GETNEXT or GETBULK  
		utils.setMaxNumRowsPerPDU(10);   //only for GETBULK, set max-repetitions, default is 10  
		OID[] columnOids = new OID[] {  
	//	new OID("1.3.6.1.2.1.1.9.1.2"), //sysORID  
	//	new OID("1.3.6.1.2.1.1.9.1.3"), //sysORDescr  
	//	new OID("1.3.6.1.2.1.1.9.1.5")  //wrong OID, expect an null in in VariableBinding array  
		new OID(Oid)
		};  
		// If not null, all returned rows have an index in a range (lowerBoundIndex, upperBoundIndex]  
		List<TableEvent> l = utils.getTable(target, columnOids, new OID(ComInfo.TableEventStart), new OID(ComInfo.TableEventEnd));  
		for (TableEvent e : l) {
		//	System.out.println(e);
			listEvent.add(e);
		}
	}
	
}
