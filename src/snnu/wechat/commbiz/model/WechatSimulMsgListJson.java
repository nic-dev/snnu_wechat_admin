/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-10-9  Create	
 */
package snnu.wechat.commbiz.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)

public class WechatSimulMsgListJson {
	@JsonProperty("item")
	private List<WechatSimulMsg> wechatSimulMsgList;
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)

	public static class WechatSimulMsg {
		private String seq;
		@JsonProperty("app_id")
		private String appId;
		@JsonProperty("file_id")
		private String fileId;
		private String title;
		private String cover;
		@JsonProperty("create_time")
		private Long createTime;
		private String digest;
		@JsonProperty("img_url")
		private String imgUrl;
		@JsonProperty("content_url")
		private String contentUrl;
		private String author;
		@JsonProperty("show_cover_pic")
		private Integer showCoverPic;
		@JsonProperty("multi_item")
		private List<MultiItem> multiItemList;

		public String getSeq() {
			return seq;
		}

		public void setSeq(String seq) {
			this.seq = seq;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getFileId() {
			return fileId;
		}

		public void setFileId(String fileId) {
			this.fileId = fileId;
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

		public Long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Long createTime) {
			this.createTime = createTime;
		}

		public String getDigest() {
			return digest;
		}

		public void setDigest(String digest) {
			this.digest = digest;
		}

		public String getImgUrl() {
			return imgUrl;
		}

		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}

		public String getContentUrl() {
			return contentUrl;
		}

		public void setContentUrl(String contentUrl) {
			this.contentUrl = contentUrl;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public Integer getShowCoverPic() {
			return showCoverPic;
		}

		public void setShowCoverPic(Integer showCoverPic) {
			this.showCoverPic = showCoverPic;
		}

		public List<MultiItem> getMultiItemList() {
			return multiItemList;
		}

		public void setMultiItemList(List<MultiItem> multiItemList) {
			this.multiItemList = multiItemList;
		}
	}
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)

	public static class MultiItem{
		private String seq;
		private String title;
		@JsonProperty("app_id")
		private String appId;
		private String cover;
		@JsonProperty("content_url")
		private String contentUrl;
		@JsonProperty("file_id")
		private String fileId;
		@JsonProperty("source_url")
		private String sourceUrl;
		private String author;
		@JsonProperty("show_cover_pic")
		private Integer showCoverPic;
		public String getSeq() {
			return seq;
		}
		public void setSeq(String seq) {
			this.seq = seq;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getAppId() {
			return appId;
		}
		public void setAppId(String appId) {
			this.appId = appId;
		}
		public String getCover() {
			return cover;
		}
		public void setCover(String cover) {
			this.cover = cover;
		}
		public String getContentUrl() {
			return contentUrl;
		}
		public void setContentUrl(String contentUrl) {
			this.contentUrl = contentUrl;
		}
		public String getFileId() {
			return fileId;
		}
		public void setFileId(String fileId) {
			this.fileId = fileId;
		}
		public String getSourceUrl() {
			return sourceUrl;
		}
		public void setSourceUrl(String sourceUrl) {
			this.sourceUrl = sourceUrl;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public Integer getShowCoverPic() {
			return showCoverPic;
		}
		public void setShowCoverPic(Integer showCoverPic) {
			this.showCoverPic = showCoverPic;
		}
	}
	public List<WechatSimulMsg> getWechatSimulMsgList() {
		return wechatSimulMsgList;
	}
	public void setWechatSimulMsgList(List<WechatSimulMsg> wechatSimulMsgList) {
		this.wechatSimulMsgList = wechatSimulMsgList;
	}
	
}
