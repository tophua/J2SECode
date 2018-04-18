package snmpmonitor.MonitorObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.snmp4j.smi.VariableBinding;
import org.snmp4j.util.TableEvent;

import snmpmonitor.Common.CardInfo;
import snmpmonitor.Common.ComInfo;
import snmpmonitor.Common.ProcessTableData;
import snmpmonitor.Common.SlotInfo;
import snmpmonitor.Common.SnmkRequest;
import snmpmonitor.SNMP4j.SNMP4jOperator;

public class ZhenXingOptical {
	
	SNMP4jOperator initOperator=null;
	public static String strIp="";
	public static String strCommunity="";
	public static String strIpAndPort="";
	
	public ZhenXingOptical(){
		initOperator=new SNMP4jOperator();
	}
	public void ZhenXingOpticalParment(String ip,String community){
		strIp=ip;
		strCommunity=community;
		strIpAndPort=ip+"/"+ComInfo.Port1;
	}
	
	public boolean InitZX(){
		boolean bResult=true;
		try {
			initOperator.InitSnmp();
			initOperator.CreateTarget(strCommunity,strIpAndPort);
			initOperator.CreateUtils();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bResult=false;
		}
		return bResult;
	}
	
	public void GetZXChassisInfo(String type,String stroid,int OidNum,Map<String,String> mapTemp){
		String strResult="";
		List<VariableBinding> listTemp =new ArrayList<VariableBinding>();
		strResult=initOperator.SendPDUAndRequest(initOperator.createGetPdu(stroid,OidNum),listTemp);
		///解析
		if(listTemp.size()>0){
			if(type.equals(ComInfo.SearchType)){
				ParseChassisOnlineOIDInfo(listTemp,mapTemp);
			}else if(type.equals(ComInfo.AddType)){
				ParseChassisInfo(listTemp,mapTemp);
			}			
		}else{
			mapTemp.put(strIp, strResult);
		}			
	}

	public String JudgeChassisOnlineOID(){
		String strAllOID=ComInfo.ShelfNumFromChassis+";"+ComInfo.ChassisIP+";";
		
		return strAllOID;
	}
	
	public String GetChassisInfoOID(){
		String strAllOID=ComInfo.ShelfNumFromChassis+";"+ComInfo.ChassisIP+";"+ComInfo.ChassisMask
				+";"+ComInfo.ChassisGateway+";";
		
		return strAllOID;
	}
	public void ParseChassisOnlineOIDInfo(List<VariableBinding> listV,Map<String,String> mapTemp){
		String strIP="",strShelfNum="";
		
		for(int i=0;i<listV.size();i++){
			String strValue=listV.get(i).toString();
			String type=strValue.substring(0,strValue.indexOf("=")).toString().trim();
			
			if(type.equals(ComInfo.ShelfNumFromChassis)){
				strShelfNum=strValue.substring(strValue.indexOf("=")+1).toString().trim(); //注意空格
			}else if(type.equals(ComInfo.ChassisIP)){
				strIP=strValue.substring(strValue.indexOf("=")+1).toString().trim();
			}			  
		}
		mapTemp.put(strIP, strShelfNum);
	}
	
	public void ParseChassisInfo(List<VariableBinding> listV,Map<String,String> mapTemp){
		String strIP="",strShelfNum="",strMask="",strGateWay="";
		
		for(int i=0;i<listV.size();i++){
			String strValue=listV.get(i).toString();
			String type=strValue.substring(0,strValue.indexOf("=")).toString().trim();
			if(type.equals(ComInfo.ShelfNumFromChassis)){
				strShelfNum=strValue.substring(strValue.indexOf("=")+1).toString().trim();
			}else if(type.equals(ComInfo.ChassisIP)){
				strIP=strValue.substring(strValue.indexOf("=")+1).toString().trim();
			}else if(type.equals(ComInfo.ChassisMask)){
				strMask=strValue.substring(strValue.indexOf("=")+1).toString().trim();
			}else if(type.equals(ComInfo.ChassisGateway)){
				strGateWay=strValue.substring(strValue.indexOf("=")+1).toString().trim();
			}			  
		}
		mapTemp.put(strIP, strShelfNum+";"+strMask+";"+strGateWay+";");
	}
		
