package Test.LOB;

public class MainClob {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		JdbcOperaClob temp=new JdbcOperaClob();
//		temp.add();
//		temp.read();
		
		JdbcOperaBlob blob=new JdbcOperaBlob();
		blob.add();
		blob.read();
	}

}
