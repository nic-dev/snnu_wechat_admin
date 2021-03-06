/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 19, 2014  Create	
 */
package snnu.wechat.commbiz.web.form;

import org.hibernate.validator.constraints.Length;

import snnu.wechat.framework.core.vo.BasePageForm;



public class MessageForm extends BasePageForm {

	private Long id;
	private Long parentId;
	private String mediaId;
	@Length(min = 2, max = 30, message = "标题长度应在2-30之间")
	private String title;
	private String author;
	private String thumbThumbMediaId;
	@Length(min = 1, message = "封面不能为空")
	private String cover;
	private String summary;
	private Integer isCoverInContent;

	@Length(min = 1, max = 10000, message = "正文长度不能为空")
	private String content;
	private String link;
	private Integer type;
	private String accessUrl;
	private String subs;
	private String wechatJson;
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
		return thumbThumbMediaId;
	}

	public void setThumbMediaId(String thumbThumbMediaId) {
		this.thumbThumbMediaId = thumbThumbMediaId;
	}

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

	public void setType(Integer type) {
		this.type = type;
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

}
