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

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.annotations.Since;
import com.hua.constant.Constant;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * RSATest
 */
public final class RSATest extends BaseTest {

	public static final String EN_RSA = "RSA";
	
	/* 公钥 */
	public static final String PUBLIC_KEY = "RSAPublicKey";
	
	/* 私钥 */
	public static final String PRIVATE_KEY = "RSAPrivateKey";
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRSA() {
		try {
			
			
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
	public void testRSAEncrypt() {
		try {
			
			
		} catch (Exception e) {
			log.error("testRSAEncrypt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 公钥加密 - 私钥解密
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRSAPublicEncryptAndPrivateDecrypt() {
		try {
			String value = "abc123";
			Map<String, Key> keyMap = null;
			byte[] key = null;
			byte[] result = null;
			keyMap = initKey();
			key = keyMap.get(PUBLIC_KEY).getEncoded();
			result = encryptByPublicKey(value.getBytes(Constant.CHART_SET_UTF_8), key);
			
			System.out.println(Arrays.toString(result));
			
			key = keyMap.get(PRIVATE_KEY).getEncoded();
			result = decryptByPrivateKey(result, key);
			String v = new String(result, Constant.CHART_SET_UTF_8);
			log.info("testRSAPublicEncryptAndPrivateDecrypt =====> v = " + v);			
			
		} catch (Exception e) {
			log.error("testRSAPublicEncryptAndPrivateDecrypt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 私钥加密 - 公钥解密
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRSAPrivateEncryptAndPublicDecrypt() {
		try {
			String value = "abc123";
			Map<String, Key> keyMap = null;
			byte[] key = null;
			byte[] result = null;
			// 一次的加密和解密, 密钥只能初始化一次
			keyMap = initKey();
			key = keyMap.get(PRIVATE_KEY).getEncoded();
			result = encryptByPrivateKey(value.getBytes(Constant.CHART_SET_UTF_8), key);
			
			System.out.println(Arrays.toString(result));
			
			key = keyMap.get(PUBLIC_KEY).getEncoded();
			result = decryptByPublicKey(result, key);
			String v = new String(result, Constant.CHART_SET_UTF_8);
			log.info("testRSAPrivateEncryptAndPublicDecrypt =====> v = " + v);			
			
		} catch (Exception e) {
			log.error("testRSAPrivateEncryptAndPublicDecrypt =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 私钥封签 - 公钥验证签名
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPrivateKeySignAndPublicKeyVerify() {
		try {
			String value = "abc123";
			Map<String, Key> keyMap = null;
			byte[] key = null;
			byte[] result = null;
			boolean flag = false;
			// 一次的加密和解密, 密钥只能初始化一次
			keyMap = initKey();
			// 私钥
			key = keyMap.get(PRIVATE_KEY).getEncoded();
			// 封签
			result = sign(value.getBytes(Constant.CHART_SET_UTF_8), key);
			
			// 公钥
			key = keyMap.get(PUBLIC_KEY).getEncoded();
			
			// 验证签名
			 flag = verify(value.getBytes(Constant.CHART_SET_UTF_8), key, result);
			log.info("testPrivateKeySignAndPublicKeyVerify =====> flag = " + flag);
			
			// 模拟篡改
			value = "abc1234";
			// 验证签名
			flag = verify(value.getBytes(Constant.CHART_SET_UTF_8), key, result);
			log.info("testPrivateKeySignAndPublicKeyVerify =====> flag = " + flag);
			
		} catch (Exception e) {
			log.error("encrypt =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRSADecrypt() {
		try {
			
			
		} catch (Exception e) {
			log.error("testRSADecrypt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	public byte[] encrypt() {
		try {
			
		} catch (Exception e) {
			log.error("encrypt =====> ", e);
		}
		return null;
	}

	/**
	 * 
	 * @description 初始化密钥
	 * @return
	 * @author qianye.zheng
	 */
	public static Map<String, Key> initKey() throws Exception
	{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EN_RSA);
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); 
		
		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Key> keyMap = new HashMap<String, Key>();
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);

		return keyMap;
	}
	
	/**
	 * 
	 * 描述: 公钥加密
	 * @author qye.zheng
	 * 
	 */
	public byte[] encryptByPublicKey(final byte[] data, final byte[] key) {
		byte[] result = null;
		try {
			// 取得公钥
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(EN_RSA);
			Key publicKey = keyFactory.generatePublic(x509KeySpec);
			
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			// 公钥加密
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			result = cipher.doFinal(data);
		} catch (Exception e) {
			log.error("encryptByPublicKey =====> ", e);
		}
		
		return result;
	}	
	
	/**
	 * 
	 * 描述: 私钥解密
	 * @author qye.zheng
	 * 
	 */
	public byte[] decryptByPrivateKey(final byte[] data, final byte[] key) {
		byte[] result = null;
		try {
			// 取得私钥
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(EN_RSA);
			// 私钥
			Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			
			// 解密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			// 私钥解密
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			result = cipher.doFinal(data);
		} catch (Exception e) {
			log.error("encryptByPublicKey =====> ", e);
		}
		
		return result;
	}		
	
	/**
	 * 
	 * 描述: 私钥加密
	 * @author qye.zheng
	 * 
	 */
	public byte[] encryptByPrivateKey(final byte[] data, final byte[] key) {
		byte[] result = null;
		try {
			// 取得私钥
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(EN_RSA);
			// 私钥
			Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			
			// 加密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			// 私钥加密
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			result = cipher.doFinal(data);
		} catch (Exception e) {
			log.error("encryptByPrivateKey =====> ", e);
		}
		return result;
	}	
	
	/**
	 * 
	 * 描述: 公钥解密
	 * @author qye.zheng
	 * 
	 */
	public byte[] decryptByPublicKey(final byte[] data, final byte[] key) {
		byte[] result = null;
		try {
			// 取得公钥
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(EN_RSA);
			Key publicKey = keyFactory.generatePublic(x509KeySpec);
			
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			// 公钥解密
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			result = cipher.doFinal(data);			
		} catch (Exception e) {
			log.error("decryptByPublicKey =====> ", e);
		}
		return result;
	}		

	/**
	 * 
	 * @description 私钥封签
	 * @param data 数据
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static byte[] sign(final byte[] data, final byte[] privateKey) throws Exception
	{
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance(EN_RSA);
		
		// 取得私钥
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance("MD5WithRSA");
		signature.initSign(priKey);
		signature.update(data);
		// 生成签名字节
		byte[] bts = signature.sign();
		//System.out.println(new String(bts, Constant.CHART_SET_UTF_8));
		
		return bts;
	}
	
	/**
	 * 
	 * @description 公钥验证签名是否正确
	 * @param data 数据
	 * @param publicKey 公钥
	 * @param sign 签名值
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static boolean verify(final byte[] data, final byte[] publicKey, final byte[] sign) throws Exception
	{
		boolean flag = false;
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance(EN_RSA);
		
		// 取得公钥对象
		PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);
		// 用公钥校验数字签名
		Signature signature = Signature.getInstance("MD5WithRSA");
		signature.initVerify(pubKey);
		signature.update(data);
		// 验证签名是否正确
		flag = signature.verify(sign);
		
		return flag;
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	public byte[] decrypt() {
		try {
			
		} catch (Exception e) {
			log.error("decrypt =====> ", e);
		}
		return null;
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
