package snmpmonitor.MonitorObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.snmp4j.CommandResponderEvent;
import org.snmp4j.smi.VariableBinding;

import net.sf.json.JSONArray;
import snmpmonitor.Common.AddDeviceBackStructure;
import snmpmonitor.Common.BackShelfStructure;
import snmpmonitor.Common.BackSlotStructure;
import snmpmonitor.Common.BrandInfo;
import snmpmonitor.Common.CardInfo;
import snmpmonitor.Common.ComInfo;
import snmpmonitor.Common.FrontSearchStructure;
import snmpmonitor.Common.ISiteviewApi;
import snmpmonitor.Common.OpticalStructure;
import snmpmonitor.Common.ShelfInfo;
import snmpmonitor.Common.SlotInfo;
import snmpmonitor.Common.SnmkRequest;

public class OpticalObject {

	public static Map<String, String> ParseSearchObject(ISiteviewApi api,Map<String,FrontSearchStructure> mapTemp){
	//	Map<String,FrontSearchStructure> mapAllOut =new HashMap<String,FrontSearchStructure>();
		Map<String,String> mapOut=new HashMap<String,String>();
		List<FrontSearchStructure> listAllOut=new ArrayList<FrontSearchStructure>();
		Map<String,FrontSearchStructure> mapQuery =new HashMap<String,FrontSearchStructure>();
		Map<String,String> mapAddedChassis=new HashMap<String,String>();
		int nMaxChassisID=0;
		nMaxChassisID=OperatorTable.QueryAddedChassis(api,mapAddedChassis);
		GetAllQueryChassis(mapTemp,mapAddedChassis,mapQuery,listAllOut);
		
		if(mapQuery.size()>0){
			//ȥ��ѯ
			Iterator iter1 = mapQuery.keySet().iterator();
			for (; iter1.hasNext();) {
				Map<String,String> mapResult=new HashMap<String,String>();
				String key = (String) iter1.next();
				String strBrand= mapQuery.get(key).getBrand();
				String strCommunity= mapQuery.get(key).getCommunity();
				GetObjectInfoByBrand(api,ComInfo.SearchType,key,"",strBrand,strCommunity,mapResult);
				
				Iterator iter2 = mapResult.keySet().iterator();	
				while(iter2.hasNext()){
					String keyIP = (String) iter2.next();
					String value2= (String) mapResult.get(keyIP).toString();
					FrontSearchStructure ResultValue=new FrontSearchStructure();
					if(value2.equals("ChassisError")||value2.equals("BrandError")||
						value2.equals("CardError")||value2.equals("SlotError")||
						value2.equals("ShelfError")){
						ResultValue.setOnlineState(ComInfo.SearchSate3);		
				     }else {
				    	ResultValue.setOnlineState(ComInfo.SearchSate2);				     
					 }
					ResultValue.setChassisIP(keyIP);
					ResultValue.setBrand(strBrand);
					ResultValue.setCivilcode("");			   
				//	mapAllOut.put(keyIP, ResultValue);
					listAllOut.add(ResultValue);
				}
		}
	  }
		//��װ���ظ�ǰ̨��
		if(listAllOut.size()==0){
			mapOut.put("Result", "error");
		}else{
			String strValue=JSONArray.fromObject(listAllOut).toString();
			mapOut.put("Result", strValue);
		}	
		return mapOut;
	}
	
