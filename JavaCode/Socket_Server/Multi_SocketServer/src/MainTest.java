
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  try {
	            // TODO code application logic here
	            EchoServerThread echoThread = new EchoServerThread(8089);
//	            String test = "一";
//	            System.out.println(System.getProperty("file.encoding"));// java默认编码是UTF-8
//	            System.out.println(test);
//	            System.out.println(test.length());
//	            System.out.println(test.getBytes("GB2312").length);
//	            System.out.println(test.getBytes("UTF8").length);
//	            System.out.println(test.getBytes("GBK").length);
//	            System.out.println(new String(test.getBytes("GB2312"),"GB2312"));//用什么拆就用什么组装，否则显示乱码
//	            System.out.println(new String(test.getBytes("GB2312"),"GB2312").length());//用什么拆就用什么组装，否则显示乱码
	        } catch (Exception ex) {
	            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}

}
