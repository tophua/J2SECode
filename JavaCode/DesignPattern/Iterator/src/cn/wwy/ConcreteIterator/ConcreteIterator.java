/**
 * 
 */
package cn.wwy.ConcreteIterator;


import cn.wwy.Aggregate.List;
import cn.wwy.Iterator.Iterator;

/**
 * @author wangwy
 *
 */
public class ConcreteIterator implements Iterator{

	private List list = null;
	private int index;
	
	public ConcreteIterator(List list){
		super();
		this.list = list;
	}	    
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
        if (index >= list.getSize()) {
            return false;
        } else {
            return true;
        }
	}
	@Override
	public Object next() {
		// TODO Auto-generated method stub
		Object object = list.get(index);
	    index++;
	    return object;
	}
}
