/**
 * UrlCoderTest.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.test.des;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.junit.Test;

import sun.misc.BASE64Encoder;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;

/**
 * UrlCoderTest
 * 描述: 
 * @author qye.zheng
 * 
 */
public final class DesTest extends BaseTest
{
	

	/**
	 * DES 加密
	 * DES是一种对称加密算法, 加密和解密使用相同的密钥.
	 * 因为DES使用56位密钥, 很容易被破解.
	 * 但是在一些简单的应用中还是可以使用DES加密.
	 * 
	 * 注意: DES加密中, 密钥长度必须是8的倍数.
	 */
	
	/* 密钥长度必须是8的倍数 */
	public static final String secret = "AMeIs65S";
	
    private final static String DES = "DES";
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDes() {
		try {
			
			
		} catch (Exception e) {
			log.error("testDes =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDesEncrypt1() {
		try {
			// [-86, -126, -62, 0, 79, 50, 46, 104]
			String target = "abcdeaa";
			//[-124, 88, -62, 114, -115, -76, 73, 64]
			//[-38, -82, 16, 88, -15, 99, -118, -113]
			// [-80, 84, -114, -18, 7, -106, -127, -104]
			byte[] result = encrypt(target.getBytes(Constant.CHART_SET_UTF_8), secret);
			
			System.out.println(Arrays.toString(result));
		} catch (Exception e) {
			log.error("testDesEncrypt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDesEncrypt2() {
		try {
			String target = "abcdeaa";
			//[-124, 88, -62, 114, -115, -76, 73, 64]
			//[-38, -82, 16, 88, -15, 99, -118, -113]
			// [-80, 84, -114, -18, 7, -106, -127, -104]
			byte[] result = encrypt__(target.getBytes(Constant.CHART_SET_UTF_8), secret.getBytes(Constant.CHART_SET_UTF_8));
			
			System.out.println(Arrays.toString(result));
		} catch (Exception e) {
			log.error("testDesEncrypt =====> ", e);
		}
	}	

	/**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt__(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
 	
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDesDecrypt() {
		try {
			byte[] target = {-38, -82, 16, 88, -15, 99, -118, -113};
			
			byte[] result =  decrypt(target, secret);
			
			String resultString = new String(result, Constant.CHART_SET_UTF_8);
			log.info("testDesDecrypt =====> resultString = " + resultString);
			
		} catch (Exception e) {
			log.error("testDesDecrypt =====> ", e);
		}
	}
	
	/**
	 * 
	 * @description DES 加密
	 * @param dataSource 数据源
	 * @param secret 密钥, 长度是8的倍数
	 * @return
	 * @author qianye.zheng
	 */
	public final byte[] encrypt(final byte[] dataSource, final String secret)
	{
		byte[] result = null;
		try
		{
			// 安全随机器
			final SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(secret.getBytes(Constant.CHART_SET_UTF_8));
			// 创建一个密钥工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Constant.EN_DES);
			SecretKey secretKey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance(Constant.EN_DES);
			// 用密钥初始化 Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
			// 执行加密操作
			result = cipher.doFinal(dataSource);
		} catch (Exception e)
		{
			log.error("encrypt =====> ", e);
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
	public byte[] decrypt(final byte[] dataSource, final String secret)
	{
		byte[] result = null;
		try
		{
			// 安全随机器
			final SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(secret.getBytes(Constant.CHART_SET_UTF_8));
			// 创建一个密钥工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Constant.EN_DES);
			SecretKey secretKey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance(Constant.EN_DES);
			// 用密钥初始化 Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
			// 执行解密操作
			result = cipher.doFinal(dataSource);
		} catch (Exception e)
		{
			log.error("decrypt =====> ", e);
		}
		
		return result;
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

}
