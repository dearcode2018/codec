/**
 * 描述: 
 * DHTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.dh;

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
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.CodecUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * DHTest
 */
public final class DHTest extends BaseTest {

	/*
	 * 
	 * 流程分析：
	 * 1.甲方构建密钥对儿，将公钥公布给乙方，将私钥保留；双方约定数据加密算法；乙方通过甲方公钥构建密钥对儿，
	 * 将公钥公布给甲方，将私钥保留。
	 * 2.甲方使用私钥、乙方公钥、约定数据加密算法构建本地密钥，然后通过本地密钥加密数据，
	 * 发送给乙方加密后的数据；乙方使用私钥、甲方公钥、约定数据加密算法构建本地密钥，然后通过本地密钥对数据解密。
	 * 3.乙方使用私钥、甲方公钥、约定数据加密算法构建本地密钥，然后通过本地密钥加密数据，
	 * 发送给甲方加密后的数据；甲方使用私钥、乙方公钥、约定数据加密算法构建本地密钥，然后通过本地密钥对数据解密。 
	*/
	
	 public static final String ALGORITHM = "DH";
	 
	 /* 默认密钥字节数 */
	 public static final int KEY_SIZE = 1024;  
	
	 /*
	  * DH加密下需要一种对称加密算法对数据加密, 这里选择 DES
	  */
	 
	 public static final String PUBLIC_KEY = "DHPublicKey";  
	 public static final String PRIVATE_KEY = "DHPrivateKey";  
	 
		/**
		 * 
		 * 描述: 
		 * @author qye.zheng
		 * 
		 */
		@Test
		public void testDH() {
			try {
				// 生成甲方密钥对
				Map<String, Object> aKeyMap = initKey();
				String aPublicKey = getPublicKey(aKeyMap);
				String aPrivateKey = getPrivateKey(aKeyMap);
			    System.err.println("甲方公钥:\r" + aPublicKey);  
		        System.err.println("甲方私钥:\r" + aPrivateKey);  
				
		        // 由甲方公钥产生本地密钥对
				Map<String, Object> bKeyMap = initKey(aPublicKey);
				String bPublicKey = getPublicKey(bKeyMap);
				String bPrivateKey = getPrivateKey(bKeyMap);
			    System.err.println("乙方公钥:\r" + bPublicKey);  
		        System.err.println("乙方私钥:\r" + bPrivateKey);  
		        
		        String aInput = "abc";
		        System.err.println("原文: " + aInput);  
		        
		        // 乙方: 由甲方公钥/乙方私钥构建密文
		        byte[] aCode = encrypt(aInput.getBytes(), aPublicKey, bPrivateKey);
	
		        // 甲方: 由乙方公钥/甲方密钥解密
		        byte[] aDecode  = decrypt(aCode, bPublicKey, aPrivateKey);
		        String aOutput = new String(aDecode);  
		        System.err.println("解密: " + aOutput);  
		        
		        assertEquals(aInput, aOutput);  
		        
		        System.err.println(" ===============反过来加密解密================== ");  
		        String bInput = "def ";  
		        System.err.println("原文: " + bInput);  
		  
		        // 	甲方: 由乙方公钥，甲方私钥构建密文  
		        byte[] bCode = encrypt(bInput.getBytes(), bPublicKey,  
		                aPrivateKey);  
		  
		        // 乙方: 由甲方公钥，乙方私钥解密  
		        byte[] bDecode = decrypt(bCode, aPublicKey, bPrivateKey);  
		        String bOutput = new String(bDecode);  
		  
		        System.err.println("解密: " + bOutput);  
		  
		        assertEquals(bInput, bOutput);
		        
			} catch (Exception e) {
				log.error("testDH =====> ", e);
			}
		}
	
	/**
	 * 
	 * @description 初始化甲方密钥
	 * @param key
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static Map<String, Object> initKey() throws Exception
	{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		keyPairGenerator.initialize(KEY_SIZE);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 甲方公钥
		DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
		// 甲方私钥
		DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
	    Map<String, Object> keyMap = new HashMap<String, Object>(2); 
	    keyMap.put(PUBLIC_KEY, publicKey);  
	    keyMap.put(PRIVATE_KEY, privateKey);  
	    
	    return keyMap;
	}
	
	/**
	 * 
	 * @description 初始化乙方密钥
	 * @param key
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static Map<String, Object> initKey(String key) throws Exception
	{
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(CodecUtil.hexStringToBytes(key));
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PublicKey publicKeyOfA = keyFactory.generatePublic(x509EncodedKeySpec);
		
		// 由甲方公钥构建乙方密钥
		DHParameterSpec dhParameterSpec = ((DHPublicKey) publicKeyOfA).getParams();
		
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		keyPairGenerator.initialize(dhParameterSpec);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 乙方公钥
		DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
		// 乙方私钥
		DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
	    Map<String, Object> keyMap = new HashMap<String, Object>(2); 
	    keyMap.put(PUBLIC_KEY, publicKey);  
	    keyMap.put(PRIVATE_KEY, privateKey);  
	    
	    return keyMap;
	}
	
	/**
	 * 
	 * @description 加密
	 * @param data
	 * @param publicKey 公钥
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static byte[] encrypt(byte[] data, String publicKey, String privateKey) throws Exception
	{
		// 生成本地密钥
		SecretKey secretKey = getSecretKey(publicKey, privateKey);
		// 数据加密
		Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @description 解密
	 * @param data
	 * @param publicKey 公钥
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static byte[] decrypt(byte[] data, String publicKey, String privateKey) throws Exception
	{
		// 生成本地密钥
		SecretKey secretKey = getSecretKey(publicKey, privateKey);
		// 数据解密
		Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @description 
	 * @param publicKey
	 * @param privateKey
	 * @return
	 * @author qianye.zheng
	 */
	private static SecretKey getSecretKey(String publicKey, String privateKey) throws Exception
	{
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(CodecUtil.hexStringToBytes(publicKey));
		PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);
		
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(CodecUtil.hexStringToBytes(privateKey));
		Key priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
		keyAgree.init(priKey);
		keyAgree.doPhase(pubKey, true);
		// 生成本地密钥
		SecretKey secretKey = keyAgree.generateSecret("DES");
		
		return secretKey;
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
