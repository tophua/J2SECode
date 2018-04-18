package infinova.Otical.Database;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import infinova.Otical.Alarminfo;
import infinova.Otical.ChassisInfo;
import infinova.Otical.CommonFunction;
import infinova.Otical.LocalAndRemoteCardInfo;
import infinova.Otical.ShelfInfo;
import infinova.Otical.ShelfTypeResult;
import infinova.Otical.SlotInfo;

public class ProcessTableData {
	
	public static ChassisInfo ProcessQueryChassisInfo(ChassisInfo ChasTemp,String strValue) throws IOException{
		
	//   ChassisInfo ChassisTemp=new ChassisInfo();
	   String strTemp="",strType="";
	   int nStart=0,nEnd=0;
	   
	   for(int i=0;i<7;i++){
		   switch(i){
		   case 0:
			   strType="id";
			   break;
		   case 1:
			   strType="pid";
			   break;
		   case 2:
			   strType="level";
			   break;
		   case 3:
			   strType="shelfNum";
			   break;
		   case 4:
			   strType="chassisIP";
			   break;
		   case 5:
			   strType="chassisMask";
			   break;
		   case 6:
			   strType="chassisGateway";
			   break;
		   default:
			   break;
		   }
		   nStart=strValue.indexOf(strType);
		   nEnd=strValue.indexOf(";");
		   strTemp=strValue.substring(nStart+strType.length()+1, nEnd);
		   strValue=strValue.substring(nEnd+1);
		   if(strType.equals("id")){
			   ChasTemp.setId(strTemp);  
		   }else if(strType.equals("pid")){
			   ChasTemp.setPid(strTemp);
		   }else if(strType.equals("level")){
			   ChasTemp.setLevel(strTemp);
		   }else if(strType.equals("shelfNum")){
			   ChasTemp.setshelfNum(strTemp);
		   }else if(strType.equals("chassisIP")){
			   ChasTemp.setchassisIP(strTemp);
		   }else if(strType.equals("chassisMask")){
			   ChasTemp.setchassisMask(strTemp);
		   }else if(strType.equals("chassisGateway")){
			   ChasTemp.setchassisGateway(strTemp);
		   }
	   }
	   ChasTemp.setchassisOnline("1");
	   ChasTemp.setchassisUpTime(CommonFunction.GetCurrentTime());
//	   ChassisTemp.setId(String.valueOf(myselfID));
//	   ChassisTemp.setPid(String.valueOf(ParentID)); //机箱没父节点PID
//	   ChassisTemp.setLevel(String.valueOf(1));
//	   ChassisTemp.setshelfNum(String.valueOf(VarValue.getVariable()));
	   
	   return ChasTemp;
	}

	public static void ProcessAlarmString(Alarminfo Alarm,String strValue) 
		throws IOException{
		   String strTemp="",strType="";
		   int nStart=0,nEnd=0;
		   
		   for(int i=0;i<3;i++){
			   switch(i){
			   case 0:
				   strType="Type";
				   break;
			   case 1:
				   strType="AlarmEvent";
				   break;
			   case 2:
				   strType="AlarmTime";
				   break;
			   default:
				   break;
			   }
			   nStart=strValue.indexOf(strType);
			   nEnd=strValue.indexOf(";");
			   strTemp=strValue.substring(nStart+strType.length()+1, nEnd);
			   strValue=strValue.substring(nEnd+1);
			   if(strType.equals("Type")){
				   Alarm.setstrType(strTemp);
			   }else if(strType.equals("AlarmEvent")){
				   Alarm.setstrAlarmEvent(strTemp);
			   }else if(strType.equals("AlarmTime")){
				   Alarm.setstrAlarmTime(strTemp);
			   }
		   }
//		   ChassisTemp.setId(String.valueOf(myselfID));
//		   ChassisTemp.setPid(String.valueOf(ParentID)); //机箱没父节点PID
//		   ChassisTemp.setLevel(String.valueOf(1));
//		   ChassisTemp.setshelfNum(String.valueOf(VarValue.getVariable()));		   		 
		}
	
	public static void GetQueryShelfInfo(ShelfInfo Temp,List<ShelfTypeResult> listTemp){
		for(int i=0;i<listTemp.size();i++){
			String strIndexType=listTemp.get(i).getIndexType();
			String strVbsValue=listTemp.get(i).getVbsValue();
			String strValue=strIndexType.substring(0,strIndexType.indexOf("."));
			switch(strValue){
			  case "1":
				  Temp.setshelfName(strVbsValue);
				  break;
			  case "2":
				  Temp.setshelfpsuA(strVbsValue);
				  break;
			  case "3":
				  Temp.setshelfpsuB(strVbsValue);
				  break;
			  case "4":
				  Temp.setshelfvolA(strVbsValue);
				  break;
			  case "5":
				  Temp.setshelfvolB(strVbsValue);
				  break;
			  case "6":
				  Temp.setshelffan(strVbsValue);
				  break;
			  case "7":
				  Temp.setshelftemperature(strVbsValue);
				  break;
			  case "8":
				  Temp.setshelfcoCardNum(strVbsValue);
				  break;
			  case "9":
				  Temp.setshelfrmtCardNum(strVbsValue);
				  break;
			  default:
				  break;
			}
			Temp.setshelfIP("");
			Temp.setshelfMask("");
			Temp.setshelfGateway("");
		}
	}
	
	public static void GetQuerySlotInfo(String strType,String strValue,SlotInfo slotTemp){
		
		switch(strType){
		  case "1":
			  slotTemp.setPid(strValue);
			  break;
		  case "2":
			  slotTemp.setId(strValue);
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
	
	public static void GetQueryCardInfo(String strType,String strValue,LocalAndRemoteCardInfo LocalcardTemp,
			LocalAndRemoteCardInfo RemotecardTemp){
		switch(strType){
		  case "1":
			  LocalcardTemp.setSheid(strValue);
			  RemotecardTemp.setSheid(strValue);
			  break;
		  case "2":
			  LocalcardTemp.setPid(strValue);
			  RemotecardTemp.setPid(strValue);
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
	
	public static void GetQueryCardStateInfo(String strType,String strValue,LocalAndRemoteCardInfo LocalcardTemp,
			LocalAndRemoteCardInfo RemotecardTemp){
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
