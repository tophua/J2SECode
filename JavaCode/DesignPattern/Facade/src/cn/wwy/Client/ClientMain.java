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
		//使用外观模式，就将复杂的调用给封装好
		comFacade pc = new comFacade();
		pc.start(); 
		
//		//不使用外观模式
//		comCPU cpu = new comCPU();
//		comdisk disk = new comdisk();
//		cpu.start();
//		disk.load();
		
	}

}
