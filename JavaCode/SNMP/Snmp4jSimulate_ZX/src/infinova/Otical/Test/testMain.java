package infinova.Otical.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import infinova.Optical.SNMP.SNMP4jOperator;
import infinova.Otical.Database.DBOperator;

//import infinova.Otical.SNMP.SNMP4jOperator;

public class testMain {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String str1=".1.3.6.1.4.1.6688.1.1.1.4.1.1";
		SNMP4jOperator Snmp1= new SNMP4jOperator();
		Snmp1.InitSnmp();
		Snmp1.CreateTarget();
		Snmp1.CreateUtils();
		
		DBOperator DBo=new DBOperator();
		DBo.InitConnect();
//		DBo.TestSelectEG1();
				
		//重新查询，前台重新查询的操作,会抛弃之前旧的chassisinfo表，重新赋值，然后在
		//此基础上获取各个机箱下的信息
//		String strsql="CREATE TABLE `TestTable` ( "+
//          "`ShelfId` int(11) DEFAULT NULL,"+
//		  "`SlotId` int(11) DEFAULT NULL,"+
//          "`Level` int(11) DEFAULT NULL,"+
//		  "`LocalCardType` int(11) DEFAULT NULL,"+
//          "`RemoteCardType` int(11) DEFAULT NULL,"+
//		  "`CardSituation` varchar(255) CHARACTER SET gbk DEFAULT NULL,"+
//          "`OnlineState` int(11) DEFAULT NULL,"+
//		  "`UpdateTime` varchar(255) CHARACTER SET gbk DEFAULT NULL"+
//          ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
//		DBo.CreateTable(strsql);
		DBo.DeleteTable("chassisinfo");
		DBo.DeleteTable("ShelfInfo");
		DBo.DeleteTable("SlotInfo");
		DBo.DeleteTable("LocalAndRemoteCardInfo");
		DBo.DeleteTable("Alarminfo");
		Snmp1.GetQueryChassisInfo();
		Snmp1.GetQueryShelfInfo();
//		long lo1=Snmp1.StringIPToLong("10.82.17.13");
//		long lo2=Snmp1.StringIPToLong("10.82.17.67");
//		String str2=Snmp1.LongToStringIP(173150533);
	//	Snmp1.GetQuerySlotInfo();
		DBo.UnitDB();
//		Snmp1.snmpWalk1(str1);
//		Snmp1.sendAsyncRequest(Snmp1.createGetPdu(".1.3.6.1.4.1.6688.1.1.1.1.0"));
//		Snmp1.SendPDUAndRequest(Snmp1.createGetPdu(".1.3.6.1.4.1.6688.1.1.1.1.0"));
	//	Snmp1.snmpWalk1(Snmp1.snmp,Snmp1.target,str1);
	//	Snmp1.snmpWalk2(Snmp1.snmp,Snmp1.target);
		
//		//数据库操作
//		DBOperator DBo=new DBOperator();
//		DBo.InitConnect();
//		DBo.TestSelectEG1();
//		DBo.UnitDB();
	}
}
