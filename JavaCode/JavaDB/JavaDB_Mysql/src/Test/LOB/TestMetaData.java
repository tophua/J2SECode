package Test.LOB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/*
 * �����ǲ���Ԫ����
 */
public class TestMetaData {

	/**
	    * @Method: testDataBaseMetaData
	    * @Description: ��ȡ���ݿ��Ԫ��Ϣ
	    * @throws SQLException
	    */ 
	    public void testDataBaseMetaData() throws SQLException {
	        Connection conn = JdbcUtils.getConnection();
	        DatabaseMetaData metadata = conn.getMetaData();
	        //getURL()������һ��String����󣬴������ݿ��URL
	        System.out.println(metadata.getURL());
	        //getUserName()���������ӵ�ǰ���ݿ����ϵͳ���û���
	        System.out.println(metadata.getUserName());
	        //getDatabaseProductName()���������ݿ�Ĳ�Ʒ����
	        System.out.println(metadata.getDatabaseProductName());
	        //getDatabaseProductVersion()���������ݿ�İ汾��
	        System.out.println(metadata.getDatabaseProductVersion());
	        //getDriverName()�����������������������
	        System.out.println(metadata.getDriverName());
	        //getDriverVersion()��������������İ汾��
	        System.out.println(metadata.getDriverVersion());
	        //isReadOnly()������һ��booleanֵ��ָʾ���ݿ��Ƿ�ֻ����������
	        System.out.println(metadata.isReadOnly());
	        JdbcUtils.release(conn, null, null);
	    }
	
	    /**
	     * @Method: testParameterMetaData
	     * @Description: ��ȡ����Ԫ��Ϣ
	     * @throws SQLException
	     */ 
	     public void testParameterMetaData() throws SQLException {
	         Connection conn = JdbcUtils.getConnection();
	         String sql = "select * from user wherer name=? and password=?";
	         //��SQLԤ����һ��
	         PreparedStatement st = conn.prepareStatement(sql);
	         ParameterMetaData pm = st.getParameterMetaData();
	         //getParameterCount() ���ָ�������ĸ���
	         System.out.println(pm.getParameterCount());
	         //getParameterType(int param)�����ָ��������sql���ͣ�MySQL���ݿ�������֧��
	         System.out.println(pm.getParameterType(1));
	         JdbcUtils.release(conn, null, null);
	     }
	
	     /**
	      * @Method: testResultSetMetaData
	      * @Description: �������Ԫ����
	      * @throws Exception
	      */
	      public void testResultSetMetaData() throws Exception {
	          Connection conn = JdbcUtils.getConnection();
	          String sql = "select * from account";
	          PreparedStatement st  = conn.prepareStatement(sql);
	          ResultSet rs = st.executeQuery();
	          //ResultSet.getMetaData()��ô���ResultSet����Ԫ���ݵ�ResultSetMetaData����
	          ResultSetMetaData metadata = rs.getMetaData();
	          //getColumnCount() ����resultset���������
	          System.out.println(metadata.getColumnCount());
	          //getColumnName(int column) ���ָ���е�����
	          System.out.println(metadata.getColumnName(1));
	          //getColumnTypeName(int column)���ָ���е�����
	          System.out.println(metadata.getColumnTypeName(1));
	          JdbcUtils.release(conn, st, rs);
	      }
	      
}