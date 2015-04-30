/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-5  Create	
 */
package snnu.wechat.commbiz.model;

import java.util.ArrayList;
import java.util.List;

import snnu.wechat.framework.core.ToStringSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WechatMessageListJson extends ToStringSupport {
	private List<WechatMessageJson> wechatMessageJsonList;
	@JsonProperty("articles")
	public List<WechatMessageJson> getWechatMessageJsonList() {
		return wechatMessageJsonList;
	}
	public void setWechatMessageJsonList(
			List<WechatMessageJson> wechatMessageJsonList) {
		this.wechatMessageJsonList = wechatMessageJsonList;
	}
	public void addWechatMessageJson(WechatMessageJson wechatMessageJson){
		if(this.wechatMessageJsonList==null){
			wechatMessageJsonList=new ArrayList<WechatMessageListJson.WechatMessageJson>();
		}
		wechatMessageJsonList.add(wechatMessageJson);
	}
	public static class WechatMessageJson extends ToStringSupport{
	
		private Long id;
		private Integer order;
		private String title;
		@JsonProperty("media_id")
		private String mediaId;
		private String author;
		@JsonProperty("thumb_media_id")
		private String thumbMediaId;
		@JsonProperty("picurl")
		private String cover;
		@JsonProperty("url")
		private String accessUrl;
		@JsonProperty("digest")
		private String summary;
		@JsonProperty("content")
		private String content;
		@JsonProperty("content_source_url")
		private String link;
		@JsonProperty("show_cover_pic")
		private String isCoverInContent;
		@JsonProperty("mid")
		private Long mid;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Integer getOrder() {
			return order;
		}
		public void setOrder(Integer order) {
			this.order = order;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getMediaId() {
			return mediaId;
		}
		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
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
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
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
		public String getIsCoverInContent() {
			return isCoverInContent;
		}
		public void setIsCoverInContent(String isCoverInContent) {
			this.isCoverInContent = isCoverInContent;
		}
		public Long getMid() {
			return mid;
		}
		public void setMid(Long mid) {
			this.mid = mid;
		}
	}
	
}
