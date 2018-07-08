/**
 * 描述: 
 * SafetyTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.safety;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 安全技术 - 测试
 * 
 * @author qye.zheng
 * SafetyTest
 */
public final class SafetyTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * @description 加密算法
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	private static String mSecret(String value)
	{
		String secretStr = "trips";
		secretStr = "abcdef";
		StringBuilder newBuilder = new StringBuilder();
		char ss[] = secretStr.toCharArray();
		/** M_SECRET长度为5,如果长度小于5处理字符会报错,直接返回value **/
		if (ss.length < 5)
			return value;
		int num = ss[0] - ss[4];
		int num1 = ss[1] - ss[3];
		int num2 = ss[2] - ss[4];
		int num3 = ss[3] - ss[4];
		int num4 = ss[0] - ss[2];
		for (int i = 0; i < value.length(); i++)
		{
			char x = value.charAt(i);
			if (x >= 'a' && x <= 'w')
			{
				x = (char) (x + 3);
				newBuilder.append(x);
			}
			if (x >= 'x' && x <= 'z')
			{
				x = (char) (x - 23);
				newBuilder.append(x);
			}
			if (x >= 'A' && x <= 'W')
			{
				x = (char) (x + 3);
				newBuilder.append(x);
			}
			if (x >= 'X' && x <= 'Z')
			{
				x = (char) (x + 3);
				newBuilder.append(x);
			}
			if (Character.isDigit(x))
			{
				Integer j = Integer.parseInt("" + x);
				j = j + 5;
				newBuilder.append(j);
			}
		}
		String newsecret = newBuilder.toString();
		for (int j = 0; j < secretStr.length(); j++)
		{
			char cs = secretStr.charAt(j);
			if (newsecret.indexOf(cs) > 0)
			{
				if (j == 0)
				{
					newsecret = newsecret.replace(cs, (char) (cs + num));
				} else if (j == 1)
				{
					newsecret = newsecret.replace(cs, (char) (cs + num1));
				} else if (j == 2)
				{
					newsecret = newsecret.replace(cs, (char) (cs + num2));
				} else if (j == 3)
				{
					newsecret = newsecret.replace(cs, (char) (cs + num3));
				} else if (j == 4)
				{
					newsecret = newsecret.replace(cs, (char) (cs + num4));
				}
			}

		}
		System.out.println(newsecret);
		return newsecret;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMSetcret() {
		try {
			//678EF
			System.out.println(mSecret("123BC"));
			
		} catch (Exception e) {
			log.error("testMSetcret =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSafety() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSafety =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			System.out.println("AC8924DC56D2EC78A3DE022137995A05".length());
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
