/**
 * 描述: 
 * EccTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.ecc;

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

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import sun.security.ec.ECPublicKeyImpl;

import com.hua.test.BaseTest;
import com.hua.util.CodecUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * EccTest
 */
public final class EccTest extends BaseTest {

	/**
	 * ECC: Elliptic Curves Cryptography 椭圆曲线密码编码学
	 * 对每比特所提供加密强度最高的一种机制, 在软件注册保护方面起到很大的作用, 
	 * 一般的序列号通常由该算法产生
	 */
	
	  public static final String ALGORITHM = "EC";  
	  private static final String PUBLIC_KEY = "ECCPublicKey";  
	  private static final String PRIVATE_KEY = "ECCPrivateKey";  
	
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
     * 取得私钥 
     *  
     * @param keyMap 
     * @return 
     * @throws Exception 
     */  
    public static String getPrivateKey(Map<String, Object> keyMap)  
            throws Exception {  
        Key key = (Key) keyMap.get(PRIVATE_KEY);  
  
        return CodecUtil.bytesToHexString(key.getEncoded());
    }  
  
    /** 
     * 取得公钥 
     *  
     * @param keyMap 
     * @return 
     * @throws Exception 
     */  
    public static String getPublicKey(Map<String, Object> keyMap)  
            throws Exception {  
        Key key = (Key) keyMap.get(PUBLIC_KEY);  
        
        return CodecUtil.bytesToHexString(key.getEncoded());
    }  	
	
	/**
	 * .
	 * @description 初始化密钥
	 * @return
	 * @author qianye.zheng
	 */
	public static Map<String, Object> initKey() throws Exception
	{
		BigInteger x1 = new BigInteger("2fe13c0537bbc11acaa07d793de4e6d5e5c94eee8", 16);
		BigInteger x2 = new BigInteger("289070fb05d38ff58321f2e800536d538ccdaa3d9", 16);
		
		ECPoint g = new ECPoint(x1, x2);
		
		BigInteger n = new BigInteger("5846006549323611672814741753598448348329118574063", 10);

		int h = 2;
		int m = 163;
		int[] ks = {7, 6, 3};
		
		ECFieldF2m ecField = new ECFieldF2m(m, ks);
		// y^2 + xy = x^3 + y^2 + 1; 
		BigInteger a = new BigInteger("1", 2);
		BigInteger b = new BigInteger("1", 2);
		EllipticCurve elliptcCuve = new EllipticCurve(ecField, a, b);
		ECParameterSpec ecParameter = new ECParameterSpec(elliptcCuve, g, n, h);
		
		// 公钥
		//ECPublicKey publicKey = new ECPublicKeyImpl(g, ecParameter);
		Map<String, Object> keyMap = new HashMap<String, Object>(2);  
		
		return keyMap;
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
