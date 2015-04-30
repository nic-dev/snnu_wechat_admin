/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-15  Create	
 */
package snnu.wechat.commbiz.common;

/**
 * 
 *
 */
public enum SysConfigType {
	WECHAT_URL("wechatUrl"), WECHAT_TOKEN("wechatToken"), WECHAT_APP_SECRET(
			"wechatAppSecret"), WECHAT_APP_ID("wechatAppId"), WECHAT_ACCESS_TOKEN_URL(
			"wechatAccessTokenUrl"), WECHAT_ACCESS_TOKEN("wechatAccessToken"), WECHAT_ACCESS_TOKEN_GRANT_TYPE(
			"wechatAccessTokenGrantType"), WECHAT_MEDIA_URL("wechatMediaUrl"), WECHAT_MEDIA_GET_URL(
			"wechatMediaGetUrl"), WECHAT_UPLOAD_NEWS_URL("wechatUploadNewsUrl"), WECHAT_PUBLISH_MENU_URL(
			"wechatPublishMenuUrl"), WECHAT_BROADCAST_BY_GROUP(
			"wechatBroadcastByGroup"), WECHAT_CREATE_GROUP("wechatCreateGroup"), WECHAT_LIST_GROUP(
			"wechatListGroup"), WECHAT_GET_USER_GROUP_ID("wechatGetUserGroupId"),WECHAT_BROADCAST_BY_OPEN_ID("wechatBroadcastByOpenId");
	private SysConfigType(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}
}
