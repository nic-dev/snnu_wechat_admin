/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-19  Create	
 */
package snnu.wechat.commbiz.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import snnu.wechat.commbiz.model.WechatMenuJson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WeChatMenuButtonJson {
	private List<WechatMenuJson> menuList;
	@JsonProperty("button")
	public List<WechatMenuJson> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<WechatMenuJson> menuList) {
		this.menuList = menuList;
	}

}
