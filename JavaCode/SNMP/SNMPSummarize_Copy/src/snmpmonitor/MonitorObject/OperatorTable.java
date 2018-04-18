package snmpmonitor.MonitorObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import snmpmonitor.Common.AddDeviceBackStructure;
import snmpmonitor.Common.BackShelfStructure;
import snmpmonitor.Common.BackSlotStructure;
import snmpmonitor.Common.CardInfo;
import snmpmonitor.Common.CommonFunction;
import snmpmonitor.Common.ISiteviewApi;
import snmpmonitor.Common.OpticalStructure;
import snmpmonitor.Common.ProcessTableData;
import snmpmonitor.Common.ShelfInfo;
import snmpmonitor.Common.ShelfState;
import snmpmonitor.Common.SlotInfo;
import snmpmonitor.Common.SlotState;
import snmpmonitor.Common.SnmkRequest;

public class OperatorTable {

	
	public static int QueryAddedChassis(ISiteviewApi api, Map<String, String> mapTemp) {
		int nMaxID = 0;
		String strsql = "select ChassisId,Civilcode,Brand,ChassisIP,OnlineState from ChassisInfo";
		// DataTable dt=DBQueryUtils.Select(strsql, api);
		// if(dt !=null){
		// for(int i=0;i<dt.get_Rows().size();i++)
		// {
		// String strChassisId = dt.get_Rows().get(i).get("ChassisId") == null ?
		// "" : dt.get_Rows().get(i).get("ChassisId").toString();
		// String strCivilcode = dt.get_Rows().get(i).get("Civilcode") == null ?
		// "" : dt.get_Rows().get(i).get("Civilcode").toString();
		// String strBrand = dt.get_Rows().get(i).get("Brand") == null ? "" :
		// dt.get_Rows().get(i).get("Brand").toString();
		// String strChassisIP = dt.get_Rows().get(i).get("ChassisIP") == null ?
		// "" : dt.get_Rows().get(i).get("ChassisIP").toString();
		// String strOnlineState = dt.get_Rows().get(i).get("OnlineState") ==
		// null ? "" : dt.get_Rows().get(i).get("OnlineState").toString();
		// mapTemp.put(strChassisIP,strCivilcode+";"+strBrand+";"+strOnlineState+";");
		// if(Integer.parseInt(strChassisId)>=nMaxID){
		// nMaxID=Integer.parseInt(strChassisId);
		// }
		// }
		// }
		return nMaxID;
	}

	public static int InsertChassisToTable(ISiteviewApi api, Map<String, String> mapTemp, String strValue) {
		// String strvalue=ip+";"+civilcode+";"+brand+";"+community+";";
		int nMaxChassisID = 0;
		String strIP = "", strCivilcode = "", strBrand = "", strCommunity = "", strV = "";
		String strShelfNum = "", strMask = "", strGateWay = "", strTime = "";
		strIP = strValue.substring(0, strValue.indexOf(";"));
		strV = strValue.substring(strValue.indexOf(";") + 1);
		strCivilcode = strV.substring(0, strV.indexOf(";"));
		strV = strV.substring(strV.indexOf(";") + 1);
		strBrand = strV.substring(0, strV.indexOf(";"));
		strV = strV.substring(strV.indexOf(";") + 1);
		strCommunity = strV.substring(0, strV.indexOf(";"));
		// mapTemp.put(strIP, strShelfNum+";"+strMask+";"+strGateWay+";");
		Iterator iter1 = mapTemp.keySet().iterator();
		for (; iter1.hasNext();) {
			String key1 = (String) iter1.next();
			String value1 = mapTemp.get(key1);
			strShelfNum = value1.substring(0, value1.indexOf(";"));
			strV = value1.substring(value1.indexOf(";") + 1);
			strMask = strV.substring(0, strV.indexOf(";"));
			strV = strV.substring(strV.indexOf(";") + 1);
			strGateWay = strV.substring(0, strV.indexOf(";"));
		}
		nMaxChassisID = GetMaxChassisID(api);
		nMaxChassisID = nMaxChassisID + 1;
		strTime = CommonFunction.GetCurrentTime();
		// String strsql="insert into
		// ChassisInfo(PId,ChassisId,Level,Civilcode,Brand,Community,ChassisIP,"
		// + "chassisMask,chassisGateway,ShelfNum,OnlineState,UpdateTime)
		// values('"+0+"','"+nMaxChassisID+
		// "','"+1+"','"+strCivilcode+"','"+strBrand+"','"+strCommunity+"','"+strIP+"','"+
		// strMask+"','"+strGateWay+"','"+strShelfNum+"','"+1+"','"+strTime+"')";
		String strsql = String.format(
				"INSERT INTO ChassisInfo(RecId,LastModDateTime,CreatedDateTime,PId,ChassisId,Level,Civilcode,Brand,Community,ChassisIP,"
						+ "chassisMask,chassisGateway,ShelfNum,OnlineState,UpdateTime)VALUES(UPPER(REPLACE(UUID(),'-','')),NOW(),NOW(),%d,%d,%d,'%s','%s','%s','%s',"
						+ "'%s','%s','%s',%d,'%s')",
				0, nMaxChassisID, 1, strCivilcode, strBrand, strCommunity, strIP, strMask, strGateWay, strShelfNum, 1,
				strTime);
		try {
			// 要执行的SQL语句
			// DBQueryUtils.UpdateorDelete(strsql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// System.out.println("insert into ChassisInfo is SUCCESS");
		}
		return nMaxChassisID;
	}

	public static int GetMaxChassisID(ISiteviewApi api) {
		int nMaxID = 0;
		String strsql = "select ChassisId from ChassisInfo";
		// DataTable dt=DBQueryUtils.Select(strsql, api);
		// if(dt !=null){
		// for(int i=0;i<dt.get_Rows().size();i++)
		// {
		// String strChassisId = dt.get_Rows().get(i).get("ChassisId") == null ?
		// "" : dt.get_Rows().get(i).get("ChassisId").toString();
		// if(Integer.parseInt(strChassisId)>=nMaxID){
		// nMaxID=Integer.parseInt(strChassisId);
		// }
		// }
		// }
		return nMaxID;
	}

	public static String InsertShelfInfoToTable(ISiteviewApi api, int nChassID, String strChelfNum,
			List<SnmkRequest> listTemp) {
		String Result = "error";
		String strTime = "";
		ShelfInfo ShelfTemp = new ShelfInfo();
		ProcessTableData.GetParseShelfInfo(ShelfTemp, listTemp);
		strTime = CommonFunction.GetCurrentTime();

		// 要执行的SQL语句
		// sql = "insert into
		// shelfinfo(ChasId,ShelfId,Level,ShelfName,ShelfpsuA,ShelfpsuB"
		// +
		// "ShelfvolA,ShelfvolB,Shelffan,Shelftemperature,ShelfcoCardNum,ShelfrmtCardNum,"
		// + "OnlineState,UpdateTime)
		// values('"+nChassID+"','"+strChelfNum+"','"+2+"','"+
		// ShelfTemp.getShelfName()+"','"+ShelfTemp.getShelfpsuA()+"','"+ShelfTemp.getShelfpsuB()+
		// "','"+ShelfTemp.getShelfvolA()+"','"+ShelfTemp.getShelfvolB()+"','"+
		// ShelfTemp.getShelffan()+"','"+ShelfTemp.getShelftemperature()+"','"+
		// ShelfTemp.getShelfcoCardNum()+"','"+ShelfTemp.getShelfrmtCardNum()+"','"
		// + 1+"','"+strTime+"');";
		String strsql = String.format(
				"INSERT INTO shelfinfo(RecId,LastModDateTime,CreatedDateTime,ChasId,ShelfId,Level,ShelfName,ShelfpsuA,ShelfpsuB,"
						+ "ShelfvolA,ShelfvolB,Shelffan,Shelftemperature,ShelfcoCardNum,ShelfrmtCardNum,OnlineState,UpdateTime)VALUES(UPPER(REPLACE(UUID(),'-','')),"
						+ "NOW(),NOW(),%d,%d,%d,%d,'%s','%s','%s','%s','%s','%s',%d,%d,%d,'%s')",
				nChassID, Integer.parseInt(strChelfNum), 2, Integer.parseInt(ShelfTemp.getShelfName()),
				ShelfTemp.getShelfpsuA(), ShelfTemp.getShelfpsuB(), ShelfTemp.getShelfvolA(), ShelfTemp.getShelfvolB(),
				ShelfTemp.getShelffan(), ShelfTemp.getShelftemperature(),
				Integer.parseInt(ShelfTemp.getShelfcoCardNum()), Integer.parseInt(ShelfTemp.getShelfrmtCardNum()), 1,
				strTime);
		try {
			// DBQueryUtils.UpdateorDelete(strsql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// System.out.println("insert into shelfinfo SUCCESS!");
			Result = "OK";
		}
		return Result;
	}

