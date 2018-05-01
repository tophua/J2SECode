package ThreadSync;

public class Timer {
	  private static int num = 0;

	 //public synchronized void add(String name)
	 // ����������ʱ����synchronizedʱ��ʾ��ִ����������Ĺ���֮�е�ǰ��������
	  public void add(String name) {

	        synchronized (this) {
	            /*
	             * ʹ��synchronized(this)��������ǰ����
	             * �����Ͳ����ٳ���������ͬ���߳�ͬʱ����ͬһ��������Դ��������
	             * ֻ�е�һ���̷߳��ʽ�����Ż��ֵ���һ���߳�������
	             */
	            num++;
	            try {
	                Thread.sleep(1);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println(name + "�����ǵ�" + num + "��ʹ��timer���߳�");
	        }
	 }
}
