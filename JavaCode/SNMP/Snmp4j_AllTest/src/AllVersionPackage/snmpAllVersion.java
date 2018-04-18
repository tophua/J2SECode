package AllVersionPackage;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
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
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableUtils;


public class snmpAllVersion {
	
	private int version; //主要支持version1、version2c和version3
	private String targetAddress;
	public Snmp snmp = null;
	private USM usm=null;
	private UsmUser usmUser=null;
	public Target target = null;
	private TableUtils utils = null;
	
	private static String securityName="MD5Admin";
	private static String AuthKey="nmsAuthKey";
	private static String PrivKey="nmsPrivKey";
	
	private static String V1_Community="public";
	private static String V2_Community="public";
	public static String Timeout="2000";
	public static String Retries="5";  
	public static String maxrepetitions="10";
	
	public void setVersion(int version){
		this.version=version;
	}
	
	public void settargetAddress(String IP){
		this.targetAddress=IP+"/161";
	}
		
	public void initSnmp(){
		//创建snmp对象
		try {
			snmp= new Snmp(new DefaultUdpTransportMapping());
			
			if(version==SnmpConstants.version1 ||
				version==SnmpConstants.version2c){
				//
			}else if(version==SnmpConstants.version3){
				//设置安全模式
				initUsm();
				//添加用户
				addUser();
			}
			//监听消息
			snmp.listen();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void CreateTarget(){
		
		if(version==SnmpConstants.version3){
			target = new UserTarget();
			 // 设置安全级别
			((UserTarget) target).setSecurityLevel(SecurityLevel.AUTH_PRIV);
			((UserTarget) target).setSecurityName(new OctetString(securityName));
			target.setVersion(SnmpConstants.version3);
		}else{
			target = new CommunityTarget();
			if(version==SnmpConstants.version1){
				((CommunityTarget) target).setCommunity(new OctetString(V1_Community));// 设置公共体 
				target.setVersion(SnmpConstants.version1);
			}else if(version==SnmpConstants.version2c){
				((CommunityTarget) target).setCommunity(new OctetString(V2_Community)); 
				target.setVersion(SnmpConstants.version2c);
			}	
		}
		// 目标对象相关设置
		target.setAddress(new UdpAddress(targetAddress));
		target.setRetries(Integer.parseInt(Retries));  //通信不成功时的重试次数
		target.setTimeout(Integer.parseInt(Timeout));  //超时时间
	}
	
	public void initUsm(){
		usm=new USM(SecurityProtocols.getInstance(),
				 new OctetString(MPv3.createLocalEngineID()),
				 0);
		SecurityModels.getInstance().addSecurityModel(usm);
	}
	
	 public void addUser(){
		 //添加用户
		 usmUser =new UsmUser(new OctetString(securityName),
				 AuthMD5.ID,
				 new OctetString(AuthKey),
				 PrivDES.ID,
				 new OctetString(PrivKey));
		 snmp.getUSM().addUser(new OctetString(securityName), usmUser);  
	 }
	
	 public void CreateUtils(){
		//为GETNEXT or GETBULK操作需要
		 utils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK)); 
		 utils.setMaxNumRowsPerPDU(Integer.parseInt(maxrepetitions)); 
	 }
	 
	 
}