	public static void InsertSlotInfoToTable(ISiteviewApi api, int nChassID, Map<String, List<String>> mapTemp) {
		String strShelfNum = "", strSlotNum = "", strSlotType = "", strValue = "";
		String sql = "", strTime = "", strAlarm = "";
		int nResult = 0;
		strTime = CommonFunction.GetCurrentTime();
		Iterator iter1 = mapTemp.keySet().iterator();
		for (; iter1.hasNext();) {
			SlotInfo Slotinfo = new SlotInfo();
			String key = (String) iter1.next();
			strShelfNum = key.substring(0, key.indexOf("."));
			strSlotNum = key.substring(key.indexOf(".") + 1);

			List<String> listInfo = (List<String>) mapTemp.get(key);
			for (int i = 0; i < listInfo.size(); i++) {
				strSlotType = listInfo.get(i).substring(0, listInfo.get(i).indexOf(":"));
				strValue = listInfo.get(i).substring(listInfo.get(i).indexOf(":") + 1);
				ProcessTableData.GetParseSlotInfo(strSlotType, strValue, Slotinfo);
			}

			try {
				// 要执行的SQL语句
				sql = String.format(
						"INSERT INTO slotinfo(RecId,LastModDateTime,CreatedDateTime,ChasId,ShelfId,SlotId,Level,"
								+ "LocalCardType,RemoteCardType,OnlineState,UpdateTime)VALUES(UPPER(REPLACE(UUID(),'-','')),"
								+ "NOW(),NOW(),%d,%d,%d,%d,%d,%d,%d,'%s')",
						nChassID, Integer.parseInt(strShelfNum), Integer.parseInt(strSlotNum), 3,
						Integer.parseInt(Slotinfo.getLocalCardType()), Integer.parseInt(Slotinfo.getRemoteCardType()),
						1, strTime);

				// sql = "insert into
				// slotinfo(ChasId,ShelfId,SlotId,Level,LocalCardType,"
				// + "RemoteCardType,OnlineState,UpdateTime) values('" +
				// nChassID + "','" + Slotinfo.getShelfId()
				// + "','" + Slotinfo.getSlotId() + "','" + 3 + "','" +
				// Slotinfo.getLocalCardType() + "','"
				// + Slotinfo.getRemoteCardType() + "','" + 1 + "','" + strTime
				// + "');";
				// DBQueryUtils.UpdateorDelete(sql, api);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				// System.out.println("insert into slotinfo SUCCESS!");
			}
		}
	}

	public static void InsertCardInfoToTable(ISiteviewApi api, int nChassID, Map<String, List<String>> mapTemp) {
		String strShelfNum = "", strSlotNum = "", strSlotType = "", strValue = "";
		String sql = "", strTime = "", strAlarm = "";
		int nResult = 0, CardID = 0;
		strTime = CommonFunction.GetCurrentTime();
		Iterator iter1 = mapTemp.keySet().iterator();
		for (; iter1.hasNext();) {
			CardInfo LocalCardInfo = new CardInfo();
			CardInfo RemoteCardInfo = new CardInfo();

			String key = (String) iter1.next();
			strShelfNum = key.substring(0, key.indexOf("."));
			strSlotNum = key.substring(key.indexOf(".") + 1);

			List<String> listInfo = (List<String>) mapTemp.get(key);
			for (int i = 0; i < listInfo.size(); i++) {
				strSlotType = listInfo.get(i).substring(0, listInfo.get(i).indexOf(":"));
				strValue = listInfo.get(i).substring(listInfo.get(i).indexOf(":") + 1);
				ProcessTableData.GetParseCardInfo(strSlotType, strValue, LocalCardInfo, RemoteCardInfo);
			}
			if (LocalCardInfo.getCardType().equals("0")) {

			} else if (LocalCardInfo.getCardType().equals("34")) {
				CardID = 1;
				InsertToCardInfoTable(api, nChassID, CardID, LocalCardInfo);
			}
			if (RemoteCardInfo.getCardType().equals("0")) {

			} else if (RemoteCardInfo.getCardType().equals("35")) {
				CardID = 2;
				InsertToCardInfoTable(api, nChassID, CardID, RemoteCardInfo);
			}
		}
	}

	public static void InsertToCardInfoTable(ISiteviewApi api, int ChasID, int CardID, CardInfo cardTemp) {
		String sql = "", strTime = "";
		int nResult = 0;
		strTime = CommonFunction.GetCurrentTime();
		try {
			// 要执行的SQL语句
			// sql = "insert into
			// cardinfo(ChasId,ShelfId,SlotId,CardId,Level,CardType,"
			// + "Fxlink,LFPState,OnlineState,UpdateTime) values('" +ChasID+
			// "','"+cardTemp.getShelfId()+"','"+cardTemp.getSlotId()+"','"+
			// CardID+"','"+4+cardTemp.getCardType()+"','"+cardTemp.getFxlink()+
			// "','"+cardTemp.getLFPState()+"','"+1+"','"+strTime+"');";
			sql = String.format(
					"INSERT INTO cardinfo(RecId,LastModDateTime,CreatedDateTime,ChasId,ShelfId,SlotId,"
							+ "CardId,Level,CardType,Fxlink,LFPState,OnlineState,UpdateTime)VALUES(UPPER(REPLACE(UUID(),'-','')),"
							+ "NOW(),NOW(),%d,%d,%d,%d,%d,%d,'%s','%s',%d,'%s')",
					ChasID, Integer.parseInt(cardTemp.getShelfId()), Integer.parseInt(cardTemp.getSlotId()), CardID, 4,
					Integer.parseInt(cardTemp.getCardType()), Integer.parseInt(cardTemp.getFxlink()),
					Integer.parseInt(cardTemp.getLFPState()), 1, strTime);
			// DBQueryUtils.UpdateorDelete(sql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// System.out.println("insert into cardinfo SUCCESS!");
		}

	}

	public static void InsertCardStateInfoToTable(ISiteviewApi api, int ChassID, Map<String, List<String>> mapTemp) {
		String strShelfNum = "", strSlotNum = "", strSlotType = "", strValue = "";
		String sql = "", strTime = "";
		int nResult = 0, CardID = 0;
		strTime = CommonFunction.GetCurrentTime();
		Iterator iter1 = mapTemp.keySet().iterator();
		for (; iter1.hasNext();) {
			CardInfo LocalCardInfo = new CardInfo();
			CardInfo RemoteCardInfo = new CardInfo();
			// SlotInfo Slotinfo=new SlotInfo();
			String key = (String) iter1.next();
			strShelfNum = key.substring(0, key.indexOf("."));
			strSlotNum = key.substring(key.indexOf(".") + 1);

			List<String> listInfo = (List<String>) mapTemp.get(key);
			for (int i = 0; i < listInfo.size(); i++) {
				strSlotType = listInfo.get(i).substring(0, listInfo.get(i).indexOf(":"));
				strValue = listInfo.get(i).substring(listInfo.get(i).indexOf(":") + 1);
				ProcessTableData.GetParseCardStateInfo(strSlotType, strValue, LocalCardInfo, RemoteCardInfo);
			}
			// 根据具体卡槽号查询是否已经有本地卡和远端卡
			if (!QueryCardType(api, ChassID, strShelfNum, strSlotNum, "34").equals("")) {
				UpdateCardStateToCardInfoTable(api, ChassID, strShelfNum, strSlotNum, "34", LocalCardInfo);
			}
			if (!QueryCardType(api, ChassID, strShelfNum, strSlotNum, "35").equals("")) {
				UpdateCardStateToCardInfoTable(api, ChassID, strShelfNum, strSlotNum, "35", RemoteCardInfo);
			}
		}
	}

	public static String QueryCardType(ISiteviewApi api, int Chaid, String Sheid, String Slotid, String type) {
		String sql = "", strResult = "";
		try {
			// 要执行的SQL语句
			sql = "SELECT CardId from cardinfo where ChasId='" + Chaid + "' and ShelfId='" + Sheid + "' and SlotId='"
					+ Slotid + "' and CardType='" + type + "';";
			// DataTable dTable = DBUtils.select(sql, api);
			// if(dTable!=null){
			// for(DataRow dRow : dTable.get_Rows())
			// {
			// strResult = dRow.get("CardId") == null ? "" :
			// dRow.get("CardId").toString();
			// break;
			// }
			// }

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// System.out.println("SELECT CardID from cardinfo is SUCCESS!");
		}
		return strResult;
	}

