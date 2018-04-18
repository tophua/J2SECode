package infinova.Optical.SNMP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

import infinova.Otical.ChassisInfo;
import infinova.Otical.CommonInfo;
import infinova.Otical.ShelfTypeResult;
import infinova.Otical.SlotInfo;
import infinova.Otical.Database.DBOperator;

public class SNMP4jOperator {

	Snmp snmp = null;
	CommunityTarget target = null;
	TableUtils utils = null;
	int nChassisID = 1; // 机箱当前id
	int nShelfID = 1;
	int nSlotID = 1;
	int nLocalCardID = 1;
	int nRemoteCardID = 1;

	public void InitSnmp() throws IOException, InterruptedException {
		snmp = new Snmp(new DefaultUdpTransportMapping());
		snmp.listen();
	}

	public void CreateTarget() throws IOException, InterruptedException {
		target = new CommunityTarget();
		target.setCommunity(new OctetString(CommonInfo.Community1)); // 设置公共体
		target.setVersion(SnmpConstants.version2c);
		target.setAddress(new UdpAddress(CommonInfo.IPAddressAndPort));
		target.setTimeout(Integer.parseInt(CommonInfo.Timeout)); // 3s //超时时间
		target.setRetries(Integer.parseInt(CommonInfo.Retries)); // 通信不成功时的重试次数
	}

