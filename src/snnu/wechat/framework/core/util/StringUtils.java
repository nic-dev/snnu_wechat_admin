/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-17  Create	
 */
package snnu.wechat.framework.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 *
 */
public class StringUtils {
	private static Pattern numberPattern=Pattern.compile("^\\d+\\.*\\d*$");
	private static final String DATETIME_PATTERN="yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat dateFormat=new SimpleDateFormat();
	public static boolean isNumber(String str){
		if(isEmpty(str)){
			return false;
		}
		Matcher matcher=numberPattern.matcher(str);
		return matcher.matches();
		
	}
	public static boolean isEmpty(String str){
		return str==null||str.isEmpty();
	}
	public static String join(String [] arr,char delimn){
		String result="";
		StringBuilder sb=new StringBuilder();
		for (String ele : arr) {
			sb.append(ele).append(delimn);
		}
		if(sb.length()>0){
			result=sb.substring(0,sb.length()-1);
		}
		return result;
	}
	public static String formatDate(Date date){
		dateFormat.applyPattern(DATETIME_PATTERN);
		return dateFormat.format(date);
	}
	public static void main(String[] args) {
		System.out.println(isNumber("123.12"));
	}
}
