/**
  * @filename Base64Util.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.util;

import org.apache.commons.codec.binary.Base64;


 /**
 * @type Base64Util
 * @description 
 * @author qye.zheng
 */
public final class Base64Util {

	
	/**
	 * Base64介绍
	 * Base64是用于传输8Bit字节的编码方式之一，使用基于64个可
	 * 打印字符来表示二进制数据的方式.
	 * 
	 * Base64编码: 从二进制到字符的过程.
	 * 将所有字符转成 8位二进制，以6个二进制为单位形成
	 * 一组，因为不够8位，每组在高位补2个0，最后查找ASCII表
	 * 即可输出结果.
	 * 例如 s 1 3 -> ASCII码十进制: 115 49 51
	 * -> 转成8位二进制: 01110011 00110001 00110011
	 * -> 6个一组(4组): 011100 110011 000100 110011
	 * -> 高位补2个0: 00011100 00110011 00000100 00110011
	 * -> 转十进制: 28 51 4 51
	 * -> 查找base64对照表输出结果: c z E z
	 * 
	 * Base64编码"加料": 通常在原始字符串头尾加上一些字符，然后再编码.
	 * 
	 * Base64编码最终可以转成字符
	 * 
	 * 
	 * Base64解码: 解码为字节，解码为字符串
	 * 很多情况下，解码为了字节让其他程序去识别，而解析为字符串则是给
	 * 文本编辑器或某些页面显示用.
	 */
	
	
	/**
	 * 
	 * @description 字节数组base64编码 输出字节数组
	 * @param binaryData 数据 (字节数组 - 8位二进制)
	 * @return
	 * @author qianye.zheng
	 */
	public static final byte[] encode(final byte[] binaryData)
	{
		
		return Base64.encodeBase64(binaryData);
	}
	
	/**
	 * 字节数组最终可以编码成一个可显示的字符串
	 * @description 字节数组base64编码 输出字符串
	 * @param binaryData 数据 (字节数组 - 8位二进制)
	 * @return
	 * @author qianye.zheng
	 */
	public static final String encodeToString(final byte[] binaryData)
	{
		
		return Base64.encodeBase64String(binaryData);
	}
	
	/**
	 * 
	 * @description 解码
	 * @param base64String base64编码字符串
	 * @return
	 * @author qianye.zheng
	 */
	public static final byte[] decodeFromString(final String base64String)
	{
		return Base64.decodeBase64(base64String);
	}
	
	/**
	 * 
	 * @description 
	 * @param base64Data base64编码字节数组
	 * @return
	 * @author qianye.zheng
	 */
	public static final byte[] decode(final byte[] base64Data)
	{
		return Base64.decodeBase64(base64Data);
	}
	
	
	
}
