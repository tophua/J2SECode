package HttpUrl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ClientLoginHttp {

	private static final String TAG = "netUtils";
	/*
	 * * ʹ��post��ʽ��½ 
	 * @param username 
	 * @param password 
	 * @return 
	 */  

   public static String loginOfPost(String username, String password) {  
		HttpURLConnection conn = null;  
		try {  
		     // ����һ��URL����  
		     URL mURL = new URL("http://localhost:12001");  
		     // ����URL��openConnection()����,��ȡHttpURLConnection����  
		     conn = (HttpURLConnection) mURL.openConnection();  
		     conn.setRequestMethod("POST");// �������󷽷�Ϊpost  
		     /* conn.setReadTimeout(5000);// ���ö�ȡ��ʱΪ5�� 
		        conn.setConnectTimeout(10000);// �����������糬ʱΪ10��*/  
		     conn.setDoOutput(true);// ���ô˷���,������������������  

             int len=0;  
		     String path="E:\\private_project\\github_project\\nsar\\test.txt";  
		     // post����Ĳ���  
		     byte[] buf= new byte[10240];  
		     FileInputStream fis=new FileInputStream(path);  
		     while((len = fis.read(buf)) != -1){  
		         System.out.print(len);  
		     }  
		  // String data = "username=" + username + "&password=" + password;  
		  // ���һ�������,�������д����,Ĭ�������,ϵͳ��������������������  
		     OutputStream out = conn.getOutputStream();// ���һ�������,�������д����  
		  // out.write(data.getBytes());  
		     out.write(buf);  
		     out.flush();  
		     out.close();  
		     int responseCode = conn.getResponseCode();// ���ô˷����Ͳ�����ʹ��conn.connect()����  
		     if (responseCode == 200) {  
		          InputStream is = conn.getInputStream();  
		//          String state = getStringFromInputStream(is);  
		 //         return state;  
		      } else {  
		          //Log.i(TAG, "����ʧ��" + responseCode);  
		          System.out.print("����ʧ��");  
		      }  
		  } catch (Exception e) {  
		         e.printStackTrace();  
		  } finally {  
		    if (conn != null) {  
		          conn.disconnect();// �ر�����  
		     }  
		  }  
		  return null;  
	}  

   /*
    * * ʹ��get��ʽ��½ 
    * @param username 
    * @param password 
    * @return 
    */  
  public static String loginOfGet(String username, String password) {  
      HttpURLConnection conn = null;  
      String data = "username=" + URLEncoder.encode(username) + "&password="+ URLEncoder.encode(password);  
      String url = "http://192.168.0.100:8080/android/servlet/LoginServlet?"+ data;  
      try {  
        // ����string url����URL����  
            URL mURL = new URL(url);  
            conn = (HttpURLConnection) mURL.openConnection();  
            conn.setRequestMethod("GET");  
            conn.setReadTimeout(5000);  
            conn.setConnectTimeout(10000);  
            int responseCode = conn.getResponseCode();  
            if (responseCode == 200) {  
                 InputStream is = conn.getInputStream();  
             //  String state = getStringFromInputStream(is);  
             //    return state;  
             } else {  
            //    Log.i(TAG, "����ʧ��" + responseCode);  
                  System.out.print("����ʧ��");  
             }  
         } catch (Exception e) {  
              e.printStackTrace();  
         } finally {  
             if (conn != null) {  
                 conn.disconnect();  
              }  
         }  
        return null;  
   }  
 
  /*
   * * ����������һ���ַ�����Ϣ         * 
    * @param is 
    * @return 
    * @throws IOException 
   */  
  private static String getStringFromInputStream(InputStream is)  
      throws IOException {  
          ByteArrayOutputStream os = new ByteArrayOutputStream();  
        // ģ����� ��������  
         byte[] buffer = new byte[1024];  
         int len = -1;  
        // һ��Ҫдlen=is.read(buffer)  
        // ���while((is.read(buffer))!=-1)���޷�������д��buffer��  
        while ((len = is.read(buffer)) != -1) {  
               os.write(buffer, 0, len);  
         }  
         is.close();  
         String state = os.toString();
     // �����е�����ת�����ַ���,���õı�����utf-8(ģ����Ĭ�ϱ���)  
         os.close();  
     return state;  
 }  
	 
}