	public static int UpdateCardStateToCardInfoTable(ISiteviewApi api, int Chaid, String Sheid, String Slotid,
			String type, CardInfo cardTemp) {
		String sql = "";
		int nResult = 0;
		try {
			// 要执行的SQL语句
			sql = "update cardinfo set Mode1='" + cardTemp.getMode1() + "' ,Txlink1='" + cardTemp.getTxlink1()
					+ "' ,CurMode1='" + cardTemp.getCurMode1() + "' ,Mode2='" + cardTemp.getMode2() + "' ,Txlink2='"
					+ cardTemp.getTxlink2() + "' ,CurMode2='" + cardTemp.getCurMode2() + "' ,Mode3='"
					+ cardTemp.getMode3() + "' ,Txlink3='" + cardTemp.getTxlink3() + "' ,CurMode3='"
					+ cardTemp.getCurMode3() + "' ,Mode4='" + cardTemp.getMode4() + "' ,Txlink4='"
					+ cardTemp.getTxlink4() + "' ,CurMode4='" + cardTemp.getCurMode4() + "'where ChasId='" + Chaid
					+ "' and ShelfId='" + Sheid + "' and SlotId='" + Slotid + "' and CardType='" + type + "';";
			// DBQueryUtils.UpdateorDelete(sql, api);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// System.out.println("update cardinfo is SUCCESS!");
			nResult = 1;
		}
		return nResult;
	}

	public static boolean JudgeChassisIsOnline(ISiteviewApi api, String IP) {
		boolean bResult = false;
		String strsql = "select ChassisId from ChassisInfo where ChassisIP='" + IP + "';";
		// DataTable dt=DBQueryUtils.Select(strsql, api);
		// if(dt !=null){
		// for(int i=0;i<dt.get_Rows().size();i++)
		// {
		// String strChassisId = dt.get_Rows().get(i).get("ChassisId") == null ?
		// "" : dt.get_Rows().get(i).get("ChassisId").toString();
		// bResult=true;
		// }
		// }
		return bResult;
	}

	public static String GetAllShowChassisInfo(ISiteviewApi api, List<AddDeviceBackStructure> listTemp) {
		String strChasId = "", strCivilcode = "", strBrand = "", strChassisIP = "", strShelfNum = "",
				strOnlineState = "";
		String strResult = "Error";
		String strChassql = "select ChassisId,Civilcode,Brand,ChassisIP,ShelfNum,OnlineState" + " from ChassisInfo;";
		// DataTable dt=DBQueryUtils.Select(strChassql, api);
		// if(dt !=null){
		// for(int i=0;i<dt.get_Rows().size();i++)
		// {
		// AddDeviceBackStructure ChasTemp=new AddDeviceBackStructure();
		// strChasId = dt.get_Rows().get(i).get("ChassisId") == null ? "" :
		// dt.get_Rows().get(i).get("ChassisId").toString();
		// strCivilcode=dt.get_Rows().get(i).get("Civilcode") == null ? "" :
		// dt.get_Rows().get(i).get("Civilcode").toString();
		// strBrand=dt.get_Rows().get(i).get("Brand") == null ? "" :
		// dt.get_Rows().get(i).get("Brand").toString();
		// strChassisIP=dt.get_Rows().get(i).get("ChassisIP") == null ? "" :
		// dt.get_Rows().get(i).get("ChassisIP").toString();
		// strShelfNum=dt.get_Rows().get(i).get("ShelfNum") == null ? "" :
		// dt.get_Rows().get(i).get("ShelfNum").toString();
		// strOnlineState=dt.get_Rows().get(i).get("OnlineState") == null ? "" :
		// dt.get_Rows().get(i).get("OnlineState").toString();
		// ChasTemp.setChassisId(strChasId);
		// ChasTemp.setCivilcode(GetAreaNameByCivilcode(api,strCivilcode));
		// ChasTemp.setBrand(strBrand);
		// ChasTemp.setChassisIP(strChassisIP);
		// ChasTemp.setShelfNum(strShelfNum);
		// ChasTemp.setChassisState(strOnlineState);
		// ChasTemp.setChassisName("机箱"+strChasId+"("+strChassisIP+")");
		// listTemp.add(ChasTemp);
		// // strShelfNum = dt.get_Rows().get(i).get("ShelfNum") == null ? "" :
		// dt.get_Rows().get(i).get("ShelfNum").toString();
		// }
		// strResult="OK";
		// }
		return strResult;
	}

	public static String GetAllShowShelfInfo(ISiteviewApi api, List<AddDeviceBackStructure> listTemp) {

		String strChaId = "", strShelfId = "", strLevel = "", strOnlineState = "";
		String strResult = "Error";

		for (int i = 0; i < listTemp.size(); i++) {
			strChaId = listTemp.get(i).getChassisId();
			String strShelfsql = "select ShelfId,Level,OnlineState from Shelfinfo where ChasId='" + strChaId + "';";
			// DataTable dt=DBQueryUtils.Select(strShelfsql, api);
			// if(dt !=null){
			// List<ShelfState> listShelf=new ArrayList<ShelfState>();
			// for(int j=0;j<dt.get_Rows().size();j++)
			// {
			// ShelfState ShelfInfo=new ShelfState();
			// strShelfId = dt.get_Rows().get(i).get("ShelfId") == null ? "" :
			// dt.get_Rows().get(i).get("ShelfId").toString();
			// strLevel=dt.get_Rows().get(i).get("Level") == null ? "" :
			// dt.get_Rows().get(i).get("Level").toString();
			// strOnlineState=dt.get_Rows().get(i).get("OnlineState") == null ?
			// "" : dt.get_Rows().get(i).get("OnlineState").toString();
			// ShelfInfo.setShelfId(strShelfId);
			// ShelfInfo.setLevel(strLevel);
			// ShelfInfo.setShelfState(strOnlineState);
			// ShelfInfo.setShelfName("机架"+strShelfId);
			// listShelf.add(ShelfInfo);
			// }
			// listTemp.get(i).setLst(listShelf);
			// strResult="OK";
			// }
		}
		return strResult;
	}

	private static String GetAreaNameByCivilcode(ISiteviewApi api, String strCode) {
		String strResult = "其他";
		String sql = "select Name from area where Code='" + strCode + "';";
		// DataTable dt=DBQueryUtils.Select(sql, api);
		// if(dt !=null){
		// for(int j=0;j<dt.get_Rows().size();j++)
		// {
		// strResult=dt.get_Rows().get(j).get("Name") == null ? "" :
		// dt.get_Rows().get(j).get("Name").toString();
		// }
		// }
		return strResult;
	}

	public static String ReStartShelfAllInfoByID(ISiteviewApi api, String ChasId, String ShelfId) {
		String sql = "", Result = "Error";
		try {
			// 要执行的SQL语句
			sql = "update shelfinfo a,slotinfo b,cardinfo c Set a.OnlineState='" + 1 + "',b.OnlineState='" + 1
					+ "',c.OnlineState='" + 1 + "' where a.ChasId='" + ChasId + "' and a.ShelfId='" + ShelfId
					+ "' and b.ChasId=a.ChasId and b.ShelfId=a.ShelfId "
					+ " and c.ChasId=b.ChasId and c.ShelfId=b.ShelfId;";
			// DBQueryUtils.UpdateorDelete(sql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// System.out.println("Sql:"+sql+".is SUCCESS!");
			Result = "OK";
		}
		return Result;
	}

	public static String ReStartSlotAllInfoByID(ISiteviewApi api, String ChasId, String ShelfId, String SlotId) {
		String sql = "", Result = "Error";
		try {
			// 要执行的SQL语句
			sql = "update slotinfo b,cardinfo c Set b.OnlineState='" + 1 + "',c.OnlineState='" + 1
					+ "' where b.ChasId='" + ChasId + "' and b.ShelfId='" + ShelfId + "' and b.SlotId='" + SlotId
					+ "' and c.ChasId=b.ChasId and c.ShelfId=b.ShelfId and c.SlotId=b.SlotId;";
			// DBQueryUtils.UpdateorDelete(sql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// System.out.println("Sql:"+sql+".is SUCCESS!");
			Result = "OK";
		}
		return Result;
	}

	public static String DelteChassisAllInfoByChasID(ISiteviewApi api, String ChasId) {
		String sql = "", Result = "error";
		try {
			// 要执行的SQL语句
			sql = "delete ch,sh,sl,ca from chassisinfo ch,shelfinfo sh,slotinfo sl,cardinfo ca WHERE"
					+ " ch.ChassisId='" + ChasId + "' and sh.ChasId=ch.ChassisId and sl.ChasId=sh.ChasId"
					+ " and ca.ChasId=sl.ChasId";
			// DBQueryUtils.UpdateorDelete(sql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// System.out.println("Sql:"+sql+".is SUCCESS!");
			Result = "OK";
		}
		return Result;
	}

