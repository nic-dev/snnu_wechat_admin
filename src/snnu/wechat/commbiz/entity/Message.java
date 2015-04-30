/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 19, 2014  Create	
 */
package snnu.wechat.commbiz.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
	public static final Integer MESSAGE_TYPE_SINGLE = 1;
	public static final Integer MESSAGE_TYPE_MULTI = 2;
	private Long id;
	private Long parentId;
	private String mediaId;
	private String simulMsgId;
	private String title;
	private String author;
	private String simulMediaId;
	private String thumbMediaId;
	private String cover;
	private String summary;
	private Integer isCoverInContent;

	private String content;
	private String link;
	private Integer type;
	private String accessUrl;
	private String subs;
	private String wechatJson;
	private String wechatJsonSimple;
	private String simulWechatJson;
	private Date createTime;
	private Date lastUpdateTime;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	@Column(name = "parent_id")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSimulMsgId() {
		return simulMsgId;
	}

	public void setSimulMsgId(String simulMsgId) {
		this.simulMsgId = simulMsgId;
	}

	public String getSimulMediaId() {
		return simulMediaId;
	}

	public void setSimulMediaId(String simulMediaId) {
		this.simulMediaId = simulMediaId;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getIsCoverInContent() {
		return isCoverInContent;
	}

	public void setIsCoverInContent(Integer isCoverInContent) {
		this.isCoverInContent = isCoverInContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getType() {
		return type;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public String getSubs() {
		return subs;
	}

	public void setSubs(String subs) {
		this.subs = subs;
	}

	public String getWechatJson() {
		return wechatJson;
	}

	public void setWechatJson(String wechatJson) {
		this.wechatJson = wechatJson;
	}

	public String getWechatJsonSimple() {
		return wechatJsonSimple;
	}

	public void setWechatJsonSimple(String wechatJsonSimple) {
		this.wechatJsonSimple = wechatJsonSimple;
	}

	public String getSimulWechatJson() {
		return simulWechatJson;
	}

	public void setSimulWechatJson(String simulWechatJson) {
		this.simulWechatJson = simulWechatJson;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(updatable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
