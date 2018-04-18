package TestPackage;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TimeTicks;
import org.snmp4j.smi.UnsignedInteger32;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpTrapSenddemo {

	public static final int DEFAULT_VERSION = SnmpConstants.version2c;
	public static final long DEFAULT_TIMEOUT = 3 * 1000L;
	public static final int DEFAULT_RETRY = 3;
 
	private Snmp snmp = null;
	private CommunityTarget target = null;
 
	public void init()  {
		System.out.println("----��ʼ Trap ��IP�Ͷ˿�----");
		target = createTarget4Trap("udp:127.0.0.1/162");
		TransportMapping transport;
		try {
			transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
			transport.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �����������Trap ��Ϣ
	 *
	 * @throws IOException
	 */
	public void sendPDU(){
		PDU pdu = new PDU();
		pdu.add(new VariableBinding(
				new OID(".1.3.6.1.2.1.1.1.0"),
				new OctetString("SNMP Trap Test.see more:http://www.micmiu.com")));
//		pdu.add(new VariableBinding(SnmpConstants.sysUpTime, new TimeTicks(
//				new UnsignedInteger32(System.currentTimeMillis() / 1000)
//						.getValue())));
//		pdu.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID(
//				".1.3.6.1.6.3.1.1.4.3")));
 
		// ��Agent����PDU
		pdu.setType(PDU.TRAP);
		try {
			snmp.send(pdu, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----Trap Send END----");
	}
 

	/**
	 * ��������communityTarget
	 *
	 * @param targetAddress
	 * @param community
	 * @param version
	 * @param timeOut
	 * @param retry
	 * @return CommunityTarget
	 */
	public static CommunityTarget createTarget4Trap(String address) {
		CommunityTarget target = new CommunityTarget();
		target.setAddress(GenericAddress.parse(address));
		target.setVersion(DEFAULT_VERSION);
		target.setTimeout(DEFAULT_TIMEOUT); // milliseconds
		target.setRetries(DEFAULT_RETRY);
		return target;
	}
 

	public static void main(String[] args) throws IOException {
		SnmpTrapSenddemo demo = new SnmpTrapSenddemo();
		demo.init();
		demo.sendPDU();

	}

}
