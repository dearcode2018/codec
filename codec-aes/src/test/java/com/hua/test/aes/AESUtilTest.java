/**
 * 描述: 
 * AESUtilTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.aes;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.charset.StandardCharsets;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.AESUtil;
import com.hua.util.StringUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * AESUtilTest
 */
public final class AESUtilTest extends BaseTest {

	/* 密码长度，8的倍数，当前密码16位 */
	private String password = "507b8aca9bfe9ee8";
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAESUtil() {
		try {
			String value = "hehheheaafsd";
			
			byte[] aesBinaryData = AESUtil.encrypt(value.getBytes(), password);
			password = "507b8aca9bfe9ee1";
			String decode = new String(AESUtil.decrypt(aesBinaryData, password));
			log.info("testAESUtil =====> 解码: " + decode);
			
		} catch (Exception e) {
			log.error("testAESUtil =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExample() {
		try {
			// 秘钥 16位，可以和MySQL的AES算法保持一致
			password = "1234567812345678";
			//password = "12345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678";
			String value = null;;
			//value = "广东省广州市海珠区广东省广州市海珠区广东省广州市海珠区广东省广州市海珠区";
			 value = "广东省广州市海珠区";
			// 英文字符 64位以下 对应 128位编码，不包括64位
			//value = "215421654321346421254545434535353485934343434543534534533453433";
			System.out.println(value.length());
			byte[] data = AESUtil.encrypt(value.getBytes(StandardCharsets.UTF_8), password);
			//System.out.println(CodecUtil.bytesToHexString(data));
			//System.out.println(new String(Hex.encodeHex(data)));
			System.out.println(StringUtil.parseByte2HexString(data, true));
			//System.out.println(new String(AESUtil.decrypt(data, password)));
			//System.out.println(byteToHex(data));
			//System.out.println(Base64Util.encodeToString(data));
			//System.out.println(Base64.encodeBase64String(data));
			//String str = "46eb5940ea6c63ee3c35f0f90262b6c293f76f8d225cdb96a2fab0c85e647bb7";
			//System.out.println(new String(AESUtil.decrypt(CodecUtil.hexStringToBytes(str), "123")));
			
			//System.out.println(StringUtil.parseByte2HexString("广东省广州市海珠区".getBytes()));
			
		} catch (Exception e) {
			log.error("testExample =====> ", e);
		}
	}
	
	
    public static String byteToHex(byte[] bytes){
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < bytes.length; n++) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
        }
        return sb.toString().trim();
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
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
