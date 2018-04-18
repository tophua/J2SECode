package MyJDBC;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
	
	  private static String driver = null;
	    private static String url = null;
	    private static String username = null;
	    private static String password = null;
	    
	    static{
	        try{
	            //��ȡdb.properties�ļ��е����ݿ�������Ϣ
	            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
	            Properties prop = new Properties();
	            prop.load(in);
	            
	            //��ȡ���ݿ���������
	            driver = prop.getProperty("driver");
	            //��ȡ���ݿ�����URL��ַ
	            url = prop.getProperty("url");
	            //��ȡ���ݿ������û���
	            username = prop.getProperty("username");
	            //��ȡ���ݿ���������
	            password = prop.getProperty("password");
	            
	            //�������ݿ�����
	            Class.forName(driver);
	            
	        }catch (Exception e) {
	            throw new ExceptionInInitializerError(e);
	        }
	    }
	    
	    /**
	    * @Method: getConnection
	    * @Description: ��ȡ���ݿ����Ӷ���
	    * @return Connection���ݿ����Ӷ���
	    * @throws SQLException
	    */ 
	    public static Connection getConnection() throws SQLException{
	        return DriverManager.getConnection(url, username,password);
	    }
	    
	    /**
	    * @Method: release
	    * @Description: �ͷ���Դ��
	    *     Ҫ�ͷŵ���Դ����Connection���ݿ����Ӷ��󣬸���ִ��SQL�����Statement���󣬴洢��ѯ�����ResultSet����
	    * @param conn
	    * @param st
	    * @param rs
	    */ 
	    public static void release(Connection conn,Statement st,ResultSet rs){
	        if(rs!=null){
	            try{
	                //�رմ洢��ѯ�����ResultSet����
	                rs.close();
	            }catch (Exception e) {
	                e.printStackTrace();
	            }
	            rs = null;
	        }
	        if(st!=null){
	            try{
	                //�رո���ִ��SQL�����Statement����
	                st.close();
	            }catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        
	        if(conn!=null){
	            try{
	                //�ر�Connection���ݿ����Ӷ���
	                conn.close();
	            }catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    /**
	    * @Method: update
	    * @Description: ���ܸ���
	    * ����ʵ���CUD�������������ͬ���������͸����ݿ��SQL��䲻ͬ���ѣ�
	    * ��˿��԰�CUD������������ͬ�����ȡ���������һ��update�����У�������������ձ仯��SQL���
	    * @param sql Ҫִ�е�SQL
	    * @param params ִ��SQLʱʹ�õĲ���
	    * @throws SQLException
	    */ 
	    public static void update(String sql,Object params[]) throws SQLException{
	        Connection conn = null;
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        try{
	            conn = getConnection();
	            st = conn.prepareStatement(sql);
	            for(int i=0;i<params.length;i++){
	                st.setObject(i+1, params[i]);
	            }
	            st.executeUpdate();
	            
	        }finally{
	            release(conn, st, rs);
	        }
	    }
	    
	    /**
	    * @Method: query
	    * @Description:���ܲ�ѯ
	    * ʵ���R��������SQL��䲻֮ͬ�⣬���ݲ�����ʵ�岻ͬ����ResultSet��ӳ��Ҳ������ͬ��
	    * ��˿���һ��query���������Բ�����ʽ���ձ仯��SQL����⣬����ʹ�ò���ģʽ��qurey�����ĵ����߾�����ΰ�ResultSet�е�����ӳ�䵽ʵ������С�
	    * @param sql Ҫִ�е�SQL
	    * @param params ִ��SQLʱʹ�õĲ���
	    * @param rsh ��ѯ���صĽ����������
	    * @return
	    * @throws SQLException
	    */ 
	    public static Object query(String sql,Object params[],ResultSetHandler rsh) throws SQLException{
	        
	        Connection conn = null;
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        
	        try{
	            conn = getConnection();
	            st = conn.prepareStatement(sql);
	            for(int i=0;i<params.length;i++){
	                st.setObject(i+1, params[i]);
	            }
	            rs = st.executeQuery();
	            /**
	             * ���ڲ�ѯ���صĽ��������ʹ�õ��˲���ģʽ��
	             * �����query����ʱ��query�����������޷�֪���û��Է��صĲ�ѯ�������ν��д���ģ�����֪��������Ĵ�����ԣ�
	             * ��ô���������Ĵ�����Ծ����û��Լ��ṩ��query�����ڲ��͵����û��ύ�Ľ����������Խ��д���
	             * Ϊ���ܹ����û��ṩ������Ĵ�����ԣ���Ҫ���û���¶��һ�����������ӿ�ResultSetHandler
	             * �û�ֻҪʵ����ResultSetHandler�ӿڣ���ôquery�����ڲ���֪���û�Ҫ��δ���������
	             */
	            return rsh.handler(rs);
	            
	        }finally{
	            release(conn, st, rs);
	        }
	    }
}
