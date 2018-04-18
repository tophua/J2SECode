package TestPackage;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

public class SNMPV1andV2Test {
	
	private static PDU createGetPdu(String strAll,int OidNum){
		PDU pdu = new PDU();  
		pdu.setType(PDU.GET);  
		int nResult=0;
		String strOID[]=new String[OidNum];
		nResult=ParseOID(strOID,strAll);
		for(int i=0;i<nResult;i++){
			pdu.add(new VariableBinding(new OID(strOID[i])));
		}
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"))); //sysUpTime  
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0"))); //sysName  
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5")));   //expect an no_such_instance error 
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.6688.1.1.1.1.0")));
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
	 private static PDU createGetNextPdu() {  
		PDU pdu = new PDU();  
		pdu.setType(PDU.GETNEXT);  
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3")));   //sysUpTime  
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5")));   //sysName  
		pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.6688.1.1.1.2.1.2.1")));
		return pdu;  
	}  
	 private static PDU createGetBulkPdu() {  
		PDU pdu = new PDU();  
		pdu.setType(PDU.GETBULK);  
		pdu.setMaxRepetitions(10);  //must set it, default is 0  
		pdu.setNonRepeaters(0);  
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1")));     //system  
		pdu.add(new VariableBinding(new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.2")));
		return pdu;  
	} 
	 private static PDU createSetPdu() {  
		PDU pdu = new PDU();  
		pdu.setType(PDU.SET);  
		pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0"), new OctetString("sysname"))); //sysName  
		return pdu;  
	}
	 
	 private static void sendRequest(Snmp snmp, PDU pdu, CommunityTarget target) throws IOException {  
	      ResponseEvent responseEvent = snmp.send(pdu, target);  
	      PDU response = responseEvent.getResponse();  
	      if (response == null) {  
	          System.out.println("TimeOut...");  
	      } else {  
	         if (response.getErrorStatus() == PDU.noError) {  
	            Vector<? extends VariableBinding> vbs = response.getVariableBindings();  
	            for (VariableBinding vb : vbs) {  
	                  System.out.println(vb + " ," + vb.getVariable().getSyntaxString());  
	               }  
	          } else {  
	            System.out.println("Error:" + response.getErrorStatusText());  
	          }  
	      }  
	} 

	private static void sendAsyncRequest(Snmp snmp, PDU pdu, CommunityTarget target)throws IOException {  
	    snmp.send(pdu, target, null, new ResponseListener(){  
	         @Override  
	         public void onResponse(ResponseEvent event) {  
	            PDU response = event.getResponse();  
	            System.out.println("Got response from " + event.getPeerAddress());  
	            if (response == null) {  
	                 System.out.println("TimeOut...");  
	             } else {  
	                 if (response.getErrorStatus() == PDU.noError) {  
	                        Vector<? extends VariableBinding> vbs = response.getVariableBindings();  
	                        for (VariableBinding vb : vbs) {  	                  
	                              System.out.println(vb.getOid() + " : " + vb.getVariable());
	                              System.out.println(vb + " ," + vb.getVariable().getSyntaxString());  
	                           }  
	                  } else {  
	                        System.out.println("Error:" + response.getErrorStatusText());  
	                  }  
	             }  
	          }
	         }
	    );  
	}  
	 
	/*
	 * 如果输入的pdu不存在，返回noSuchObject,否则正常返回
	 */
	public static String SendPDUAndRequest(Snmp snmp, PDU pdu, CommunityTarget target, String strType)
			throws IOException, InterruptedException {

		// List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		// Map<String, String> mapResult = new HashMap<String, String>();
		String strResult = "";
		// String[] ArrayResult;
		// List<String> RequestList = new ArrayList<String>();
		// RequestList.clear();

		// 向Agent发送PDU，并接收Response
		ResponseEvent respEvnt = snmp.send(pdu, target);
		// Thread.sleep(10); //停0.01s
		// 解析Response
		if (respEvnt != null && respEvnt.getResponse() != null) {
			Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getResponse().getVariableBindings();
			for (int i = 0; i < recVBs.size(); i++) {
				VariableBinding recVB = recVBs.elementAt(i);
				if (recVB.getVariable().equals("noSuchObject") || recVB.getVariable() == null) {
					strResult = "OID Error";
					System.out.println("no Result. pdu:" + pdu);
				} else {
					//System.out.println(recVBs+"; "+recVB);
					System.out.println(recVB);
			//		strResult = PackageOnlyPDURequest(recVB, strType, myselfID, ParentID);
					// RequestList.add(strResult);
				}
				// System.out.println(recVB);
				// System.out.println(recVB.getOid() + " : " +
				// recVB.getVariable());

			}
		} else if (respEvnt == null || respEvnt.getResponse() == null) {
			strResult = "Chassis Off";
			System.out.println("机箱没回复，确定为机箱离线！");
		} else {
			strResult = "Error";
			System.out.println("TimeOut...,no Result." + "Type:" + strType + " pdu:" + pdu);
		}
		return strResult;
		// if(RequestList.size()>0){
		// ArrayResult= new String[RequestList.size()];
		// RequestList.toArray(ArrayResult);
		// return ArrayResult;
		// }else
		// return null;
	}

	
	private static void snmpWalk(Snmp snmp, CommunityTarget target) {  
		TableUtils utils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));//GETNEXT or GETBULK  
		utils.setMaxNumRowsPerPDU(10);   //only for GETBULK, set max-repetitions, default is 10  
		OID[] columnOids = new OID[] {  
	//	new OID("1.3.6.1.2.1.1.9.1.2"), //sysORID  
	//	new OID("1.3.6.1.2.1.1.9.1.3"), //sysORDescr  
	//	new OID("1.3.6.1.2.1.1.9.1.5")  //wrong OID, expect an null in in VariableBinding array  
		new OID("1.3.6.1.4.1.26381")
	//	new OID(".1.3.6.1.4.1.6688.1.1.1.4.2.23.1.1.1")
		};  
		// If not null, all returned rows have an index in a range (lowerBoundIndex, upperBoundIndex]  
		List<TableEvent> l = utils.getTable(target, columnOids, new OID("1"), new OID("100"));  
		for (TableEvent e : l) {  
		    System.out.println(e);  
		    //测试KA电箱的解析过程
		    VariableBinding[] Values = e.getColumns();
		    String strOidIndex = e.getIndex().toString();
			int nStart = 0;
			nStart = strOidIndex.indexOf(".");
			String strResult = strOidIndex.substring(nStart + 1);
			 System.out.println(strResult+": "+Values[0].getVariable().toString()); 					   	    
		    
		}  
	}  
	
	private static void snmpWalk1(Snmp snmp, CommunityTarget target,String strValue,int OidNum) {  
		TableUtils utils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));//GETNEXT or GETBULK  
		utils.setMaxNumRowsPerPDU(10);   //only for GETBULK, set max-repetitions, default is 10  
//		int nResult=0;
//		String strOID[]=new String[OidNum];
//		nResult=ParseOID(strOID,strValue);
//		
//		OID[] columnOids = new OID[nResult];
//		for(int i=0;i<nResult;i++){
//		//	pdu.add(new VariableBinding(new OID(strOID[i])));
//			OID[i]=strOID[i];
//		}
		OID[] columnOids = new OID[] {  
	//	new OID("1.3.6.1.2.1.1.9.1.2"), //sysORID  
	//	new OID("1.3.6.1.2.1.1.9.1.3"), //sysORDescr  
	//	new OID("1.3.6.1.2.1.1.9.1.5")  //wrong OID, expect an null in in VariableBinding array  
		new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.1"),
		new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.2"),
		new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.3"),
		new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.4"),
		new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.5"),
		new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.6"),
		new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.7"),
		new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.8"),
		new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.9")
		};  
		// If not null, all returned rows have an index in a range (lowerBoundIndex, upperBoundIndex]  
		List<TableEvent> l = utils.getTable(target, columnOids, new OID("1"), new OID("100"));  
		for (TableEvent e : l) {  
		    System.out.println(e);  
		}  
	}  
	public static void main(String[] args) throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		Snmp snmp = new Snmp(new DefaultUdpTransportMapping());  
		snmp.listen();  
		CommunityTarget target = new CommunityTarget();  
		target.setCommunity(new OctetString("public"));  //设置公共体
		target.setVersion(SnmpConstants.version2c);  
		target.setAddress(new UdpAddress("10.82.17.105/161"));  
		target.setTimeout(3000);    //3s  //超时时间
		target.setRetries(1);      //通信不成功时的重试次数
		
		String strAllOID="1.3.6.1.4.1.26381.4.1.0;";
   //     sendRequest(snmp, createGetPdu(strAllOID,1), target);  
   //     SendPDUAndRequest(snmp,createGetPdu(strAllOID,1),target, strAllOID);
	//	sendRequest(snmp, createGetNextPdu(), target);  
	//	sendRequest(snmp, createGetBulkPdu(), target);  
		snmpWalk(snmp, target);  

      //  target.setCommunity(new OctetString("private"));  
	//	sendRequest(snmp, createSetPdu(), target);  
