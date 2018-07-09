/**
 * MD5Util.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.security.MessageDigest;

import com.hua.constant.Constant;
import com.hua.util.StringUtil;

/**
 * 
 * @type MD5Util
 * @description MD5 编码工具
 * @author qye.zheng
 */
public final class MD5Util {
	
	/* [0, 15] */
	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 构造方法 描述:
	 * 
	 * @author qye.zheng
	 */
	private MD5Util() {
	}

	/**
	 * @description 字节数组 转化为 16进制字符串
	 * @param array
	 * @return
	 * @author qye.zheng
	 */
	public static final String byteArrayToHexString(final byte[] array) {
		final StringBuilder builder = StringUtil.getBuilder();

		for (int i = 0; i < array.length; i++) {
			builder.append(byteToHexString(array[i]));
		}

		return builder.toString();
	}

	/**
	 * 
	 * @description 字节转成 16进制字符串形式
	 * @param b
	 * @return
	 * @author qye.zheng
	 */
	public static final String byteToHexString(final byte b) {
		int n = b;
		// 字节8位 转成 int类型
		// int n = b & 0XFF;
		if (n < 0) {
			n = 256 + n;
		}
		// 高位
		final int d1 = n / 16;
		// 低位
		final int d2 = n % 16;

		return hexDigits[d1] + hexDigits[d2];
	}
	
	/**
	 * 
	 * @description 16进制字符串转成字节数组形式
	 * @param b
	 * @return
	 * @author qye.zheng
	 */
	public static final byte[] hexToBytes(final String hexString) {
		int m = 0;
		int n = 0;  
	    int l = hexString.length() / 2;  
	    byte[] ret = new byte[l];  
	    for (int i = 0; i < l; i++) {  
	        m = i * 2 + 1;  
	        n = m + 1;  
	        ret[i] = uniteBytes(hexString.substring(i * 2, m), hexString.substring(m, n));  
	    }  
	    
	    return ret;  
	}
	
	/**
	 * 
	 * @description 
	 * @param src0
	 * @param src1
	 * @return
	 * @author qianye.zheng
	 */
	private static byte uniteBytes(String src0, String src1) {  
	    byte b0 = Byte.decode("0x" + src0).byteValue();  
	    b0 = (byte) (b0 << 4);  
	    byte b1 = Byte.decode("0x" + src1).byteValue();  
	    byte ret = (byte) (b0 | b1);  
	    return ret;  
	}  

	/**
	 * 
	 * @description md5 编码
	 * @param origin
	 * @return
	 * @author qye.zheng
	 */
	public static final String md5Encode(final String origin) {
		String result = null;
		try {
			result = new String(origin);

			final MessageDigest md = MessageDigest
					.getInstance(Constant.EN_DECRY_MD5);
			// md.update(result.getBytes());
			result = byteArrayToHexString(md.digest(result.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

}
