/**
 * 描述: 
 * PbeTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.pbe;

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

import java.security.Key;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * PbeTest
 */
public final class PbeTest extends BaseTest {

	/**
	 * 支持一下任意一种算法
	 * <pre>
	 * PBEWithMD5AndDES  
	 * PBEWithMD5AndTripleDES
	 * PBEWithSHA1AndDESede
	 * PBEWithSHA1AndRC2_40
	 * </pre>
	 */
	public static final String ALGORITHM = "PBEWithMD5AndDES";

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPbe() {
		try {
			
			
		} catch (Exception e) {
			log.error("testPbe =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPbeEncrypt() {
		try {
			String value = "abc风景";
			String password = "123456";
			//byte[] salt = initSalt();
			byte[] salt = {-38, 54, -38, -22, 56, 64, -127, -79};
			byte[] data = encrypt(value.getBytes(Constant.CHART_SET_UTF_8), password, salt);
			//System.out.println(new String(data, Constant.CHART_SET_UTF_8));
			System.out.println(Arrays.toString(salt));
			System.out.println(Arrays.toString(data));
			
		} catch (Exception e) {
			log.error("testPbeEncrypt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPbeDecrypt() {
		try {
			byte[] salt = {-38, 54, -38, -22, 56, 64, -127, -79};
			String password = "123456";
			byte[] data = {-45, 15, 120, 35, 19, 5, 22, 55, -89, 35, 3, -45, 74, 124, 84, -50};
			byte[] result = decrypt(data, password, salt);
			System.out.println(new String(result, Constant.CHART_SET_UTF_8));
		} catch (Exception e) {
			log.error("testPbeDecrypt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	public byte[] encrypt(byte[] data, String password, byte[] salt) {
		byte[] result = null;
		try {
			Key key = toKey(password);
			PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			result = cipher.doFinal(data);
		} catch (Exception e) {
			log.error("testEncrypt =====> ", e);
		}
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	public byte[] decrypt(byte[] data, String password, byte[] salt) {
		byte[] result = null;
		try {
			Key key = toKey(password);
			PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			result = cipher.doFinal(data);
		} catch (Exception e) {
			log.error("testDecrypt =====> ", e);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @description 转换key
	 * @param password
	 * @return
	 * @author qianye.zheng
	 */
	private static Key toKey(String password) throws Exception
	{
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		
		return secretKey;
	}
	
	/**
	 * 
	 * @description 盐初始化
	 * @return
	 * @author qianye.zheng
	 */
	public byte[] initSalt()
	{
		byte[] salt = new byte[8];
		Random random = new Random();
		random.nextBytes(salt);
		
		return salt;
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
