package snmpmonitor.MonitorObject;

import java.util.List;

import snmpmonitor.Common.CardInfo;
import snmpmonitor.Common.ShelfInfo;
import snmpmonitor.Common.SlotInfo;
import snmpmonitor.Common.SnmkRequest;

public class ProcessTableData {
	public static void GetParseShelfInfo(ShelfInfo Temp,List<SnmkRequest> listTemp){
		for(int i=0;i<listTemp.size();i++){
			String strIndexType=listTemp.get(i).getIndexType();
			String strVbsValue=listTemp.get(i).getVbsValue();
			String strValue=strIndexType.substring(0,strIndexType.indexOf("."));
			switch(strValue){
			  case "1":
				  Temp.setShelfName(strVbsValue);
				  break;
			  case "2":
				  Temp.setShelfpsuA(strVbsValue);
				  break;
			  case "3":
				  Temp.setShelfpsuB(strVbsValue);
				  break;
			  case "4":
				  Temp.setShelfvolA(strVbsValue);
				  break;
			  case "5":
				  Temp.setShelfvolB(strVbsValue);
				  break;
			  case "6":
				  Temp.setShelffan(strVbsValue);
				  break;
			  case "7":
				  Temp.setShelftemperature(strVbsValue);
				  break;
			  case "8":
				  Temp.setShelfcoCardNum(strVbsValue);
				  break;
			  case "9":
				  Temp.setShelfrmtCardNum(strVbsValue);
				  break;
			  default:
				  break;
			}
		}
	}
	
	public static void GetParseSlotInfo(String strType,String strValue,SlotInfo slotTemp){
		
		switch(strType){
		  case "1":
			  slotTemp.setShelfId(strValue);
			  break;
		  case "2":
			  slotTemp.setSlotId(strValue);
			  break;
		  case "3":	
			  slotTemp.setLocalCardType(strValue);
			  break;
		  case "4":
			  break;
		  case "5":
			  slotTemp.setRemoteCardType(strValue);
			  break;
		  case "6":
			  break;
		  default:
			  break;
		}	
	}
	
	public static void GetParseCardInfo(String strType,String strValue,CardInfo LocalcardTemp,
			CardInfo RemotecardTemp){
		switch(strType){
		  case "1":
			  LocalcardTemp.setShelfId(strValue);
			  RemotecardTemp.setShelfId(strValue);
			  break;
		  case "2":
			  LocalcardTemp.setSlotId(strValue);
			  RemotecardTemp.setSlotId(strValue);
			  break;
		  case "3":	
			  LocalcardTemp.setCardType(strValue);
			  break;
		  case "10":
			  LocalcardTemp.setLFPState(strValue);		 
			  break;
		  case "14":
			  LocalcardTemp.setFxlink(strValue);
			  RemotecardTemp.setFxlink(strValue);
			  break;
		  case "23":
			  RemotecardTemp.setCardType(strValue);
			  break;
		  case "27":
			  RemotecardTemp.setLFPState(strValue);
			  break;
		  default:
			  break;
		}
	}
	
	public static void GetParseCardStateInfo(String strType,String strValue,CardInfo LocalcardTemp,
			CardInfo RemotecardTemp){
		switch(strType){
		  case "4":
			  LocalcardTemp.setCurMode1(strValue);
			  break;
		  case "5":
			  LocalcardTemp.setMode1(strValue);
			  break;
		  case "6":	
			  LocalcardTemp.setTxlink1(strValue);	
			  break;
		  case "7":
			  LocalcardTemp.setCurMode2(strValue);		 
			  break;
		  case "8":
			  LocalcardTemp.setMode2(strValue);
			  break;
		  case "9":
			  LocalcardTemp.setTxlink2(strValue);
			  break;
		  case "10":
			  LocalcardTemp.setCurMode3(strValue);		 
			  break;
		  case "11":
			  LocalcardTemp.setMode3(strValue);
			  break;
		  case "12":
			  LocalcardTemp.setTxlink3(strValue);
			  break;
		  case "13":
			  LocalcardTemp.setCurMode4(strValue);		 
			  break;
		  case "14":
			  LocalcardTemp.setMode4(strValue);
			  break;
		  case "15":
			  LocalcardTemp.setTxlink4(strValue);
			  break;
		  case "18":
			  RemotecardTemp.setCurMode1(strValue);
			  break;
		  case "19":
			  RemotecardTemp.setMode1(strValue);
			  break;
		  case "20":	
			  RemotecardTemp.setTxlink1(strValue);	
			  break;
		  case "21":
			  RemotecardTemp.setCurMode2(strValue);		 
			  break;
		  case "22":
			  RemotecardTemp.setMode2(strValue);
			  break;
		  case "23":
			  RemotecardTemp.setTxlink2(strValue);
			  break;
		  case "24":
			  RemotecardTemp.setCurMode3(strValue);		 
			  break;
		  case "25":
			  RemotecardTemp.setMode3(strValue);
			  break;
		  case "26":
			  RemotecardTemp.setTxlink3(strValue);
			  break;
		  case "27":
			  RemotecardTemp.setCurMode4(strValue);		 
			  break;
		  case "28":
			  RemotecardTemp.setMode4(strValue);
			  break;
		  case "29":
			  RemotecardTemp.setTxlink4(strValue);
			  break;
		  default:
			  break;
		}
	}
}