	public static void GetAllQueryChassis(Map<String,FrontSearchStructure> mapIn,Map<String,String> mapNow,
			Map<String,FrontSearchStructure> mapQuery,List<FrontSearchStructure> listOut){
		Iterator iter1 = mapIn.keySet().iterator();		
		for (; iter1.hasNext();) {
			String key = (String) iter1.next();					
			String value2=mapNow.get(key);
			if(value2==null || value2.equals("")){
				mapQuery.put(key, mapIn.get(key));
			}
		}
		Iterator iter2 = mapNow.keySet().iterator();	
		for(; iter2.hasNext();){
	        String key2 = (String) iter2.next();				
	        FrontSearchStructure strvalue2=(FrontSearchStructure)mapIn.get(key2);
			if(strvalue2!=null || !strvalue2.equals("")){
				String value2=mapNow.get(key2);
				String strCivilcode= value2.substring(0, value2.indexOf(";"));
				value2=value2.substring(value2.indexOf(";")+1);
				String strBrand= value2.substring(0, value2.indexOf(";"));
				value2=value2.substring(value2.indexOf(";")+1);
				String strOnlineState= value2.substring(0, value2.indexOf(";"));
				value2=value2.substring(value2.indexOf(";")+1);
				FrontSearchStructure NowValue=new FrontSearchStructure();
				NowValue.setChassisIP(key2);
				NowValue.setCivilcode(strCivilcode);
				NowValue.setBrand(strBrand);
				NowValue.setOnlineState(ComInfo.SearchSate1);		
				listOut.add(NowValue);
			}
		}	
	}
	
	
	public static void GetObjectInfoByBrand(ISiteviewApi api,String type,String ip,String civilcode,
			String brand,String community,Map<String,String> mapTemp){
		
		if(brand.equals(BrandInfo.strZhenXing)){
			ZhenXingOptical ZXOptical =new ZhenXingOptical();
			ZXOptical.ZhenXingOpticalParment(ip,community);
			if(!ZXOptical.InitZX()){
				return;
			}
			
			String AllOID="",strReturn="";
			switch(type){
			   case "Search":
					AllOID=ZXOptical.JudgeChassisOnlineOID();
					ZXOptical.GetZXChassisInfo(ComInfo.SearchType,AllOID,2,mapTemp);
				   break;
			   case "Add":
				    Map<String,String> mapTemp1=new HashMap<String,String>();
					AllOID=ZXOptical.GetChassisInfoOID();
					ZXOptical.GetZXChassisInfo(ComInfo.AddType,AllOID,4,mapTemp1);
//					Iterator iterTemp1 = mapTemp1.keySet().iterator();
//					String keyTemp1 = (String) iterTemp1.next(); 
					String ValueTemp1=(String)mapTemp1.get(ip);
					if(!ValueTemp1.equals("OIDError")&&!ValueTemp1.equals("IPError")&&
							!ValueTemp1.equals("UnknownError"))
					{
						String strvalue=ip+";"+civilcode+";"+brand+";"+community+";";
						int nChasID=0;
//						if(value2.equals("OIDError")||value2.equals("IPError")||
//								value2.equals("UnknownError"))
						nChasID=OperatorTable.InsertChassisToTable(api,mapTemp1,strvalue);
						Map<String, List<SnmkRequest>> mapShelfInfo=new HashMap<String,List<SnmkRequest>>();
						strReturn=ZXOptical.GetZXShelfInfo(mapShelfInfo);
						if(strReturn.equals("OK")&& mapShelfInfo.size() > 0){
							Iterator iter1 = mapShelfInfo.keySet().iterator();
							for (; iter1.hasNext();) {
								String key = (String) iter1.next(); //���ܺ�
								List<SnmkRequest> ShelfInfo = (List<SnmkRequest>) mapShelfInfo.get(key);
								OperatorTable.InsertShelfInfoToTable(api,nChasID, key, ShelfInfo);
							}
							strReturn=GetSlotInfo(api,nChasID,ZXOptical);
							if(!strReturn.equals("error")){
								strReturn=GetCardInfo(api,nChasID,ZXOptical);
								if(!strReturn.equals("error")){
									mapTemp.put(ip, "OK"); //��ʾ��ѯ����
								}else
									mapTemp.put(ip, "CardError");
							}else{
								mapTemp.put(ip, "SlotError");
							}						
						}else{
							mapTemp.put(ip, "ShelfError");
						}
//						GetSlotInfo(api,nChasID,ZXOptical);
//						GetCardInfo(api,nChasID,ZXOptical);
					}else{
						mapTemp.put(ip, "ChassisError");
					}			
				   break;
			   default:
				   break;
			}			
		}else {
			mapTemp.put(ip, "BrandError");
		}
	}
	
	public static String GetSlotInfo(ISiteviewApi api,int ChassisID,ZhenXingOptical ZXOptical){
		String strResult = "OK";
		Map<String, List<String>> mapSlotInfo = new HashMap<String, List<String>>(); //�Ի��ܺ�+���ۺ�Ϊkey
		strResult = ZXOptical.GetZXSlotInfo(mapSlotInfo);
		if(strResult.equals("OK")&& mapSlotInfo.size() > 0){
			OperatorTable.InsertSlotInfoToTable(api,ChassisID, mapSlotInfo);
		}
		return strResult;
	}
	
