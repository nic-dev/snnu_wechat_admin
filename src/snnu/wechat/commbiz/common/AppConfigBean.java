/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-26  Create	
 */
package snnu.wechat.commbiz.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

/**
 * 
 *
 */
public class AppConfigBean {
	public static final String WEBAPP_PROP_NEED_BIND="needBind";
	public static final String APP_DEBUG="debug";

	public static final String APP_CONFIG="appConfig";
	public static final String APP_PROP_ID="appId";
	public static final String APP_PROP_TITLE="title";
	public static final String APP_PPOP_TOKEN="token";
	public static final String APP_PROP_IMAGE="image";
	public static final String APP_ACCESS_URL="accessUrl";
	public static final String APP_BIND="bind";
	public static final String APP_SALARY="salary";
	public static final String APP_ECARD_DETAIL="ecardDetail";
	public static final String APP_LIBRARY_DETAIL="libraryDetail";
	public static final String APP_LIBRARY_DETAIL_URL="libraryDetailUrl";
	public static final String APP_LIBRARY_USER_URL="libraryUserInfoUrl";
	public static final String APP_ACCESS_URL_BASE_CONTEXT_URL="baseContextUrl";
	public static final String APP_ACCESS_URL_FRONT_ARTICLE_URL="frontArticleUrl";
	public static final String APP_ACCESS_URL_MOBILE_ARTICLE_URL="mobileArticleUrl";
	private Map<String,Map<String,String>> configMap=new HashMap<String, Map<String,String>>();
	private Map<String,Map<String,String> > webappMenuConfigMap;
	private boolean debug=false;
	private String invalidResource;
	private int defaultDuration;
	private Map<String, String> appMap=new HashMap<String, String>();
 
	public Map<String, Map<String,String>> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, Map<String,String>> configMap) {
		this.configMap = configMap;
	}
	public boolean getDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getInvalidResource() {
		return invalidResource;
	}

	public void setInvalidResource(String invalidResource) {
		this.invalidResource = invalidResource;
	}

	public int getDefaultDuration() {
		return defaultDuration;
	}

	public void setDefaultDuration(int defaultDuration) {
		this.defaultDuration = defaultDuration;
	}

	public Map<String, String> getAppMap() {
		return appMap;
	}

	public void setAppMap(Map<String, String> appMap) {
		this.appMap = appMap;
	}

	public Map<String,String> config(String key){
		if(configMap==null){
			return null;
		}
		else{
			return configMap.get(key);
		}
	}
	public Map<String,String> menuConfig(String appName){
		Map<String,String> result=new HashMap<String, String>();
		return webappMenuConfigMap.get(appName);
	}
	public boolean needBind(String appName){
		boolean result=false;
		if(menuConfig(appName)!=null&&"true".equals(menuConfig(appName).get(WEBAPP_PROP_NEED_BIND))){
			result=true;
		}
		return result;
	}
	public Map<String, Map<String, String>> getWebappMenuConfigMap() {
		return webappMenuConfigMap;
	}
	public void setWebappMenuConfigMap(
			Map<String, Map<String, String>> webappMenuConfigMap) {
		this.webappMenuConfigMap = webappMenuConfigMap;
	}
	@PostConstruct
	public void loadAppMap()
	{
		
		for (String configKey : configMap.keySet()) {
			appMap.put(configMap.get(configKey).get(APP_PROP_ID), configKey);
		}
		
	}
	public String getAppToken(String appId){
		return appMap.get(appId);
	}
	
}
