package ServletPackage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet_TestBasicHttp extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	           response.setStatus(302);//���÷���������Ӧ״̬��
	           /**
	           *������Ӧͷ��������ͨ�� Location���ͷ���������������������������ν�������ض���
	           */
	          response.setHeader("Location", "/JavaWeb_HttpProtocol_Study_20140528/1.jsp");
	  }
	@Override
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
	     throws ServletException, IOException {
	           this.doGet(request, response);
    }
}
