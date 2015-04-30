/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 19, 2014  Create	
 */
package snnu.wechat.commbiz.entity;

/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-16  Create	
 */
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "simul_media")
public class SimulMedia {
	private Long id;
	private String mediaId;
	private String simulMediaId;
	private String name;
	private String createdName;
	private String path;
	private Long size;
	private Long playLength;
	private Integer type;
	private Integer customMenuMark;
	private Date createTime;
	private Date lastUpdateTime;

	@Id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getSimulMediaId() {
		return simulMediaId;
	}

	public void setSimulMediaId(String simulMediaId) {
		this.simulMediaId = simulMediaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getPlayLength() {
		return playLength;
	}

	public void setPlayLength(Long playLength) {
		this.playLength = playLength;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCustomMenuMark() {
		return customMenuMark;
	}

	public void setCustomMenuMark(Integer customMenuMark) {
		this.customMenuMark = customMenuMark;
	}

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
