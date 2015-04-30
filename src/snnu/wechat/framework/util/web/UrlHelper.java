/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-24  Create	
 */
package snnu.wechat.framework.util.web;

import java.util.Map;
import java.util.Map.Entry;

import snnu.wechat.framework.log.Log;


/**
 * 
 *
 */
public class UrlHelper {
	public static String createFullUrl(String baseUrl,Map<String,String> params){
		StringBuilder sb=new StringBuilder();
		sb.append(baseUrl);
		if(baseUrl.indexOf('?')<0){
			sb.append('?');
		}else{
			sb.append('&');
		}
		for (Entry<String, String> param : params.entrySet()) {
			sb.append(param.getKey()).append('=').append(param.getValue()).append('&');
		}
		Log.logInfo("params size="+params.entrySet().size()+"createdUrl="+sb);
		return decode( sb.substring(0,sb.length()-1).toString());
	}
	public static String decode(String url){
		return url.replace("&amp;", "&");
	}
	public static UrlBean parse(String url){
		if(url==null){
			return null;
		}
		url=decode(url);
		UrlBean result=new UrlBean();
		
		int questionMarkPos=url.indexOf('?');
		int andMarkPos=url.indexOf('&');
		int hashMarkPos=url.indexOf('#');
		String params;
		if(questionMarkPos>0){
			if(hashMarkPos>questionMarkPos){
				result.setBaseUrl(url.substring(0,questionMarkPos));
				result.setParams(url.substring(questionMarkPos+1,hashMarkPos));
				result.setHash(url.substring(hashMarkPos+1));
			}
			else if(hashMarkPos>0){
				result.setBaseUrl(url.substring(0,hashMarkPos));
				result.setParams(url.substring(questionMarkPos+1));
				result.setHash(url.substring(hashMarkPos+1,questionMarkPos));
			}
			else{
				result.setBaseUrl(url.substring(0,questionMarkPos));
				result.setParams(url.substring(questionMarkPos+1));
			}
		}
		else if(hashMarkPos>0) {
			result.setBaseUrl(url.substring(0,hashMarkPos));
			result.setHash(url.substring(hashMarkPos));
		}
		else {
			result.setBaseUrl(url);
		}
		return result;
	}
	public static void main(String[] args) {
		String url="http://abc.com?a=1&amp;b=2#h1";
		UrlBean result=parse(url);
		System.out.println("baseUrl="+result.getBaseUrl());
		System.out.println("hash="+result.getHash());
		System.out.println("param size="+result.getParamMap().size());
		System.out.println(result);
	}
	
}
