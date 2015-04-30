/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-27  Create	
 */
package snnu.wechat.commbiz.common;

import java.util.Map;

/**
 * 
 *
 */
public class ServiceConfigBean {
	
//	public static final String LOCAL="local";
	
	private boolean isLocal;
	private Map<String, String> configMap;

	public boolean getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}

	public Map<String, String> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, String> configMap) {
		this.configMap = configMap;
	}
	public String config(String key){
		return configMap.get(key);
	}
}
