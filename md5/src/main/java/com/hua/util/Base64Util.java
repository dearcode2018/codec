/**
  * @filename Base64Util.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

 /**
 * @type Base64Util
 * @description 
 * @author qye.zheng
 */
public final class Base64Util {

	/* base64 编码器 */
	private static final BASE64Encoder encoder = new BASE64Encoder();
	
	/* base64 解码器 */
	private static final BASE64Decoder decoder = new BASE64Decoder();
	
	//Base64 base64 = new Base64();
	
	/**
	 * 
	 * @description  base64 编码
	 * @param target
	 * @return
	 * @author qye.zheng
	 */
	public static final String encodeBase64(final String target)
	{
		//String result = encoder.encode(target.getBytes()).trim();
		String result = null;
		if (null != target)
		{
			result = encoder.encode(target.getBytes()).trim();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @description base64 解码
	 * @param target
	 * @return
	 * @author qye.zheng
	 */
	public static final String decodeBase64(final String target)
	{
		String result = null;
		try {
			if (null != target)
			{
				final byte[] array = decoder.decodeBuffer(target);
				result = new String(array);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
