
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  EchoServerThread���½��߳��������ͻ�������
 * EchoThread��Ϊ�����Ĵ����̵߳Ļ��࣬��Ҫ�ǰ�socketͨ�Ų����ٷ�װһ��
 * ��run�����аѿͻ����������Ϣ����ClientRequestHandler������������ֻ����socket����Ϣ���պͷ���
 */


public class EchoThread extends Thread{
	 protected PrintStream out;
	    protected String message;
	    protected Socket clientSocket;

	    //������
	    public EchoThread(Socket clientSocket) {
	        super();
	        try {
	            //�������ݣ��������������ģ���Ϊ������
	            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
	            byte[] buffer = new byte[10000];  //�������Ĵ�С
	            in.read(buffer);               //�������յ��ı��ģ�ת�����ַ���
	            /**
	             * C++���ݹ����������֣���Ҫת��һ�¡�C++Ĭ��ʹ��GBK��
	             * GB2312��GBK���Ӽ���ֻ�м������ġ���Ϊ���ݿ���GB2312����������ֱ��תΪGB2312
	             * */
	            message = new String(buffer, "GB2312").trim();
	            System.out.println("���Կͻ��˵���Ϣ��"+message);
	            
	            this.clientSocket = clientSocket;
	            // �����������
	            out = new PrintStream(clientSocket.getOutputStream());
	        } catch (IOException ex) {
	            Logger.getLogger(EchoServerThread.class.getName()).log(Level.SEVERE, null, ex);
	            try {
	                clientSocket.close();
	            } catch (IOException ex1) {
	                Logger.getLogger(EchoServerThread.class.getName()).log(Level.SEVERE, null, ex1);
	            }
	        }
	    }

	    //���Ƿ�,����
	    public void run() {
	        try {
	             /**
	             * ������Ҫʹ�õײ��byte���������ݡ���Ϊ��ʹֱ��д�ַ��������ײ㻹�ǵ����˴���byte�ķ�����
	             * �����漰���������⡣C++Ĭ��ʹ��GBK����GB2312��GBK���Ӽ���
	             */

	            byte[] responseBuffer = new ClientRequestHandler(message).response().getBytes("GB2312");
	        	for(int i=0;i<10;i++){
	        		 out.write(responseBuffer, 0, responseBuffer.length);
	        		 Thread.sleep(2000);
	        	}
	     //       out.write(responseBuffer, 0, responseBuffer.length);
//	            String test = new ClientRequestHandler(message).response();
//	            out.print(test);//ֱ��UTF8��������յײ�ÿ��������3���ֽڴ���
//	            out.print(new String(test.getBytes(),"GBK"));//תGBKʧ�ܣ�ʵ��ÿ������������4��5���ֽڴ���
//	            out.print(new String(test.getBytes("GBK"),"GBK"));//תGBK�����ײ㻹��Ҫ����ֽ����飬��Ȼ���ջ��Ǹ�UTF8һ��

	        } catch (Exception ex) {
	            Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        try {
	            clientSocket.close();
	        } catch (IOException ex) {
	            Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

	    public String getMessage() {
	        return message;
	    }

	    /**
	     * ��ȡ�����
	     * @return
	     */
	    public PrintStream getOutputStream() {
	        return out;
	    }
}