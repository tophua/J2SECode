package AllVersionPackage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;


public class snmpTrapOperator implements CommandResponder{
	
	private MultiThreadedMessageDispatcher dispatcher;
	private Snmp snmp = null;
	private Address listenAddress;
	private ThreadPool threadPool;
    private static int threadPoolNum=5;
	
    private processTrapEvent trapEventHandle=null;
    
    public snmpTrapOperator(){
    	//������trap�̼߳���ʱ���Ὺ��һ������trap�¼����߳�
    	trapEventHandle=new processTrapEvent();
    	new Thread(trapEventHandle).start();
    }
    
	public void startTrapRun(){
		initTrap();
		snmp.addCommandResponder(this);
		System.out.println("----��ʼ�����˿�162���ȴ�Trap message----");
	}
	
	@Override
	public void processPdu(CommandResponderEvent event) {
		// TODO Auto-generated method stub
		//���ǽ���trap��Ϣ
		if (event == null || event.getPDU() == null) {
		    System.out.println("[Warn] ResponderEvent or PDU is null");
		    return;
	    }
		//�����յ�trap��Ϣ������������д���
		processTrapEvent(event);
	}

	public void initTrap(){
		
		String Address=String.format("udp:%s/162", GetLocalIP());
		threadPool = ThreadPool.create("TrapPool", threadPoolNum);
		dispatcher = new MultiThreadedMessageDispatcher(threadPool,
				new MessageDispatcherImpl());
		listenAddress = GenericAddress.parse(System.getProperty(
				"snmp4j.listenAddress", Address));   //"udp:10.82.17.15/162"
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
		      USM usm = new USM(SecurityProtocols.getInstance(),
		    		  new OctetString(MPv3.createLocalEngineID()),
		    		  0);
		      SecurityModels.getInstance().addSecurityModel(usm);
		      snmp.listen();			
	    	} catch (IOException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
		    }
	}
	
	public String GetLocalIP(){
		String hostAddress="";
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
			hostAddress = address.getHostAddress();  
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return hostAddress;
	}
	
	public void processTrapEvent(CommandResponderEvent event){
		//�������ר�ſ����첽�߳̽��д���
		//����˼·�ǣ����trap�߳�ר�Ų������ݲ�����һ���߳���ר�Ŵ�������
		trapEventHandle.InsertEventToList(event);
	}
}
