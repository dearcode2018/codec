/**
 * 描述: 
 * RSATest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.rsa;

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
import com.hua.util.RSAUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * RSATest
 */
public final class RSATest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRSA() {
		try {
			/*
			 * 公钥、私钥都是VM自动生成的，每次值都不同.
			 */
			/**
				公钥: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIUSk1BNBMXwgQM6vxki4jtQe9ii6scIYCg4UocwTnSYczdzFg1CLGxtppwIX2pQM50CvfASD11HNsD/vJYvaM8CAwEAAQ==
				私钥: MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAhRKTUE0ExfCBAzq/GSLiO1B72KLqxwhgKDhShzBOdJhzN3MWDUIsbG2mnAhfalAznQK98BIPXUc2wP+8li9ozwIDAQABAkB6uq4I4m+jivPBob27lhOmds0x+NelZxEgJwneSuwg1VqS70Z8vh7rg0hqrd4b1/tvyntr5MS42QFdnGFhKpM5AiEA4BohvJmobAh0Qhv7yFgSSRp2TdGHffKTT8hBPEyioHMCIQCYA37Fsgtwft47NaC6a8yFR94WRskDGLCQV659ylPLNQIgRxnjZEw4NHaGNEdenhTbUFhV+qCk5V/mPyThFbmWy2MCIAYNzZSt/IqkYtFU12vVMzXzoMKeWw8GgrGc7FQSJLgpAiBJ2JRMnB/TR3PL1xPz+MBqnAIUp/W2WjHMhCO6Qun/CA==
				解密后: 哈哈哈， hello
				解密后: 哈哈哈， hello
			 */
			byte[] publicKey = RSAUtil.getPublicKey();
			byte[] privateKey = RSAUtil.getPrivateKey();
			
			System.out.println("公钥: " + RSAUtil.getPublicKeyBase64String());
			System.out.println("私钥: " + RSAUtil.getPrivateKeyBase64String());
			
			final String orginal = "哈哈哈， hello RSA";
			/* 公钥加密，私钥解密 */
			byte[] encryptPublic = RSAUtil.encryptPublicKey(orginal.getBytes(), publicKey);
			byte[] decryptPrivate = RSAUtil.decryptPrivateKey(encryptPublic, privateKey);
			System.out.println("[公钥加密，私钥解密] 解密后: " + new String(decryptPrivate));
			
			/* 私钥加密，公钥解密 */
			byte[] encryptPrivate = RSAUtil.encryptPrivateKey(orginal.getBytes(), privateKey);
			byte[] decryptPublic = RSAUtil.decryptPublicKey(encryptPrivate, publicKey);
			System.out.println("[私钥加密，公钥解密] 解密后: " + new String(decryptPublic));
			
		} catch (Exception e) {
			log.error("testRSA =====> ", e);
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
