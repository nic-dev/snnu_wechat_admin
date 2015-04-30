/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-17  Create	
 */
package snnu.wechat.commbiz.model;

import java.util.List;

import snnu.wechat.framework.core.ToStringSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 *
 */
public class WechatResponseJson extends ToStringSupport {

	private String accessToken;
	private String expiresIn;
	private int errCode;
	private String errMsg;
	private String type;
	private String mediaId;
	private Long createdAt;
	private String msgId;
	private String groupId;
	private List<WechatGroupJson> groupList;
	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@JsonProperty("expires_in")
	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	@JsonProperty("errcode")
	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	@JsonProperty("errmsg")
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@JsonProperty("media_id")
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	@JsonProperty("created_at")
	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	@JsonProperty("msg_id")
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	@JsonProperty("groupid")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@JsonProperty("groups")
	public List<WechatGroupJson> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<WechatGroupJson> groupList) {
		this.groupList = groupList;
	}
	public static class WechatGroupJson extends ToStringSupport{
		private Long id;
		private String name;
		private Long count;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Long getCount() {
			return count;
		}
		public void setCount(Long count) {
			this.count = count;
		}
	}
}
