/**
 * UrlCoderTest.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.test.codec;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.entity.codec.MemberMail;
import com.hua.test.BaseTest;


/**
 * UrlCoderTest
 * 描述: 
 * @author qye.zheng
 * 
 */
public final class CoDecTest extends BaseTest
{
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCoDec() {
		try {
			byte b = -123;
			int n = b;
			log.info("testCoDec =====> n = " + n);
			
		} catch (Exception e) {
			log.error("testCoDec =====> ", e);
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
			String str = "123bac123456";
			log.info("testTemp =====> " + str.indexOf("bac"));
			log.info("testTemp =====> " + str.lastIndexOf("bac"));
			
			
			
			final MemberMail mail = new MemberMail();
			mail.setId("490fe386-2b80-4ff1-9413-1e434bb7260e");
			mail.setCheckCode("457952");
			getActivateUrl(mail);
			
			
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
			// url 参数
			final String param = "sid=mf7y9JhYik8aVm&name=张三&address=gz";
			log.info("testCommon =====> param = " + param);
			// apache base64
			Base64 base64 = new Base64();
			
			// 1. url 编码
			encodeResult = URLEncoder.encode(param, Constant.CHART_SET_UTF_8);
			log.info("testCommon ==1. url 编码===> encodeResult = " + encodeResult);
			
			// 2. base64加密
			encodeResult = base64.encodeAsString(encodeResult.getBytes(Constant.CHART_SET_UTF_8));
			log.info("testCommon ==2. 加密===> encodeResult = " + encodeResult);
			
			// 3. base64 解密
			encodeResult =  new String(base64.decode(encodeResult), Constant.CHART_SET_UTF_8);
			log.info("testCommon ==3. base64 解密===> encodeResult = " + encodeResult);
			
			// 4. url 解码
			encodeResult = URLDecoder.decode(encodeResult, Constant.CHART_SET_UTF_8);
			log.info("testCommon ==4. url 解码===> encodeResult = " + encodeResult);
			
		
			
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
	public void testUrlDecode() {
		try {
			String url = "http%3a%2f%2fm.7daysinn.cn%2fwx%2flogin%3fredirecturl%3dhttp%253a%252f%252f120.24.53.191%252fwechatprod%252fgetmagic%252ftomagic%253fbrandid%253d0%2526memid%253d181312893%2526idno%253d%2526ct%253d1%2526v%253d3%2526sellerid%253d282353%2526phone%253d13710640909&response_type=code&scope=snsapi_base&state=77777777/#wechat_redirect";
			url = "http%3a%2f%2f120.24.53.191%2fwechatprod%2fgetmagic%2ftomagic%3fbrandid%3d0%26memid%3d181312893%26idno%3d%26ct%3d1%26v%3d3%26sellerid%3d282353%26phone%3d13710640909";
			URLDecoder urlDecorder = new URLDecoder();
			System.out.println(urlDecorder.decode(url, Constant.CHART_SET_UTF_8));
		} catch (Exception e) {
			log.error("testUrlDecode =====> ", e);
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
	
	/**
	 * 
	 * 描述: 获取激活地址
	 * @author qye.zheng
	 * 
	 * @return
	 */
	private String getActivateUrl(final MemberMail mail) {
		String url = null;
		try
		{
			// 会员Id / 验证码 / 失效时间戳
			// Url中的参数data的内容需要 Base64 加密
			final StringBuilder paramBuilder = new StringBuilder(1024);
			// apache base64
			Base64 base64 = new Base64();
			//
			paramBuilder.append("id=" + mail.getId());
			paramBuilder.append("&checkCode=" + mail.getCheckCode());
			paramBuilder.append("&invalidTs=" + mail.getInvalidTs());
			// 1.url 参数编码
			String encodeResult = URLEncoder.encode(paramBuilder.toString(), Constant.CHART_SET_UTF_8);
			log.info("getActivateUrl ==1.url 参数编码===> encodeResult = " + encodeResult);
			
			// 2. url参数加密
			encodeResult = base64.encodeAsString(encodeResult.getBytes(Constant.CHART_SET_UTF_8));
			log.info("getActivateUrl ==2. url参数加密===> encodeResult = " + encodeResult);
			
			url = "http://www.abc.com" + "?data=" + encodeResult;
			
			log.info("getActivateUrl =====> url --> " + url);
		} catch (Exception e)
		{
			log.error("getActivateUrl =====> ", e);
		}
		
		return url;
	}
	
}
