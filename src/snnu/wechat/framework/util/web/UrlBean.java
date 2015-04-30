/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-24  Create	
 */
package snnu.wechat.framework.util.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 *
 */
public class UrlBean {
	private String baseUrl;
	private Map<String, String> paramMap;
	private String hash;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		if(this.paramMap==null){
			this.paramMap = paramMap;
		}
		else {
			this.paramMap.putAll(paramMap);
		}
	}

	public void addParam(String key, String value) {
		if (paramMap == null) {
			paramMap = new HashMap<String, String>();
		}
		paramMap.put(key, value);
	}
	public String getParam(String key){
		if(paramMap==null){
			return null;
		}
		return paramMap.get(key);
	}
	public void removeParam(String key){
		if(paramMap!=null){
			paramMap.remove(key);
		}
	}
	public void setParams(String params) {
		if (params == null) {
			return;
		}
		if (paramMap == null) {
			paramMap = new HashMap<String, String>();
		}
		String[] paramPair = params.split("&");
		for (String param : paramPair) {
			String[] keyAndValue = param.split("=");
			if (keyAndValue.length == 2) {
				paramMap.put(keyAndValue[0], keyAndValue[1]);
			}
		}

	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl);
		if (paramMap != null) {
			sb.append('?');
			for (Entry<String, String> paramEntry : paramMap.entrySet()) {
				sb.append(paramEntry.getKey()).append('=')
						.append(paramEntry.getValue()).append('&');
			}
			sb = sb.deleteCharAt(sb.length() - 1);

		}
		if (hash != null) {
			sb.append('#').append(hash);
		}
		return sb.toString();
	}
}
