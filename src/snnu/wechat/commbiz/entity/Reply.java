/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-12  Create	
 */
package snnu.wechat.commbiz.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 *
 */
@Entity
public class Reply implements Serializable {
	public static final Integer TYPE_SUBSCRIBE=1;
	public static final Integer TYPE_AUTO=2;
	public static final Integer TYPE_KEYWORD=3;
	public static final Integer TYPE_CUSTOM_KEYWORD=4;
	private Long id;
	private String name;
	private String ruleName;
	private String keyword;
	private Integer type;
	private Integer msgType;
	private String wechatJson;
	private String description;
	private Date CreateTime;
	private Date LastUpdateTime;
	@Id
	@GeneratedValue
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
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public Date getLastUpdateTime() {
		return LastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		LastUpdateTime = lastUpdateTime;
	}
}
