/**
 * CodecUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hua.constant.Constant;

/**
 * CodecUtil
 * 描述: 
 * @author  qye.zheng
 */
public final class CodecUtil
{
	protected final Logger log = LogManager.getLogger(CodecUtil.class.getClass());

	/* [0, 15] */
	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	static final String B64T = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private CodecUtil()
	{
	}

	/**
	 * 
	 * @description AES 加密
	 * @param dataSource 数据源
	 * @param secret 密钥, 长度是8的倍数
	 * @return
	 * @author qianye.zheng
	 */
	public final byte[] encryptAes(final byte[] dataSource, final String secret)
	{
		byte[] result = null;
		try
		{
			KeyGenerator keyGenerator = KeyGenerator.getInstance(Constant.EN_AES);
			// 安全随机器
			final SecureRandom random = new SecureRandom(secret.getBytes(Constant.CHART_SET_UTF_8));
			keyGenerator.init(128, random);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, Constant.EN_AES);
			Cipher cipher = Cipher.getInstance(Constant.EN_AES);
			// 用密钥初始化 Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, random);
			// 执行加密操作
			result = cipher.doFinal(dataSource);
		} catch (Exception e)
		{
			log.error("encryptAes =====> ", e);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @description 
	 * @param source
	 * @param secret
	 * @return
	 * @author qianye.zheng
	 */
	public byte[] decryptAes(final byte[] dataSource, final String secret)
	{
		byte[] result = null;
		try
		{
			KeyGenerator keyGenerator = KeyGenerator.getInstance(Constant.EN_AES);
			// 安全随机器
			final SecureRandom random = new SecureRandom(secret.getBytes(Constant.CHART_SET_UTF_8));
			keyGenerator.init(128, random);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, Constant.EN_AES);
			Cipher cipher = Cipher.getInstance(Constant.EN_AES);
			// 用密钥初始化 Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, random);
			// 执行加密操作
			result = cipher.doFinal(dataSource);
		} catch (Exception e)
		{
			log.error("decryptAes =====> ", e);
		}
		
		return result;
	}	
	
	/**
	 * @description 字节数组 转化为 16进制字符串
	 * @param array
	 * @return
	 * @author qye.zheng
	 */
	public static final String bytesToHexString(final byte[] array) {
		final StringBuilder builder = StringUtil.getBuilder();

		for (int i = 0; i < array.length; i++) {
			builder.append(byteToHexString(array[i]));
		}

		return builder.toString();
	}

	/**
	 * 
	 * @description 字节转成 16进制字符串形式
	 * @param b
	 * @return
	 * @author qye.zheng
	 */
	public static final String byteToHexString(final byte b) {
		int n = b;
		// 字节8位 转成 int类型
		// int n = b & 0XFF;
		if (n < 0) {
			n = 256 + n;
		}
		// 高位
		final int d1 = n / 16;
		// 低位
		final int d2 = n % 16;

		return hexDigits[d1] + hexDigits[d2];
	}
	
	/**
	 * 
	 * @description 16进制字符串转成 字节数组
	 * @param b
	 * @return
	 * @author qye.zheng
	 */
	public static final byte[] hexStringToBytes(final String hexString) {
		int m = 0;
		int n = 0;  
	    final int len = hexString.length() / 2;  
	    byte[] ret = new byte[len];  
	    for (int i = 0; i < len; i++) {  
	        m = i * 2 + 1;  
	        n = m + 1;  
	        ret[i] = uniteBytes(hexString.substring(i * 2, m), hexString.substring(m, n));  
	    }  
	    
	    return ret;  
	}
	
	/**
	 * 
	 * @description 合并字节
	 * @param src0
	 * @param src1
	 * @return
	 * @author qianye.zheng
	 */
	private static byte uniteBytes(final String src0, final String src1) {  
	    byte b0 = Byte.decode("0x" + src0).byteValue();  
	    // 左移 4位, 作为高位
	    b0 = (byte) (b0 << 4);  
	    byte b1 = Byte.decode("0x" + src1).byteValue();  
	    byte ret = (byte) (b0 | b1);  
	    
	    return ret;  
	}  
	
	 /**
     * Generates a string of random chars from the B64T set.
     *
     * @param num
     *            Number of chars to generate.
     */
    public static String getRandomCode(final int num) {
        final StringBuilder saltString = new StringBuilder();
        for (int i = 1; i <= num; i++) {
            saltString.append(B64T.charAt(new Random().nextInt(B64T.length())));
        }
        return saltString.toString();
    }
}
