/**
 * 
 */
package cn.wwy.Client;

import cn.wwy.Facade.comFacade;
import cn.wwy.computer.comCPU;
import cn.wwy.computer.comdisk;

/**
 * @author wangwy
 *
 */
public class ClientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ʹ�����ģʽ���ͽ����ӵĵ��ø���װ��
		comFacade pc = new comFacade();
		pc.start(); 
		
//		//��ʹ�����ģʽ
//		comCPU cpu = new comCPU();
//		comdisk disk = new comdisk();
//		cpu.start();
//		disk.load();
		
	}

}
