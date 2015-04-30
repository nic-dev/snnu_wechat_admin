/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-16  Create	
 */
package snnu.wechat.commbiz.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import snnu.wechat.commbiz.model.WechatArticleXml;
import snnu.wechat.framework.core.ToStringSupport;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;

/**
 * 
 *
 */
@XStreamAlias("xml")
public class WechatHttpXml extends ToStringSupport implements Cloneable {
	// 公共
	@XStreamAlias("ToUserName")
	private String toUserName;
	@XStreamAlias("FromUserName")
	private String fromUserName;
	@XStreamAlias("CreateTime")
	private Long createTime;
	@XStreamAlias("MsgType")
	private String msgType;
	@XStreamAlias("MsgId")
	private Long msgId;
	@XStreamAlias("MediaId")
	private String mediaId;
	// 文本
	@XStreamAlias("Content")
	private String content;
	// 图片
	@XStreamAlias("PicUrl")
	private String picUrl;
	// 语音
	@XStreamAlias("Format")
	private String format;
	// 视频
	@XStreamAlias("ThumbMediaId")
	private String thumbMediaId;
	// TODO 地理位置
	// 链接
	@XStreamAlias("Title")
	private String title;
	@XStreamAlias("Description")
	private String description;
	@XStreamAlias("Url")
	private String url;
	// 事件
	@XStreamAlias("Event")
	private String event;

	@XStreamAlias("EventKey")
	private String eventKey;

	// 群发
	@XStreamAlias("Status")
	private String status;
	@XStreamAlias("TotalCount")
	private Integer totalCount;
	@XStreamAlias("FilterCount")
	private Integer filterCount;
	@XStreamAlias("SentCount")
	private Integer sentCount;
	@XStreamAlias("ErrorCount")
	private Integer errorCount;
	// 图片
	@XStreamAlias("Image")
	private Image image;
	// 语音
	@XStreamAlias("Voice")
	private Voice voice;
	// 视频
	@XStreamAlias("Video")
	private Video video;
	// 回复多图文
	@XStreamAlias("ArticleCount")
	private Integer articleCount;
	@XStreamAlias("Articles")
	private List<WechatArticleXml> articleList;
	// 音乐
	@XStreamAlias("Music")
	private Music music;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getFilterCount() {
		return filterCount;
	}

	public void setFilterCount(Integer filterCount) {
		this.filterCount = filterCount;
	}

	public Integer getSentCount() {
		return sentCount;
	}

	public void setSentCount(Integer sentCount) {
		this.sentCount = sentCount;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public List<WechatArticleXml> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<WechatArticleXml> articleList) {
		this.articleList = articleList;
	}

	public void addArticle(WechatArticleXml wechatArticleXml) {
		if (this.articleList == null) {
			this.articleList = new ArrayList<WechatArticleXml>();
		}
		this.articleList.add(wechatArticleXml);
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public static class Image {
		@XStreamAlias("MediaId")
		private String mediaId;

		public Image(String mediaId) {
			this.mediaId = mediaId;
		}

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
	}

	public static class Voice {
		@XStreamAlias("MediaId")
		private String mediaId;

		public Voice(String mediaId) {
			this.mediaId = mediaId;
		}

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
	}

	public static class Video {
		@XStreamAlias("MediaId")
		private String mediaId;

		public Video(String mediaId) {
			this.mediaId = mediaId;
		}

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
	}

	public static class Music extends ToStringSupport {
		@XStreamAlias("MusicUrl")
		private String musicUrl;
		@XStreamAlias("HQMusicUrl")
		private String hqMusicUrl;
		@XStreamAlias("Title")
		private String title;
		@XStreamAlias("Description")
		private String decription;

		// @XStreamAlias("ThumbMediaId")
		// private String thumbMediaId;

		public Music(String title, String musicUrl) {
			this.title = title;
			this.musicUrl = musicUrl;
			this.hqMusicUrl = musicUrl;
			this.decription = "";

		}

		public String getMusicUrl() {
			return musicUrl;
		}

		public void setMusicUrl(String musicUrl) {
			this.musicUrl = musicUrl;
		}

		public String getHqMusicUrl() {
			return hqMusicUrl;
		}

		public void setHqMusicUrl(String hqMusicUrl) {
			this.hqMusicUrl = hqMusicUrl;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDecription() {
			return decription;
		}

		public void setDecription(String decription) {
			this.decription = decription;
		}
		/*
		 * public String getThumbMediaId() { return thumbMediaId; }
		 * 
		 * public void setThumbMediaId(String thumbMediaId) { this.thumbMediaId
		 * = thumbMediaId; }
		 */
	}



}
