/**
 * MemberMail.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.entity.codec;

import java.util.Calendar;
import java.util.Date;


/**
 * MemberMail
 * 描述: 会员邮件
 * @author qye.zheng
 * 
 */
public final class MemberMail
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	public MemberMail()
	{
	}
	
	// logo url
	public static String logoUrl = "a";
	
	// 失效间隔时间 (单位 : 小时)
	private static final int VALID_PEROID_HOUR = 24;
	
	 // 会员ID ID
	private String id;
	
	// 登录帐号 MEMBER_NO
	private String memberNo; 
	
	// 会员昵称NICKNAME
	private String nickname; 
	
	// 真实姓名NAME
	private String name; 
	
	// 性别 SEX
	private String sex; 
	
	// 电子邮件EMAIL
	private String email; 

	// 校验码 CHECK_CODE
	private String checkCode;
	
	// 激活地址
	private String activateUrl;
	
	// 失效时间戳
	private long invalidTs;

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the memberNo
	 */
	public String getMemberNo()
	{
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo)
	{
		this.memberNo = memberNo;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname()
	{
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public String getSex()
	{
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the checkCode
	 */
	public String getCheckCode()
	{
		return checkCode;
	}

	/**
	 * @param checkCode the checkCode to set
	 */
	public void setCheckCode(String checkCode)
	{
		this.checkCode = checkCode;
	}

	/**
	 * @return the logoUrl
	 */
	public static String getLogoUrl()
	{
		return logoUrl;
	}

	/**
	 * @return the activateUrl
	 */
	public String getActivateUrl()
	{
		return activateUrl;
	}

	/**
	 * @param activateUrl the activateUrl to set
	 */
	public void setActivateUrl(String activateUrl)
	{
		this.activateUrl = activateUrl;
	}

	/**
	 * @return the invalidTs
	 */
	public long getInvalidTs()
	{
		final Calendar cal = Calendar.getInstance();
		// 加上失效周期，计算出失效时间
		cal.add(Calendar.HOUR, VALID_PEROID_HOUR);
		invalidTs = cal.getTimeInMillis();
		
		return invalidTs;
	} 
	
	
	
}
