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

public class SNMPV1andV2Test_原始版本 {
	
	private static PDU createGetPdu(){
		PDU pdu = new PDU();  
		pdu.setType(PDU.GET);  
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"))); //sysUpTime  
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0"))); //sysName  
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5")));   //expect an no_such_instance error 
		pdu.add(new VariableBinding(new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.2")));
		return pdu;  
	}
	 private static PDU createGetNextPdu() {  
		PDU pdu = new PDU();  
		pdu.setType(PDU.GETNEXT);  
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3")));   //sysUpTime  
	//	pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5")));   //sysName  
		pdu.add(new VariableBinding(new OID(".1.3.6.1.4.1.6688.1.1.1.2.1.2")));
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
	 
	private static void snmpWalk(Snmp snmp, CommunityTarget target) {  
		TableUtils utils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));//GETNEXT or GETBULK  
		utils.setMaxNumRowsPerPDU(5);   //only for GETBULK, set max-repetitions, default is 10  
		OID[] columnOids = new OID[] {  
	//	new OID("1.3.6.1.2.1.1.9.1.2"), //sysORID  
	//	new OID("1.3.6.1.2.1.1.9.1.3"), //sysORDescr  
	//	new OID("1.3.6.1.2.1.1.9.1.5")  //wrong OID, expect an null in in VariableBinding array  
		new OID(".1.3.6.1.4.1.6688.1.1.1.2")
		};  
		// If not null, all returned rows have an index in a range (lowerBoundIndex, upperBoundIndex]  
		List<TableEvent> l = utils.getTable(target, columnOids, new OID("1"), new OID("2"));  
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
		target.setAddress(new UdpAddress("10.82.17.87/161"));  
		target.setTimeout(3000);    //3s  //超时时间
		target.setRetries(1);      //通信不成功时的重试次数

        sendRequest(snmp, createGetPdu(), target);  
		sendRequest(snmp, createGetNextPdu(), target);  
		sendRequest(snmp, createGetBulkPdu(), target);  
		snmpWalk(snmp, target);  

      //  target.setCommunity(new OctetString("private"));  
	//	sendRequest(snmp, createSetPdu(), target);  
		
		CommunityTarget broadcastTarget = new CommunityTarget();  
		broadcastTarget.setCommunity(new OctetString("public"));  
		broadcastTarget.setVersion(SnmpConstants.version2c);  
		broadcastTarget.setAddress(new UdpAddress("10.82.17.87/161"));  
		broadcastTarget.setTimeout(5000);   //5s  
		sendAsyncRequest(snmp, createGetNextPdu(), broadcastTarget);  
		Thread.sleep(6000); //main thread wait 6s for the completion of asynchronous request   
	}

}
