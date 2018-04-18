
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;



public class SimpleServer {
	ServerSocket serverSkt = null;// 服务器端侦探听的Socket
	Socket clientSket = null;// 客户端
	BufferedReader in = null;// 客户端输入流
	PrintStream out = null;// 客户端输出流
	// 构造方法

	public SimpleServer(int port) {
		System.out.println("===服务器正在监听，端口：" + port + "===");
		try {
			serverSkt = new ServerSocket(port);// 创建监听Socket
		} catch (IOException e) {
			System.out.println("监听端口+" + port + "失败");
		}

		try {
			clientSket = serverSkt.accept();// 接收连接请求
		} catch (IOException e) {
			System.out.println("连接失败");
		}
		// 获取输入输出流
		try {
			in = new BufferedReader(new InputStreamReader(clientSket.getInputStream()));
			out = new PrintStream(clientSket.getOutputStream());
		} catch (IOException e) {

		}
	}

	// 收到客户端请求
	public String getRequest() {
		String frmClt = null;
		try {
			frmClt = in.readLine();
			// 从客户端的输入流中读取一行数据
			System.out.print("Server收到请求：" + frmClt);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("无法读取端口.....");
			System.exit(0);
		}
		return frmClt;
	}

	// 发送响应给客户端
	public void sendResponse(String response) {
		try {
			out.println(response);// 往客户端输出流中写入数据
			System.out.println("Server响应请求：" + response);
		} catch (Exception e) {
			System.out.print("写端口失败");
			System.exit(0);
		}
	}

}
