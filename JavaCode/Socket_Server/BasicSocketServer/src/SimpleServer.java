
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;



public class SimpleServer {
	ServerSocket serverSkt = null;// ����������̽����Socket
	Socket clientSket = null;// �ͻ���
	BufferedReader in = null;// �ͻ���������
	PrintStream out = null;// �ͻ��������
	// ���췽��

	public SimpleServer(int port) {
		System.out.println("===���������ڼ������˿ڣ�" + port + "===");
		try {
			serverSkt = new ServerSocket(port);// ��������Socket
		} catch (IOException e) {
			System.out.println("�����˿�+" + port + "ʧ��");
		}

		try {
			clientSket = serverSkt.accept();// ������������
		} catch (IOException e) {
			System.out.println("����ʧ��");
		}
		// ��ȡ���������
		try {
			in = new BufferedReader(new InputStreamReader(clientSket.getInputStream()));
			out = new PrintStream(clientSket.getOutputStream());
		} catch (IOException e) {

		}
	}

	// �յ��ͻ�������
	public String getRequest() {
		String frmClt = null;
		try {
			frmClt = in.readLine();
			// �ӿͻ��˵��������ж�ȡһ������
			System.out.print("Server�յ�����" + frmClt);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�޷���ȡ�˿�.....");
			System.exit(0);
		}
		return frmClt;
	}

	// ������Ӧ���ͻ���
	public void sendResponse(String response) {
		try {
			out.println(response);// ���ͻ����������д������
			System.out.println("Server��Ӧ����" + response);
		} catch (Exception e) {
			System.out.print("д�˿�ʧ��");
			System.exit(0);
		}
	}

}
