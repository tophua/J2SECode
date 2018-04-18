package Mysql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class mysqlOperator {

	public static String DriverName="com.mysql.jdbc.Driver";
	public static String UrlValue="jdbc:mysql://localhost:3306/my_test";
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

	/*
	 * ͨ�����������ļ�������
	 */
	public static void InitConnectFromFile() throws IOException{
	//��ȡdb.properties�ļ��е����ݿ�������Ϣ
		InputStream in = mysqlOperator.class.getClassLoader().getResourceAsStream("db.properties");
		Properties prop = new Properties();
		prop.load(in);
	//��ȡ��ز���ֵ
		//����������
		String driver = prop.getProperty("driver");
		//URLָ��Ҫ���ʵ����ݿ���mydata
		String url = prop.getProperty("url");
		//MySQL����ʱ���û���
		String user = prop.getProperty("username");
		//MySQL����ʱ������
		String password = prop.getProperty("password");
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
	
	/*
	 * ������
	 * 		String strsql="CREATE TABLE `TestTable` ( "+
//          "`ShelfId` int(11) DEFAULT NULL,"+
//		  "`SlotId` int(11) DEFAULT NULL,"+
//          "`Level` int(11) DEFAULT NULL,"+
//		  "`LocalCardType` int(11) DEFAULT NULL,"+
//          "`RemoteCardType` int(11) DEFAULT NULL,"+
//		  "`CardSituation` varchar(255) CHARACTER SET gbk DEFAULT NULL,"+
//          "`OnlineState` int(11) DEFAULT NULL,"+
//		  "`UpdateTime` varchar(255) CHARACTER SET gbk DEFAULT NULL"+
//          ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	 */
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
 
    /*
     * ɾ����
     */
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
    
    /*
     * ��ѯ���ݲ�ȡ��
     */
    
    public static ResultSet QueryInfoFromTable(String strquery){
    	 ResultSet reSet=null;
    	try{
    		 reSet = statement.executeQuery(strquery);
          //��ResultSet�ı�������������
    		 while(reSet.next()){
    			 System.out.println("id=" + reSet.getObject("id"));
    			 System.out.println("name=" + reSet.getObject("name"));
    			 //������
    			 System.out.println("id=" + reSet.getString(1));
    			 System.out.println("name=" + reSet.getString(2)); 
    		} 
    	}catch (Exception e) {
		   // TODO: handle exception
		    e.printStackTrace();
		 }finally{
		    System.out.println("SQL���:"+strquery+"ִ�гɹ�!");
       }
    	return reSet;
    }
    
    public static void UnitConnect() throws IOException{
    	//�ر����ӣ��ͷ���Դ
    	try{
    		 statement.close();
    		 con.close();
    	}catch (Exception e) {
		   // TODO: handle exception
		    e.printStackTrace();
		 }finally{
		    System.out.println("���ݿ��ͷųɹ�����");
        }	    	  
    }
    
}