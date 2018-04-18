package infinova.Otical.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

import infinova.Otical.Alarminfo;
import infinova.Otical.ChassisInfo;
import infinova.Otical.CommonFunction;
import infinova.Otical.LocalAndRemoteCardInfo;
import infinova.Otical.ShelfInfo;
import infinova.Otical.ShelfTypeResult;
import infinova.Otical.SlotInfo;

public class DBOperator {

	public static String DriverName="com.mysql.jdbc.Driver";
	public static String UrlValue="jdbc:mysql://localhost:3307/my_test";
	public static String UserValue="root";
	public static String PWDValue="root";
	
	public static Statement statement=null;
	public static Connection con=null;
	public static void InitConnect() throws IOException{
		//�������ݿ�
		//����Connection����
		//Connection con;
		//����������
		String driver = DriverName;
		//URLָ��Ҫ���ʵ����ݿ���mydata
		String url = UrlValue;
		//MySQL����ʱ���û���
		String user = UserValue;
		//MySQL����ʱ������
		String password = PWDValue;
		//������ѯ�����
		try {
		   //������������
		   Class.forName(driver);
		   //1.getConnection()����������MySQL���ݿ⣡��
		   con = DriverManager.getConnection(url,user,password);
		   if(!con.isClosed())
		       System.out.println("Succeeded connecting to the Database!");
		  //2.����statement���������ִ��SQL��䣡��
		   statement = con.createStatement();
		   
		}catch(ClassNotFoundException e) {   
		   //���ݿ��������쳣����
		    System.out.println("Sorry,can`t find the Driver!");   
		    e.printStackTrace();   
		 } catch(SQLException e) {
		  //���ݿ�����ʧ���쳣����
		    e.printStackTrace();  
		 }catch (Exception e) {
		   // TODO: handle exception
		    e.printStackTrace();
		 }finally{
		    System.out.println("���ݿ����ݳɹ���ȡ����");
        }	
	}
	
    public static void TestSelectEG1() throws IOException{
    	String sql="";
    	try{
    	  //Ҫִ�е�SQL���
 		   sql = "select * from test1";
 		  //3.ResultSet�࣬������Ż�ȡ�Ľ��������
 		   ResultSet rs = statement.executeQuery(sql);
 		   System.out.println("-----------------");
 		   System.out.println("ִ�н��������ʾ:");  
 		   System.out.println("-----------------");  
 		   System.out.println("����" + "\t" + "ְ��");  
 		   System.out.println("-----------------");  
 		   
 		   String job = null;
 		   String id = null;
 		   while(rs.next()){
 		     //��ȡName1��������
 		      job = rs.getString("Name1");
 		      //��ȡUrl��������
 		      id = rs.getString("Url");
 		      //������
 		      System.out.println(id + "\t" + job);
 		   }
 		   rs.close();
   	    }catch (Exception e) {
		   // TODO: handle exception
		    e.printStackTrace();
		 }finally{
		    System.out.println("SQL���:"+sql+"ִ�гɹ�!");
       }	   
    }
    
    public static void UnitDB() throws IOException{
    	try{
    		 con.close();
    	}catch (Exception e) {
		   // TODO: handle exception
		    e.printStackTrace();
		 }finally{
		    System.out.println("���ݿ��ͷųɹ�����");
        }	    	  
    }

    public static void CreateTable(String strsql){
    	int nResult=0;
    	try{
      	  //Ҫִ�е�SQL��� 		   
   		  nResult = statement.executeUpdate(strsql);		   
     	    }catch (Exception e) {
  		   // TODO: handle exception
  		    e.printStackTrace();
  		 }finally{
  		    System.out.println("SQL���:"+strsql+"ִ�гɹ�!"+"Result:"+nResult);
         }	   
    }
    public static int DeleteTable(String strTable) throws IOException{
    	int nResult=0;
     	String sql="";
    	try{
    	  //Ҫִ�е�SQL���
 		   sql = "delete from "+strTable;
 		  nResult = statement.executeUpdate(sql);		   
   	    }catch (Exception e) {
		   // TODO: handle exception
		    e.printStackTrace();
		 }finally{
		    System.out.println("SQL���:"+sql+"ִ�гɹ�!"+"Result:"+nResult);
		    nResult=1;
       }	   
    	return nResult;
    }
    
