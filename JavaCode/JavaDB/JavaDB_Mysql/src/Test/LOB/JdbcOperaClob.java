package Test.LOB;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * ʹ��JDBC����Mysql�Ĵ��ı�
 */
public class JdbcOperaClob {

	  /**
	    * @Method: add
	    * @Description:�����ݿ��в�����ı�����
	    * @Anthor:�°�����
	    *
	    */ 
	
	    public void add(){
	        Connection conn = null;
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        Reader reader = null;
	        try{
	            conn = JdbcUtils.getConnection();
	            String sql = "insert into testclob(resume) values(?)";
	            st = conn.prepareStatement(sql);
	            //���ַ�ʽ��ȡ��·�������еĿո�ᱻʹ�á�%20������
	            String path = JdbcOperaClob.class.getClassLoader().getResource("data.txt").getPath();
	            //����%20���滻�ؿո�
	            path = path.replaceAll("%20", " ");
	            File file = new File(path);
	            reader = new FileReader(file); //�Ķ�ʱʧ��
	            st.setCharacterStream(1, reader,(int) file.length());
	            int num = st.executeUpdate();
	            if(num>0){
	                System.out.println("����ɹ�����");
	            }
	            //�ر���
	            reader.close();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            JdbcUtils.release(conn, st, rs);
	        }
	    }
	    
	    /**
	    * @Method: read
	    * @Description: ��ȡ���ݿ��еĴ��ı�����
	    * @Anthor:�°�����
	    *
	    */ 
	    public void read(){
	        Connection conn = null;
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        try{
	            conn = JdbcUtils.getConnection();
	            String sql = "select resume from testclob where id=2";
	            st = conn.prepareStatement(sql);
	            rs = st.executeQuery();
	            
	            String contentStr ="";
	            String content = "";
	            if(rs.next()){
	                //ʹ��resultSet.getString("�ֶ���")��ȡ���ı����ݵ�����
	                content = rs.getString("resume");
	                //ʹ��resultSet.getCharacterStream("�ֶ���")��ȡ���ı����ݵ�����
	                Reader reader = rs.getCharacterStream("resume");
	                char buffer[] = new char[1024];
	                int len = 0;
	                FileWriter out = new FileWriter("D:\\1.txt");
	                while((len=reader.read(buffer))>0){
	                    contentStr += new String(buffer);
	                    out.write(buffer, 0, len);
	                }
	                out.close();
	                reader.close();
	            }
	            System.out.println(content);
	            System.out.println("-----------------------------------------------");
	            System.out.println(contentStr);
	        }catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            JdbcUtils.release(conn, st, rs);
	        }
	    }
	
	
	
}