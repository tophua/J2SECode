/**
 * 
 */
package cn.wwy.ConcreteAggregate;

import cn.wwy.Aggregate.List;
import cn.wwy.ConcreteIterator.ConcreteIterator;
import cn.wwy.Iterator.Iterator;
/**
 * @author wangwy
 *
 */
public class ConcreteAggregate implements List{

	private Object[] list;
	private int size = 0;
	private int index = 0;	
    public ConcreteAggregate(){
        index=0;
        size=0;
        list=new Object[100];
    } 
	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		list[index++] = obj;
		size++;
	}
	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return list[index];
	}
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new ConcreteIterator(this);
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}
}