    public static int InsertChassisInfoTable(String strTable,String strValue) throws Exception{
    	int nResult=0; //1��ʾִ�гɹ�
     	String sql="",strAlarm="";
     	ChassisInfo ChassisTemp=new ChassisInfo();
     	ProcessTableData.ProcessQueryChassisInfo(ChassisTemp,strValue);
     	strAlarm="Type:"+ChassisTemp.getId()+"+*+*+*+"+ChassisTemp.getLevel()+
     			";AlarmEvent:"+ChassisTemp.getchassisOnline()+";AlarmTime:"+ChassisTemp.getchassisUpTime()
     			+";";
    	try{
    	  //Ҫִ�е�SQL���
 		   sql = "insert into "+strTable+" values( '"+Integer.parseInt(ChassisTemp.getId())+"','"+
 				  Integer.parseInt(ChassisTemp.getPid())+"','"+Integer.parseInt(ChassisTemp.getLevel())+"','"+
 				 Integer.parseInt(ChassisTemp.getshelfNum())+"','"+ChassisTemp.getchassisIP()+"','"+
 				ChassisTemp.getchassisMask()+"','"+ChassisTemp.getchassisGateway()+"','"+
 				Integer.parseInt(ChassisTemp.getchassisOnline())+"','"+ChassisTemp.getchassisUpTime()+"');";
 		  nResult = statement.executeUpdate(sql);		   
   	    }catch (Exception e) {
		   // TODO: handle exception
		    e.printStackTrace();
		 }finally{
		    System.out.println("SQL���:"+sql+"ִ�гɹ�!"+"Result:"+nResult);
		    if(InsertAlarmToAlarmEventTable(strAlarm)>0){
		    	nResult=1;
		    }else{
		    	System.out.println("Insert Alarminfo Table failed!");
		    	nResult=2;
		    }
		        
       }	   
    	return nResult;
    }
    
    public static int SelectChassisInfoTable(Map<String, ChassisInfo> mapTemp,String strTable,String strCondition) throws IOException{
    	int nResult=0; //1��ʾִ�гɹ�
     	String sql="",strTemp="";
     	int nTmep=-1;
    	try{
    	  //Ҫִ�е�SQL���
 		   sql = "select * from "+strTable+" where ChassisIP = '"+strCondition+"';";
 		   ResultSet reSet = statement.executeQuery(sql);	
 		   while(reSet.next()){
 			  ChassisInfo ChaInfo=new ChassisInfo();
 			  ChaInfo.setId(reSet.getString(1));
 			  ChaInfo.setPid(reSet.getString(2));
 			  ChaInfo.setLevel(reSet.getString(3));
 			  ChaInfo.setshelfNum(reSet.getString(4));
 			  ChaInfo.setchassisIP(reSet.getString(5));
 			  strTemp=reSet.getString(5);
 			  ChaInfo.setchassisMask(reSet.getString(6));
 			  ChaInfo.setchassisGateway(reSet.getString(7)); 	
 			  ChaInfo.setchassisOnline(reSet.getString(8));
 			  ChaInfo.setchassisUpTime(reSet.getString(9));
 			  mapTemp.put(strTemp,ChaInfo);
 			  nResult++;
// 			   int m1=reSet.getInt(1);//��һ�е�ֵ
// 			   String s1=reSet.getString(2);//�ڶ��е�ֵ 			   			   
 		   }
 		   
   	    }catch (Exception e) {
		   // TODO: handle exception
		    e.printStackTrace();
		 }finally{
		    System.out.println("SQL���:"+sql+"ִ�гɹ�!"+"Result:"+nResult);
		    nResult=1;
       }	   
    	return nResult;
    }
    
