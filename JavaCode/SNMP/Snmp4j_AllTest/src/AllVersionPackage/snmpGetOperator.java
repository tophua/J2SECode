package AllVersionPackage;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.snmp4j.PDU;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;


public class snmpGetOperator {
	
	snmpAllVersion snmpHandle=null;
	public static String TableEventStart="1";
	public static String TableEventEnd="100";
	
	snmpGetOperator(){
		snmpHandle=new snmpAllVersion();
	}
	public void initSNMPVersion(int version,String ip){
		snmpHandle.setVersion(version);
		snmpHandle.settargetAddress(ip);
		snmpHandle.initSnmp();
		snmpHandle.CreateTarget();
		snmpHandle.CreateUtils();//为GETNEXT or GETBULK操作需要
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
		//形如oid1;oid2;oid3;
		int i=0;	
		while(!strValue.equals("")){
			strOid[i]= strValue.substring(0, strValue.indexOf(";")).toString();    
			strValue=strValue.substring(strValue.indexOf(";")+1);
			i++;
		}
		return i;
	}

	/**
	 * 下面将介绍两种方式来Get
	 * 一种是直接发送单个oid来获取具体结果；一种是发送根oid来获取所有的结果
	 */
	
	/*
	 * 如果输入单个的pdu不存在，返回noSuchObject,否则正常返回
	 */
	public String SendPDUAndResponse(PDU pdu, List<VariableBinding> listTemp){
		String strResult = "";
		// 向Agent发送PDU，并接收Response
		ResponseEvent respEvnt;
		try {
			respEvnt = snmpHandle.snmp.send(pdu, snmpHandle.target);
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
	public void SNMPWalk(String Oid,List<TableEvent> listEvent){
		TableUtils utils = new TableUtils(snmpHandle.snmp, new DefaultPDUFactory(PDU.GETBULK));//GETNEXT or GETBULK  
		utils.setMaxNumRowsPerPDU(10);   //only for GETBULK, set max-repetitions, default is 10  
		OID[] columnOids = new OID[] {  
	//	new OID("1.3.6.1.2.1.1.9.1.2"), //sysORID  
	//	new OID("1.3.6.1.2.1.1.9.1.3"), //sysORDescr  
	//	new OID("1.3.6.1.2.1.1.9.1.5")  //wrong OID, expect an null in in VariableBinding array  
		new OID(Oid)
		};  
		// If not null, all returned rows have an index in a range (lowerBoundIndex, upperBoundIndex]  
		List<TableEvent> l = utils.getTable(snmpHandle.target, 
				columnOids,
				new OID(TableEventStart),
				new OID(TableEventEnd));  
		for (TableEvent e : l) {
		//	System.out.println(e);
			listEvent.add(e);
		}
	}
}