	public static String GetCardInfo(ISiteviewApi api,int ChassisID,ZhenXingOptical ZXOptical){
		String strResult = "OK";
		Map<String, List<String>> mapCardInfo = new HashMap<String, List<String>>(); //�Ի��ܺ�+���ۺ�Ϊkey
		strResult=ZXOptical.GetZXCardInfo(mapCardInfo);
		if(strResult.equals("OK")&& mapCardInfo.size() > 0){
			OperatorTable.InsertCardInfoToTable(api,ChassisID, mapCardInfo);
			mapCardInfo.clear();
			strResult=ZXOptical.GetZXCardStateInfo(mapCardInfo);
			if(strResult.equals("OK") && mapCardInfo.size() > 0){
				OperatorTable.InsertCardStateInfoToTable(api,ChassisID, mapCardInfo);
			}
		}	
		return strResult;
	}
	
	
	public static Map<String, String> ParseAddDeviceObject(ISiteviewApi api,Map<String,FrontSearchStructure> mapTemp){
		Map<String,String> mapOut=new HashMap<String,String>();
	    List<String> listSuccessIP=new ArrayList<String>();
	    List<AddDeviceBackStructure> listAllOut=new ArrayList<AddDeviceBackStructure>();
	    
		if(mapTemp.size()==0){
			mapOut.put("Result", "error"); //ǰ̨�����ֵΪ��
		}else{
			Iterator iter1 = mapTemp.keySet().iterator();
			for (; iter1.hasNext();) {
				String key1 = (String) iter1.next();
				String strCivilcode1= mapTemp.get(key1).getCivilcode();
				String strBrand1= mapTemp.get(key1).getBrand();
				String strCommunity1= mapTemp.get(key1).getCommunity();
				if(!OperatorTable.JudgeChassisIsOnline(api,key1)){
					Map<String,String> mapResult=new HashMap<String,String>();
					GetObjectInfoByBrand(api,"Add",key1,strCivilcode1,strBrand1,strCommunity1,mapResult);
					if(mapResult.get(key1).toString().equals("OK")){
						listSuccessIP.add(key1);
					}
				}

			}
		}
		//ֱ�Ӳ�ѯ����Chassisinfo�������
		GetAllShowDeviceInfo(api,listAllOut);
		if(listAllOut.size()==0){
			mapOut.put("Result", "NoData");
		}else{
			String strValue=JSONArray.fromObject(listAllOut).toString();
			mapOut.put("Result", strValue);
		}
//		if(listSuccessIP.size()>0){
//			for(int i=0;i<listSuccessIP.size();i++){
//				String strChasIP=(String)listSuccessIP.get(i);
//				GetAddDeviceInfo(api,strChasIP,listAllOut);
//			}
//			if(listAllOut.size()==0){
//				mapOut.put("Result", "error");
//			}else{
//				String strValue=JSONArray.fromObject(listAllOut).toString();
//				mapOut.put("Result", strValue);
//			}
//		}else{
//			mapOut.put("Result", "error");
//		}			
		return mapOut;
	}
	
	private static void GetAllShowDeviceInfo(ISiteviewApi api,List<AddDeviceBackStructure> listTemp){
		String strChassis="",strShelf="";
		strChassis=OperatorTable.GetAllShowChassisInfo(api, listTemp);
		strShelf=OperatorTable.GetAllShowShelfInfo(api, listTemp);	
	}
	
	public static Map<String, String> RenovateShowDeviceObject(ISiteviewApi api){
		Map<String,String> mapOut=new HashMap<String,String>();
		 List<AddDeviceBackStructure> listAllOut=new ArrayList<AddDeviceBackStructure>();	 
		 GetAllShowDeviceInfo(api,listAllOut);
			if(listAllOut.size()==0){
				mapOut.put("Result", "error");
			}else{
				String strValue=JSONArray.fromObject(listAllOut).toString();
				mapOut.put("Result", strValue);
			}
		return mapOut;		
	}
	
