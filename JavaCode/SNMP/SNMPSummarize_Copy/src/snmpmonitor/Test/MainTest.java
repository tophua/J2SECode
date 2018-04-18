package snmpmonitor.Test;

import snmpmonitor.Common.ISiteviewApi;
import snmpmonitor.SNMP4j.SNMP4jTrap;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ISiteviewApi api=new ISiteviewApi();
		SNMP4jTrap trap=new SNMP4jTrap();
		trap.TrapRun(api); //启动后就开启一个trap线程
		
		
	}

}
