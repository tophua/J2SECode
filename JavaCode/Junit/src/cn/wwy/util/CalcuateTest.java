package cn.wwy.util;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class CalcuateTest {

	Calcuate calcuate;
	
	@Test
	public void testAdd() {
	//	fail("Not yet implemented");
		calcuate = new Calcuate();
		int result = calcuate.add(2, 3);
		Assert.assertEquals("������",5, result);
	}

	@Test
	public void testSubtract() {
	// fail("Not yet implemented");
		calcuate = new Calcuate();
		int result = calcuate.subtract(12, 2); 
		Assert.assertEquals("����������", 10000, result); //�������ü�������ֵΪ10000
	}

	@Test
	public void testMultiply() {
	//	fail("Not yet implemented");
		calcuate = new Calcuate();
		int result = calcuate.multiply(2, 3);
		Assert.assertEquals("�˷�������", 6, result);

	}

	@Test
	public void testDivide() {
	//	fail("Not yet implemented");
		calcuate = new Calcuate();
		int result = calcuate.divide(6, 3);
		Assert.assertEquals("����������", 2, result);

	}

}
