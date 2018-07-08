/**
 * Md5Test.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.test.md5;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;

/**
 * Md5Test
 * 描述: 
 * @author qye.zheng
 * 
 */
public final class Md5Test extends BaseTest
{

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJdkMd5() {
		try {
			//value = "中";
			log.info("testJdkMd5 =====> value = " + value);
			final MessageDigest md5 = MessageDigest.getInstance(Constant.EN_DECRY_MD5);
			// 16位MD5
			log.info("testJdkMd5 =====> " + md5.getDigestLength());
			//log.info("testJdkMd5 =====> " + md5.getAlgorithm());
			data = value.getBytes(Constant.CHART_SET_UTF_8);
			// 使用指定的字节更新摘要
			md5.update(data);
			// 获得密文 字节
			resultData = md5.digest();
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
			log.info("testJdkMd5 =====> encodeResult = " + encodeResult);
			
		} catch (Exception e) {
			log.error("testJdkMd5 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testApacheMd5() {
		try {
			/*
			 * apache 中 md5方法每次都自动加盐, 因此同一个值
			 * 每次的md5结果都不同, 盐以  $1$ 为前缀的后面 8个字符
			 */
			log.info("testApacheMd5 =====> value = " + value);
			data = value.getBytes(Constant.CHART_SET_UTF_8);
			// $1$rHVuInbp$W1JGesD0nEnY6dKxYWKc40
			encodeResult = Md5Crypt.md5Crypt(data);
			//encodeResult = Md5Crypt.apr1Crypt(data);
			log.info("testApacheMd5 =====> encodeResult = " + encodeResult);
			
			
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
			//data = value.getBytes(Constant.CHART_SET_UTF_8);
			//encodeResult = DigestUtils.md5Hex(data);
			encodeResult = DigestUtils.md5Hex(value);
			log.info("testApacheDigest =====> encodeResult = " + encodeResult);
			
		} catch (Exception e) {
			log.error("testApacheDigest =====> ", e);
		}
	}
	
	/*
	 * 如果直接对密码散列, 攻击者可以通过获取该散列值, 然后通过
	 * 查散列字典来破解密码.
	 * 加 salt 可以一定程度上解决这个问题.
	 * salt值存储在服务器, 即便是同一个密码, 由于
	 * salt值不同他们的散列值也是不同的, 破解密码的概率非常小.
	 * salt值是随机数存储在服务器, 用户在第一次生成密码时便生成
	 * 该随即值且存储起来, 以后就用该salt值和密码进行md5.
	 * 即使攻击者拿到salt值, 破解过程也会比纯md5复杂很多.
	 * 
	 * 加盐加密过程
	 * 1) 获取明文的hash值
	 * 2) 进行计算获取md5明文hash值
	 * 3) 随机生成加盐值并插入
	 * 4) md5插入加盐值得到hash
	 * 
	 */
	/**
	 * 
	 * 描述: md5编码 加盐 salt
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMd5WithSalt() {
		try {
			log.info("testMd5WithSalt =====> value = " + value);
			data = value.getBytes(Constant.CHART_SET_UTF_8);
			/*
			 * $1$ + 8位随机字符
			 * 
			 */
			String B64T = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			String salt = "$1$abcejgk..";
			// 未加盐: $1$tR.1YdLx$X4WeetWUHmlNsCDpLGTnR1
			// 加盐: $1$a1231111$EZkAmRYtadSTQ/GMsFp8m0
			encodeResult = Md5Crypt.md5Crypt(data, salt);
			//encodeResult = Md5Crypt.apr1Crypt(data);
			log.info("testMd5WithSalt =====> encodeResult = " + encodeResult);
			
			
		} catch (Exception e) {
			log.error("testMd5WithSalt =====> ", e);
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
