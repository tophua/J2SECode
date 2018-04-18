
public class ClientRequestHandler {
	  private String message;

	    public ClientRequestHandler(String message) {
	        this.message = message;
	    }

	    public String response() {
	        return "服务端返回给客户端111";
	    }
}
