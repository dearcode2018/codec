/**
 * RSAUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * RSAUtil
 * 描述: 非对称加解密
 * @author  qye.zheng
 */
public final class RSAUtil
{
	
	/**
	 * 公钥、私钥
	 * 
	 * 1.生成密钥对: 公钥、私钥
	 * 
	 * 2.下发公钥
	 * 
	 * 3.公钥加密，私钥解密
	 * 
	 * 4.私钥加密，公钥解密
	 * 
	 */

	private static final String ALGORITHM = "RSA";
	
	/* 密钥长度，64的倍数，范围 [512, 65536] */
	private static final int KEY_SIZE = 512;
	
	/* 公钥 */
	private static final String PUBLIC_KEY = "RSAPublicKey";
	
	/* 私钥 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	
	private static final Map<String, Key> keyMap = new HashMap<String, Key>();
	
	static
	{
		try
		{
			initKey();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private RSAUtil()
	{
	}
	
	/**
	 * 
	 * @description 公钥加密
	 * @param data
	 * @param key 密钥 - 公钥
	 * @return
	 * @author qianye.zheng
	 */
	public static final byte[] encryptPublicKey(final byte[] data, final byte[] key) throws Exception
	{
		final KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		// 初始化公钥
		final X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		// 公钥
		final PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		// 数据加密
		final Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		// 编码模式
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @description 私钥加密
	 * @param data
	 * @param key 密钥 - 私钥
	 * @return
	 * @author qianye.zheng
	 */
	public static final byte[] encryptPrivateKey(final byte[] data, final byte[] key) throws Exception
	{
		// 获取私钥
		final PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		final KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		// 私钥
		final PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 数据加密
		final Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		// 编码模式
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @description 公钥解密
	 * @param data
	 * @param key 密钥 - 公钥
	 * @return
	 * @author qianye.zheng
	 */
	public static final byte[] decryptPublicKey(final byte[] data, final byte[] key) throws Exception
	{
		final KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		// 初始化公钥
		final X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		// 公钥
		final PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		// 数据解密
		final Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		// 解码模式
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @description 私钥解密
	 * @param data
	 * @param key 密钥 - 私钥
	 * @return
	 * @author qianye.zheng
	 */
	public static final byte[] decryptPrivateKey(final byte[] data, final byte[] key) throws Exception
	{
		// 获取私钥
		final PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		final KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		// 私钥
		final PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 数据解密
		final Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		// 解码模式
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	private static final void initKey() throws Exception
	{
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		// 初始化密钥生成器
		keyPairGenerator.initialize(KEY_SIZE);
		// 生成密钥对
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 公钥
		final Key publicKey = keyPair.getPublic();
		// 私钥
		final Key privateKey = keyPair.getPrivate();
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
	}

	/**
	 * 
	 * @description 获取公钥
	 * @param keyMap
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static final byte[] getPublicKey() throws Exception
	{
		final Key key = keyMap.get(PUBLIC_KEY);
		
		return key.getEncoded();
	}
	
	/**
	 * 
	 * @description 获取公钥-字符串
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static final String getPublicKeyBase64String() throws Exception
	{
		return Base64Util.encodeToString(getPublicKey());
	}
	
	/**
	 * 
	 * @description 获取私钥
	 * @param keyMap
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static final byte[] getPrivateKey() throws Exception
	{
		final Key key = keyMap.get(PRIVATE_KEY);
		
		return key.getEncoded();
	}
	
	/**
	 * 
	 * @description 获取私钥-字符串
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static final String getPrivateKeyBase64String() throws Exception
	{
		return Base64Util.encodeToString(getPrivateKey());
	}
	
}
