package TestPackage;

import java.io.IOException;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;
public class SnmpTrapRecv {
	
	private Snmp snmp = null;
    private Address targetAddress = null;
	private TransportMapping transport = null;
	
    public void initComm() throws IOException {   	 
        // ����Agent����IP�Ͷ˿�
        targetAddress = GenericAddress.parse("udp:10.82.17.13/161");
        // ���ý���trap��IP�Ͷ˿�
        transport = new DefaultUdpTransportMapping(new UdpAddress("10.82.17.15/162"));
        snmp = new Snmp(transport);
        //CommandResponder�����ڽ���trap,��дprocessPdu()�����������յ�trap��
        //�Զ�����÷����������Ƕ�trap���д���
        CommandResponder trapRec = new CommandResponder() {
            public synchronized void processPdu(CommandResponderEvent e) {
                      // ����trap
                      PDU command = e.getPDU();
                      if (command != null) {
                             System.out.println(command.toString());
                      }
               }
        };
        snmp.addCommandResponder(trapRec);
        transport.listen();
 }
	
    public synchronized void listen() {
        System.out.println("Waiting for traps..");
        try {
               this.wait();//Wait for traps to come in
            } catch (InterruptedException ex) {
               System.out.println("Interrupted while waiting for traps: " + ex);
               System.exit(-1);
            }
    }	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub  
        try {
        	SnmpTrapRecv util = new SnmpTrapRecv();
            util.initComm();
            util.listen();
            } catch (IOException e) {
                  e.printStackTrace();
            }
	}
}
