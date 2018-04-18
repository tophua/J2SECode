package snmpmonitor.Common;

public class ComInfo {

	public static String Community1="public";
	public static String Community2="private";
	public static String IPAddressAndPort="10.82.17.87/161";
	public static String Port1="161";
	public static String Port2="162";
	public static String Timeout="3000";
	public static String Retries="1";  
	public static String maxrepetitions="10"; ////only for GETBULK, set max-repetitions, default is 10
	public static String TableEventStart="1";
	public static String TableEventEnd="100";
	
	
	public static String SearchSate1="已添加";
	public static String SearchSate2="未添加";
	public static String SearchSate3="不存在";
	
	//前台操作类型
	public static String SearchType="Search";
	public static String AddType="Add";
	//Return Value
	public static String OIDError="OIDError";
	public static String ChassisOff="IPError";
	public static String UnKnownError="UnknownError";
	public enum InputType{
		CHASSIS,
		SHELF,
		SLOT,
		LOCALCARD,
		REMOTECARD
	}
	
	///////振兴光端机OID
	//品牌OID
	public static String Enterprise="1.3.6.1.4.1.6688.1.1.2"; //振兴
	public static String ShelfType="1.3.6.1.4.1.6688.1.1.1.3.1.1.1";
	public static String SlotType="1.3.6.1.4.1.6688.1.1.1.3.1.1.2";
	//机箱的OID
	public static String ShelfNumFromChassis="1.3.6.1.4.1.6688.1.1.1.1.0"; //机架总数
	public static String ChassisIP="1.3.6.1.4.1.6688.1.1.1.4.1.1.2.0"; 
	public static String ChassisMask="1.3.6.1.4.1.6688.1.1.1.4.1.1.3.0";	
	public static String ChassisGateway="1.3.6.1.4.1.6688.1.1.1.4.1.1.4.0";	
	
	public static String ChassisInfo=".1.3.6.1.4.1.6688.1.1.1.4.1.1";	
	//机架的OID
	public static String ShelfInfo=".1.3.6.1.4.1.6688.1.1.1.2.1";
	//卡槽的OID
	public static String SlotInfo=".1.3.6.1.4.1.6688.1.1.1.3.1.1";	
	//Card的OID 
	public static String CardInfo=".1.3.6.1.4.1.6688.1.1.1.4.2.1.1";
	public static String CardStateInfo=".1.3.6.1.4.1.6688.1.1.1.4.2.23.1.1.1";
	
}
