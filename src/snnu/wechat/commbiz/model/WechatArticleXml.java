/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-1  Create	
 */
package snnu.wechat.commbiz.model;

import snnu.wechat.framework.core.ToStringSupport;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 *
 */
@XStreamAlias("item")
public class WechatArticleXml extends ToStringSupport {
	@XStreamAlias("Title")
	private String title;
	@XStreamAlias("Description")
	private String description;
	@XStreamAlias("PicUrl")
	private String cover;
	@XStreamAlias("Url")
	private String accessUrl;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getAccessUrl() {
		return accessUrl;
	}
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

}
