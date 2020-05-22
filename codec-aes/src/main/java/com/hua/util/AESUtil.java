/**
 * AESUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.hua.constant.Constant;

/**
 * AESUtil
 * 描述: AES 编码器
 * @author  qye.zheng
 */
public final class AESUtil
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private AESUtil()
	{
	}

	/**
	 * 
	 * @description 
	 * @param binaryData
	 * @param password
	 * @return
	 * @author qianye.zheng
	 */
	public static final byte[] encrypt(final byte[] binaryData, final String password)
	{
		byte[] result = null;
		try {
			final SecretKey key = new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8), Constant.EN_AES);
			
			// 密码器
			final Cipher cipher = Cipher.getInstance(Constant.EN_AES);
			//初始化为加密模式
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 加密
			result = cipher.doFinal(binaryData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @description 解密
	 * @param aesBinaryData
	 * @param password
	 * @return
	 * @author qianye.zheng
	 */
	public static final byte[] decrypt(final byte[] aesBinaryData, final String password)
	{

		byte[] result = null;
		try {
			final SecretKey keySpec = new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8), Constant.EN_AES);
			
			/*
			 * NoPadding/PKCS5Padding/ISO10126Padding
			 * 
			 * 模式: ECB/CBC/CFB
			 * 
			 * 
			 */
			// 密码器 参数顺序: 算法/模式/填充
			final Cipher cipher = Cipher.getInstance(Constant.EN_AES);
			//初始化为解密模式
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			
			// 解密
			result = cipher.doFinal(aesBinaryData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
