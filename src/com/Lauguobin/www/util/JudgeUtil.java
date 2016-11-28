package com.Lauguobin.www.util;

import java.util.regex.*;

public class JudgeUtil
{
	/**
	 * 正则判断电话是否合法
	 * @param mobiles 电话文本域传入的数据
	 * @return 是或否
	 */
	public static boolean isMobileNO(String mobiles) 
	{
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	  /**
	   * 判断是不是都是整数，用于判断是否为班级和id
	   * @param str  文本域数据
	   * @return
	   */
	 public static boolean isInteger(String str) 
	 {    
		 if(str.matches("(\\d*)"))
			return true;
		 return false;
	 }  
	 
	 /**
	  * 判断是不是邮箱
	  * @param str 文本数据（如果有）
	  * @return
	  */
	 public static boolean isEmail(String str)
	 {
		 return str.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$" );
	 }
	 
	 /**
	  * 判断用户名是否带符号和数字
	  * @param str
	  * @return 有符号和数字返回否
	  */
	 public static boolean isName(String str)
	 {
		 return str.matches("^([\u4E00-\u9FA5]+|[a-zA-Z]+)$");
	 }
	 
	 /**
	  * 注册时判断用户名是否合法
	  * @param str
	  * @return
	  */
	 public static boolean isUser(String str)
	 {
		 return str.matches("^[\u4E00-\u9FA5A-Za-z0-9]{4,20}$");
	 }
	 
	 /**
	  * 判断一个字符串是不是中文
	  * @param str
	  * @return
	  */
	 public static boolean isChinese(String str)
	 {
		 return str.matches( "^[\u4E00-\u9FA5A-Za-z]{2,20}$");
	 }
}