	public String GetZXShelfInfo(Map<String, List<SnmkRequest>> mapResult){
		String strResult="OK",strShelfNum="";
	//	List<VariableBinding> listTemp =new ArrayList<VariableBinding>();
	//	Map<String, List<SnmkRequest>> mapShelfInfo=new HashMap<String,List<SnmkRequest>>();
		List<TableEvent> listEvent =new ArrayList<TableEvent>();
		initOperator.SNMPWalkToBatch(ComInfo.ShelfInfo,listEvent);
		
		///解析
		if(listEvent!=null){
			for(TableEvent e:listEvent){
				SnmkRequest Result=new SnmkRequest();
				strShelfNum=PackageSnmpWalkRequest(e,"SHELF",Result);
				if (!strShelfNum.equals("")) {
					// Iterator<Map.Entry<String,List<ShelfTypeResult>>>
					// IterChas=mapShelf.entrySet().iterator();
					Object obj = new Object();
					obj = mapResult.get(strShelfNum);
					if (obj != null) {
						List<SnmkRequest> listShelfTemp = (List<SnmkRequest>) obj;
						listShelfTemp.add(Result);
					} else {
						List<SnmkRequest> listShelf = new ArrayList<SnmkRequest>();
						listShelf.add(Result);
						mapResult.put(strShelfNum, listShelf);
					}
				}
			}					
		}else{
			strResult="Error";
		}
		return strResult;
	}
	
	public String GetZXSlotInfo(Map<String, List<String>> mapResult){
		String strResult="OK",strNum="";
	//	List<VariableBinding> listTemp =new ArrayList<VariableBinding>();
		Map<String, List<SnmkRequest>> mapShelfInfo=new HashMap<String,List<SnmkRequest>>();
		List<TableEvent> listEvent =new ArrayList<TableEvent>();
		initOperator.SNMPWalkToBatch(ComInfo.SlotInfo,listEvent);
		
		///解析
		if(listEvent!=null){
			for(TableEvent e:listEvent){
				SnmkRequest Result=new SnmkRequest();
				strNum=PackageSnmpWalkRequest(e,"SLOT",Result);
				if (!strNum.equals("") && Result!=null) {
					String strVbs = Result.getVbsValue();
					String strIndexType = Result.getIndexType().substring(0, Result.getIndexType().indexOf("."));
					Object obj = new Object();
					obj = mapResult.get(strNum);
					if (obj != null) {
						List<String> listTemp = (List<String>) obj;
						listTemp.add(strIndexType + ":" + strVbs); //类型：值
					} else {
						List<String> listNew = new ArrayList<String>();
						listNew.add(strIndexType + ":" + strVbs);
						mapResult.put(strNum, listNew); //key为机架号+卡槽号
					}
				}
			}					
		}else{
			strResult="error";
		}
		return strResult;
	}
	
	public String GetZXCardInfo(Map<String, List<String>> mapResult){
		String strResult="OK",strNum="";
	//	List<VariableBinding> listTemp =new ArrayList<VariableBinding>();
		Map<String, List<SnmkRequest>> mapShelfInfo=new HashMap<String,List<SnmkRequest>>();
		List<TableEvent> listEvent =new ArrayList<TableEvent>();
		initOperator.SNMPWalkToBatch(ComInfo.CardInfo,listEvent);
		
		///解析
		if(listEvent!=null){
			for(TableEvent e:listEvent){
				SnmkRequest Result=new SnmkRequest();
				strNum=PackageSnmpWalkRequest(e,"CARD",Result);
				if (!strNum.equals("") && Result!=null) {
					String strVbs = Result.getVbsValue();
					String strIndexType = Result.getIndexType().substring(0, Result.getIndexType().indexOf("."));
					Object obj = new Object();
					obj = mapResult.get(strNum);
					if (obj != null) {
						List<String> listTemp = (List<String>) obj;
						listTemp.add(strIndexType + ":" + strVbs); //类型：值
					} else {
						List<String> listNew = new ArrayList<String>();
						listNew.add(strIndexType + ":" + strVbs);
						mapResult.put(strNum, listNew); //key为机架号+卡槽号
					}
				}
			}					
		}else{
			strResult="error";
		}
		return strResult;
	}
	
	public String GetZXCardStateInfo(Map<String, List<String>> mapResult){
		String strResult="OK",strNum="";
	//	List<VariableBinding> listTemp =new ArrayList<VariableBinding>();
		Map<String, List<SnmkRequest>> mapShelfInfo=new HashMap<String,List<SnmkRequest>>();
		List<TableEvent> listEvent =new ArrayList<TableEvent>();
		initOperator.SNMPWalkToBatch(ComInfo.CardStateInfo,listEvent);
		
		///解析
		if(listEvent!=null){
			for(TableEvent e:listEvent){
				SnmkRequest Result=new SnmkRequest();
				strNum=PackageSnmpWalkRequest(e,"CARDSTATE",Result);
				if (!strNum.equals("") && Result!=null) {
					String strVbs = Result.getVbsValue();
					String strIndexType = Result.getIndexType().substring(0, Result.getIndexType().indexOf("."));
					Object obj = new Object();
					obj = mapResult.get(strNum);
					if (obj != null) {
						List<String> listTemp = (List<String>) obj;
						listTemp.add(strIndexType + ":" + strVbs); //类型：值
					} else {
						List<String> listNew = new ArrayList<String>();
						listNew.add(strIndexType + ":" + strVbs);
						mapResult.put(strNum, listNew); //key为机架号+卡槽号
					}
				}
			}					
		}else{
			strResult="error";
		}
		return strResult;
	}
	
