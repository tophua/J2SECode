package ThreadPackage;

class MyThread extends Thread{
	private String name;
	private int ticket=5;
	public MyThread(String name){
		this.name=name;
	}
	@Override
	public synchronized void run(){
		for(int i=0;i<10;i++){
			if(this.ticket>0){
				System.out.println("�߳̿�ʼ:"+this.name+"��Ʊ:i="+this.ticket);
				ticket--;
			}
		}
	}
}

public class Test_ExtendsThreadClass{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//����run()����
//	Thread t1=new MyThread("�߳�a");
//	Thread t2=new MyThread("�߳�b");
//	t1.run(); //ֻ����run()���������ַ�����ʵ��û�ж��߳�˼��
//	t2.run();		

//����start()����
		Thread t3=new MyThread("�߳�c");
		Thread t4=new MyThread("�߳�d");
		t3.start(); //start()�����½�һ���߳�ȡִ��Run()����������Դ������
		t4.start();
	}

}
