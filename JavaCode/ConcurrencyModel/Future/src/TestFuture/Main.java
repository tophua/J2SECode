package TestFuture;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = new Client();
		//��ⷵ��һ��FutureData
		Data data = client.request("name");
		System.out.println("������ϣ�");
		
		try{		
			//��������ҵ��
			//��������У���ʵ����RealData��װ��ɣ��ظ����õȴ�ʱ��
			Thread.sleep(2000);						
		    }catch (Exception e){			
		 }
		
		//��ʵ����
		System.out.println("���� = "+ data.getResult());
	}

}
