/**
 * DESUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.hua.constant.Constant;

/**
 * DESUtil
 * 描述: DES加解密
 * @author  qye.zheng
 */
public final class DESUtil
{

    private final static String ALGORITHM = "DES";
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private DESUtil()
	{
	}
	
    /**
     * Description 根据键值进行加密
     * @param data 
     * @param secret  密钥
     * @return
     * @throws Exception
     */
    public static final String encryptToString(final String data, final String secret) throws Exception 
    {
    	return new String(encrypt(data, secret));
    }
	
    /**
     * Description 根据键值进行加密
     * @param data 
     * @param secret  密钥
     * @return
     * @throws Exception
     */
    public static final byte[] encrypt(final String data, final String secret) throws Exception 
    {
    	return encrypt(data.getBytes(Constant.CHART_SET_UTF_8), secret.getBytes(Constant.CHART_SET_UTF_8));
    }
	
	/**
     * Description 根据键值进行加密
     * @param data
     * @param secret  密钥
     * @return
     * @throws Exception
     */
    public static final byte[] encrypt(final byte[] data, final byte[] secret) throws Exception {
        // 生成一个可信任的随机数源
        final SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        final DESKeySpec dks = new DESKeySpec(secret);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        final  SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        final SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成加密操作
        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
    
    /**
     * 
     * @description 解密为字符串
     * @param data
     * @param secret 密钥
     * @return
     * @throws Exception
     * @author qianye.zheng
     */
    public static final String decryptToString(final String data, final String secret) throws Exception 
    {
    	return new String(decrypt(data, secret));
    }
    
    /**
     * 
     * @description 解密为字节数组
     * @param data
     * @param secret 密钥
     * @return
     * @throws Exception
     * @author qianye.zheng
     */
    public static final byte[] decrypt(final String data, final String secret) throws Exception 
    {
    	return decrypt(data.getBytes(Constant.CHART_SET_UTF_8), secret.getBytes(Constant.CHART_SET_UTF_8));
    }
    
    /**
     * Description 根据键值进行解密
     * @param data
     * @param secret  密钥
     * @return
     * @throws Exception
     */
    public static final byte[] decrypt(final byte[] data, final byte[] secret) throws Exception {
        // 生成一个可信任的随机数源
    	final SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
    	final DESKeySpec dks = new DESKeySpec(secret);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
    	final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
    	final SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成解密操作
    	final Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }

}
