/**
 * 描述: 
 * Base64UtilTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.base64;

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
import com.hua.util.Base64Util;
import com.hua.util.ByteUtil;
import com.hua.util.StringUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * Base64UtilTest
 */
public final class Base64UtilTest extends BaseTest {

	

	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void base64Example() {
		try {
			/*
			 * 	 * Base64编码: 从二进制到字符的过程.
			 * 将所有字符转成 8位二进制，以6个二进制为单位形成
			 * 一组，因为不够8位，每组在高位补2个0，最后查找ASCII表
			 * 即可输出结果.
			 * 例如 s 1 3 -> ASCII码十进制: 115 49 51
			 * -> 转成8位二进制: 01110011 00110001 00110011
			 * -> 6个一组(4组): 011100 110011 000100 110011
			 * -> 高位补2个0: 00011100 00110011 00000100 00110011
			 * -> 转十进制: 28 51 4 51
			 * -> 查找base64对照表输出结果: c z E z
			 * 
			 * [99, 122, 69, 122]
			 * 
			 * 
			 * 
			 */
			final String originalStr = "s13";
			/* base64编码过程，自主实现 */
			StringBuilder binaryStrBuilder = StringUtil.getBuilder();
			// 1. 字符-整型值(ASCII码)转成二进制格式字符串
			for (int i = 0; i < originalStr.length(); i++)
			{
				String binaryStr = addZero(Integer.toBinaryString(originalStr.codePointAt(i)));
				binaryStrBuilder.append(binaryStr);
			}
			
			// 3. 分成4组。每组6位 111001 111000 1110011
			String group0 = binaryStrBuilder.substring(0, 6);
			String group1 = binaryStrBuilder.substring(6, 12);
			String group2 = binaryStrBuilder.substring(12, 18);
			String group3 = binaryStrBuilder.substring(18, 24);
			
			// 4. 每组高位补2个0
			group0 = "00" + group0;
			group1 = "00" + group1;
			group2 = "00" + group2;
			group3 = "00" + group3;
			
/*			System.out.println(group0);
			System.out.println(group1);
			System.out.println(group2);
			System.out.println(group3);*/
			
			// 5. 将每组8位二进制转成整型值
			byte value0 = Byte.parseByte(group0, 2);
			byte value1 = Byte.parseByte(group1, 2);
			byte value2 = Byte.parseByte(group2, 2);
			byte value3 = Byte.parseByte(group3, 2);
	
			// 6. 查找base64对照表得出对应的字符 c z E z

			
			
			// byte 值范围: [-128, +127)
			//System.out.println(Byte.MIN_VALUE);
			//System.out.println(Byte.MAX_VALUE);

			
			
		} catch (Exception e) {
			log.error("base64Example =====> ", e);
		}
	}
	
	/**
	 * 
	 * @description 补充为完整的二进制
	 * @param str
	 * @return
	 * @author qianye.zheng
	 */
	private String addZero(String str)
	{
		while (str.length() < 8)
		{
			str = "0" + str;
		}
		
		return str;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase64Util() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase64Util =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testEncodeBase64() {
		try {
			String target = "abcde";
			log.info("testEncodeBase64 =====> " + Base64Util.encodeToString(target.getBytes()));
			
			// 极端值 - 测试
			/*
			 * null 值将导致空指针异常 target.getBytes()
			 */
			target = null;
			log.info("testEncodeBase64 =====> " + Base64Util.encodeToString(target.getBytes()));
			
			target = "";
			log.info("testEncodeBase64 =====> " + Base64Util.encodeToString(target.getBytes()));
			
		} catch (Exception e) {
			log.error("testEncodeBase64 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDecodeBase64() {
		try {
			String target = "YWJjZGU=";
			log.info("testDecodeBase64 =====> " + Base64Util.decodeFromString(target));
			
			// 极端值 - 测试
			/* null 值将导致java.lang.NullPointerException
			at sun.misc.CharacterDecoder.decodeBuffer(CharacterDecoder.java:187) */
			target = null;
			log.info("testDecodeBase64 =====> " + Base64Util.decodeFromString(target));
			
			target = "";
			log.info("testDecodeBase64 =====> " + Base64Util.decodeFromString(target));
		} catch (Exception e) {
			log.error("testDecodeBase64 =====> ", e);
		}
	}
	
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
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			String str = "a";
			System.out.println(str.codePointAt(0));
			System.out.println(Byte.parseByte("1010", 2));
			byte[] data = "czEz".getBytes();
			System.out.println(data);
			
			
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