	public static String ReStartMonitorShelfDevice(ISiteviewApi api,String ChasID,String ShelfID){
		String strResult="error";
		strResult=OperatorTable.ReStartShelfAllInfoByID(api,ChasID,ShelfID);
		return strResult;
	}
	
	public static String ReStartMonitorSlotDevice(ISiteviewApi api,String ChasID,String ShelfID,
			String SlotID){
		String strResult="error";
		if( Integer.parseInt(SlotID)>0 && Integer.parseInt(SlotID)<=16){
			strResult=OperatorTable.ReStartSlotAllInfoByID(api,ChasID,ShelfID,SlotID);
		}
		return strResult;
	}
	
	public static String DeleteChassisDevice(ISiteviewApi api,String ChasID){
		String strResult="error";
		strResult=OperatorTable.DelteChassisAllInfoByChasID(api,ChasID);
		return strResult;
	}
	
	public static String DeleteShelfDevice(ISiteviewApi api,String ChasID,String ShelfID){
		String strResult="error";
		strResult=OperatorTable.SetShelfAllInfoByID(api,ChasID,ShelfID);
		return strResult;
	}
	
	public static String DeleteSlotDevice(ISiteviewApi api,String ChasID,String ShelfID,
			String SlotID){
		String strResult="error";
		strResult=OperatorTable.SetSlotAllInfoByID(api,ChasID,ShelfID,SlotID);
		return strResult;
	}
	
	public static Map<String, String> ClickShelfDeviceObject(ISiteviewApi api,String ChaID,String ShelfID){
		Map<String,String> mapOut=new HashMap<String,String>();
		 List<BackShelfStructure> listAllOut=new ArrayList<BackShelfStructure>();	 
		 GetClickShelfDeviceInfo(api,ChaID,ShelfID,listAllOut);
		 
			if(listAllOut.size()==0){
				mapOut.put("Result", "error");
			}else{
				String strValue=JSONArray.fromObject(listAllOut).toString();
				mapOut.put("Result", strValue);
			}
		return mapOut;		
	}
	
	private static void GetClickShelfDeviceInfo(ISiteviewApi api,String ChasId,String SchelfId
			,List<BackShelfStructure> listTemp){
		String strResult="";
		if(ChasId.equals("")||SchelfId.equals("")){
			strResult="error";
		}else{
			strResult=OperatorTable.GetClickShelfInfo(api,ChasId,SchelfId,listTemp);
			if(strResult.equals("OK")&&listTemp.size()>0){
				strResult=OperatorTable.GetCardStateByClickShelf(api,listTemp);
			}
		}
	
	//	strChassis=OperatorTable.GetAllShowChassisInfo(api, listTemp);
	//	strShelf=OperatorTable.GetAllShowShelfInfo(api, listTemp);	
	}	
	
	public static Map<String, String> ClickSlotDeviceObject(ISiteviewApi api,String ChaID,String ShelfID,
			String SlotID){
		Map<String,String> mapOut=new HashMap<String,String>();
		 List<BackSlotStructure> listAllOut=new ArrayList<BackSlotStructure>();	 
		 GetClickSlotDeviceInfo(api,ChaID,ShelfID,SlotID,listAllOut);
		 
			if(listAllOut.size()==0){
				mapOut.put("Result", "error");
			}else{
				String strValue=JSONArray.fromObject(listAllOut).toString();
				mapOut.put("Result", strValue);
			}
		return mapOut;		
	}
	
	private static void GetClickSlotDeviceInfo(ISiteviewApi api,String ChasId,String SchelfId
			,String SlotId,List<BackSlotStructure> listTemp){
		String strResult="";
		strResult=OperatorTable.GetClickSlotInfo(api,ChasId,SchelfId,SlotId,listTemp);
		if(strResult.equals("OK")&&listTemp.size()>0){
			strResult=OperatorTable.GetCardInfoByClickSlot(api,listTemp);
		}
	//	strChassis=OperatorTable.GetAllShowChassisInfo(api, listTemp);
	//	strShelf=OperatorTable.GetAllShowShelfInfo(api, listTemp);	
	}
	
