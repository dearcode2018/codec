/**
 * Base64Test.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.test.base64;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;


import com.hua.constant.Constant;
import com.hua.test.BaseTest;

/**
 * Base64Test
 * 描述: 
 * @author qye.zheng
 * 
 */
public final class Base64Test extends BaseTest
{
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testApacheBase64() {
		try {
			log.info("testApacheBase64 =====> value = " + value);
			// org.apache.commons.codec.binary.Base64
			Base64 base64 = new Base64();
			encodeResult = base64.encodeAsString(value.getBytes(Constant.CHART_SET_UTF_8));
			log.info("testApacheBase64 =====> encodeResult = " + encodeResult);
			data = base64.decode(encodeResult);
			decodeResult = new String(data, Constant.CHART_SET_UTF_8);
			
		} catch (Exception e) {
			log.error("testApacheBase64 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 自定义base64加盐 呵呵
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase64WithSalt() {
		try {
			log.info("testBase64WithSalt =====> value = " + value);
			// org.apache.commons.codec.binary.Base64
			Base64 base64 = new Base64();
			String saltAfterBase64 = base64.encodeAsString(salt.getBytes(Constant.CHART_SET_UTF_8));
			log.info("testBase64WithSalt =====> saltAfterBase64 = " + saltAfterBase64);
			encodeResult = base64.encodeAsString(value.getBytes(Constant.CHART_SET_UTF_8));
			// 分开 base64 之后 再次 base64
			encodeResult = base64.encodeAsString((encodeResult + saltAfterBase64).getBytes(Constant.CHART_SET_UTF_8));
			log.info("testBase64WithSalt =====> encodeResult = " + encodeResult);
			data = base64.decode(encodeResult);
			decodeResult = new String(data, Constant.CHART_SET_UTF_8);
			
		} catch (Exception e) {
			log.error("testBase64WithSalt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 自定义base64加盐 呵呵
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase64EncodeWithSalt() {
		try {
			/*
			 * 1) 单独 base64
			 * 2) 合在一起base64
			 */
			log.info("testBase64EncodeWithSalt =====> value = " + value);
			// org.apache.commons.codec.binary.Base64
			Base64 base64 = new Base64();
			// 对盐进行编码
			String saltAfterBase64 = base64.encodeAsString(salt.getBytes(Constant.CHART_SET_UTF_8));
			log.info("testBase64EncodeWithSalt =====> saltAfterBase64 = " + saltAfterBase64);
			encodeResult = base64.encodeAsString(value.getBytes(Constant.CHART_SET_UTF_8));
			// 分开 base64 之后 再次 base64
			encodeResult = base64.encodeAsString((encodeResult + saltAfterBase64).getBytes(Constant.CHART_SET_UTF_8));
			log.info("testBase64EncodeWithSalt =====> encodeResult = " + encodeResult);			
		} catch (Exception e) {
			log.error("testBase64EncodeWithSalt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 自定义base64加盐 呵呵
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase64DecodeWithSalt() {
		try {
			encodeResult = "WVdabkkyUWtOU1V6";
			Base64 base64 = new Base64();
			data = base64.decode(encodeResult);
			decodeResult = new String(data, Constant.CHART_SET_UTF_8);
			log.info("testBase64DecodeWithSalt =====> decodeResult0 = " + decodeResult);
			// 
			String saltAfterBase64 = base64.encodeAsString(salt.getBytes(Constant.CHART_SET_UTF_8));
			log.info("testBase64DecodeWithSalt =====> saltAfterBase64 = " + saltAfterBase64);

			// 取出 原始编码串
			decodeResult = decodeResult.substring(0, decodeResult.lastIndexOf(saltAfterBase64));
			log.info("testBase64DecodeWithSalt =====> decodeResult1 = " + decodeResult);
			// 分开后解码
			data = base64.decode(decodeResult);
			decodeResult = new String(data, Constant.CHART_SET_UTF_8);
			log.info("testBase64DecodeWithSalt =====> decodeResult2 = " + decodeResult);

		} catch (Exception e) {
			log.error("testBase64DecodeWithSalt =====> ", e);
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
