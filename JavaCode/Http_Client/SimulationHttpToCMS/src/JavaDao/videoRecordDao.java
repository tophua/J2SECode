package JavaDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import JavaBeanClass.videoRecordAlarm;

public class videoRecordDao {
	public static String DriverName="com.mysql.jdbc.Driver";
	public static String UrlValue="jdbc:mysql://localhost:3308/itoss";
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
	
	public static void UnitConnect() throws IOException{
		// �ر����ӣ��ͷ���Դ
		try {
			statement.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("���ݿ��ͷųɹ�����");
		}
	 }
	    
	//��RecId���videoFlag   
	
	public String getVideoFlagFromPointInfo(String recId){
		//������Ҫ
		recId="0672f0c3719e4708bc593b8f517584b8";
   	 String videoflag="";
   	 ResultSet reSet=null;
   	 String sql="SELECT VIDEOFLAG from videopointinfo where RecId='"+recId+"'";
   	try{
   		reSet = statement.executeQuery(sql);
         //��ResultSet�ı�������������
   		 while(reSet.next()){
//   			 System.out.println("id=" + reSet.getObject("VIDEOFLAG"));
//   			 //������
//   			 System.out.println("id=" + reSet.getString(1));
   			videoflag=reSet.getObject("VIDEOFLAG").toString(); 
   		} 
   	}catch (Exception e) {
		   // TODO: handle exception
		    e.printStackTrace();
		 }finally{
      }
   	return videoflag;
	}
	
	public void InsertAlarmToRecordResult(videoRecordAlarm alarmTemp){
		
		String Tabl1=String.format("SELECT '%s' AS ST, '%s' AS ET,MAX(LOSTENDTIME) AS MT FROM VIDEORECORDRESULT "
				+ "WHERE VIDEOFLAG = '%s'", alarmTemp.getLostStart(),alarmTemp.getLostEnd(),alarmTemp.getVideoFlag());
		String Tabl2=String.format("SELECT * FROM (%s) Tabl1 WHERE Tabl1.ET > Tabl1.MT OR Tabl1.ST > Tabl1.MT "
				+ "OR Tabl1.MT IS NULL", Tabl1);

		String sql=String.format("INSERT INTO VIDEORECORDRESULT (RecId,CreatedDateTime,VIDEOFLAG,LOSTSTARTTIME,LOSTENDTIME,"
				+ "LOSTDURATION) SELECT UPPER(REPLACE(UUID(), '-', '')),NOW(),'%s',CASE WHEN (Tabl2.ST <= Tabl2.MT) "
				+ "THEN Tabl2.MT ELSE Tabl2.ST END, Tabl2.ET, CASE WHEN (Tabl2.ST <= Tabl2.MT) THEN UNIX_TIMESTAMP(Tabl2.ET)"
				+ " - UNIX_TIMESTAMP(Tabl2.MT) ELSE UNIX_TIMESTAMP(Tabl2.ET) - UNIX_TIMESTAMP(Tabl2.ST) END FROM (%s) Tabl2",
				alarmTemp.getVideoFlag(),Tabl2);
		
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