	//trap����
	public static void ParseTrapOperator(ISiteviewApi api,CommandResponderEvent event){
		String strEvent= event.toString();
		String ObjectName="",SpeciTrap="",PeerAddress="",value="";
		value=strEvent.substring(strEvent.indexOf("enterprise="));
		
		ObjectName=GetCompanyBrand(value.substring(value.indexOf("=")+1, value.indexOf(",")));
		value=strEvent.substring(strEvent.indexOf("specificTrap="));
		SpeciTrap=value.substring(value.indexOf("=")+1, value.indexOf(","));
		value=strEvent.substring(strEvent.indexOf("peerAddress="));
		PeerAddress=value.substring(value.indexOf("=")+1, value.indexOf("/"));
		if(ObjectName.equals(BrandInfo.strZhenXing)){
			ParseZXTrapOperator(api,event,PeerAddress,SpeciTrap);
		}
	}	
	
	public static String GetCompanyBrand(String strEnterprise){
		String strResult="";
		if(strEnterprise.equals(ComInfo.Enterprise)){
			strResult="ZX";
		}else {
			
		}
		return strResult;
	}
	
	public static void ParseZXTrapOperator(ISiteviewApi api,CommandResponderEvent event,String IP,String TrapValue){
		
		String strResult=ZhenXingOptical.ParseZXAlarmMIB(TrapValue);
		if(!strResult.equals("")){
			String ChassisID="",ShelfID="",SlotID="";
			Vector<? extends VariableBinding> vbVect = event.getPDU().getVariableBindings();
			for (VariableBinding vb : vbVect) {
				String value=vb.getOid().toString();
				if(value.indexOf(ComInfo.ShelfType)!=-1){
					ShelfID=vb.getVariable().toString();
				}else if(value.indexOf(ComInfo.SlotType)!=-1){
					SlotID=vb.getVariable().toString();
				}else
					return;
			//	System.out.println(vb);
			//	System.out.println(vb.getOid() + " = " + vb.getVariable());
			}
			ChassisID=OperatorTable.GetChassisIDByIP(api,IP);
			if(!ChassisID.equals("")){ //ȷ�������ݿ����Ѿ����ڵ�
				if(TrapValue.equals("20")||TrapValue.equals("21")){ //�忨(����)����
					if(OperatorTable.JudgeSlotIsOnlineFromTrap(api,ChassisID,ShelfID,SlotID)){
						OperatorTable.SetSlotAllInfoState(api,ChassisID,ShelfID,SlotID,strResult);
					}				
				}else if(TrapValue.equals("32")||TrapValue.equals("33")){//Զ�˿�����
					if(OperatorTable.JudgeCardIsOnlineFromTrap(api,ChassisID,ShelfID,SlotID)){
						OperatorTable.SetRemoteCardAllInfoState(api,ChassisID,ShelfID,SlotID,strResult);
					}				
				}
			}

		}else
			return;	
	}
	
	
	//��ʱ����
	public static void	UpdateOperatorZXOptical(ISiteviewApi api,String strIP,String strCommunity){
		//��ѯChassis��
		OpticalStructure ChassisInfo=new OpticalStructure();
		OperatorTable.QueryChassisInfoByIP(api,strIP,strCommunity,ChassisInfo);
		
		//SNMP��ѯChassis
		String ChassAllOID="";
		 Map<String,String> mapChasResult=new HashMap<String,String>();
		ZhenXingOptical ZXOptical =new ZhenXingOptical();
		ZXOptical.ZhenXingOpticalParment(strIP,strCommunity);
		ZXOptical.InitZX();	
		ChassAllOID=ZXOptical.GetChassisInfoOID();
		ZXOptical.GetZXChassisInfo(ComInfo.AddType,ChassAllOID,4,mapChasResult);
		String ValueTemp1=(String)mapChasResult.get(strIP);
		if(!ValueTemp1.equals("OIDError")&&!ValueTemp1.equals("IPError")&&
				!ValueTemp1.equals("UnknownError"))
		{
			OperatorTable.UpdateChassisTableFromExtension(api,mapChasResult);
			UpdateOperatorZXShelf(api,ChassisInfo,ZXOptical);
			
		}else{			
		  //Ӧ����ǰ̨����һ������(��ʾ��ǰ��IP�鲻����Ϣ),���ڻ�����˵��û��-1״̬��ֻ��0��1
			//��ǰ̨���͸��������Ҹ�������������е�״̬Ϊ����0
			OperatorTable.UpdateChassisToOffline(api,ChassisInfo.getChassisId());
//		   if(!ChassisInfo.getOnlineState().equals("-1")){
//				//������-1�����ͱ���
//			}
//			//ɾ�����ݿ�Chassis�����Ϣ
//			OpticalObject.DeleteChassisDevice(api,ChassisInfo.getChassisId());
		}	
	}
	
