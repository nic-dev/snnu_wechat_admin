/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-10-7  Create	
 */
package snnu.wechat.commbiz.model;

import snnu.wechat.framework.core.ToStringSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WechatSimulResponseJson extends ToStringSupport {
	private int ret;
	private String msg;
	@JsonProperty("redirect_url")
	private String redirectUrl;
	private String location;
	private String type;
	private String content; //midia 的 id
	@JsonProperty("base_resp")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BaseResp baseResp;
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)

	public static class BaseResp extends ToStringSupport {
		@JsonProperty("ret")
		private int ret;
		@JsonProperty("err_msg")
		private String errMsg;
		
		public int getRet() {
			return ret;
		}
		public void setRet(int ret) {
			this.ret = ret;
		}
	
		public String getErrMsg() {
			return errMsg;
		}
		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}
	
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BaseResp getBaseResp() {
		return baseResp;
	}

	public void setBaseResp(BaseResp baseResp) {
		this.baseResp = baseResp;
	}
}
