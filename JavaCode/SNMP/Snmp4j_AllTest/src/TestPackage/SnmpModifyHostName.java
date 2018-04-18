package TestPackage;

import java.io.IOException;
import java.util.Vector;

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

public class SnmpModifyHostName {
	/*
	 * ͨ��Snmp�޸Ķ�ȡ�������Ƶ�����
	 * ����ע�⣺����Agent����������Ҫ��װSNMP����Ҫ�����������ƺͽ���IP,
	 * "��������"��Ӧ���桰13Test��������Ҫ����дȨ�ޡ�����IP�ǹ�����(����)��IP���Ա�Agent�ܹ�����.
	 * ���ڸ����ӣ����������Բ���װSNMP������SNMP
	 */
	  private Snmp snmp = null;
      private Address targetAddress = null;
      public void initComm() throws IOException {
         // ����Agent����IP�Ͷ˿�
         targetAddress = GenericAddress.parse("udp:10.82.17.13/161");
         TransportMapping transport = new DefaultUdpTransportMapping();
         snmp = new Snmp(transport);
         transport.listen();
      }

      public ResponseEvent sendPDU(PDU pdu) throws IOException {
             // ���� target
             CommunityTarget target = new CommunityTarget();
             target.setCommunity(new OctetString("17.15Test"));
             target.setAddress(targetAddress);
             // ͨ�Ų��ɹ�ʱ�����Դ���
             target.setRetries(2);
             // ��ʱʱ��
             target.setTimeout(1500);
             target.setVersion(SnmpConstants.version1);
             // ��Agent����PDU��������Response
             return snmp.send(pdu, target);
      }
  
      public void setPDU() throws IOException {
             // set PDU
             PDU pdu = new PDU();
             pdu.add(new VariableBinding(new OID(new int[] { 1, 3, 6, 1, 2, 1, 1, 5, 0 }), new OctetString("RD-082-PC")));
             pdu.setType(PDU.SET);
             sendPDU(pdu);
      }
    
      public void getPDU() throws IOException {
             // get PDU
             PDU pdu = new PDU();
             pdu.add(new VariableBinding(new OID(new int[] { 1, 3, 6, 1, 2, 1, 1, 5, 0 })));
             pdu.setType(PDU.GET);
             readResponse(sendPDU(pdu));
      }
      
      public void readResponse(ResponseEvent respEvnt) {
          // ����Response
          if (respEvnt != null && respEvnt.getResponse() != null) {
                 Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getResponse()
                               .getVariableBindings();
                 for (int i = 0; i < recVBs.size(); i++) {
                        VariableBinding recVB = recVBs.elementAt(i);
                        System.out.println(recVB.getOid() + " : " + recVB.getVariable());
                 }
           }
     }
      
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	     try {
	    	 SnmpModifyHostName util1 = new SnmpModifyHostName();
	             util1.initComm();
	             util1.setPDU(); //����ʱ�����лظ���Ϣ��ֱ�ӷ��͹�ȥ����
	             util1.getPDU();  //���»�ȡ���ȷ���Ȼ������Ϣ
              } catch (IOException e) {
                   e.printStackTrace();
              }
	}

}