	public static void UpdateOperatorZXShelf(ISiteviewApi api,OpticalStructure Chass,ZhenXingOptical ZXoptical){
		//��ѯshelfinfo��		
		Map<String,ShelfInfo> mapOldShelf=new HashMap<String,ShelfInfo>();
		OperatorTable.QueryShelfInfoByID(api,Chass.getChassisId(),mapOldShelf);
		
		//SNMP��ѯShelf
		Map<String, List<SnmkRequest>> mapShelfInfo=new HashMap<String,List<SnmkRequest>>();
		String strReturn=ZXoptical.GetZXShelfInfo(mapShelfInfo);
		if(strReturn.equals("OK")&& mapShelfInfo.size() > 0){
			Iterator iter1 = mapShelfInfo.keySet().iterator();
			for (; iter1.hasNext();) {
				String key = (String) iter1.next(); //���ܺ�			
				ShelfInfo value2=mapOldShelf.get(key);
				List<SnmkRequest> ShelfInfo = (List<SnmkRequest>) mapShelfInfo.get(key);
				if(value2==null || value2.equals("")){ //˵���»���
					//��Ҫ����
					
					//�����»���					
					OperatorTable.InsertShelfInfoToTable(api,Integer.parseInt(Chass.getChassisId()),key,ShelfInfo);
				}else{//�Ѿ��еĻ��ܣ�ֻ��Ҫ����
				//	List<SnmkRequest> ShelfInfo = (List<SnmkRequest>) mapShelfInfo.get(key);
					ShelfInfo ShelfTemp=new ShelfInfo();
					ProcessTableData.GetParseShelfInfo(ShelfTemp,ShelfInfo);			
					OperatorTable.UpdateShelfTableFromExtension(api,Chass.getChassisId(),key,ShelfTemp);
				}
			}
			Iterator iter2 = mapOldShelf.keySet().iterator();
			for (; iter2.hasNext();) {
				String key2 = (String) iter2.next(); //���ܺ�			
			//	String valueNew=mapShelfInfo.get(key2).toString();
				List<SnmkRequest> valueNew = (List<SnmkRequest>) mapShelfInfo.get(key2);
		//		ShelfInfo valueNew=mapShelfInfo.get(key2);
				if(valueNew==null || valueNew.equals("")){ //˵���ɻ������в����ڵ� 
					//��Ҫ����(OnlinestateΪ-1�Ĳ�����)����ɾ��������Ϣ
					ShelfInfo ShelfOld=mapOldShelf.get(key2);				
					if(!ShelfOld.getShelfOnlineState().equals("-1")){
						//������-1�����ͱ����󣬽�onlinestate��Ϊ0
						OpticalObject.UpdateShelfDeviceToOffline(api,Chass.getChassisId(),key2);
					}else{//onlinestateΪ-1�ģ�ɾ�����ݿ�Shelf�����Ϣ
						OpticalObject.DeleteShelfDeviceFromExtension(api,Chass.getChassisId(),key2);
					}								
				}
			}
		}else{//û���κλ���,�������
			
		}
		//
		Map<String,ShelfInfo> mapCurShelf=new HashMap<String,ShelfInfo>();
		Iterator iter3 = mapShelfInfo.keySet().iterator();
		for (; iter3.hasNext();) {
			String key3 = (String) iter3.next(); //���ܺ�			
			List<SnmkRequest> ShelfInfo = (List<SnmkRequest>) mapShelfInfo.get(key3);
			ShelfInfo CurShelf=new ShelfInfo();
			ProcessTableData.GetParseShelfInfo(CurShelf,ShelfInfo);	
			mapCurShelf.put(key3, CurShelf);
		}
		UpdateOperatorZXSlot(api,Chass,ZXoptical,mapCurShelf);					
	}	
	
