/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-10-11  Create	
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
public class WechatSimulMediaListJson {
	private Integer type;
	@JsonProperty("file_item")
	private List<FileItem> fileItemList;
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	public static class FileItem{
		@JsonProperty("file_id")
		private String fileId;
		private String name;
		@JsonProperty("update_time")
		private Long updateTime;
		private Integer type;
		private String size;
		@JsonProperty("play_length")
		private Long playLength;
		public String getFileId() {
			return fileId;
		}
		public void setFileId(String fileId) {
			this.fileId = fileId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Long getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Long updateTime) {
			this.updateTime = updateTime;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public Long getPlayLength() {
			return playLength;
		}
		public void setPlayLength(Long playLength) {
			this.playLength = playLength;
		}
		
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<FileItem> getFileItemList() {
		return fileItemList;
	}
	public void setFileItemList(List<FileItem> fileItemList) {
		this.fileItemList = fileItemList;
	}
}