//		String ShelfNumFromChassis="1.3.6.1.4.1.6688.1.1.1.1.0"; //机架总数
//		String ChassisIP="1.3.6.1.4.1.6688.1.1.1.4.1.1.2.0"; 
//		String ChassisMask="1.3.6.1.4.1.6688.1.1.1.4.1.1.3.0";	
//		String ChassisGateway="1.3.6.1.4.1.6688.1.1.1.4.1.1.4.0";	
//		String strAllOID=ShelfNumFromChassis+";"+ChassisIP+";"+ChassisMask+";"+ChassisGateway+";";
//		CommunityTarget broadcastTarget = new CommunityTarget();  
//		broadcastTarget.setCommunity(new OctetString("public"));  
//		broadcastTarget.setVersion(SnmpConstants.version2c);  
//		broadcastTarget.setAddress(new UdpAddress("10.82.17.87/161"));  
//		broadcastTarget.setTimeout(5000);   //5s  
	//	sendAsyncRequest(snmp, createGetPdu(strAllOID), broadcastTarget);  
		
	//	SendPDUAndRequest(snmp, createGetPdu(strAllOID,4), broadcastTarget, strAllOID);
		
//		String shelfName=".1.3.6.1.4.1.6688.1.1.1.2.1.1";
//		String psuA=".1.3.6.1.4.1.6688.1.1.1.2.1.2";
//		String psuB=".1.3.6.1.4.1.6688.1.1.1.2.1.3";
//		String volA=".1.3.6.1.4.1.6688.1.1.1.2.1.4";
//		String volB=".1.3.6.1.4.1.6688.1.1.1.2.1.5";
//		String fan=".1.3.6.1.4.1.6688.1.1.1.2.1.6";
//		String temperature=".1.3.6.1.4.1.6688.1.1.1.2.1.7";
//		String cocardNum=".1.3.6.1.4.1.6688.1.1.1.2.1.8";
//		String rmtcardNum=".1.3.6.1.4.1.6688.1.1.1.2.1.9";
//		String strShelf=shelfName+";"+psuA+";"+psuB+";"+volA+";"+volB+";"+
//				fan+";"+temperature+";"+cocardNum+";"+rmtcardNum+";";
		
//		snmpWalk1(snmp, broadcastTarget,strShelf,9);  
//		SendPDUAndRequest(snmp, createGetPdu(strShelf,9), broadcastTarget, strAllOID);
//		Thread.sleep(6000); //main thread wait 6s for the completion of asynchronous request   
	}

}
