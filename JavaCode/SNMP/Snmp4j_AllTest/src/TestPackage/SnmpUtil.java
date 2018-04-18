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

public class SnmpUtil {
	/*
	 * 通过Snmp读取机器名称的例子
	 * 这里注意：对于Agent方，这里需要安装SNMP，且要配置社区名称和接收IP,
	 * "社区名称"对应下面“13Test”、接收IP是管理方(发送)的IP，以便Agent能够接收.
	 * 对于该例子，管理方可以不安装SNMP和配置SNMP
	 */
	
	private Snmp snmp = null;
    private Address targetAddress = null;
   public void initComm() throws IOException {
      // 设置Agent方的IP和端口
             targetAddress = GenericAddress.parse("udp:10.82.17.13/161");
             TransportMapping transport = new DefaultUdpTransportMapping();
             snmp = new Snmp(transport);
             transport.listen();
      }
      public void sendPDU() throws IOException {
             // 设置 target
             CommunityTarget target = new CommunityTarget();
             target.setCommunity(new OctetString("17.15Test")); //public
             target.setAddress(targetAddress);
             // 通信不成功时的重试次数
             target.setRetries(2);
             // 超时时间
             target.setTimeout(1500);
             target.setVersion(SnmpConstants.version1);
             // 创建 PDU
             PDU pdu = new PDU(); //.1.3.6.1.4.1.6688.1.1.1.4.2.1.1.2
             pdu.add(new VariableBinding(new OID(new int[] { 1, 3, 6, 1, 2, 1, 1, 5, 0 })));//原始内容1, 3, 6, 1, 2, 1, 1, 5, 0
             // MIB的访问方式
             pdu.setType(PDU.GET);
             // 向Agent发送PDU，并接收Response
             ResponseEvent respEvnt = snmp.send(pdu, target);
             // 解析Response
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
            SnmpUtil util = new SnmpUtil();
            util.initComm();
            util.sendPDU();

            } catch (IOException e) {
                    e.printStackTrace();
            }
	}
}
