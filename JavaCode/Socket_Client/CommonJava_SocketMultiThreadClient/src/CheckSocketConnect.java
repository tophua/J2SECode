
public class CheckSocketConnect extends Thread{
	RecvThread Recv=null;
	SocketClient ClientSo=null;
	public String ServerName;
	public int nPort;
	
	public CheckSocketConnect(){
		super();
		
	}
	
	public void InitConnect(String serverName,int port,RecvThread recv){
		this.Recv=recv;
		this.ServerName=serverName;
		this.nPort=port;
		
		ClientSo =new SocketClient();
		ClientSo.InitSocket(serverName,port,recv);
		ClientSo.start();
	}
	
	
	@Override
	public void run(){
		
		while(true){		
			if(ClientSo.getClientSocket()==null){ //说明socket断开连接，重新连接
				InitConnect(ServerName,nPort,Recv);
			}else
			{
				ClientSo.getReponse();
			}
		}		
	}
	
	public SocketClient GetSocketHandle(){
		return ClientSo;
	}
}
