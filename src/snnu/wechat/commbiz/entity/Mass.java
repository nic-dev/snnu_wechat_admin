/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-10  Create	
 */
package snnu.wechat.commbiz.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 *
 */
@Entity
public class Mass {
	private Long id;
	private String msgId;
	private Integer msgType;
	private String wechatJson;
	private String wechatStatistics;
	private String description;
	private Date createTime;
	private Integer state;
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
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
	public String getWechatStatistics() {
		return wechatStatistics;
	}
	public void setWechatStatistics(String wechatStatistics) {
		this.wechatStatistics = wechatStatistics;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