	public static String SetShelfAllInfoByID(ISiteviewApi api, String ChasId, String ShelfId) {
		String sql = "", Result = "Error";
		try {
			// 要执行的SQL语句
			sql = "update shelfinfo a,slotinfo b,cardinfo c Set a.OnlineState='" + -1 + "',b.OnlineState='" + -1
					+ "',c.OnlineState='" + -1 + "' where a.ChasId='" + ChasId + "' and a.ShelfId='" + ShelfId
					+ "' and b.ChasId=a.ChasId and b.ShelfId= "
					+ "a.ShelfId and c.ChasId=b.ChasId and c.ShelfId=b.ShelfId;";
			// DBQueryUtils.UpdateorDelete(sql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// System.out.println("Sql:"+sql+".is SUCCESS!");
			Result = "OK";
		}
		return Result;
	}

	public static String SetSlotAllInfoByID(ISiteviewApi api, String ChasId, String ShelfId, String SlotId) {
		String sql = "", Result = "Error";
		try {
			// 要执行的SQL语句
			sql = "update slotinfo b,cardinfo c Set b.OnlineState='" + -1 + "',c.OnlineState='" + -1
					+ "' where b.ChasId='" + ChasId + "' and b.ShelfId='" + ShelfId + "' and b.SlotId='" + SlotId
					+ "' and c.ChasId=b.ChasId and c.ShelfId=b.ShelfId and c.SlotId=b.SlotId;";
			// DBQueryUtils.UpdateorDelete(sql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// System.out.println("Sql:"+sql+".is SUCCESS!");
			Result = "OK";
		}
		return Result;
	}		   
		   
	public static String GetClickShelfInfo(ISiteviewApi api,String ChasId,String SchelfId,
			List<BackShelfStructure> listTemp){
		String strResult="error",strValue="";
		String sql="select * from shelfinfo where chasId='"+ChasId+"' and ShelfId='"+
			SchelfId+"';";
		BackShelfStructure ShelfV=new BackShelfStructure();
//		DataTable dt=DBQueryUtils.Select(sql, api);
//		if(dt !=null){
//			for(int j=0;j<dt.get_Rows().size();j++)
//			{
//				ShelfInfo shelf=new ShelfInfo();
//				strValue=dt.get_Rows().get(j).get("ChasId") == null ? "" : dt.get_Rows().get(j).get("ChasId").toString();	              
//				shelf.setChasId(strValue);
//			    strValue=dt.get_Rows().get(j).get("ShelfId") == null ? "" : dt.get_Rows().get(j).get("ShelfId").toString();	              
//			    shelf.setShelfId(strValue);
//			    strValue=dt.get_Rows().get(j).get("Level") == null ? "" : dt.get_Rows().get(j).get("Level").toString();	              
//			    shelf.setLevel(strValue);
//			    strValue=dt.get_Rows().get(j).get("ShelfName") == null ? "" : dt.get_Rows().get(j).get("ShelfName").toString();	              				
//			    shelf.setShelfName(strValue);
//			    strValue=dt.get_Rows().get(j).get("ShelfpsuA") == null ? "" : dt.get_Rows().get(j).get("ShelfpsuA").toString();	              
//			    shelf.setShelfpsuA(ZXResultExplain.ExplainShelfInfo("psuA",strValue));
//			    strValue=dt.get_Rows().get(j).get("ShelfpsuB") == null ? "" : dt.get_Rows().get(j).get("ShelfpsuB").toString();	              
//			    shelf.setShelfpsuB(ZXResultExplain.ExplainShelfInfo("psuB",strValue));
//			    strValue=dt.get_Rows().get(j).get("ShelfvolA") == null ? "" : dt.get_Rows().get(j).get("ShelfvolA").toString();	              
//			    shelf.setShelfvolA(ZXResultExplain.ExplainShelfInfo("volA",strValue));
//			    strValue=dt.get_Rows().get(j).get("ShelfvolB") == null ? "" : dt.get_Rows().get(j).get("ShelfvolB").toString();	              
//			    shelf.setShelfvolB(ZXResultExplain.ExplainShelfInfo("volB",strValue));
//			    strValue=dt.get_Rows().get(j).get("Shelffan") == null ? "" : dt.get_Rows().get(j).get("Shelffan").toString();	              
//			    shelf.setShelffan(ZXResultExplain.ExplainShelfInfo("fan",strValue));
//			    strValue=dt.get_Rows().get(j).get("Shelftemperature") == null ? "" : dt.get_Rows().get(j).get("Shelftemperature").toString();	              
//			    shelf.setShelftemperature(strValue);
//			    strValue=dt.get_Rows().get(j).get("ShelfcoCardNum") == null ? "" : dt.get_Rows().get(j).get("ShelfcoCardNum").toString();	              
//			    shelf.setShelfcoCardNum(strValue);
//			    strValue=dt.get_Rows().get(j).get("ShelfrmtCardNum") == null ? "" : dt.get_Rows().get(j).get("ShelfrmtCardNum").toString();	              
//			    shelf.setShelfrmtCardNum(strValue);
//			    strValue=dt.get_Rows().get(j).get("OnlineState") == null ? "" : dt.get_Rows().get(j).get("OnlineState").toString();	              
//			    shelf.setShelfOnlineState(strValue);
//			    ShelfV.setShelf(shelf);
//			}	
//			listTemp.add(ShelfV);
//			strResult="OK";
//		}	
		return strResult;
	}
	
	public static String GetCardStateByClickShelf(ISiteviewApi api,List<BackShelfStructure> listTemp){
		
		String ChasId="",SchelfId="",Result="Error";
		String strSlotId="",strLevel="",strOnline="";
		
		for(int k=0;k<listTemp.size();k++){
			ChasId=listTemp.get(k).getShelf().getChasId();
			SchelfId=listTemp.get(k).getShelf().getShelfId();
			String sql="select SlotId,Level,OnlineState from slotinfo where ChasId='"+ChasId+"' and ShelfId='"+
					SchelfId+"';";
//			DataTable dt=DBQueryUtils.Select(sql, api);
//			if(dt !=null){
//				List<SlotState> listSlot=new ArrayList<SlotState>();
//				for(int j=0;j<dt.get_Rows().size();j++)
//				{				
//					strSlotId=dt.get_Rows().get(j).get("SlotId") == null ? "" : dt.get_Rows().get(j).get("SlotId").toString();	              
//					strLevel=dt.get_Rows().get(j).get("Level") == null ? "" : dt.get_Rows().get(j).get("Level").toString();	              
//					strOnline=dt.get_Rows().get(j).get("OnlineState") == null ? "" : dt.get_Rows().get(j).get("OnlineState").toString();	              				
//				//	if(Integer.parseInt(strSlotId)>0 && Integer.parseInt(strSlotId)<17){
//					if(Integer.parseInt(strSlotId)>0){
//						SlotState SlotstateInfo=new SlotState();	
//						SlotstateInfo.setSlotId(strSlotId);
//						SlotstateInfo.setLevel(strLevel);
//						SlotstateInfo.setSlotState(strOnline);		
//						SlotstateInfo.setLocalCardState("-2");
//						SlotstateInfo.setRemoteCardState("-2");
//						String cardID="",CardState="";
//						String Cardsql="select CardId,OnlineState from cardinfo where ChasId='"+ChasId+"' and ShelfId='"+
//						        SchelfId+"' and SlotId='"+strSlotId+"';";
//						DataTable dt1=DBQueryUtils.Select(Cardsql, api);
//						if(dt1!=null){
//							for(int i=0;i<dt1.get_Rows().size();i++){
//								cardID=dt1.get_Rows().get(i).get("CardId") == null ? "" : dt1.get_Rows().get(i).get("CardId").toString();	
//								CardState=dt1.get_Rows().get(i).get("OnlineState") == null ? "" : dt1.get_Rows().get(i).get("OnlineState").toString();	
//								if(Integer.parseInt(cardID)==1){ //本地卡
//									SlotstateInfo.setLocalCardState(CardState);
//								}else if(Integer.parseInt(cardID)==2){
//									SlotstateInfo.setRemoteCardState(CardState);
//								}
//							}
//						}
//						listSlot.add(SlotstateInfo);
//				//		listTemp.get(k).lstSlotState.add(SlotstateInfo);
//					}				
//				}
//				listTemp.get(k).setLstSlotState(listSlot);
//				Result="OK";
//			}		
		}	
		return Result;
	}
	
