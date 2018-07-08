/**
 * 描述: 
 * DsaTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.dsa;

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
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.CodecUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * DsaTest
 */
public final class DsaTest extends BaseTest {

	/*
	 * DSA: Digital Signature Algorithm 
	 * 公钥/密钥/数字签名
	 */
	
	public static final String EN_DSA = "DSA";
	
	/* 公钥 */
	public static final String PUBLIC_KEY = "DSAPublicKey";
	
	/* 私钥 */
	public static final String PRIVATE_KEY = "DSAPrivateKey";
	
	/* 默认种子 */
	public static final String DEFAULT_SEED = "0f22507a10bbddd07d8a3082122966e3";
	
	/**
	 * 
	 * @description 
	 * @throws Exception
	 * @author qianye.zheng
	 */
	@Test  
    public void testDsa() throws Exception {  
        String inputStr = "abc";  
        byte[] data = inputStr.getBytes();  
  
        // 构建密钥  
        Map<String, Object> keyMap = initKey();  
  
        // 获得密钥  
        String publicKey = getPublicKey(keyMap);  
        String privateKey = getPrivateKey(keyMap);  
  
        System.err.println("公钥:\r" + publicKey);  
        System.err.println("私钥:\r" + privateKey);  
  
        // 产生签名  
        byte[] signResult = sign(data, CodecUtil.hexStringToBytes(privateKey));  
        System.err.println("签名:\r" + CodecUtil.bytesToHexString(signResult));  
  
        // 验证签名  
        boolean status = verify(data, CodecUtil.hexStringToBytes(publicKey), signResult);  
        System.err.println("状态:\r" + status);  
        assertTrue(status);  
  
    }  	

	/** 
     * 取得私钥 
     *  
     * @param keyMap 
     * @return 
     * @throws Exception 
     */  
    public static String getPrivateKey(Map<String, Object> keyMap)  
            throws Exception {  
        Key key = (Key) keyMap.get(PRIVATE_KEY);  
  
        return CodecUtil.bytesToHexString(key.getEncoded());
    }  
  
    /** 
     * 取得公钥 
     *  
     * @param keyMap 
     * @return 
     * @throws Exception 
     */  
    public static String getPublicKey(Map<String, Object> keyMap)  
            throws Exception {  
        Key key = (Key) keyMap.get(PUBLIC_KEY);  
        
        return CodecUtil.bytesToHexString(key.getEncoded());
    }  	
	
	/**
	 * .
	 * @description 初始化密钥
	 * @return
	 * @author qianye.zheng
	 */
	public static Map<String, Object> initKey(String seed) throws Exception
	{
		SecureRandom secureRandom = new SecureRandom();  
		secureRandom.setSeed(seed.getBytes());  
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EN_DSA);
        keyPairGenerator.initialize(1024, secureRandom);
        
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 公钥
		DSAPublicKey publicKey = (DSAPublicKey) keyPair.getPublic(); 
		
		// 私钥
		DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);

		return keyMap;
	}
	
	/**
	 * 
	 * @description 使用默认种子初始化密钥
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static Map<String, Object> initKey() throws Exception
	{
		return initKey(DEFAULT_SEED);
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
			KeyFactory keyFactory = KeyFactory.getInstance(EN_DSA);
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
			KeyFactory keyFactory = KeyFactory.getInstance(EN_DSA);
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
			KeyFactory keyFactory = KeyFactory.getInstance(EN_DSA);
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
			KeyFactory keyFactory = KeyFactory.getInstance(EN_DSA);
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
		KeyFactory keyFactory = KeyFactory.getInstance(EN_DSA);
		
		// 取得私钥
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
		signature.initSign(priKey);
		signature.update(data);
		// 生成签名字节
		byte[] bts = signature.sign();
		
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
		KeyFactory keyFactory = KeyFactory.getInstance(EN_DSA);
		
		// 取得公钥对象
		PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);
		// 用公钥校验数字签名
		Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
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
