/**
 * ShaTest.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.test.sha;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.codec.digest.Sha2Crypt;
import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;

/**
 * ShaTest
 * 描述: 
 * @author qye.zheng
 * 
 */
public final class ShaTest extends BaseTest
{

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJdkSha() {
		try {
			//value = "中";
			log.info("testJdkSha =====> value = " + value);
			final MessageDigest sha1 = MessageDigest.getInstance(Constant.EN_DECRY_SHA_1);
			// 16位MD5
			log.info("testJdkSha =====> " + sha1.getDigestLength());
			log.info("testJdkSha =====> " + sha1.getAlgorithm());
			data = value.getBytes(Constant.CHART_SET_UTF_8);
			// 使用指定的字节更新摘要
			sha1.update(data);
			// 获得密文 字节
			resultData = sha1.digest();
			// 把密文转换成十六进制 字符数组形式
			final int length = resultData.length;
			// 1个字节 8 位2进制数组成；由 2 位 16进制数组成
			final char[] ch = new char[2 * length];
			byte singleByte;
			final int shift = 4;
			for (int i = 0, k = 0; i < length; i++) {
				// 密文的单个字节
				singleByte = resultData[i];
				// 高 4 位 求值
				ch[k++] = Constant.HEXDIG_UPPER_CASE[ singleByte >>> shift & Constant.OXF ];
				// 低 4 位 求值
				ch[k++] = Constant.HEXDIG_UPPER_CASE[ singleByte & Constant.OXF ];
			}
			// 将字符数组 转成字符串
			encodeResult = new String(ch);
			log.info("testJdkSha =====> encodeResult = " + encodeResult);
			
			
			
		} catch (Exception e) {
			log.error("testJdkSha =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testApacheSha1() {
		try {
			log.info("testApacheSha1 =====> value = " + value);
			data = value.getBytes(Constant.CHART_SET_UTF_8);
			encodeResult = Sha2Crypt.sha512Crypt(data);
			//encodeResult = Md5Crypt.apr1Crypt(data);
			log.info("testApacheSha1 =====> encodeResult = " + encodeResult);
			
		} catch (Exception e) {
			log.error("testApacheMd5 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testApacheDigest() {
		try {
			log.info("testApacheDigest =====> value = " + value);
			data = value.getBytes(Constant.CHART_SET_UTF_8);
			encodeResult = DigestUtils.sha1Hex(data);
			log.info("testApacheDigest =====> encodeResult = " + encodeResult);
			
		} catch (Exception e) {
			log.error("testApacheDigest =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSha1WithSalt() {
		try {
			/*
			 * $6$ + 8位随机字符
			 * 
			 */
			String B64T = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			String salt = "$6$abcejgk..";
			log.info("testSha1WithSalt =====> value = " + value);
			data = value.getBytes(Constant.CHART_SET_UTF_8);
			encodeResult = Sha2Crypt.sha512Crypt(data, salt);
			log.info("testSha1WithSalt =====> encodeResult = " + encodeResult);
			
		} catch (Exception e) {
			log.error("testSha1WithSalt =====> ", e);
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
