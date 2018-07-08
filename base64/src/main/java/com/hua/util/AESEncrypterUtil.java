/**
 * AESEncrypterUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;

import com.hua.constant.Constant;

/**
 * AESEncrypterUtil
 * 描述: AES 编码器
 * @author  qye.zheng
 */
public final class AESEncrypterUtil
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private AESEncrypterUtil()
	{
	}

	public static final byte[] encrypt(final String content, final String password)
	{
		try {
			final KeyGenerator keyGenerator = KeyGenerator.getInstance(Constant.EN_AES);
			final SecureRandom sRandom = SecureRandom.getInstance(Constant.EN_SHA1PRNG);
			//sRandom.setSeed(seed);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
