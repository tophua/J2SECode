package BasicTestPackage;

import java.io.IOException;

import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.UserTarget;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMPv3Test {

	private int version; //主要支持version1、version2c和version3
	private Snmp snmp = null;
	private USM usm=null;
	private UsmUser usmUser=null;
	private Target target = null;
	 public void initSnmp(int version){
		 try {
			 this.version=version;
			 //创建snmp对象
			snmp= new Snmp(new DefaultUdpTransportMapping());
			if(version==SnmpConstants.version3){ //这是version3版本
				//设置安全模式
				initUsm();
			}
			snmp.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public void initUsm(){
		 usm=new USM(SecurityProtocols.getInstance(),
				 new OctetString(MPv3.createLocalEngineID()),
				 0);
		 SecurityModels.getInstance().addSecurityModel(usm);
	 }
	 
	 public void addUser(){
		 //添加用户
		 usmUser =new UsmUser(new OctetString("nmsAdmin"),
				 AuthMD5.ID,
				 new OctetString("nmsAuthKey"),
				 PrivDES.ID,
				 new OctetString("nmsPrivKey"));
		 snmp.getUSM().addUser(new OctetString("nmsAdmin"), usmUser);  
	 }
	 
	 public void createUserTarget(){
		 target = new UserTarget();
		 target.setVersion(SnmpConstants.version3);  
	     target.setAddress(new UdpAddress("10.82.17.15/161"));  
	     target.setSecurityLevel(SecurityLevel.AUTH_PRIV);  
	     target.setSecurityName(new OctetString("nmsAdmin"));  
	     target.setTimeout(3000);    //3s  
	     target.setRetries(0);  	 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
