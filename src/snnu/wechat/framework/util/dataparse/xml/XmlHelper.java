/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-17  Create	
 */
package snnu.wechat.framework.util.dataparse.xml;

/**
 * 
 *
 */
public class XmlHelper {
	public static final String CDATA_START="<![CDATA[";
	public static final String CDATA_END="]]>";
	public static String unwrapCDATA(String str){
		return str.replace(CDATA_START, "").replace(CDATA_END, "");
	}
	public static String wrapCDATA(String str){
		return CDATA_START+str+CDATA_END;
	}
}