	public static String UpdateShelfDeviceToOffline(ISiteviewApi api,String ChasID,String ShelfID){
		String strResult="error";
		strResult=OperatorTable.UpdateShelfIsOfflineByID(api,ChasID,ShelfID);
		return strResult;
	}
	
	public static String DeleteShelfDeviceFromExtension(ISiteviewApi api,String ChasID,String ShelfID){
		String strResult="error";
		strResult=OperatorTable.DelteShelfInfoByID(api,ChasID,ShelfID);
		return strResult;
	}
	
	public static String UpdateSlotDeviceToOffline(ISiteviewApi api,String ChasID,String ID){
		String strResult="error";
		strResult=OperatorTable.UpdateSlotIsOfflineByID(api,ChasID,ID);
		return strResult;
	}
	
	public static String DeleteSlotDeviceFromExtension(ISiteviewApi api,String ChasID,String ID){
		String strResult="error";
		strResult=OperatorTable.DelteSlotInfoByID(api,ChasID,ID);
		return strResult;
	}
	
	public static void UpdateOperatorZXSlot(ISiteviewApi api,OpticalStructure Chass,ZhenXingOptical ZXoptical,
			Map<String,ShelfInfo> mapShelf){
		//��ѯSlotInfo��
		Map<String,SlotInfo> mapOldSlot=new HashMap<String,SlotInfo>(); //�Ի��ܺ�.���ۺ�Ϊkey
		Iterator iter1 = mapShelf.keySet().iterator();
		for (; iter1.hasNext();) {
			String keyId = (String) iter1.next(); //���ܺ�
			OperatorTable.QuerySlotInfoByID(api,Chass.getChassisId(),keyId,mapOldSlot);
		}
		
		//SNMP��ѯslot	
		Map<String,SlotInfo> mapNewSlot=new HashMap<String,SlotInfo>();   //�Ի��ܺ�.���ۺ�Ϊkey
		ZXoptical.QuerySlotForExtension(ZXoptical,mapNewSlot);
       
		if(mapNewSlot.size()>0){
			Iterator iter2 = mapNewSlot.keySet().iterator();
			for (; iter2.hasNext();) {
				String key2 = (String) iter2.next(); //���ܺ�.���ۺ�
				SlotInfo OldSlotValue=mapOldSlot.get(key2);
				SlotInfo NewSlot=mapNewSlot.get(key2);
				if(OldSlotValue==null||OldSlotValue.equals("")){ //�¼ӵĿ��ۺ�
					//��Ҫ����
					
					//�����¿��ۺ�				
					OperatorTable.InsertNewSlotToTable(api,Chass.getChassisId(),key2,NewSlot);
				}else{ //���еģ�ֻ��Ҫ����		
					OperatorTable.UpdateSlotTableFromExtension(api,Chass.getChassisId(),key2,NewSlot);
				}
			}
			
			Iterator iter3 = mapOldSlot.keySet().iterator();
			for (; iter3.hasNext();) {
				String key3 = (String) iter3.next(); //���ܺ�.���ۺ�			
				SlotInfo valueNew3=mapNewSlot.get(key3);
				if(valueNew3==null || valueNew3.equals("")){ //˵���ɿ������в�����
					//��Ҫ����(OnlinestateΪ-1�Ĳ�����)����ɾ��������Ϣ
					SlotInfo SlotOld=mapOldSlot.get(key3);					
					if(!SlotOld.getOnlineState().equals("-1")){
						//������-1�����ͱ����󣬽�onlinestate��Ϊ0
						OpticalObject.UpdateSlotDeviceToOffline(api,Chass.getChassisId(),key3);
					}else{//onlinestateΪ-1�ģ�ɾ�����ݿ�Slot�����Ϣ
						OpticalObject.DeleteSlotDeviceFromExtension(api,Chass.getChassisId(),key3);	
					}				
				}
			}
			
		}else{ //û�κο���
			//ɾ�����ݿ������еĿ���
			Iterator iter4 = mapOldSlot.keySet().iterator();
			for (; iter4.hasNext();) {
				String key4 = (String) iter4.next(); //���ܺ�.���ۺ�			
				SlotInfo valueNew4=mapNewSlot.get(key4);
				if(valueNew4==null || valueNew4.equals("")){ //˵���ɿ������в�����
					//��Ҫ����(OnlinestateΪ-1�Ĳ�����)����ɾ��������Ϣ
					SlotInfo SlotOld4=mapOldSlot.get(key4);				
					if(!SlotOld4.getOnlineState().equals("-1")){
						//������-1�����ͱ���,��onlinestate��Ϊ0
						OpticalObject.UpdateSlotDeviceToOffline(api,Chass.getChassisId(),key4);
					}else{
						//onlinestateΪ-1�ģ�ɾ�����ݿ�Slot�����Ϣ
						OpticalObject.DeleteSlotDeviceFromExtension(api,Chass.getChassisId(),key4);	
					}
									
				}
			}
		}	
		//
		UpdateOperatorZXCard(api,Chass,ZXoptical,mapNewSlot);		
	}
	
