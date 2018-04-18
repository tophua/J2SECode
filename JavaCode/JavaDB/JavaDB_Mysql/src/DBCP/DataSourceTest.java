package DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataSourceTest {

	    public void dbcpDataSourceTest() {
	        Connection conn = null;
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        try{
	            //��ȡ���ݿ�����
	            conn = JdbcUtils_DBCP.getConnection();
	            String sql = "insert into test1(name) values(?)";
	            st = conn.prepareStatement(sql);
	            st.setString(1, "gacl");
	            st.executeUpdate();
	            //��ȡ���ݿ��Զ����ɵ�����
	            rs = st.getGeneratedKeys();
	            if(rs.next()){
	                System.out.println(rs.getInt(1));
	            }
	        }catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            //�ͷ���Դ
	            JdbcUtils_DBCP.release(conn, st, rs);
	        }
	    }
}