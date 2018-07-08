/**
 * 描述: 
 * HmacTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.hmac;

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

import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * HmacTest
 */
public final class HmacTest extends BaseTest {

	/*
	 * HMAC (Hash Message Authentication Code 散列消息鉴别码)
	 * 原理: 用公开函数和密钥产生一个固定长度的值作为认证标识, 
	 * 用这个标识鉴别消息的完整性. 使用一个密钥生成一个固定大小
	 * 的数据块, 即MAC, 并将其加入消息中, 然后传输. 接收方利用与
	 * 发送方共享的密钥进行鉴别认证.
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHmac() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(Constant.EN_HMAC);
			SecretKey secretKey = keyGenerator.generateKey();
			Base64 base64 = new Base64();
			String hmacSecret = base64.encodeAsString(secretKey.getEncoded());
			log.info("testHmac =====> hmacSecret = " + hmacSecret);
			
			
		} catch (Exception e) {
			log.error("testHmac =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHmacEncrypt() {
		try {
			String str = "A75DA8B909463903";
			String secret = "123";
			SecretKey secretKey = new SecretKeySpec(
					secret.getBytes(Constant.CHART_SET_UTF_8), Constant.EN_HMAC);
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			// 初始化 密钥
			mac.init(secretKey);
			// [22, -11, 32, 73, 27, 82, 45, 114, -56, -65, 100, -20, -17, 81, 48, 82]
			// 执行加密
			byte[] array = mac.doFinal(str.getBytes(Constant.CHART_SET_UTF_8));
			System.out.println(Arrays.toString(array));
			//System.out.println(new String(array));
			
		} catch (Exception e) {
			log.error("testHmacEncrypt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInitHmacKey() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(Constant.EN_HMAC);
			SecretKey secretKey = keyGenerator.generateKey();
			Base64 base64 = new Base64();
			String hmacSecret = base64.encodeAsString(secretKey.getEncoded());
			log.info("testHmac =====> hmacSecret = " + hmacSecret);
			
			
		} catch (Exception e) {
			log.error("testInitHmacKey =====> ", e);
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