    public static int InsertShelfInfoToTable(int nPID,String strName,String Table,List<ShelfTypeResult> listTemp)
    		throws Exception{
    	
    	//�Ȼ�ȡShelf��id��Ϣ
   // 	String strShelfID=GetShelfID(nPID);  	
    	//����PID��ShelfNameɾ��ShelfInfo�������
    	int nResult=0;
     	String sql="",strAlarm="",strTime="";
     	strTime=CommonFunction.GetCurrentTime();
    	strAlarm="Type:"+nPID+"+"+strName+"+*+*+"+2+";AlarmEvent:"+1+";AlarmTime:"+strTime+";";
    	
    	ShelfInfo ShelfTemp=new ShelfInfo();
    	ProcessTableData.GetQueryShelfInfo(ShelfTemp,listTemp);
//    	try{
//    	  //Ҫִ�е�SQL���
// 		   sql = "delete from "+Table+" where PId='"+nPID+"' and ShelfName='"+strName+"'";
// 		  nResult = statement.executeUpdate(sql);		   
//   	    }catch (Exception e) {
//		   // TODO: handle exception
//		    e.printStackTrace();
//		 }finally{
//		    System.out.println("SQL���:"+sql+"ִ�гɹ�!"+"Result:"+nResult);
//		    if(InsertAlarmToAlarmEventTable(strAlarm)>0){
//		    	nResult=1;
//		    }else{
//		    	System.out.println("Insert Alarminfo Table failed!");
//		    	nResult=2;
//		    }
//       }	   
      //����ShelfInfo���������
       	try{
      	  //Ҫִ�е�SQL���
   		   sql = "insert into "+Table+" values( '"+strName+"','"+nPID+"','"+2+"','"+
   				strName+"','"+ShelfTemp.getshelfIP()+"','"+ShelfTemp.getshelfMask()+
   				"','"+ShelfTemp.getshelfGateway()+"','"+ShelfTemp.getshelfpsuA()+"','"+
   				ShelfTemp.getshelfpsuB()+"','"+ShelfTemp.getshelfvolA()+"','"+
   				ShelfTemp.getshelfvolB()+"','"+ShelfTemp.getshelffan()+"','"+
   				ShelfTemp.getshelftemperature()+"','"+ShelfTemp.getshelfcoCardNum()+"','"+
   				ShelfTemp.getshelfrmtCardNum()+"','"+1+"','"+strTime+"');";
   		  nResult = statement.executeUpdate(sql);		   
     	    }catch (Exception e) {
  		   // TODO: handle exception
  		    e.printStackTrace();
  		 }finally{
  		    System.out.println("SQL���:"+sql+"ִ�гɹ�!"+"Result:"+nResult);
  		    if(InsertAlarmToAlarmEventTable(strAlarm)>0){
  		    	nResult=1;
  		    }else{
  		    	System.out.println("Insert Alarminfo Table failed!");
  		    	nResult=2;
  		    }
  		        
         }	
   
    	return nResult;
    }
    
