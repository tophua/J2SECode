/**
 * 
 */
package finalPackage;

/**
 * @author wangwy
 *
 */
public class ColorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(isRed(Color.GREEN));
		System.out.println(Color.GREEN);	
	}
	
	static boolean isRed(Color color){
		if(Color.RED.equals(color)){
			return true;
		}
		return false;
	}

}
