/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-10-4  Create	
 */
package snnu.wechat.commbiz.common;

import java.util.Set;


/**
 * 
 *
 */
public class WechatConfigBean {
	public static final String WEBAPP_AUTO_MAPPING_KEYWORD="mappingKeyword";
	public static final String WEBAPP_AUTO_MAPPING_VALUE="mappingValue";
	public static final String WEBAPP_AUTO_MEDIA_VALUE="mediaType";
	
	
	
	
	private Set<String > webappMenuNameSet;
	private String needBindRuleName;
	




	public Set<String> getWebappMenuNameSet() {
		return webappMenuNameSet;
	}
	public void setWebappMenuNameSet(Set<String> webappMenuNameSet) {
		this.webappMenuNameSet = webappMenuNameSet;
	}
	public String getNeedBindRuleName() {
		return needBindRuleName;
	}
	public void setNeedBindRuleName(String needBindRuleName) {
		this.needBindRuleName = needBindRuleName;
	}
	
}
