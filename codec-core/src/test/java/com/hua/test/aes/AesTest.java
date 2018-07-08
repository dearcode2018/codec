/**
 * UrlCoderTest.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.test.aes;

import java.io.File;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;
import com.hua.util.Base64Util;
import com.hua.util.CodecUtil;
import com.hua.util.FileUtil;
import com.hua.util.MD5Util;
import com.hua.util.ProjectUtil;

/**
 * UrlCoderTest
 * 描述: 
 * @author qye.zheng
 * 
 */
public final class AesTest extends BaseTest
{

	/* 密钥长度必须是8的倍数 */
	public static final String secret = "1qgijkRFTG3dfea4m.5ZsqFpqElKhOMT";
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAes() {
		try {

			
		} catch (Exception e) {
			log.error("testAes =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDecryptTemp() {
		try {
			String str = FileUtil.getString(new File(ProjectUtil.getAbsolutePath("/doc/a.txt", true)));
			byte[] target = Base64Util.decodeBase64(str).getBytes();
			//byte[] result =  decrypt(target, "961ff7b4beeeed9e");
			byte[] result = Decrypt(target, "961ff7b4beeeed9e");
			FileUtil.writeByteArray(new File("D:/b.ts"), result);
			
		} catch (Exception e) {
			log.error("testDecryptTemp =====> ", e);
		}
	}

    // 解密
    public static byte[] Decrypt(byte[] sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
         
         
            try {
               // byte[] original = cipher.doFinal(parseHexStr2Byte(sSrc));
                byte[] original = cipher.doFinal(sSrc);
                String originalString = new String(original,"utf-8");
                return original;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    /**将16进制转换为二进制 
     * @param hexStr 
     * @return 
     */  
    public static byte[] parseHexStr2Byte(String hexStr) {  
            if (hexStr.length() < 1)  
                    return null;  
            byte[] result = new byte[hexStr.length()/2];  
            for (int i = 0;i< hexStr.length()/2; i++) {  
                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
                    result[i] = (byte) (high * 16 + low);  
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
	public void testAesEncrypt() {
		try {
			String target = "test00";
			// -15, 123, 44, -35, -31, 95, 125, -3, -52, 91, -107, -78, -104, -26, 116, -56
			byte[] result = encrypt(target.getBytes(Constant.CHART_SET_UTF_8), secret);
			System.out.println("reString = " + new String(result, Constant.CHART_SET_UTF_8));
			System.out.println(Arrays.toString(result));
			String hexString = CodecUtil.bytesToHexString(result);
			log.info("testAesEncrypt =====> hexString = " + hexString);
			//String resultString = new String(result, Constant.CHART_SET_UTF_8);
			//log.info("testAesEncrypt =====> resultString = " + resultString);
		} catch (Exception e) {
			log.error("testAesEncrypt =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAesDecrypt() {
		try {
			byte[] target = {-15, 123, 44, -35, -31, 95, 125, -3, -52, 91, -107, -78, -104, -26, 116, -56};
			target = CodecUtil.hexStringToBytes("742295341e947560415122d4312be7b6");
			byte[] result =  decrypt(target, secret);
			
			String resultString = new String(result, Constant.CHART_SET_UTF_8);
			log.info("testDesDecrypt =====> resultString = " + resultString);
			
		} catch (Exception e) {
			log.error("testAesDecrypt =====> ", e);
		}
	}	
	
	/**
	 * 
	 * @description AES 加密
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
	public void testBytesToHexString() {
		try {
			byte[] array = {-89, 93, -88, -71, 9, 70, 57, 3};
			System.out.println(MD5Util.byteArrayToHexString(array).toUpperCase());
		} catch (Exception e) {
			log.error("testBytesToHexString =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHexStringToBytes() {
		try {
			String str = "A75DA8B909463903";
			byte[] array = MD5Util.hexToBytes(str);
			System.out.println(Arrays.toString(array));
		} catch (Exception e) {
			log.error("testHexStringToBytes =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetRandomCode() {
		try {
			String randomCode = CodecUtil.getRandomCode(32);
			log.info("testGetRandomCode =====> randomCode = " + randomCode);
			System.out.println("TQt9Yeq3pkKggTupIsMc4ux5zgxQeCyi".length());
		} catch (Exception e) {
			log.error("testGetRandomCode =====> ", e);
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
