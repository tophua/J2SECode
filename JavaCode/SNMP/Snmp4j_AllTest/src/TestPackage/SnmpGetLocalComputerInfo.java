package TestPackage;

import java.io.IOException;

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

public class SnmpGetLocalComputerInfo {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
      try {
    	     //�趨CommunityTarget   
    	     CommunityTarget myTarget = new CommunityTarget();
    	     //����Զ�������ĵ�ַ  
    	     //Address deviceAdd = GenericAddress.parse("udp:192.168.1.233/161"); 
    	     //���屾���ĵ�ַ   
    	     Address localAdd = GenericAddress.parse("udp:localhost/161");
    	    //�趨Զ�������ĵ�ַ 
    	    //myTarget.setAddress(deviceAdd); 
    	    //�趨���������ĵ�ַ
    	     myTarget.setAddress(localAdd);
    	    //����snmp��ͬ��   
    	     myTarget.setCommunity(new OctetString("public")); 
    	    //���ó�ʱ���Դ���   
    	     myTarget.setRetries(2); 
    	    //���ó�ʱ��ʱ��  
    	    myTarget.setTimeout(5*60);
    	    //����ʹ�õ�snmp�汾 
    	    myTarget.setVersion(SnmpConstants.version2c);  
    	    //�趨��ȡ��Э��   
    	    TransportMapping transport = new DefaultUdpTransportMapping();//�趨����Э��ΪUDP
    	    //����TransportMapping�е�listen()�����������������̣�������Ϣ�����ڸü����������ػ����̣����Ӧ����close()�������ͷŸý���
    	    transport.listen();  
    	    //����SNMP�������ڷ�������PDU
    	    Snmp protocol = new Snmp(transport);  
    	    //��������pdu,��ȡmib   
    	    PDU request = new PDU(); 
    	    //���õ�add������Ҫ��ѯ��OID
    	    request.add(new VariableBinding(new OID("1.3.6.1.2.1.1.1")));  
    	    request.add(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,2})));
    	    //����setType()������ȷ����pdu������
    	    request.setType(PDU.GETNEXT);
    	    //���� send(PDU pdu,Target target)����pdu������һ��ResponseEvent����
    	    ResponseEvent responseEvent = protocol.send(request, myTarget);  
    	    //ͨ��ResponseEvent���������SNMP�����Ӧ��pdu��������public PDU getResponse()
    	    PDU response=responseEvent.getResponse();  
    	    //���   
    	   if(response != null){  
    	       System.out.println("request.size()="+request.size());  
    	       System.out.println("response.size()="+response.size());
    	       //ͨ��Ӧ��pdu���mib��Ϣ��֮ǰ�󶨵�OID��ֵ����������VaribleBinding get(int index)
    	       VariableBinding vb1 = response.get(0);  
    	       VariableBinding vb2 = response.get(1);  
    	       System.out.println(vb1);  
    	       System.out.println(vb2);
    	       //����close()�����ͷŸý���
    	       transport.close();  
    	      /**
    	       * ��������
    	       * request.size()=2
    	         response.size()=2
    	         1.3.6.1.2.1.1.1.0 = Hardware: x86 Family 6 Model 58 Stepping 9 AT/AT COMPATIBLE - Software: Windows 2000 Version 5.1 (Build 2600 Multiprocessor Free)
    	         1.3.6.1.2.1.1.2.0 = 1.3.6.1.4.1.311.1.1.3.1.1
    	       */
    	   }
	   } catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}

}