    public static void InsertSlotInfoToTable(int HeadID,Map<String,List<String>> mapTemp) throws Exception{
    	String strShelfNum="",strSlotNum="",strSlotType="",strValue="";
    	String sql="",strTime="",strAlarm="";
    	int nResult=0;
    	strTime=CommonFunction.GetCurrentTime();
    	Iterator iter1=mapTemp.keySet().iterator();
		for(;iter1.hasNext();){
			SlotInfo Slotinfo=new SlotInfo();
			String key=(String)iter1.next();
			strShelfNum=key.substring(0, key.indexOf("."));
			strSlotNum=key.substring(key.indexOf(".")+1);
			
			List<String> listInfo=(List<String>)mapTemp.get(key);
			for(int i=0;i<listInfo.size();i++){
				strSlotType=listInfo.get(i).substring(0, listInfo.get(i).indexOf(":"));
				strValue=listInfo.get(i).substring(listInfo.get(i).indexOf(":")+1);
				ProcessTableData.GetQuerySlotInfo(strSlotType,strValue,Slotinfo);
			}
			strAlarm="Type:"+HeadID+"+"+strShelfNum+"+"+strSlotNum+"+*+"+3+
					";AlarmEvent:"+1+";AlarmTime:"+strTime+";";
	      	try{
	        	  //Ҫִ�е�SQL���
	     		 sql = "insert into Slotinfo "+" values( '"+strSlotNum+"','"+HeadID+"','"
	        	  +strShelfNum+"','"+3+"','"+Slotinfo.getLocalCardType()+"','"+
	        	  Slotinfo.getRemoteCardType()+"','"+1+"','"+strTime+"');";
	     		  nResult = statement.executeUpdate(sql);		   
	       	    }catch (Exception e) {
	    		   // TODO: handle exception
	    		    e.printStackTrace();
	    		 }finally{
	    		    System.out.println("SQL���:"+sql+"ִ�гɹ�!"+"Result:"+nResult);
	    		    if(InsertAlarmToAlarmEventTable(strAlarm)>0){
	    		    	nResult=1;
	    		    }else{
	    		    	System.out.println("Insert Alarminfo Table failed!");
	    		    	nResult=2;
	    		    }
	    		        
	           }	
			
		//	DBOperator.InsertShelfInfoToTable(ParentID,key,"shelfinfo",ShelfInfo);
		}
    }
    
    public static void InsertCardInfoToTable(int HeadID,Map<String,List<String>> mapTemp) throws Exception{
    	String strShelfNum="",strSlotNum="",strSlotType="",strValue="";
    	String sql="",strTime="",strAlarm="";
    	int nResult=0,CardID=0;
    	strTime=CommonFunction.GetCurrentTime();
    	Iterator iter1=mapTemp.keySet().iterator();
		for(;iter1.hasNext();){
			LocalAndRemoteCardInfo LocalCardInfo =new LocalAndRemoteCardInfo();
			LocalAndRemoteCardInfo RemoteCardInfo =new LocalAndRemoteCardInfo();
		//	SlotInfo Slotinfo=new SlotInfo();
			String key=(String)iter1.next();
			strShelfNum=key.substring(0, key.indexOf("."));
			strSlotNum=key.substring(key.indexOf(".")+1);
			
			List<String> listInfo=(List<String>)mapTemp.get(key);
			for(int i=0;i<listInfo.size();i++){
				strSlotType=listInfo.get(i).substring(0, listInfo.get(i).indexOf(":"));
				strValue=listInfo.get(i).substring(listInfo.get(i).indexOf(":")+1);
				ProcessTableData.GetQueryCardInfo(strSlotType,strValue,LocalCardInfo,RemoteCardInfo);
			}
			if(LocalCardInfo.getCardType().equals("0")){
				
			}else if(LocalCardInfo.getCardType().equals("34")){
				CardID=1;
				InsertToCardInfoTable(HeadID,CardID,LocalCardInfo);
			}
	        if(RemoteCardInfo.getCardType().equals("0")){
				
			}else if(RemoteCardInfo.getCardType().equals("35")){
				CardID=2;
				InsertToCardInfoTable(HeadID,CardID,RemoteCardInfo);
	       }
			
//			strAlarm="Type:"+HeadID+"+"+strShelfNum+"+"+strSlotNum+"+*+"+3+
//					";AlarmEvent:"+1+";AlarmTime:"+strTime+";";
//	      	try{
//	        	  //Ҫִ�е�SQL���
//	     		 sql = "insert into Slotinfo "+" values( '"+strSlotNum+"','"+HeadID+"','"
//	        	  +strShelfNum+"','"+3+"','"+Slotinfo.getLocalCardType()+"','"+
//	        	  Slotinfo.getRemoteCardType()+"','"+1+"','"+strTime+"');";
//	     		  nResult = statement.executeUpdate(sql);		   
//	       	    }catch (Exception e) {
//	    		   // TODO: handle exception
//	    		    e.printStackTrace();
//	    		 }finally{
//	    		    System.out.println("SQL���:"+sql+"ִ�гɹ�!"+"Result:"+nResult);
//	    		    if(InsertAlarmToAlarmEventTable(strAlarm)>0){
//	    		    	nResult=1;
//	    		    }else{
//	    		    	System.out.println("Insert Alarminfo Table failed!");
//	    		    	nResult=2;
//	    		    }
//	    		        
//	           }	
			
		//	DBOperator.InsertShelfInfoToTable(ParentID,key,"shelfinfo",ShelfInfo);
		}
    	
    }
    
