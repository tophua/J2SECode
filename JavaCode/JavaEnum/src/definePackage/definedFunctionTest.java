/**
 * 
 */
package definePackage;

import finalPackage.Color;

/**
 * @author wangwy
 *
 */
public class definedFunctionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(definedFunction de:definedFunction.values()){
			System.out.println("type�� "+de.getType()+
					" des: "+de.getDes()+
					" level: "+de.getLevel());	
		}
	}

}
