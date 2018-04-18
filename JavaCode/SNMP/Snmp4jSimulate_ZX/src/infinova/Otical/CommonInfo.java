package infinova.Otical;

public final class CommonInfo {

	public static String Community1="public";
	public static String Community2="private";
	public static String IPAddressAndPort="10.82.17.87/161";
	public static String Timeout="3000";
	public static String Retries="1";  
	public static String maxrepetitions="10"; ////only for GETBULK, set max-repetitions, default is 10
	public static String TableEventStart="1";
	public static String TableEventEnd="100";
	
	//Return Value
	public static String OIDError="OID Error";
	public static String ChassisOff="Chassis Off";
	public static String UnKnownError="Error";
	
	public enum InputType{
		CHASSIS,
		SHELF,
		SLOT,
		LOCALCARD,
		REMOTECARD
	}
	//机箱的OID
	public static String ShelfNumFromChassis="1.3.6.1.4.1.6688.1.1.1.1.0";
	public static String ChassisInfo=".1.3.6.1.4.1.6688.1.1.1.4.1.1";	
	//机架的OID
	public static String ShelfInfo=".1.3.6.1.4.1.6688.1.1.1.2.1";
	//卡槽的OID
	public static String SlotInfo=".1.3.6.1.4.1.6688.1.1.1.3.1.1";	
	//Card的OID 
	public static String CardInfo=".1.3.6.1.4.1.6688.1.1.1.4.2.1.1";
	public static String CardStateInfo=".1.3.6.1.4.1.6688.1.1.1.4.2.23.1.1.1";
}
