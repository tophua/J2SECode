package TestPackage;

import java.io.IOException;
import java.util.Vector;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

public class SnmpTrapMultiThreadRecv implements CommandResponder {
	private MultiThreadedMessageDispatcher dispatcher;
	private Snmp snmp = null;
	private Address listenAddress;
	private ThreadPool threadPool;

	
	@Override
	public void processPdu(CommandResponderEvent event) {
		// TODO Auto-generated method stub
		System.out.println("----�յ�Trap��Ϣ�� ��ʼ����ResponderEvent----");
		if (event == null || event.getPDU() == null) {
			System.out.println("[Warn] ResponderEvent or PDU is null");
			return;
		}
		System.out.println(event.getSource()+"; "+event.getPduHandle()+"; "+event.getMessageDispatcher());
		System.out.println(event);
		
		Vector<? extends VariableBinding> vbVect = event.getPDU().getVariableBindings();
		for (VariableBinding vb : vbVect) {
		//	System.out.println(vb);
			System.out.println(vb.getOid() + " = " + vb.getVariable());
		}
		System.out.println("----����ResponderEvent �������� ----");

	}
	
	private void init(){
		threadPool = ThreadPool.create("TrapPool", 2);
		dispatcher = new MultiThreadedMessageDispatcher(threadPool,
				new MessageDispatcherImpl());
		listenAddress = GenericAddress.parse(System.getProperty(
				"snmp4j.listenAddress", "udp:10.82.17.15/162"));
		TransportMapping transport;
		
		try {
			  if (listenAddress instanceof UdpAddress) {
			      transport = new DefaultUdpTransportMapping((UdpAddress) listenAddress);
		        } else {
			      transport = new DefaultTcpTransportMapping((TcpAddress) listenAddress);
		       }
		      snmp = new Snmp(dispatcher, transport);
		      snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
		      snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
		      snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());
		      USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(
				        MPv3.createLocalEngineID()), 0);
		      SecurityModels.getInstance().addSecurityModel(usm);
		      snmp.listen();			
	    	} catch (IOException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
		    }
	}
	
	public void run() {
		System.out.println("----Trap Receiver run ...----");
		try {
			init();
			snmp.addCommandResponder(this);
			System.out.println("----��ʼ�����˿ڣ��ȴ�Trap message----");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SnmpTrapMultiThreadRecv trapReceiver = new SnmpTrapMultiThreadRecv();
		trapReceiver.run();

	}

}
