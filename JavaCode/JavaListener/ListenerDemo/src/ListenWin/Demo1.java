package ListenWin;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Demo1 {

	 /**
     *java���¼���������
     *1���¼������漰������������¼�Դ���¼������¼�������
     *2�����¼�Դ�Ϸ���ĳһ������ʱ����������¼���������һ�����������ڵ��ø÷���ʱ���¼����󴫵ݽ�ȥ��
     *    ������Ա�ڼ�������ͨ���¼����󣬾Ϳ����õ��¼�Դ���Ӷ����¼�Դ���в�����
     */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  Frame f = new Frame();
	        f.setSize(400, 400);
	        f.setVisible(true);
	        
	        //ע���¼�������
	        f.addWindowListener(new WindowListener(){

	            public void windowActivated(WindowEvent e) {
	                
	            }

	            public void windowClosed(WindowEvent e) {
	                
	            }

	            /**
	             * ��window����ر�ʱ�ͻ�WindowListener�����������������
	             * �������ͻ����windowClosing��������window����ر�ʱ�Ķ���
	             */
	            public void windowClosing(WindowEvent e) {
	                //ͨ���¼�����e����ȡ�¼�Դ����
	                Frame f = (Frame) e.getSource();
	                System.out.println(f+"�������ڹر�");
	                f.dispose();
	            }

	            public void windowDeactivated(WindowEvent e) {
	                
	            }

	            public void windowDeiconified(WindowEvent e) {
	                
	            }

	            public void windowIconified(WindowEvent e) {
	                
	            }

	            public void windowOpened(WindowEvent e) {
	                
	            }
	        });
	        
	}

}
