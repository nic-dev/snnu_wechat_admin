/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-10-8  Create	
 */
package snnu.wechat.commbiz.model;

import snnu.wechat.framework.core.ToStringSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WechatUserJson extends ToStringSupport {

	private String t;
	private String ticket;
	private String uin;
	@JsonProperty("user_name")
	private String userName;
	@JsonProperty("nick_name")
	private String nickName;

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
