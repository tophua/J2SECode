import cn.wwy.Aggregate.List;
import cn.wwy.ConcreteAggregate.ConcreteAggregate;
import cn.wwy.Iterator.Iterator;

/**
 * 
 */

/**
 * @author wangwy
 *
 */
public class IteratorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List list = new ConcreteAggregate();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		Iterator it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
