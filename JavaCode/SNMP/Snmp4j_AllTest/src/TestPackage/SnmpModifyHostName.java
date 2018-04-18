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
	 * 通过Snmp修改读取机器名称的例子
	 * 这里注意：对于Agent方，这里需要安装SNMP，且要配置社区名称和接收IP,
	 * "社区名称"对应下面“13Test”并且需要增加写权限、接收IP是管理方(发送)的IP，以便Agent能够接收.
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

      public ResponseEvent sendPDU(PDU pdu) throws IOException {
             // 设置 target
             CommunityTarget target = new CommunityTarget();
             target.setCommunity(new OctetString("17.15Test"));
             target.setAddress(targetAddress);
             // 通信不成功时的重试次数
             target.setRetries(2);
             // 超时时间
             target.setTimeout(1500);
             target.setVersion(SnmpConstants.version1);
             // 向Agent发送PDU，并返回Response
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
	    	 SnmpModifyHostName util1 = new SnmpModifyHostName();
	             util1.initComm();
	             util1.setPDU(); //设置时不会有回复信息，直接发送过去就行
	             util1.getPDU();  //重新获取，先发送然后获得信息
              } catch (IOException e) {
                   e.printStackTrace();
              }
	}

}