	public String PackageSnmpWalkRequest(TableEvent EventTemp, String strType, SnmkRequest TempResult) {

		VariableBinding[] Values = EventTemp.getColumns();
		String strResult = "";
		switch (strType) 
		{
		case "SHELF": 
		case "SLOT": 
		case "CARD":
		case "CARDSTATE":
		{
			String strOidIndex = EventTemp.getIndex().toString();
			int nStart = 0;
			nStart = strOidIndex.indexOf(".");
			strResult = strOidIndex.substring(nStart + 1);
			TempResult.setIndexType(strOidIndex);
			TempResult.setVbsValue(Values[0].getVariable().toString());
		}
			break;
		default:
			break;
		}
		return strResult; //返回机架号或机架号+卡槽号
	}
	
	public static String ParseZXAlarmMIB(String TrapValue){
		String Result="";
		if(TrapValue.equals("1")){
		//	Result="机架连接";
		}else if(TrapValue.equals("2")){
		//	Result="机架断开";
		}else if(TrapValue.equals("20")||TrapValue.equals("32")){
		//	Result="板卡连接";
			Result="Up";
		}else if(TrapValue.equals("21")||TrapValue.equals("33")){
		//	Result="板卡断开";
			Result="down";
		}
//		else if(TrapValue.equals("32")){
//		//	Result="远端卡连接";
//			Result="Up";
//		}else if(TrapValue.equals("33")){
//		//	Result="远端卡断开";
//			Result="down";
//		}
		return Result;
	}
	
	public static void QuerySlotForExtension(ZhenXingOptical ZXoptical,Map<String,SlotInfo> mapNewSlot){
		
		Map<String, List<String>> mapQuerySlotInfo = new HashMap<String, List<String>>(); //以机架号.卡槽号为key
		String strResult = ZXoptical.GetZXSlotInfo(mapQuerySlotInfo);
		String strShelfNum="",strSlotNum="",strSlotType="",strValue="";

		if(strResult.equals("OK")&&mapQuerySlotInfo.size()>0){
			Iterator iter2 = mapQuerySlotInfo.keySet().iterator();
			for (; iter2.hasNext();) {
				SlotInfo SlotNew = new SlotInfo();
				String key2 = (String) iter2.next();
				strShelfNum = key2.substring(0, key2.indexOf("."));
				strSlotNum = key2.substring(key2.indexOf(".") + 1);

				List<String> listInfo = (List<String>) mapQuerySlotInfo.get(key2);
				for (int i = 0; i < listInfo.size(); i++) {
					strSlotType = listInfo.get(i).substring(0, listInfo.get(i).indexOf(":"));
					strValue = listInfo.get(i).substring(listInfo.get(i).indexOf(":") + 1);
					ProcessTableData.GetParseSlotInfo(strSlotType, strValue, SlotNew);
				}
			mapNewSlot.put(key2, SlotNew);
		  }
		}
  }
	