    public static void InsertCardStateInfoToTable(int HeadID,Map<String,List<String>> mapTemp) throws Exception{
    	String strShelfNum="",strSlotNum="",strSlotType="",strValue="";
    	String sql="",strTime="",strAlarm="";
    	int nResult=0,CardID=0;
    	strTime=CommonFunction.GetCurrentTime();
    	Iterator iter1=mapTemp.keySet().iterator();
		for(;iter1.hasNext();){
			LocalAndRemoteCardInfo LocalCardInfo =new LocalAndRemoteCardInfo();
			LocalAndRemoteCardInfo RemoteCardInfo =new LocalAndRemoteCardInfo();
		//	SlotInfo Slotinfo=new SlotInfo();
			String key=(String)iter1.next();
			strShelfNum=key.substring(0, key.indexOf("."));
			strSlotNum=key.substring(key.indexOf(".")+1);
			
			List<String> listInfo=(List<String>)mapTemp.get(key);
			for(int i=0;i<listInfo.size();i++){
				strSlotType=listInfo.get(i).substring(0, listInfo.get(i).indexOf(":"));
				strValue=listInfo.get(i).substring(listInfo.get(i).indexOf(":")+1);
				ProcessTableData.GetQueryCardStateInfo(strSlotType,strValue,LocalCardInfo,RemoteCardInfo);
			}
			//���ݾ��忨�ۺŲ�ѯ�Ƿ��Ѿ��б��ؿ���Զ�˿�
			if(!QueryCardType(HeadID,strShelfNum,strSlotNum,"34").equals("")){
				UpdateCardStateToCardInfoTable(HeadID,strShelfNum,strSlotNum,"34",LocalCardInfo);
			  }
			if(!QueryCardType(HeadID,strShelfNum,strSlotNum,"35").equals("")){
				UpdateCardStateToCardInfoTable(HeadID,strShelfNum,strSlotNum,"35",RemoteCardInfo);
			  }
		 }
//			if(LocalCardInfo.getcardType().equals("0")){
//				
//			}else if(LocalCardInfo.getcardType().equals("34")){
//				CardID=1;
//				InsertToCardInfoTable(HeadID,CardID,LocalCardInfo);
//			}
//	        if(RemoteCardInfo.getcardType().equals("0")){
//				
//			}else if(RemoteCardInfo.getcardType().equals("35")){
//				CardID=2;
//				InsertToCardInfoTable(HeadID,CardID,RemoteCardInfo);
//	       }
		 	
    }
    
    public static void InsertToCardInfoTable(int HeadID,int CardID,LocalAndRemoteCardInfo cardTemp) throws Exception{
		String sql = "", strTime = "", strAlarm = "";
		int nResult = 0;
		strTime = CommonFunction.GetCurrentTime();
		strAlarm = "Type:" + HeadID + "+" + cardTemp.getSheid() + "+" + cardTemp.getPid() + 
				"+" +CardID+"+"+ 4 + ";AlarmEvent:" + 1+ ";AlarmTime:" + strTime + ";";
		try {
			// Ҫִ�е�SQL���
			sql = "insert into localandremotecardinfo(Id,Chaid,Sheid,Pid,Level,CardType,Fxlink,LFPState,OnlineState,UpdateTime) " +
			" values( '" + CardID + "','" + HeadID + "','" + cardTemp.getSheid() +"','" +cardTemp.getPid()+ "','" + 4
					+ "','" + cardTemp.getCardType() + "','" + cardTemp.getFxlink() +"','" +cardTemp.getLFPState() +"','" + 1 + "','"
					+ strTime + "');";
			nResult = statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("SQL���:" + sql + "ִ�гɹ�!" + "Result:" + nResult);
			if (InsertAlarmToAlarmEventTable(strAlarm) > 0) {
				nResult = 1;
			} else {
				System.out.println("Insert Alarminfo Table failed!");
				nResult = 2;
			}
		}
    	
    }
    