	public static void UpdateOperatorZXCard(ISiteviewApi api,OpticalStructure Chass,ZhenXingOptical ZXoptical,
			Map<String,SlotInfo> mapSlot){
		//��ѯCard��
		Map<String,CardInfo> mapOldCard=new HashMap<String,CardInfo>(); //�Ի��ܺ�.���ۺ�.Card���ͺ�Ϊkey
		Iterator iter1 = mapSlot.keySet().iterator();
		for (; iter1.hasNext();) {
			String keyId = (String) iter1.next(); //���ܺ�.���ۺ�
			OperatorTable.QueryCardInfoByID(api,Chass.getChassisId(),keyId,mapOldCard);
		}
		
		//SNMP��ѯcard	
		Map<String,CardInfo> mapNewCard=new HashMap<String,CardInfo>();   //�Ի��ܺ�.���ۺ�.Card���ͺ�Ϊkey
		ZXoptical.QueryCardForExtension(ZXoptical,mapNewCard);
       
		if(mapNewCard.size()>0){
			Iterator iter2 = mapNewCard.keySet().iterator();
			for (; iter2.hasNext();) {
				String key2 = (String) iter2.next(); //���ܺ�.���ۺ�.Card���ͺ�
				CardInfo OldCardValue=mapOldCard.get(key2);
				CardInfo NewCard=mapNewCard.get(key2);
				if(OldCardValue==null||OldCardValue.equals("")){//�¼ӵĿ�
					//��Ҫ����
					
					//�����¿�
					OperatorTable.InsertNewCardToTable(api,Chass.getChassisId(),key2,NewCard);
				}else{ //���еģ�ֻ��Ҫ����		
					OperatorTable.UpdateCardTableFromExtension(api,Chass.getChassisId(),key2,NewCard);
				}
			}
			
			Iterator iter3 = mapOldCard.keySet().iterator();
			for (; iter3.hasNext();) {
				String key3 = (String) iter3.next(); //���ܺ�.���ۺ�.Card���ͺ�			
				CardInfo valueNew3=mapNewCard.get(key3);
				if(valueNew3==null || valueNew3.equals("")){ //˵���ɿ����в�����
					//��Ҫ����(OnlinestateΪ-1�Ĳ�����)����ɾ��������Ϣ
					CardInfo CardOld=mapOldCard.get(key3);				
					if(!CardOld.getOnlineState().equals("-1")){
						//������-1�����ͱ���,��onlinestate��Ϊ0
						OpticalObject.UpdateCardDeviceToOffline(api,Chass.getChassisId(),key3);
					}else{
						//onlinestateΪ-1�ģ�ɾ�����ݿ�Card�����Ϣ
						OpticalObject.DeleteCardDeviceFromExtension(api,Chass.getChassisId(),key3);		
					}
							
				}
			}
			
		}else{ //û���κ�Card���������
			
		}		
	}
	
	public static String UpdateCardDeviceToOffline(ISiteviewApi api,String ChasID,String ID){
		String strResult="error";
		strResult=OperatorTable.UpdateCardIsOfflineByID(api,ChasID,ID);
		return strResult;
	}
	
	public static String DeleteCardDeviceFromExtension(ISiteviewApi api,String ChasID,String ID){
		String strResult="error";
		strResult=OperatorTable.DelteCardInfoByID(api,ChasID,ID);
		return strResult;
	}
	
}
