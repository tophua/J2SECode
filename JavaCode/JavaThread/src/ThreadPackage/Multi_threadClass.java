package ThreadPackage;

class Thread1 extends Thread{
	private String name;
	public Thread1(String name){
		this.name=name;
	}
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(name+"����:"+i);
			try{
//Thread.sleep()��������Ŀ���ǲ��õ�ǰ�̶߳��԰�ռ
//�ý�������ȡ��CPU��Դ��������һ��ʱ��������߳�ִ�еĻ��ᡣ
				sleep((int) Math.random() * 10); 
			}catch(InterruptedException e){
				 e.printStackTrace();  
			}
		}
	}
}
public class Multi_threadClass {
///������������mainʱ��java���������һ�����̣����߳�main��main()
//����ʱ�򱻴��������ŵ���MitiSay�����������start������
//���������߳�Ҳ�����ˣ�����������Ӧ�þ��ڶ��߳������С�
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	Thread1 mt1=new Thread1("A");
	//	Thread1 mt2=new Thread1("B");
	//	mt1.start();
	//	mt2.start();
		new Thread1("A").start();
		new Thread1("B").start();
//start()�����ĵ��ú󲢲�������ִ�ж��̴߳��룬����ʹ�ø��̱߳�Ϊ������̬
//��Runnable����ʲôʱ���������ɲ���ϵͳ�����ġ�
	}
}
