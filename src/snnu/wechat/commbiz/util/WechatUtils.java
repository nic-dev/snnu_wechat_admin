/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-15  Create	
 */
package snnu.wechat.commbiz.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Sha1Hash;

import snnu.wechat.commbiz.common.WechatConfigBean;
import snnu.wechat.framework.log.Log;



/**
 * 
 *
 */
public class WechatUtils {
	public static boolean checkSignature(String signature, String timestamp,
			String nonce, String token) {
		if(signature==null||timestamp==null||nonce==null||token==null){
			return false;
		}
		Log.logInfo("[checkSignature] signature=" + signature + " timestamp="
				+ timestamp + " nonce=" + nonce + " token=" + token);
		boolean result;
	

		result = signature
				.equals(signature(timestamp, nonce, token));
		Log.logInfo("[checkSignatrue] result=" + result);
		return result;
	}
	public static String signature(String timestamp,
			String nonce, String token){
		String result;
		List<String> list = new ArrayList<String>();
		list.add(timestamp);
		list.add(nonce);
		list.add(token);
		Collections.sort(list);
		StringBuilder newSignature = new StringBuilder();
		for (String data : list) {
			newSignature.append(data);
		}
		result=new Sha1Hash(newSignature.toString()).toHex();
		return result;
	}
	public static Integer findCustomMenuMark(String title,Map<String, Map<String, String>>  webappMenuConfigMap) {
		Integer result = null;

		for (Map<String, String> entry : webappMenuConfigMap.values()) {
			String mappingKeyword = entry
					.get(WechatConfigBean.WEBAPP_AUTO_MAPPING_KEYWORD);
			if (mappingKeyword != null && title.contains(mappingKeyword)) {
				result = Integer.parseInt(entry
						.get(WechatConfigBean.WEBAPP_AUTO_MAPPING_VALUE));

			}
		}
		return result;

	}
	public static String getSeqFromUserInput(String userInput,String channelName){
		int beginIndex=userInput.indexOf(channelName);
		if (beginIndex==-1||userInput.length()==channelName.length()){
			return "";
		}
		return userInput.substring(beginIndex+channelName.length());
	}
	public static void main(String[] args) {
		String timestamp="1411567873418";
		String openId="oCsvjjg2LjDFEjLYdjHrfccWN5q0";
		String token="SNNU_ECARD";
		timestamp="1411570790904";
		token="WEINXI_SHOW_TOKEN";
		System.out.println(signature(openId,timestamp, token));

	}
}
 