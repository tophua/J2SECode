
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * һ���򵥵�socket���������ܽ��ܿͻ������󣬲������󷵻ظ��ͻ���
 * ÿ����һ���ͻ������½���Ӧ��EchoThread��Ϊ��Ӧ��
 */

public class EchoServerThread {

	  // �����������Socket
  static int port = 9538;
  ServerSocket serverSkt = null;

  // ���췽��
  private EchoServerThread() {
      System.out.println("�������������ڼ������˿ڣ�" + port);
      try {
          // ��������socket
          serverSkt = new ServerSocket(port);
      } catch (IOException ex) {
          Logger.getLogger(EchoServerThread.class.getName()).log(Level.SEVERE, null, ex);
      }
      while (true) {
          try {
              // ������������
              Socket clientSocket = serverSkt.accept(); //��ΪSocket����ˣ���һֱ��accept��������
                                                       //������Client����ʱ����ô�Ϳ�ʼ������߳�
                                                       //start()������߳̾ʹ���������ʧ������Ϊ
                                                       //socket����ˣ�������ȴ��¸�����
              new EchoThread(clientSocket).start();
          } catch (IOException e) {
              System.out.println("�޷����ܵ�ǰ�ͻ���������");
              break;
          }
      }
  }
  
  public static EchoServerThread getInstance(){
	  return Singleton.INSIANCE.getInstance();
  }
  
  private static enum Singleton{
	  INSIANCE;
	  
	  private EchoServerThread singleton;
	  //JVMȷ���˷���ֻ����һ��
	  private Singleton(){
		  singleton = new EchoServerThread();
	  }
	  
	  public EchoServerThread getInstance(){
		  return singleton;
	  }
  }
  
  
}
