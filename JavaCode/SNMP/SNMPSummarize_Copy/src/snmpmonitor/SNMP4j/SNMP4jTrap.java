package snmpmonitor.SNMP4j;

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

import snmpmonitor.Common.ISiteviewApi;
import snmpmonitor.MonitorObject.OpticalObject;

public class SNMP4jTrap implements CommandResponder{

	ISiteviewApi api;
	private MultiThreadedMessageDispatcher dispatcher;
	private Snmp snmp = null;
	private Address listenAddress;
	private ThreadPool threadPool;

	public SNMP4jTrap() {

	}
	@Override
	public void processPdu(CommandResponderEvent event) {
		// TODO Auto-generated method stub
		if (event == null || event.getPDU() == null) {
		    System.out.println("[Warn] ResponderEvent or PDU is null");
		    return;
	    }
		OpticalObject.ParseTrapOperator(api,event);
		
//		if(SNMPTimedZXMonitor.ZXScheduler==0){  //防止trap和调度冲突
//			if (event == null || event.getPDU() == null) {
//			    System.out.println("[Warn] ResponderEvent or PDU is null");
//			    return;
//		    }
//			OpticalObject.ParseTrapOperator(api,event);
//		}else
//			 System.out.println("Scheduler is run, stop recv trapinfo!");
	}
	
	public void init(ISiteviewApi Api){
		this.api=Api;
		
		String Address=String.format("udp:%s/162", GetLocalIP());
		threadPool = ThreadPool.create("TrapPool", 2);
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
		      USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(
				        MPv3.createLocalEngineID()), 0);
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
	
	public void TrapRun(ISiteviewApi API) {
		try {
			init(API);
			snmp.addCommandResponder(this);
			System.out.println("----开始监听端口162，等待Trap message----");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
