/**
 * UrlCoderTest.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.test.urlcoder;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
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
public final class UrlCoderTest extends BaseTest
{
	
	/**
	 * 
	 * 描述: Url 编/解码器
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUrlCoder() {
		/**
		 * url 编码器，是对Url的参数部分进行编码，而不是对全部url进行编码
		 * 
		 * 客户端 : 只是对Url参数部分进行编码，然后拼接到Url进行传输
		 * 
		 * 服务端 : 拿到具体的值之后，进行Url解码器进行解码
		 * 
		 */
		try {
			final String url = "http://mail.qq.com/cgi-bin/frame_html";
			param = "sid=mf7y9JhYik8aVm&name=张三";
			log.info("testUrlCoder =====> param = " + param);
			// java.net.URLEncoder
			encodeResult = URLEncoder.encode(param, Constant.CHART_SET_UTF_8);
			log.info("testUrlCoder =====> encodeResult --> " + encodeResult);
			// java.net.URLEncoder
			decodeResult = URLDecoder.decode(encodeResult, Constant.CHART_SET_UTF_8);
			log.info("testUrlCoder =====> decodeResult --> " + decodeResult);
			
		} catch (Exception e) {
			log.error("testUrlCoder =====> ", e);
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
			param = "0gCYf6KnA%2BBTDicQo%2BctHDT4YRH%2FsY5lym8qPK3%2FPpRoks%2FotlNMgv2TnzXXEew75tA%2B6gSRn7NLrKRziObU1hR0AldEavx%2BY%2B2tdWfCw2P%2BDgbrZ%2Brg3udLdbK1z8GZDrVsU0agqH9pi0hurOoUTqWDMiUpPs2ScRi%2BLYx3rWY%3D";
			
			// java.net.URLEncoder
			decodeResult = URLDecoder.decode(param, Constant.CHART_SET_UTF_8);
			
			log.info("testTemp =====> decodeResult = " + decodeResult );
			Base64 base64 = new Base64();
			data = base64.decode(decodeResult);
			decodeResult = new String(data, Constant.CHART_SET_UTF_8);
			log.info("testTemp =====> decodeResult = " + decodeResult );
			
			//BASE64Encoder encoder = new BASE64Encoder();
			
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
