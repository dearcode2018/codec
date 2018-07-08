/**
 * ByteUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import com.hua.constant.Constant;
import com.hua.util.FormatUtil;

/**
 * ByteUtil
 * 描述: 
 * @author  qye.zheng
 */
public final class ByteUtil
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private ByteUtil()
	{
	}
	
	/**
	 * 
	 * 描述: 生成8位二进制计算机码(补码)
	 * @author qye.zheng
	 * @param value
	 * @return
	 */
	public static String toBinaryString(final byte value)
	{
		/*
		  正数: 前面需要补0，直至结果字符串的长度等于8
		  
		  负数: 截取24 - 31 位 substring(24)
		  
		 */
		String result = null;
		if (value >= Constant.ZERO)
		{
			// 正数(包括0)
			result = Integer.toBinaryString(value);
			// 补0
			result = FormatUtil.addZero(result, Byte.SIZE);
		} else 
		{
			// 负数 截取24 - 31 位 substring(24)
			result = Integer.toBinaryString(value);
			// 截取 32bit int二进制的倒数8位
			result = result.substring(24);
		}
		
		return result;
	}

	/**
	 * 
	 * 描述: 转成八进制字符串
	 * @author qye.zheng
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String toOctalString(final byte value)
	{
		/*
		 说明: 由于3位二进制表示一位八进制，而byte是8位二进制
		 无法取整数个八进制
		 */
		
		return null;
	}
	
	/**
	 * 
	 * 描述: 转成十六进制字符串
	 * @author qye.zheng
	 * @param value
	 * @return
	 */
	public static String toHexString(final byte value)
	{
		/*
		  取int类型完整十六进制的最后2位，2位十六进制 == 一个byte
		 */
		String result = null;
		if (value >= Constant.ZERO)
		{
			// 正数(包括0)
			result = Integer.toHexString(value);
		} else 
		{
			// 取int类型完整十六进制的最后2位 6 / 7 位
			result = Integer.toHexString(value);
			result = result.substring(6);
		}
		
		return result;
	}
	
}
