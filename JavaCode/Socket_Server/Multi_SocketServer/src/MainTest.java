
import java.util.logging.Level;
import java.util.logging.Logger;

import socketclient.SimpleClient;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  try {
	            // TODO code application logic here
	//		    subscribe();
	            EchoServerThread echoThread = EchoServerThread.getInstance();
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
	
	//���Ͷ��Ĺ���
	
	static void subscribe(){
		byte[] byAll=new byte[16];
		
		byte[] byCode=new byte[3];
		byCode="INF".getBytes();
		byAll[0]=byCode[0];
		byAll[1]=byCode[1];
		byAll[2]=byCode[2];
		byAll[3]=(byte)168;
		byAll[4]=(byte)1;
		byAll[5]=(byte)0;
		byAll[6]=(byte)1;
		byAll[8]=(byte)0;
		
		
		SimpleClient si=new SimpleClient("10.82.18.113",9537);
		si.sendRequest(byAll);  
		si.getReponse();
	}

}