    public static String QueryCardType(int Chaid,String Sheid,String Slotid,String type){
    	String sql="",strResult="";
    	try {
			// Ҫִ�е�SQL���
			sql = "SELECT Id from localandremotecardinfo where Chaid='" +Chaid+ "' and Sheid='"
					+Sheid+"' and PId='"+Slotid+"' and CardType='"+type+"';";
			ResultSet reSet = statement.executeQuery(sql);	
	 		while(reSet.next()){
	 			strResult=reSet.getString(1);
//	 			int m1=reSet.getInt(1);//��һ�е�ֵ
//	 			String s1=reSet.getString(2);//�ڶ��е�ֵ 			   			   
	 		   }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("SQL���:" + sql + "ִ�гɹ�!");	
		}
    	return strResult;
    }
    
    public static int UpdateCardStateToCardInfoTable(int Chaid,String Sheid,String Slotid,String type,
    		LocalAndRemoteCardInfo cardTemp){
    	String sql="";
    	int nResult=0;
    	try {
			// Ҫִ�е�SQL���
			sql = "update localandremotecardinfo set Mode1='"+cardTemp.getMode1()+"' ,Txlink1='"+
					cardTemp.getTxlink1()+"' ,CurMode1='"+cardTemp.getCurMode1()+"' ,Mode2='"+
					cardTemp.getMode2()+"' ,Txlink2='"+cardTemp.getTxlink2()+"' ,CurMode2='"+
					cardTemp.getCurMode2()+"' ,Mode3='"+cardTemp.getMode3()+"' ,Txlink3='"+
					cardTemp.getTxlink3()+"' ,CurMode3='"+cardTemp.getCurMode3()+"' ,Mode4='"+
					cardTemp.getMode4()+"' ,Txlink4='"+cardTemp.getTxlink4()+"' ,CurMode4='"+
					cardTemp.getCurMode4()+"'where Chaid='" +Chaid+ "' and Sheid='"
					+Sheid+"' and PId='"+Slotid+"' and CardType='"+type+"';";				
			nResult = statement.executeUpdate(sql);	

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("SQL���:" + sql + "ִ�гɹ�!");	
			nResult=1;
		}
    	return nResult;
    }
    
    public static String GetShelfID(int ParentID){
    	String sql="",strResult="";
    	try{
    		sql="SELECT Id  FROM  chassisinfo WHERE PId = '"+ParentID+"';";
    		ResultSet reSet = statement.executeQuery(sql);	
     		while(reSet.next()){
     			strResult+=reSet.getString(1)+";";	   			   
     	    }
    	  }catch (Exception e) {
		   // TODO: handle exception
		    e.printStackTrace();
		  }finally{
		    System.out.println("SQL���:"+sql+"ִ�гɹ�!"+"Result:"+strResult);
       }	
    	return strResult;
    }
    
    public static int InsertAlarmToAlarmEventTable(String strValue) throws Exception{
    	int nResult=0; //1��ʾִ�гɹ�
     	String sql="",strTable="alarminfo";
        String strType="",strEvent="",strTime="";
        Alarminfo AlarmTemp=new Alarminfo();
        ProcessTableData.ProcessAlarmString(AlarmTemp,strValue);
    	try{
    	  //Ҫִ�е�SQL���
 		   sql = "insert into "+strTable+" values( '"+AlarmTemp.getstrType()+"','"+
    	  AlarmTemp.getstrAlarmEvent()+"','"+AlarmTemp.getstrAlarmTime()+"');";
 		   nResult = statement.executeUpdate(sql);		   
   	    }catch (Exception e) {
		   // TODO: handle exception
		    e.printStackTrace();
		 }finally{
		    System.out.println("SQL���:"+sql+"ִ�гɹ�!"+"Result:"+nResult);
		    nResult=1;
       }	   
    	return nResult;
    }
    
}
