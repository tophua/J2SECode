/**
 * 
 */
package cn.wwy.Facade;

import cn.wwy.computer.comCPU;
import cn.wwy.computer.comdisk;

/**
 * @author wangwy
 *  门面系统角色
 */
public class comFacade {

	private comCPU cpu;
	private comdisk disk;
	
	public comFacade(){
		cpu = new comCPU();
		disk = new comdisk();
	}
	
	public void start(){
		cpu.start();
		disk.load();
	}
}
