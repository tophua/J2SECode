
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  try {
	            // TODO code application logic here
	            EchoServerThread echoThread = new EchoServerThread(8089);
//	            String test = "һ";
//	            System.out.println(System.getProperty("file.encoding"));// javaĬ�ϱ�����UTF-8
//	            System.out.println(test);
//	            System.out.println(test.length());
//	            System.out.println(test.getBytes("GB2312").length);
//	            System.out.println(test.getBytes("UTF8").length);
//	            System.out.println(test.getBytes("GBK").length);
//	            System.out.println(new String(test.getBytes("GB2312"),"GB2312"));//��ʲô�����ʲô��װ��������ʾ����
//	            System.out.println(new String(test.getBytes("GB2312"),"GB2312").length());//��ʲô�����ʲô��װ��������ʾ����
	        } catch (Exception ex) {
	            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}

}
