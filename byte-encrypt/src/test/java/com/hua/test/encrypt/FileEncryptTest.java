/**
 * 描述: 
 * FileEncryptTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.encrypt;

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

import java.io.File;
import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.FileUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * FileEncryptTest
 */
public final class FileEncryptTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFileEncryptReverse() {
		try {
			level = 0;
			file = new File(ProjectUtil.getAbsolutePath("/doc/encrypt/info_01.txt", true));
			
			//inputStream = new FileInputStream(file);
			data = FileUtil.getByteArray(file);
			
			System.out.println(Arrays.toString(data));
			//data = null;
			// 中间字节数组，第一个位置存放加密级别
			byte[] temp = new byte[data.length + 1];
			temp[0] = (byte) level;
			// 字节移位
			for (int i = 0; null != data && i < data.length; i++)
			{
				// 左移 1 位
				data[i]  <<= 1;
				temp[i + 1] = (byte) Integer.reverse(data[i]);
			}
			
			System.out.println(Arrays.toString(temp));
			
			for (int i = 1; null != temp && i < temp.length; i++)
			{
				// 左移 1 位
				temp[i] = (byte) Integer.reverse(temp[i]);
			}
			System.out.println(Arrays.toString(temp));
			
			
			
			//FileUtil.writeByteArray(file, temp);
			
			// 数组拷贝，将data中的数据拷贝到 temp
			
		} catch (Exception e) {
			log.error("testFileEncryptReverse =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 移位无法实现反向解密
	 * 移位加密可以视为单向加密，而无法解密
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFileEncryptShiftBit() {
		try {
			level = 0;
			file = new File(ProjectUtil.getAbsolutePath("/doc/encrypt/info_01.txt", true));
			
			//inputStream = new FileInputStream(file);
			data = FileUtil.getByteArray(file);
			
			System.out.println(Arrays.toString(data));
			//data = null;
			// 中间字节数组，第一个位置存放加密级别
			byte[] temp = new byte[data.length + 1];
			temp[0] = (byte) level;
			// 字节移位
			for (int i = 0; null != data && i < data.length; i++)
			{
				// 左移 1 位
				data[i]  <<= 1;
				temp[i + 1] = data[i];
			}
			
			System.out.println(Arrays.toString(temp));
			
			for (int i = 1; null != temp && i < temp.length; i++)
			{
				// 右移 1 位
				temp[i] >>>= 1;
			}
			System.out.println(Arrays.toString(temp));
			
			
			
			//FileUtil.writeByteArray(file, temp);
			
			// 数组拷贝，将data中的数据拷贝到 temp
			
		} catch (Exception e) {
			log.error("testFileEncryptShiftBit =====> ", e);
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