	public void CreateUtils() throws IOException, InterruptedException {
		utils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));// GETNEXT
																			// or
																			// GETBULK
		utils.setMaxNumRowsPerPDU(Integer.parseInt(CommonInfo.maxrepetitions)); // only
																				// for
																				// GETBULK,
																				// set
																				// max-repetitions,
																				// default
																				// is
																				// 10
	}

	public PDU createGetPdu(String stroid) {
		PDU pdu = new PDU();
		pdu.setType(PDU.GET);
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0")));
		// //sysUpTime
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0"))); //sysName
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5"))); //expect an
		// no_such_instance error
		pdu.add(new VariableBinding(new OID(stroid)));
		return pdu;
	}

	public PDU createGetNextPdu(String stroid) {
		PDU pdu = new PDU();
		pdu.setType(PDU.GETNEXT);
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3"))); //sysUpTime
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5"))); //sysName
		pdu.add(new VariableBinding(new OID(stroid)));
		return pdu;
	}

	public PDU createGetBulkPdu(String stroid) {
		PDU pdu = new PDU();
		pdu.setType(PDU.GETBULK);
		pdu.setMaxRepetitions(10); // must set it, default is 0
		pdu.setNonRepeaters(0);
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1"))); //system
		pdu.add(new VariableBinding(new OID(stroid)));
		return pdu;
	}

	public void sendAsyncRequest(PDU pdu) throws IOException {
		snmp.send(pdu, target, null, new ResponseListener() {
			@Override
			public void onResponse(ResponseEvent event) {
				PDU response = event.getResponse();
				System.out.println("Got response from: " + event.getPeerAddress()); // 这是返回代理的IP和port
				if (response == null) {
					System.out.println("TimeOut...");
				} else {
					if (response.getErrorStatus() == PDU.noError) {
						Vector<? extends VariableBinding> vbs = response.getVariableBindings();
						for (VariableBinding vb : vbs) {
							System.out.println(vb.getOid() + " : " + vb.getVariable());
							System.out.println(vb + " ," + vb.getVariable().getSyntaxString());
						}
					} else {
						System.out.println("Error:" + response.getErrorStatusText());
					}
				}
			}
		});
	}

	/*
	 * 如果输入的pdu不存在，返回noSuchObject,否则正常返回
	 */
	public String SendPDUAndRequest(PDU pdu, String strType, int myselfID, int ParentID)
			throws IOException, InterruptedException {

		// List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		// Map<String, String> mapResult = new HashMap<String, String>();
		String strResult = "";
		// String[] ArrayResult;
		// List<String> RequestList = new ArrayList<String>();
		// RequestList.clear();

		// 向Agent发送PDU，并接收Response
		ResponseEvent respEvnt = snmp.send(pdu, target);
		// Thread.sleep(10); //停0.01s
		// 解析Response
		if (respEvnt != null && respEvnt.getResponse() != null) {
			Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getResponse().getVariableBindings();
			for (int i = 0; i < recVBs.size(); i++) {
				VariableBinding recVB = recVBs.elementAt(i);
				if (recVB.getVariable().equals("noSuchObject") || recVB.getVariable() == null) {
					strResult = "OID Error";
					System.out.println("no Result. pdu:" + pdu);
				} else {
					System.out.println(recVBs+"; "+recVB);
					strResult = PackageOnlyPDURequest(recVB, strType, myselfID, ParentID);
					// RequestList.add(strResult);
				}
				// System.out.println(recVB);
				// System.out.println(recVB.getOid() + " : " +
				// recVB.getVariable());

			}
		} else if (respEvnt == null || respEvnt.getResponse() == null) {
			strResult = "Chassis Off";
			System.out.println("机箱没回复，确定为机箱离线！");
		} else {
			strResult = "Error";
			System.out.println("TimeOut...,no Result." + "Type:" + strType + " pdu:" + pdu);
		}
		return strResult;
		// if(RequestList.size()>0){
		// ArrayResult= new String[RequestList.size()];
		// RequestList.toArray(ArrayResult);
		// return ArrayResult;
		// }else
		// return null;
	}

	public String GetAllChassisMIBFromsnmpWalk(String strOid, String strType) {
		String strResult = "", strTemp = "";
		OID[] columnOids = new OID[] {
				// new OID("1.3.6.1.2.1.1.9.1.2"), //sysORID
				// new OID("1.3.6.1.2.1.1.9.1.3"), //sysORDescr
				// new OID("1.3.6.1.2.1.1.9.1.5") //wrong OID, expect an null in
				// in VariableBinding array
				new OID(strOid) };
		// If not null, all returned rows have an index in a range
		// (lowerBoundIndex, upperBoundIndex]
		List<TableEvent> l = utils.getTable(target, columnOids, new OID(CommonInfo.TableEventStart),
				new OID(CommonInfo.TableEventEnd));
		for (TableEvent e : l) {
			System.out.println(e);
			strTemp = PackageSnmpWalkRequest(e, strType, null);
			strResult += strTemp;
		}
		return strResult;
	}

	public String GetAllShelfMIBFromsnmpWalk(String strOid, String strType,
			Map<String, List<ShelfTypeResult>> mapShelf) {
		String strResult = "error", strTemp = "";
		OID[] columnOids = new OID[] {
				// new OID("1.3.6.1.2.1.1.9.1.2"), //sysORID
				// new OID("1.3.6.1.2.1.1.9.1.3"), //sysORDescr
				// new OID("1.3.6.1.2.1.1.9.1.5") //wrong OID, expect an null in
				// in VariableBinding array
				new OID(strOid) };
		// If not null, all returned rows have an index in a range
		// (lowerBoundIndex, upperBoundIndex]
		List<TableEvent> l = utils.getTable(target, columnOids, new OID(CommonInfo.TableEventStart),
				new OID(CommonInfo.TableEventEnd));
		for (TableEvent e : l) {
			System.out.println(e);
			// List<ShelfTypeResult> listShelf= new
			// ArrayList<ShelfTypeResult>();
			ShelfTypeResult ShelfRe = new ShelfTypeResult();
			strTemp = PackageSnmpWalkRequest(e, strType, ShelfRe);
			if (!strTemp.equals("")) {
				// Iterator<Map.Entry<String,List<ShelfTypeResult>>>
				// IterChas=mapShelf.entrySet().iterator();
				Object obj = new Object();
				obj = mapShelf.get(strTemp);
				if (obj != null) {
					List<ShelfTypeResult> listShelfTemp = (List<ShelfTypeResult>) obj;
					listShelfTemp.add(ShelfRe);
				} else {
					List<ShelfTypeResult> listShelf = new ArrayList<ShelfTypeResult>();
					listShelf.add(ShelfRe);
					mapShelf.put(strTemp, listShelf);
				}
			}
		}
		if (mapShelf.size() > 0) {
			strResult = "OK";
		}
		return strResult;
	}

	public String GetAllSlotMIBFromsnmpWalk(String strOid, String strType, Map<String, List<String>> mapSlot) {
		String strResult = "error", strTemp = "";
		OID[] columnOids = new OID[] {
				// new OID("1.3.6.1.2.1.1.9.1.2"), //sysORID
				// new OID("1.3.6.1.2.1.1.9.1.3"), //sysORDescr
				// new OID("1.3.6.1.2.1.1.9.1.5") //wrong OID, expect an null in
				// in VariableBinding array
				new OID(strOid) };
		// If not null, all returned rows have an index in a range
		// (lowerBoundIndex, upperBoundIndex]
		List<TableEvent> l = utils.getTable(target, columnOids, new OID(CommonInfo.TableEventStart),
				new OID(CommonInfo.TableEventEnd));
		for (TableEvent e : l) {
			System.out.println(e);
			// List<ShelfTypeResult> listShelf= new
			// ArrayList<ShelfTypeResult>();
			ShelfTypeResult ShelfRe = new ShelfTypeResult();
			strTemp = PackageSnmpWalkRequest(e, strType, ShelfRe);
			// List<SlotInfo> listSlot=new ArrayList<SlotInfo>();
			if (!strTemp.equals("") && ShelfRe != null) {
				String strVbs = ShelfRe.getVbsValue();
				String strIndexType = ShelfRe.getIndexType().substring(0, ShelfRe.getIndexType().indexOf("."));
				Object obj = new Object();
				obj = mapSlot.get(strTemp);
				if (obj != null) {
					List<String> listTemp = (List<String>) obj;
					listTemp.add(strIndexType + ":" + strVbs);
				} else {
					List<String> listNew = new ArrayList<String>();
					listNew.add(strIndexType + ":" + strVbs);
					mapSlot.put(strTemp, listNew);
				}

				// SlotInfo SlotTemp=new SlotInfo();
				// ParseSNMPData.ParseSlotRequest(SlotTemp,ShelfRe);
			}

			// if(!strTemp.equals("")){
			// //Iterator<Map.Entry<String,List<ShelfTypeResult>>>
			// IterChas=mapShelf.entrySet().iterator();
			// Object obj=new Object();
			// obj=mapShelf.get(strTemp);
			// if(obj!=null){
			// List<ShelfTypeResult> listShelfTemp=(List<ShelfTypeResult>)obj;
			// listShelfTemp.add(ShelfRe);
			// }else{
			// List<ShelfTypeResult> listShelf= new
			// ArrayList<ShelfTypeResult>();
			// listShelf.add(ShelfRe);
			// mapShelf.put(strTemp, listShelf);
			// }
			// }
		}
		if (mapSlot.size() > 0) {
			strResult = "OK";
		}
		return strResult;
	}

	public String PackageOnlyPDURequest(VariableBinding VarValue, String strType, int myselfID, int ParentID) {
		String strResult = "";
		switch (strType) {
		case "CHASSIS": {
			// 由于List<ChassisInfo>不能转换为String
			// List<ChassisInfo> ChaList=new ArrayList<ChassisInfo>();
			// ChassisInfo ChassisTemp=new ChassisInfo();
			// ChassisTemp.setId(String.valueOf(myselfID));
			// ChassisTemp.setPid(String.valueOf(ParentID)); //机箱没父节点PID
			// ChassisTemp.setLevel(String.valueOf(1));
			// ChassisTemp.setshelfNum(String.valueOf(VarValue.getVariable()));
			// ChaList.add(ChassisTemp);
			strResult += "id:" + String.valueOf(myselfID) + ";";
			strResult += "pid:" + String.valueOf(ParentID) + ";";
			strResult += "level:" + String.valueOf(1) + ";";
			strResult += "shelfNum:" + String.valueOf(VarValue.getVariable()) + ";";
		}
			break;
		case "SHELF":
			break;
		case "SLOT":
			break;
		case "LOCALCARD":
			break;
		case "REMOTECARD":
			break;
		}
		return strResult;
	}

	public String PackageSnmpWalkRequest(TableEvent EventTemp, String strType, ShelfTypeResult TempResult) {

		VariableBinding[] Values = EventTemp.getColumns();
		String strResult = "";
		switch (strType) {
		case "CHASSIS": {
			// OID oid=e.getIndex();
			// System.out.println("index: "+oid);
			// VariableBinding[] Values=e.getColumns();
			// if(Values==null){
			// continue;
			// }
			// System.out.println("String: "+Values[0].toString()+"
			// vbsIndex:"+Values[0].getOid()
			// +" vbsValue:"+Values[0].getVariable().toString());
			// OID oid=EventTemp.getIndex();
			String strOid = EventTemp.getIndex().toString();
			if (strOid.equals("2.0")) {
				strResult += "chassisIP:" + Values[0].getVariable().toString() + ";";
			} else if (strOid.equals("3.0")) {
				strResult += "chassisMask:" + Values[0].getVariable().toString() + ";";
			} else if (strOid.equals("4.0")) {
				strResult += "chassisGateway:" + Values[0].getVariable().toString() + ";";
			}
		}
			break;
		case "SHELF": 
		case "SLOT": 
		case "CARD":
		case "CARDSTATE":
		{
			String strOidIndex = EventTemp.getIndex().toString();
			int nStart = 0;
			nStart = strOidIndex.indexOf(".");
			strResult = strOidIndex.substring(nStart + 1);
			// ShelfTypeResult ShelfRe= new ShelfTypeResult();
			TempResult.setIndexType(strOidIndex);
			TempResult.setVbsValue(Values[0].getVariable().toString());
			// listTemp.add(ShelfRe);
		}
			break;
//		case "SLOT": 
//		case "CARD":
//		case "CARDSTATE":
//		{
//			String strOidIndex = EventTemp.getIndex().toString();
//			int nStart = 0;
//			nStart = strOidIndex.indexOf(".");
//			strResult = strOidIndex.substring(nStart + 1);
//			TempResult.setIndexType(strOidIndex);
//			TempResult.setVbsValue(Values[0].getVariable().toString());
//		}
//			break;
		default:
			break;
		}
		return strResult;
	}

	// 测试例子
	public void snmpWalk1(String strOID) {

		OID[] columnOids = new OID[] {
				// new OID("1.3.6.1.2.1.1.9.1.2"), //sysORID
				// new OID("1.3.6.1.2.1.1.9.1.3"), //sysORDescr
				// new OID("1.3.6.1.2.1.1.9.1.5") //wrong OID, expect an null in
				// in VariableBinding array
				new OID(strOID) };
		// If not null, all returned rows have an index in a range
		// (lowerBoundIndex, upperBoundIndex]
		List<TableEvent> l = utils.getTable(target, columnOids, new OID(CommonInfo.TableEventStart),
				new OID(CommonInfo.TableEventEnd));
		for (TableEvent e : l) {
			System.out.println(e);
			// OID oid=e.getIndex();
			// System.out.println("index: "+oid);
			// VariableBinding[] Values=e.getColumns();
			// if(Values==null){
			// continue;
			// }
			// System.out.println("String: "+Values[0].toString()+"
			// vbsIndex:"+Values[0].getOid()
			// +" vbsValue:"+Values[0].getVariable().toString());
		}
	}

	public void GetQueryChassisInfo() throws Exception {
		String strChasHead = "", strChas = "";
		boolean bUpdate = false;
		int nDeleteTableRe = 0;
		strChasHead = SendPDUAndRequest(createGetPdu(CommonInfo.ShelfNumFromChassis), "CHASSIS", nChassisID, 0);
		if (strChasHead.equals(CommonInfo.OIDError)) {
			System.out.println("GetChassisInfo(): OID Error!");
		} else if (strChasHead.equals(CommonInfo.ChassisOff)) {
			// 需要更新ChassisInfo和AlarmEvent表
			strChas = strChasHead;
			// bUpdate=true; //重新查询时，会删除表所有信息，则不管离线情况
		} else if (strChasHead.equals(CommonInfo.UnKnownError)) {
			System.out.println("GetChassisInfo(): UnKnown Error!");
		} else if (!strChasHead.equals("")) {
			nChassisID++;
			System.out.println(strChasHead);
			strChas = GetAllChassisMIBFromsnmpWalk(CommonInfo.ChassisInfo, "CHASSIS");
			if (strChas.equals("")) {
				System.out.println("Get ChassisInfo(IP/Getway...) Error!");
			} else {
				bUpdate = true;
			}
			// nDeleteTableRe=DBOperator.DeleteTable("chassisinfo");
			// if(nDeleteTableRe>0){
			// if(DBOperator.InsertTable("chassisinfo",strChasHead+strChas)>0){
			// System.out.println("Insert Table:chassisinfo Success!");
			// }
			// }
		}
		if (bUpdate) {
			if (DBOperator.InsertChassisInfoTable("chassisinfo", strChasHead + strChas) == 1) {
				System.out.println("Insert Table:chassisinfo And Alarminfo Success!");
			}
		} else
			System.out.println("no Update Chassis!");

	}

	public void GetQueryShelfInfo() throws Exception {
		String strChas = "";
		Map<String, ChassisInfo> mapChas = new HashMap<String, ChassisInfo>();
		if (DBOperator.SelectChassisInfoTable(mapChas, "chassisinfo", "10.82.17.87") > 0) {
			System.out.println("Select Table:chassisinfo Success!");
		}
		// 查询chassisinfo表可能有很多机箱，但这里只能是一个
		if (mapChas.size() > 0) {

			Iterator<Map.Entry<String, ChassisInfo>> IterChas = mapChas.entrySet().iterator();
			while (IterChas.hasNext()) {
				Map.Entry<String, ChassisInfo> entry = IterChas.next();
				if (entry.getKey().equals("10.82.17.87")) {
					int ParentID = -1;
					Map<String, List<ShelfTypeResult>> mapShelfInfo = new HashMap<String, List<ShelfTypeResult>>();
					mapShelfInfo.clear();
					ParentID = Integer.parseInt(entry.getValue().getId());
					strChas = GetAllShelfMIBFromsnmpWalk(CommonInfo.ShelfInfo, "SHELF", mapShelfInfo);
					if (strChas.equals("OK") && mapShelfInfo.size() > 0) {
						Iterator iter1 = mapShelfInfo.keySet().iterator();
						for (; iter1.hasNext();) {
							String key = (String) iter1.next();
							List<ShelfTypeResult> ShelfInfo = (List<ShelfTypeResult>) mapShelfInfo.get(key);
							DBOperator.InsertShelfInfoToTable(ParentID, key, "shelfinfo", ShelfInfo);
						}
					} else {
						System.out.println("Query Shelf failed or no Shelf");
					}
					GetQuerySlotInfo(ParentID);
					GetCardInfo(ParentID);
				}
			}
		}
	}

	public void GetQuerySlotInfo(int ChassisID) throws Exception {
		String strResult = "";
		Map<String, List<String>> mapSlotInfo = new HashMap<String, List<String>>();
		strResult = GetAllSlotMIBFromsnmpWalk(CommonInfo.SlotInfo, "SLOT", mapSlotInfo);
		if (strResult.equals("OK") && mapSlotInfo.size() > 0) {
			DBOperator.InsertSlotInfoToTable(ChassisID, mapSlotInfo);
		}
	}
	
	public void GetCardInfo(int ChassisID) throws Exception{
		String strResult = "";
		Map<String, List<String>> mapCardInfo = new HashMap<String, List<String>>();
		strResult = GetAllSlotMIBFromsnmpWalk(CommonInfo.CardInfo, "CARD", mapCardInfo);
		if (strResult.equals("OK") && mapCardInfo.size() > 0) {
			DBOperator.InsertCardInfoToTable(ChassisID, mapCardInfo);
			mapCardInfo.clear();
			strResult = GetAllSlotMIBFromsnmpWalk(CommonInfo.CardStateInfo, "CARDSTATE", mapCardInfo);
			if(strResult.equals("OK") && mapCardInfo.size() > 0){
				DBOperator.InsertCardStateInfoToTable(ChassisID, mapCardInfo);
			}
		}
	
	}

	/** 
	* ip地址转成long型数字 
	* 将IP地址转化成整数的方法如下： 
	* 1、通过String的split方法按.分隔得到4个长度的数组 
	* 2、通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1 
	* @param strIp 
	* @return 
	*/ 

	public static long StringIPToLong(String IPValue) {
		String[] strip=IPValue.split("\\.");
		return (Long.parseLong(strip[0]) << 24) + 
				(Long.parseLong(strip[1]) << 16) + (Long.parseLong(strip[2]) << 8)
				+ Long.parseLong(strip[3]);  
	}
	

   /** 
    * 将十进制整数形式转换成127.0.0.1形式的ip地址 
    * 将整数形式的IP地址转化成字符串的方法如下： 
    * 1、将整数值进行右移位操作（>>>），右移24位，右移时高位补0，得到的数字即为第一段IP。 
    * 2、通过与操作符（&）将整数值的高8位设为0，再右移16位，得到的数字即为第二段IP。 
    * 3、通过与操作符吧整数值的高16位设为0，再右移8位，得到的数字即为第三段IP。 
    * 4、通过与操作符吧整数值的高24位设为0，得到的数字即为第四段IP。 
    * @param longIp 
    * @return 
    */  

	 public static String LongToStringIP(long longIp) {  
		StringBuffer sb = new StringBuffer("");  
		// 直接右移24位  
		sb.append(String.valueOf((longIp >>> 24)));  
		sb.append(".");  
		// 将高8位置0，然后右移16位  
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));  
		sb.append(".");  
		// 将高16位置0，然后右移8位  
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));  
		sb.append(".");  
		// 将高24位置0  
		sb.append(String.valueOf((longIp & 0x000000FF)));  
		return sb.toString();  
	}  

}
