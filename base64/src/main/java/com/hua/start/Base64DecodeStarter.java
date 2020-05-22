/**
 * 描述: 
 * Base64DecodeStarter.java
 * @author	qye.zheng
 * 
 *  version 1.0
 */
package com.hua.start;

import org.apache.tools.ant.taskdefs.Length.FileMode;
import org.junit.Test;

import com.hua.util.Base64Util;
import com.hua.util.FileUtil;
import com.hua.util.ProjectUtil;

/**
 * 描述: 启动器
 * @author  qye.zheng
 * 
 * Base64DecodeStarter
 */
public final class Base64DecodeStarter
{


	

	// 启动器模板
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void start()
	{
		/** ===== begin 驱动参数设置  ===== */
		// 设置例子
		final String path = ProjectUtil.getAbsolutePath("/doc/dataString.txt", true);
		final String base64String = FileUtil.getString(path);
		String filename = null;
		
		// 设置参数
		filename = "test.txt";
		
		String targetPath = ProjectUtil.getAbsolutePath("/doc", true) + "/" + filename;
		FileUtil.writeByteArray(targetPath, Base64Util.decodeFromString(base64String), false);
		
		/** ===== end of 驱动参数设置 ===== */

		// 启动驱动
		
		
	}

}
