/**
 * 
 */
package cn.wwy.Aggregate;

import cn.wwy.Iterator.Iterator;

/**
 * @author wangwy
 *
 */
//���弯�Ͽ��Խ��еĲ���
public interface List {
	public void add(Object obj);  
	public Object get(int index);
	public Iterator iterator();  
	public int getSize();
}
