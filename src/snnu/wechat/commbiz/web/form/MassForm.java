/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-9  Create	
 */
package snnu.wechat.commbiz.web.form;

import snnu.wechat.framework.core.vo.BasePageForm;

/**
 * 
 *
 */
public class MassForm extends BasePageForm{
	private Integer msgType;
	private String wechatJson;
	private String description;
	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public String getWechatJson() {
		return wechatJson;
	}

	public void setWechatJson(String wechatJson) {
		this.wechatJson = wechatJson;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