	static String GetClickSlotInfo(ISiteviewApi api,String ChaID,String ShelfID,
			String SlotID,List<BackSlotStructure> listTemp){
		String strResult="Error",strValue="";
		String sql="select * from slotinfo where chasId='"+ChaID+"' and ShelfId='"+
				ShelfID+"' and SlotId='"+SlotID+"';";
		BackSlotStructure SlotV=new BackSlotStructure();
//		DataTable dt=DBQueryUtils.Select(sql, api);
//		if(dt !=null){
//			for(int j=0;j<dt.get_Rows().size();j++)
//			{
//				SlotInfo slot=new SlotInfo();
//				strValue=dt.get_Rows().get(j).get("chasId") == null ? "" : dt.get_Rows().get(j).get("ChasId").toString();	              
//				slot.setChasId(strValue);
//			    strValue=dt.get_Rows().get(j).get("ShelfId") == null ? "" : dt.get_Rows().get(j).get("ShelfId").toString();	              
//			    slot.setShelfId(strValue);
//			    strValue=dt.get_Rows().get(j).get("SlotId") == null ? "" : dt.get_Rows().get(j).get("SlotId").toString();	              
//			    slot.setSlotId(strValue);
//			    strValue=dt.get_Rows().get(j).get("Level") == null ? "" : dt.get_Rows().get(j).get("Level").toString();	              
//			    slot.setLevel(strValue);
//			    strValue=dt.get_Rows().get(j).get("LocalCardType") == null ? "" : dt.get_Rows().get(j).get("LocalCardType").toString();	              				
//			    slot.setLocalCardType(ZXResultExplain.ExplainSlotInfo("CardType",strValue));
//			    strValue=dt.get_Rows().get(j).get("RemoteCardType") == null ? "" : dt.get_Rows().get(j).get("RemoteCardType").toString();	              
//			    slot.setRemoteCardType(ZXResultExplain.ExplainSlotInfo("CardType",strValue));
//			    strValue=dt.get_Rows().get(j).get("OnlineState") == null ? "" : dt.get_Rows().get(j).get("OnlineState").toString();	              
//			    slot.setOnlineState(strValue);
//			    SlotV.setSlot(slot);
//			    
//				listTemp.add(SlotV);
//				strResult="OK";
//			}		
//		}	
		return strResult;
	}
	
public static String GetCardInfoByClickSlot(ISiteviewApi api,List<BackSlotStructure> listTemp){
		
		String ChasId="",SchelfId="",SlotId="",Result="Error";
		String strValue="";
		
		for(int k=0;k<listTemp.size();k++){
			ChasId=listTemp.get(k).getSlot().getChasId();
			SchelfId=listTemp.get(k).getSlot().getShelfId();
			SlotId=listTemp.get(k).getSlot().getSlotId();
			String sql="select * from cardinfo where ChasId='"+ChasId+"' and ShelfId='"+
					SchelfId+"' and SlotId='"+SlotId+"';";
//			DataTable dt=DBQueryUtils.Select(sql, api);
//			if(dt !=null){
//				List<CardInfo> listCard=new ArrayList<CardInfo>();
//				for(int j=0;j<dt.get_Rows().size();j++)
//				{	
//					CardInfo Cardinfo=new CardInfo();	
//					strValue=dt.get_Rows().get(j).get("ChasId") == null ? "" : dt.get_Rows().get(j).get("ChasId").toString();
//					Cardinfo.setChasId(strValue);
//					strValue=dt.get_Rows().get(j).get("ShelfId") == null ? "" : dt.get_Rows().get(j).get("ShelfId").toString();
//					Cardinfo.setShelfId(strValue);
//					strValue=dt.get_Rows().get(j).get("SlotId") == null ? "" : dt.get_Rows().get(j).get("SlotId").toString();
//					Cardinfo.setSlotId(strValue);
//					strValue=dt.get_Rows().get(j).get("CardId") == null ? "" : dt.get_Rows().get(j).get("CardId").toString();
//					Cardinfo.setCardId(strValue);
//					strValue=dt.get_Rows().get(j).get("Level") == null ? "" : dt.get_Rows().get(j).get("Level").toString();
//					Cardinfo.setLevel(strValue);
//					strValue=dt.get_Rows().get(j).get("CardType") == null ? "" : dt.get_Rows().get(j).get("CardType").toString();
//					Cardinfo.setCardType(ZXResultExplain.ExplainCardInfo("CardType",strValue));
//					strValue=dt.get_Rows().get(j).get("Fxlink") == null ? "" : dt.get_Rows().get(j).get("Fxlink").toString();
//					Cardinfo.setFxlink(ZXResultExplain.ExplainCardInfo("Fxlink",strValue));
//					strValue=dt.get_Rows().get(j).get("LFPState") == null ? "" : dt.get_Rows().get(j).get("LFPState").toString();
//					Cardinfo.setLFPState(ZXResultExplain.ExplainCardInfo("LFPState",strValue));
//					strValue=dt.get_Rows().get(j).get("Mode1") == null ? "" : dt.get_Rows().get(j).get("Mode1").toString();
//					Cardinfo.setMode1(ZXResultExplain.ExplainCardInfo("Mode1",strValue));
//					strValue=dt.get_Rows().get(j).get("Txlink1") == null ? "" : dt.get_Rows().get(j).get("Txlink1").toString();
//					Cardinfo.setTxlink1(ZXResultExplain.ExplainCardInfo("Txlink1",strValue));
//					strValue=dt.get_Rows().get(j).get("CurMode1") == null ? "" : dt.get_Rows().get(j).get("CurMode1").toString();
//					Cardinfo.setCurMode1(ZXResultExplain.ExplainCardInfo("CurMode1",strValue));
//					strValue=dt.get_Rows().get(j).get("Mode2") == null ? "" : dt.get_Rows().get(j).get("Mode2").toString();
//					Cardinfo.setMode2(ZXResultExplain.ExplainCardInfo("Mode2",strValue));
//					strValue=dt.get_Rows().get(j).get("Txlink2") == null ? "" : dt.get_Rows().get(j).get("Txlink2").toString();
//					Cardinfo.setTxlink2(ZXResultExplain.ExplainCardInfo("Txlink2",strValue));
//					strValue=dt.get_Rows().get(j).get("CurMode2") == null ? "" : dt.get_Rows().get(j).get("CurMode2").toString();
//					Cardinfo.setCurMode2(ZXResultExplain.ExplainCardInfo("CurMode2",strValue));
//					strValue=dt.get_Rows().get(j).get("Mode3") == null ? "" : dt.get_Rows().get(j).get("Mode3").toString();
//					Cardinfo.setMode3(ZXResultExplain.ExplainCardInfo("Mode3",strValue));
//					strValue=dt.get_Rows().get(j).get("Txlink3") == null ? "" : dt.get_Rows().get(j).get("Txlink3").toString();
//					Cardinfo.setTxlink3(ZXResultExplain.ExplainCardInfo("Txlink3",strValue));
//					strValue=dt.get_Rows().get(j).get("CurMode3") == null ? "" : dt.get_Rows().get(j).get("CurMode3").toString();
//					Cardinfo.setCurMode3(ZXResultExplain.ExplainCardInfo("CurMode3",strValue));
//					strValue=dt.get_Rows().get(j).get("Mode4") == null ? "" : dt.get_Rows().get(j).get("Mode4").toString();
//					Cardinfo.setMode4(ZXResultExplain.ExplainCardInfo("Mode4",strValue));
//					strValue=dt.get_Rows().get(j).get("Txlink4") == null ? "" : dt.get_Rows().get(j).get("Txlink4").toString();
//					Cardinfo.setTxlink4(ZXResultExplain.ExplainCardInfo("Txlink4",strValue));
//					strValue=dt.get_Rows().get(j).get("CurMode4") == null ? "" : dt.get_Rows().get(j).get("CurMode4").toString();             
//					Cardinfo.setCurMode4(ZXResultExplain.ExplainCardInfo("CurMode4",strValue));
//					strValue=dt.get_Rows().get(j).get("OnlineState") == null ? "" : dt.get_Rows().get(j).get("OnlineState").toString();	              				
//					Cardinfo.setOnlineState(strValue);
//					listCard.add(Cardinfo);			
//				}
//				listTemp.get(k).setLstCardinfo(listCard);
//				Result="OK";
//			}		
		}	
		return Result;
	}
	
public static String GetChassisIDByIP(ISiteviewApi api,String ChasIP){
		String sql="",Result="";
		try {
			// 要执行的SQL语句
		    sql="select ChassisId from chassisinfo where chassisIP='"+ChasIP+"';";
//			DataTable dt=DBQueryUtils.Select(sql, api);
//			if(dt !=null){
//				for(int j=0;j<dt.get_Rows().size();j++)
//				{
//					Result=dt.get_Rows().get(j).get("ChassisId") == null ? "" : dt.get_Rows().get(j).get("ChassisId").toString();	              
//				}
//			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
//			System.out.println("Sql:"+sql+".is SUCCESS!");
		}
		return Result;
	}
 
public static boolean JudgeSlotIsOnlineFromTrap(ISiteviewApi api,String ChasId,String ShelfId,String SlotId){
		String sql="",value="";
		boolean Result=false;
		try {
			// 要执行的SQL语句
			sql=String.format("select COUNT(*) as count from slotinfo where ChasId=%s and ShelfId=%s and SlotId=%s", ChasId,ShelfId,SlotId);

//			DataTable dt=DBQueryUtils.Select(sql, api);
//			if(dt !=null){
//				for(int j=0;j<dt.get_Rows().size();j++)
//				{
//					value=dt.get_Rows().get(j).get("count") == null ? "" : dt.get_Rows().get(j).get("count").toString();	              
//				}
//				if(!value.equals("")&&Integer.parseInt(value)>0){
//					Result=true;
//				}
//			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
//			System.out.println("Sql:"+sql+".is SUCCESS!");
		}
		return Result;
	}

public static String SetSlotAllInfoState(ISiteviewApi api,String ChasId,String ShelfId,
	     String SlotId,String State){
			String sql="",Result="Error";
			try {
				// 要执行的SQL语句
				if(State.equals("Up")){
					 sql="update slotinfo b,cardinfo c Set b.OnlineState='"+1+"',c.OnlineState='"+1+
						    	"' where b.ChasId='"+ChasId+"' and b.ShelfId='"+ShelfId+"' and b.SlotId='"+
						    	SlotId+"' and c.ChasId=b.ChasId and c.ShelfId=b.ShelfId and c.SlotId=b.SlotId;";
				}else if(State.equals("down")){
					 sql="update slotinfo b,cardinfo c Set b.OnlineState='"+0+"',c.OnlineState='"+0+
						    	"' where b.ChasId='"+ChasId+"' and b.ShelfId='"+ShelfId+"' and b.SlotId='"+
						    	SlotId+"' and c.ChasId=b.ChasId and c.ShelfId=b.ShelfId and c.SlotId=b.SlotId;";
				}
	//			DBQueryUtils.UpdateorDelete(sql, api);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
//				System.out.println("Sql:"+sql+".is SUCCESS!");
				Result="OK";
			}
		   return Result;
	 }

public static boolean JudgeCardIsOnlineFromTrap(ISiteviewApi api,String ChasId,String ShelfId,String SlotId){
		String sql="",value="";
		boolean Result=false;
		try {
			// 要执行的SQL语句
			sql=String.format("select COUNT(*) as count from cardinfo where ChasId=%s and ShelfId=%s and SlotId=%s and "
					+ "CardType=%d", ChasId,ShelfId,SlotId,35);

//			DataTable dt=DBQueryUtils.Select(sql, api);
//			if(dt !=null){
//				for(int j=0;j<dt.get_Rows().size();j++)
//				{
//					value=dt.get_Rows().get(j).get("count") == null ? "" : dt.get_Rows().get(j).get("count").toString();	              
//				}
//				if(!value.equals("")&&Integer.parseInt(value)>0){
//					Result=true;
//				}
//			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
//			System.out.println("Sql:"+sql+".is SUCCESS!");
		}
		return Result;
	}

public static String SetRemoteCardAllInfoState(ISiteviewApi api,String ChasId,String ShelfId,
	     String SlotId,String State){
	String sql = "", Result = "Error";
	try {
		// 要执行的SQL语句
		if (State.equals("Up")) {
			sql = "update cardinfo c Set c.OnlineState='" + 1 + "' where c.ChasId='" + ChasId + "' and c.ShelfId='"
					+ ShelfId + "' and c.SlotId='" + SlotId + "' and c.CardType='" + 35 + "';";
		} else if (State.equals("down")) {
			sql = "update cardinfo c Set c.OnlineState='" + 0 + "' where c.ChasId='" + ChasId + "' and c.ShelfId='"
					+ ShelfId + "' and c.SlotId='" + SlotId + "' and c.CardType='" + 35 + "';";
		}
	//	DBQueryUtils.UpdateorDelete(sql, api);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
//		System.out.println("Sql:" + sql + ".is SUCCESS!");
		Result = "OK";
	}
	return Result;
}

public static void GetAllChassisIPFromTable(ISiteviewApi api,Map<String,String> mapTemp){
		String sql = "";
		try {
			// 要执行的SQL语句
			sql="select Community,ChassisIP from chassisinfo;";
//			DataTable dt = DBQueryUtils.Select(sql, api);		
//			for(DataRow dr : dt.get_Rows()) {
//				String IP=dr.get("ChassisIP").toString().trim();
//				String Community=dr.get("Community").toString().trim();
//			//	String Id=dr.get("ChassisId").toString().trim();
//				mapTemp.put(IP, Community);
//			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
	//		System.out.println("Sql:" + sql + ".is SUCCESS!");
		}
	}


public static void QueryChassisInfoByIP(ISiteviewApi api,String IP,String Community,
		OpticalStructure ChasTemp){
		String sql = "",value="";
		try {
			// 要执行的SQL语句
			sql="select * from chassisinfo where Community='"+Community+"' and ChassisIP='"
					+IP+"';";
//			DataTable dt = DBQueryUtils.Select(sql, api);		
//			for(DataRow dr : dt.get_Rows()) {
//			//	value=dr.get("PId").toString().trim();
//		//		ChasTemp.setPId(dr.get("PId").toString().trim());
//				ChasTemp.setChassisId(dr.get("ChassisId").toString().trim());
//				ChasTemp.setLevel(dr.get("Level").toString().trim());
////				ChasTemp.setCivilcode(dr.get("Civilcode").toString().trim());
////				ChasTemp.setBrand(dr.get("Brand").toString().trim());
//				ChasTemp.setCommunity(dr.get("Community").toString().trim());
//				ChasTemp.setChassisIP(dr.get("ChassisIP").toString().trim());
//				ChasTemp.setOnlineState(dr.get("OnlineState").toString().trim());
//			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
//			System.out.println("Sql:" + sql + ".is SUCCESS!");
		}	   
 }
 
public static void UpdateChassisTableFromExtension(ISiteviewApi api,Map<String,String> mapTemp){
		String sql = "", Result = "Error";
		String strTime=CommonFunction.GetCurrentTime();
		String strshelfNum="",strIP="",strValue="";
		Iterator iter1 = mapTemp.keySet().iterator();
		for (; iter1.hasNext();) {
			strIP = (String) iter1.next(); //IP
			strValue = mapTemp.get(strIP);
			strshelfNum=strValue.substring(0, strValue.indexOf(";"));
		}
		try {
			// 要执行的SQL语句
			sql="update chassisinfo ch Set ch.ShelfNum='"+strshelfNum+"',ch.UpdateTime='"
					+strTime+"' where ch.ChassisIP='"+strIP+"';";
	//		DBQueryUtils.UpdateorDelete(sql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
//			System.out.println("Sql:" + sql + ".is SUCCESS!");
			Result = "OK";
		}
 }

public static void UpdateChassisToOffline(ISiteviewApi api,String ChasId){
		String sql="";
		try {
			// 要执行的SQL语句
			sql=String.format("update chassisinfo ch,shelfinfo sh,slotinfo sl,cardinfo ca Set "
					+ "ch.OnlineState=%d,sh.OnlineState=%d,sl.OnlineState=%d,ca.OnlineState=%d"
					+ " where ch.ChassisId=%d and sh.ChasId=ch.ChassisId and sl.ChasId=sh.ChasId"
					+ " and ca.ChasId=sl.ChasId",0,0,0,0,Integer.parseInt(ChasId));
//			DBQueryUtils.UpdateorDelete(sql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
//			System.out.println("Sql:"+sql+".is SUCCESS!");
		}
}

public static void QueryShelfInfoByID(ISiteviewApi api,String ChasID,
		   Map<String,ShelfInfo> mapShelf){
		String sql = "";
		try {
			// 要执行的SQL语句
			sql="select * from shelfinfo where ChasId='"+ChasID+"';";
//			DataTable dt = DBQueryUtils.Select(sql, api);		
//			for(DataRow dr : dt.get_Rows()) {				
//		//		OpticalShelfStructure shelfStructure=new OpticalShelfStructure();
//				ShelfInfo shelf=new ShelfInfo();
//				String ChaId=dr.get("ChasId").toString().trim();
//				shelf.setChasId(dr.get("ChasId").toString().trim());
//				shelf.setShelfId(dr.get("ShelfId").toString().trim());
//				shelf.setShelfOnlineState(dr.get("OnlineState").toString().trim());
//		//		shelfStructure.setShelfinfo(shelf);
//				mapShelf.put(ChaId,shelf);
//				
//			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
//			System.out.println("Sql:" + sql + ".is SUCCESS!");
		}	   
}

public static void UpdateShelfTableFromExtension(ISiteviewApi api,String ChasId,String ShelfId,ShelfInfo ShelfTemp){
		String sql = "";
		String strTime=CommonFunction.GetCurrentTime();
		try {
			// 要执行的SQL语句
			sql="update shelfinfo ch Set ch.ShelfpsuA='"+ShelfTemp.getShelfpsuA()+"',ch.ShelfpsuB='"+
					ShelfTemp.getShelfpsuB()+"',ch.ShelfvolA='"+ShelfTemp.getShelfvolA()+"',ch.ShelfvolB='"+
					ShelfTemp.getShelfvolB()+"',ch.Shelffan='"+ShelfTemp.getShelffan()+"',ch.Shelftemperature='"+
					ShelfTemp.getShelftemperature()+"',ch.ShelfcoCardNum='"+ShelfTemp.getShelfcoCardNum()+
					"',ch.ShelfrmtCardNum='"+ShelfTemp.getShelfrmtCardNum()+"',ch.UpdateTime='"+
					strTime+"' where ch.ChasId='"+ChasId+"' and ch.ShelfId='"+ShelfId+"';";
	//		DBQueryUtils.UpdateorDelete(sql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
//			System.out.println("Sql:" + sql + ".is SUCCESS!");
		}
}

public static String UpdateShelfIsOfflineByID(ISiteviewApi api,String ChasId,String ShelfId){
	String sql="",Result="error";
	try {
		// 要执行的SQL语句
		sql=String.format("update shelfinfo sh,slotinfo sl,cardinfo ca Set sh.OnlineState=%d,"
				+ "sl.OnlineState=%d,ca.OnlineState=%d where sh.ChasId=%s and sh.ShelfId=%s and"
				+ " sl.ChasId=sh.ChasId and sl.ShelfId=sh.ShelfId and ca.ChasId=sl.ChasId and "
				+ "ca.ShelfId=sl.ShelfId",0,0,0,ChasId,ShelfId);
	//	DBQueryUtils.UpdateorDelete(sql, api);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
//		System.out.println("Sql:"+sql+".is SUCCESS!");
		Result="OK";
	}
	return Result;
}

public static String DelteShelfInfoByID(ISiteviewApi api,String ChasId,String ShelfId){
	String sql="",Result="error";
	try {
		// 要执行的SQL语句
		sql="delete sh,sl,ca from shelfinfo sh,slotinfo sl,cardinfo ca WHERE sh.ChasId='"
				+ChasId+"' and sh.ShelfId='"+ShelfId+"' and sl.ChasId=sh.ChasId and sl.ShelfId="
				+"sh.ShelfId and ca.ChasId=sl.ChasId and ca.ShelfId=sl.ShelfId;";
//		DBQueryUtils.UpdateorDelete(sql, api);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
		System.out.println("Sql:"+sql+".is SUCCESS!");
		Result="OK";
	}
	return Result;
}

public static void QuerySlotInfoByID(ISiteviewApi api,String ChasID,String ShelfID,
		   Map<String,SlotInfo> mapslot){
		String sql = "";
		try {
			// 要执行的SQL语句
			sql="select * from slotinfo where ChasId='"+ChasID+"' and ShelfId='"+ShelfID+"';";
//			DataTable dt = DBQueryUtils.Select(sql, api);		
//			for(DataRow dr : dt.get_Rows()) {				
//		//		OpticalShelfStructure shelfStructure=new OpticalShelfStructure();
//				SlotInfo slot=new SlotInfo();
//				String keyId=dr.get("ShelfId").toString().trim()+"."+dr.get("SlotId").toString().trim();
//				slot.setChasId(ChasID);
//				slot.setShelfId(ShelfID);
//				slot.setSlotId(dr.get("SlotId").toString().trim());
//				slot.setLocalCardType(dr.get("LocalCardType").toString().trim());
//				slot.setRemoteCardType(dr.get("RemoteCardType").toString().trim());
//				slot.setOnlineState(dr.get("OnlineState").toString().trim());
//		//		shelfStructure.setShelfinfo(shelf);
//				mapslot.put(keyId,slot);				
//			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
	//		System.out.println("Sql:" + sql + ".is SUCCESS!");
		}	   
}

public static void InsertNewSlotToTable(ISiteviewApi api,String ChassId,String shelfAndslotId,
		SlotInfo slotTemp){
	String shelfId = "", slotId = "";
	String sql = "", strTime = "";
	int nResult = 0;
	strTime = CommonFunction.GetCurrentTime();
	shelfId=shelfAndslotId.substring(0,shelfAndslotId.indexOf("."));
	slotId=shelfAndslotId.substring(shelfAndslotId.indexOf(".")+1);
	
	try {
		// 要执行的SQL语句
//		sql = "insert into slotinfo(ChasId,ShelfId,SlotId,Level,LocalCardType,"
//				+ "RemoteCardType,OnlineState,UpdateTime)  values('" + ChassId + "','" + shelfId
//				+ "','" + slotId + "','" + 3 + "','" + slotTemp.getLocalCardType() + "','"
//				+ slotTemp.getRemoteCardType() + "','" + 1 + "','" + strTime + "');";
		sql = String.format("INSERT INTO slotinfo(RecId,LastModDateTime,CreatedDateTime,ChasId,ShelfId,SlotId,Level,"
				+ "LocalCardType,RemoteCardType,OnlineState,UpdateTime)VALUES(UPPER(REPLACE(UUID(),'-','')),"
				+ "NOW(),NOW(),%d,%d,%d,%d,%d,%d,%d,'%s')",Integer.parseInt(ChassId), Integer.parseInt(shelfId), 
				Integer.parseInt(slotId),3,Integer.parseInt(slotTemp.getLocalCardType()),
				Integer.parseInt(slotTemp.getRemoteCardType()), 1, strTime);
	//	DBQueryUtils.UpdateorDelete(sql, api);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
	//	System.out.println("insert into slotinfo SUCCESS!");
	}
}

public static void UpdateSlotTableFromExtension(ISiteviewApi api,String ChasId,String Id,SlotInfo SlotTemp){
    String shelfId="",slotId="";
	shelfId=Id.substring(0,Id.indexOf("."));
	slotId=Id.substring(Id.indexOf(".")+1);
		String sql = "";
		String strTime=CommonFunction.GetCurrentTime();
		try {
			// 要执行的SQL语句
			sql="update slotinfo ch Set ch.LocalCardType='"+SlotTemp.getLocalCardType()+"',ch.RemoteCardType='"+
					SlotTemp.getRemoteCardType()+"',ch.UpdateTime='"+strTime+"' where ch.ChasId='"+
					ChasId+"' and ch.ShelfId='"+shelfId+"' and ch.SlotId='"+slotId+"';";
//			DBQueryUtils.UpdateorDelete(sql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
//			System.out.println("Sql:" + sql + ".is SUCCESS!");
		}
}

public static String UpdateSlotIsOfflineByID(ISiteviewApi api,String ChasId,String Id){
	String shelfId="",slotId="";
	shelfId=Id.substring(0, Id.indexOf("."));
	slotId=Id.substring(Id.indexOf(".")+1);
	String sql="",Result="error";
	try {
		// 要执行的SQL语句
		sql=String.format("update slotinfo sl,cardinfo ca Set sl.OnlineState=%d,ca.OnlineState=%d"
				+ " where sl.ChasId=%s and sl.ShelfId=%s and sl.SlotId=%s and ca.ChasId=sl.ChasId and "
				+ "ca.ShelfId=sl.ShelfId and ca.SlotId=sl.SlotId",0,0,ChasId,shelfId,slotId);
//		DBQueryUtils.UpdateorDelete(sql, api);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
//		System.out.println("Sql:"+sql+".is SUCCESS!");
		Result="OK";
	}
	return Result;
}

public static String DelteSlotInfoByID(ISiteviewApi api,String ChasId,String Id){
	String shelfId="",slotId="";
	shelfId=Id.substring(0, Id.indexOf("."));
	slotId=Id.substring(Id.indexOf(".")+1);
	String sql="",Result="error";
	try {
		// 要执行的SQL语句
		sql="delete sl,ca from slotinfo sl,cardinfo ca WHERE sl.ChasId='"
				+ChasId+"' and sl.ShelfId='"+shelfId+"' and sl.SlotId='"+slotId+
				"' and ca.ChasId=sl.ChasId and ca.ShelfId=sl.ShelfId and ca.SlotId=sl.SlotId;";
	//	DBQueryUtils.UpdateorDelete(sql, api);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
//		System.out.println("Sql:"+sql+".is SUCCESS!");
		Result="OK";
	}
	return Result;
}

public static void QueryCardInfoByID(ISiteviewApi api,String ChasID,String ID,
		   Map<String,CardInfo> mapcard){
	    String shelfId="",slotId="";
	    shelfId=ID.substring(0,ID.indexOf("."));
	    slotId=ID.substring(ID.indexOf(".")+1);
		String sql = "";
		try {
			// 要执行的SQL语句
			sql="select * from cardinfo where ChasId='"+ChasID+"' and ShelfId='"+shelfId+
					"' and SlotId='"+slotId+"';";
//			DataTable dt = DBQueryUtils.Select(sql, api);		
//			for(DataRow dr : dt.get_Rows()) {				
//				CardInfo card=new CardInfo();
//				String keyId=dr.get("ShelfId").toString().trim()+"."+dr.get("SlotId").toString().trim()+
//						"."+dr.get("CardId").toString().trim();
//				card.setChasId(ChasID);
//				card.setShelfId(shelfId);
//				card.setCardId(slotId);
//				card.setCardType(dr.get("CardType").toString().trim());
//				card.setOnlineState(dr.get("OnlineState").toString().trim());
//		//		shelfStructure.setShelfinfo(shelf);
//				mapcard.put(keyId,card);				
//			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
//			System.out.println("Sql:" + sql + ".is SUCCESS!");
		}	   
}

public static void InsertNewCardToTable(ISiteviewApi api,String ChassId,String Id,
		CardInfo cardTemp){
	String shelfId = "", slotId = "",cardId="";
	String sql = "", strTime = "";
	strTime = CommonFunction.GetCurrentTime();
	shelfId=Id.substring(0,Id.indexOf("."));
	Id=Id.substring(Id.indexOf(".")+1);
	slotId=Id.substring(0,Id.indexOf("."));
	cardId=Id.substring(Id.indexOf(".")+1);
	
	try {
		// 要执行的SQL语句
//		sql = "insert into cardinfo(ChasId,ShelfId,SlotId,CardId,Level,CardType,Fxlink,LFPState,"
//				+ "Mode1,Txlink1,CurMode1,Mode2,Txlink2,CurMode2,Mode3,Txlink3,CurMode3,"
//				+ "Mode4,Txlink4,CurMode4,OnlineState,UpdateTime) values('"+ChassId+"','"+
//				shelfId+"','"+slotId+"','"+cardId+"','"+4+"','"+cardTemp.getCardType()+"','"+
//				cardTemp.getFxlink()+"','"+cardTemp.getLFPState()+"','"+cardTemp.getMode1()+"','"+
//				cardTemp.getTxlink1()+"','"+cardTemp.getCurMode1()+"','"+cardTemp.getMode2()+"','"+
//				cardTemp.getTxlink2()+"','"+cardTemp.getCurMode2()+"','"+cardTemp.getMode3()+"','"+
//				cardTemp.getTxlink3()+"','"+cardTemp.getCurMode3()+"','"+cardTemp.getMode4()+"','"+
//				cardTemp.getTxlink4()+"','"+cardTemp.getCurMode4()+1+"','"+strTime+"');";
		
		sql = String.format("INSERT INTO cardinfo(RecId,LastModDateTime,CreatedDateTime,ChasId,ShelfId,SlotId,"
				+ "CardId,Level,CardType,Fxlink,LFPState,Mode1,Txlink1,CurMode1,Mode2,Txlink2,CurMode2,Mode3,"
				+ "Txlink3,CurMode3,Mode4,Txlink4,CurMode4,OnlineState,UpdateTime)VALUES(UPPER(REPLACE(UUID(),'-','')),"
				+ "NOW(),NOW(),%d,%d,%d,%d,%d,%d,'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s',"
				+ "'%s','%s',%d,'%s')",Integer.parseInt(ChassId), Integer.parseInt(shelfId), 
				Integer.parseInt(slotId),Integer.parseInt(cardId),4,Integer.parseInt(cardTemp.getCardType()),
				cardTemp.getFxlink(),cardTemp.getLFPState(),cardTemp.getMode1(),cardTemp.getTxlink1(),
				cardTemp.getCurMode1(),cardTemp.getMode2(),cardTemp.getTxlink2(),cardTemp.getCurMode2(),
				cardTemp.getMode3(),cardTemp.getTxlink3(),cardTemp.getCurMode3(),cardTemp.getMode4(),
				cardTemp.getTxlink4(),cardTemp.getCurMode4(),1, strTime);
		
	//	DBQueryUtils.UpdateorDelete(sql, api);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
	//	System.out.println("insert into cardinfo SUCCESS!");
	}
}

public static void UpdateCardTableFromExtension(ISiteviewApi api,String ChasId,String Id,CardInfo cardTemp){
		String shelfId = "", slotId = "",cardId="";
		String sql = "", strTime = "";
		strTime = CommonFunction.GetCurrentTime();
		shelfId=Id.substring(0,Id.indexOf("."));
		Id=Id.substring(Id.indexOf(".")+1);
		slotId=Id.substring(0,Id.indexOf("."));
		cardId=Id.substring(Id.indexOf(".")+1);
		
		try {
			// 要执行的SQL语句
			sql="update cardinfo ca Set ca.Fxlink='"+cardTemp.getFxlink()+"', ca.LFPState='"+cardTemp.getLFPState()+
					"', ca.Mode1='"+cardTemp.getMode1()+"',ca.Txlink1='"+cardTemp.getTxlink1()+"',ca.CurMode1='"+
					cardTemp.getCurMode1()+"', ca.Mode2='"+cardTemp.getMode2()+"',ca.Txlink2='"+cardTemp.getTxlink2()
					+"',ca.CurMode2='"+cardTemp.getCurMode2()+"', ca.Mode3='"+cardTemp.getMode3()+"',ca.Txlink3='"+
					cardTemp.getTxlink3()+"',ca.CurMode3='"+cardTemp.getCurMode3()+"', ca.Mode4='"+cardTemp.getMode4()
					+"',ca.Txlink4='"+cardTemp.getTxlink4()+"',ca.CurMode4='"+cardTemp.getCurMode4()+"',ca.UpdateTime='"
					+strTime+"' where ca.ChasId='"+ChasId+"' and ca.ShelfId='"+shelfId+"' and ca.SlotId='"+slotId+"' and"
					+ " ca.CardId='"+cardId+"';";
	//		DBQueryUtils.UpdateorDelete(sql, api);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
//			System.out.println("Sql:" + sql + ".is SUCCESS!");
		}
 }

public static String UpdateCardIsOfflineByID(ISiteviewApi api,String ChasId,String Id){
	String shelfId = "", slotId = "",cardId="";
	String sql = "",Result="error";
	shelfId=Id.substring(0,Id.indexOf("."));
	Id=Id.substring(Id.indexOf(".")+1);
	slotId=Id.substring(0,Id.indexOf("."));
	cardId=Id.substring(Id.indexOf(".")+1);
	try {
		// 要执行的SQL语句
		sql=String.format("update cardinfo ca Set ca.OnlineState=%d where ca.ChasId=%s and"
				+ " ca.ShelfId=%s and ca.SlotId=%s and ca.CardId=%s",0,ChasId,shelfId,slotId,cardId);
	//	DBQueryUtils.UpdateorDelete(sql, api);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
//		System.out.println("Sql:"+sql+".is SUCCESS!");
		Result="OK";
	}
	return Result;
}

public static String DelteCardInfoByID(ISiteviewApi api,String ChasId,String Id){
	String shelfId = "", slotId = "",cardId="";
	String sql = "", strTime = "",Result="Error";
	strTime = CommonFunction.GetCurrentTime();
	shelfId=Id.substring(0,Id.indexOf("."));
	Id=Id.substring(Id.indexOf(".")+1);
	slotId=Id.substring(0,Id.indexOf("."));
	cardId=Id.substring(Id.indexOf(".")+1);
	
	try {
		// 要执行的SQL语句
		sql="DELETE FROM cardinfo WHERE ChasId='"+ChasId+"' and ShelfId='"+shelfId+
			"' and SlotId='"+slotId+"' and CardId='"+cardId+"';";
//		DBQueryUtils.UpdateorDelete(sql, api);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
//		System.out.println("Sql:"+sql+".is SUCCESS!");
		Result="OK";
	}
	return Result;
}

}
