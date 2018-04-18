package Test_Dom4j;

public class OperatorDom4jTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OperatorDom4j xml1=new OperatorDom4j();
		try{
			xml1.creat("D://Java_wwy//workspace//TestJAVAInfo//src//Test_Dom4j//users.xml");
			xml1.readAll("D://Java_wwy//workspace//TestJAVAInfo//src//Test_Dom4j//users_copy.xml");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
