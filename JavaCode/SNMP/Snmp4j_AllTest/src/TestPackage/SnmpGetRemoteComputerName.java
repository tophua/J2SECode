package TestPackage;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpGetRemoteComputerName {

	private Snmp snmp = null;
	private int version ;
	
	public SnmpGetRemoteComputerName(int version) {
		 try {
		        this.version = version;
		        TransportMapping transport = new DefaultUdpTransportMapping();
		        snmp = new Snmp(transport);
		        if (version == SnmpConstants.version3) {
		            // ���ð�ȫģʽ
		             USM usm = new USM(SecurityProtocols.getInstance(),new OctetString(MPv3.createLocalEngineID()), 0);
		             SecurityModels.getInstance().addSecurityModel(usm);
		         }
		        // ��ʼ������Ϣ
		        transport.listen();
		     } catch (IOException e) {
		           e.printStackTrace();
		     }
	}
	
	public void sendMessage(Boolean syn, final Boolean bro, PDU pdu, String addr)throws IOException {
	    // ����Ŀ���ַ����
	    Address targetAddress = GenericAddress.parse(addr);
	    Target target = null;
	    if (version == SnmpConstants.version3) {
	        // ����û�
	        snmp.getUSM().addUser(new OctetString("MD5DES"),new UsmUser(new OctetString("MD5DES"), AuthMD5.ID,new OctetString("MD5DESUserAuthPassword"),PrivDES.ID, new OctetString("MD5DESUserPrivPassword")));
	        target = new UserTarget();
	        // ���ð�ȫ����
	        ((UserTarget) target).setSecurityLevel(SecurityLevel.AUTH_PRIV);
	        ((UserTarget) target).setSecurityName(new OctetString("MD5DES"));
	        target.setVersion(SnmpConstants.version3);
	     } else {
	        target = new CommunityTarget();
	        if (version == SnmpConstants.version1) {
	             target.setVersion(SnmpConstants.version1);
	             ((CommunityTarget) target).setCommunity(new OctetString("public"));
	          } else {
	             target.setVersion(SnmpConstants.version2c);
	             ((CommunityTarget) target).setCommunity(new OctetString("public")); //���޸ĳ�public
	          }	 
	     }
	     // Ŀ������������
	     target.setAddress(targetAddress);
	     target.setRetries(5);
	     target.setTimeout(1000);
	 
	     if (!syn) {
	        // ���ͱ��� ���ҽ�����Ӧ
	       ResponseEvent response = snmp.send(pdu, target);
	        // ������Ӧ
	       System.out.println("Synchronize(ͬ��) message(��Ϣ) from(����) "
	            + response.getPeerAddress() + "\r\n"+"request(���͵�����):"
	            + response.getRequest() + "\r\n"+"response(���ص���Ӧ):"
	            + response.getResponse());
	       /**
	        * ��������
	        * Synchronize(ͬ��) message(��Ϣ) from(����) 192.168.1.233/161
	          request(���͵�����):GET[requestID=632977521, errorStatus=Success(0), errorIndex=0, VBS[1.3.6.1.2.1.1.5.0 = Null]]
	          response(���ص���Ӧ):RESPONSE[requestID=632977521, errorStatus=Success(0), errorIndex=0, VBS[1.3.6.1.2.1.1.5.0 = WIN-667H6TS3U37]]
	        */
	         } else{
	          // ���ü�������
	          ResponseListener listener = new ResponseListener() {
	            public void onResponse(ResponseEvent event) {
	                if (bro.equals(false)) {
	                        ((Snmp) event.getSource()).cancel(event.getRequest(),this);
	                   }
	               // ������Ӧ
	               PDU request = event.getRequest();
	               PDU response = event.getResponse();
	               System.out.println("Asynchronise(�첽) message(��Ϣ) from(����) "
	                     + event.getPeerAddress() + "\r\n"+"request(���͵�����):" + request
	                     + "\r\n"+"response(���ص���Ӧ):" + response);
	             }	 
	          };
	        // ���ͱ���
	         snmp.send(pdu, target, null, listener);
	       }
	    }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //Snmp�������汾��
        //int ver3 = SnmpConstants.version3;
          int ver2c = SnmpConstants.version2c;
        //int ver1 = SnmpConstants.version1;
          SnmpGetRemoteComputerName manager = new SnmpGetRemoteComputerName(ver2c);
        // ���챨��
          PDU pdu = new PDU();
        //PDU pdu = new ScopedPDU();
        // ����Ҫ��ȡ�Ķ���ID�����OID����Զ�̼����������
          OID oids = new OID(".1.3.6.1.4.1.6688.1.1.1.1.0"); //1, 3, 6, 1, 2, 1, 1, 5, 0
          pdu.add(new VariableBinding(oids));
  
        // ���ñ�������
          pdu.setType(PDU.GET);
        //((ScopedPDU) pdu).setContextName(new OctetString("priv"));
        try {
              // ������Ϣ �������һ������Ҫ���͵�Ŀ���ַ
              // manager.sendMessage(false, true, pdu, "udp:192.168.1.229/161");//192.168.1.229 Linux������
                 manager.sendMessage(false, true, pdu, "udp:10.82.17.87/161");//192.168.1.233 WinServer2008������
            } catch (IOException e) {
            e.printStackTrace();
          }
	 }
}