	public static void QueryCardForExtension(ZhenXingOptical ZXoptical,Map<String,CardInfo> mapNewCard){
		
		Map<String, List<String>> mapQueryCardInfo = new HashMap<String, List<String>>(); //以机架号+卡槽号为key
		String strResult=ZXoptical.GetZXCardInfo(mapQueryCardInfo);
		if(strResult.equals("OK")&& mapQueryCardInfo.size() > 0){
			Iterator iter1 = mapQueryCardInfo.keySet().iterator();
			for (; iter1.hasNext();) {
				CardInfo LocalCardInfo=new CardInfo();
				CardInfo RemoteCardInfo=new CardInfo();
				String key1 = (String) iter1.next();
//				String strShelfNum = key1.substring(0, key1.indexOf("."));
//				String strSlotNum = key1.substring(key1.indexOf(".") + 1);
                String strCardType="",Key="";
				List<String> listInfo = (List<String>) mapQueryCardInfo.get(key1);
				for (int i = 0; i < listInfo.size(); i++) {
					String strSlotType = listInfo.get(i).substring(0, listInfo.get(i).indexOf(":"));
					String strValue = listInfo.get(i).substring(listInfo.get(i).indexOf(":") + 1);
					ProcessTableData.GetParseCardInfo(strSlotType, strValue, LocalCardInfo,RemoteCardInfo);
				}
				if(LocalCardInfo.getCardType().equals("0")){
					
				}else if(LocalCardInfo.getCardType().equals("34")){
					strCardType="1";
					Key=key1+"."+strCardType;
					mapNewCard.put(Key, LocalCardInfo);
				}
				if(RemoteCardInfo.getCardType().equals("0")){
						
				}else if(RemoteCardInfo.getCardType().equals("35")){
					strCardType="2";
					Key=key1+"."+strCardType;
					mapNewCard.put(Key, RemoteCardInfo);
			   }			
			}
		}
	//
		Map<String, List<String>> mapQueryCardStateInfo = new HashMap<String, List<String>>(); //以机架号+卡槽号为key
		strResult=ZXoptical.GetZXCardStateInfo(mapQueryCardStateInfo);
		if(strResult.equals("OK") && mapQueryCardStateInfo.size() > 0){
			Iterator iter2=mapQueryCardStateInfo.keySet().iterator();
			for(;iter2.hasNext();){
				CardInfo LocalCardInfo1 =new CardInfo();
				CardInfo RemoteCardInfo1 =new CardInfo();
				String key2=(String)iter2.next();
				String key3="";
				
				List<String> listInfo=(List<String>)mapQueryCardStateInfo.get(key2);
				for(int i=0;i<listInfo.size();i++){
					String strSlotType1=listInfo.get(i).substring(0, listInfo.get(i).indexOf(":"));
					String strValue1=listInfo.get(i).substring(listInfo.get(i).indexOf(":")+1);
					ProcessTableData.GetParseCardStateInfo(strSlotType1,strValue1,LocalCardInfo1,RemoteCardInfo1);
				}
				//根据具体卡槽号查询是否已经有本地卡和远端卡
				key3=key2+"."+"1";
				if(mapNewCard.get(key3)!=null){
					mapNewCard.get(key3).setTxlink1(LocalCardInfo1.getTxlink1());
					mapNewCard.get(key3).setTxlink2(LocalCardInfo1.getTxlink2());
					mapNewCard.get(key3).setTxlink3(LocalCardInfo1.getTxlink3());
					mapNewCard.get(key3).setTxlink4(LocalCardInfo1.getTxlink4());
					mapNewCard.get(key3).setCurMode1(LocalCardInfo1.getCurMode1());
					mapNewCard.get(key3).setCurMode2(LocalCardInfo1.getCurMode2());
					mapNewCard.get(key3).setCurMode3(LocalCardInfo1.getCurMode3());
					mapNewCard.get(key3).setCurMode4(LocalCardInfo1.getCurMode4());
					mapNewCard.get(key3).setMode1(LocalCardInfo1.getMode1());
					mapNewCard.get(key3).setMode2(LocalCardInfo1.getMode2());
					mapNewCard.get(key3).setMode3(LocalCardInfo1.getMode3());
					mapNewCard.get(key3).setMode4(LocalCardInfo1.getMode4());				
				}
				key3=key2+"."+"2";
				if(mapNewCard.get(key3)!=null){
					mapNewCard.get(key3).setTxlink1(RemoteCardInfo1.getTxlink1());
					mapNewCard.get(key3).setTxlink2(RemoteCardInfo1.getTxlink2());
					mapNewCard.get(key3).setTxlink3(RemoteCardInfo1.getTxlink3());
					mapNewCard.get(key3).setTxlink4(RemoteCardInfo1.getTxlink4());
					mapNewCard.get(key3).setCurMode1(RemoteCardInfo1.getCurMode1());
					mapNewCard.get(key3).setCurMode2(RemoteCardInfo1.getCurMode2());
					mapNewCard.get(key3).setCurMode3(RemoteCardInfo1.getCurMode3());
					mapNewCard.get(key3).setCurMode4(RemoteCardInfo1.getCurMode4());
					mapNewCard.get(key3).setMode1(RemoteCardInfo1.getMode1());
					mapNewCard.get(key3).setMode2(RemoteCardInfo1.getMode2());
					mapNewCard.get(key3).setMode3(RemoteCardInfo1.getMode3());
					mapNewCard.get(key3).setMode4(RemoteCardInfo1.getMode4());				
				}						
			 }
		}
  }
}
