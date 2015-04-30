/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-10-17  Create	
 */
package snnu.wechat.commbiz.common;

import java.util.Map;

/**
 * 
 *
 */
public class WechatSimulConfigBean {
	private Map<String,String> webappSimulRestTemplateMap;
	private String simulMediaContextPath;
	public String getSimulMediaContextPath() {
		return simulMediaContextPath;
	}
	public void setSimulMediaContextPath(String simulMediaContextPath) {
		this.simulMediaContextPath = simulMediaContextPath;
	}
	public Map<String, String> getWebappSimulRestTemplateMap() {
		return webappSimulRestTemplateMap;
	}
	public void setWebappSimulRestTemplateMap(
			Map<String, String> webappSimulRestTemplateMap) {
		this.webappSimulRestTemplateMap = webappSimulRestTemplateMap;
	}
	public String getWebappSimulRestTemplateMapConfig(String key) {
		return this.webappSimulRestTemplateMap.get(key);
	}
}
