/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-29  Create	
 */
package snnu.wechat.commbiz.web.form;

/**
 * 
 *
 */
public class CustomMenuForm {
	private Long id;
	private Long parentId;
	private String name;
	private Integer type;
	private Long rel;
	private String description;
	private String wechatJson;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getRel() {
		return rel;
	}
	public void setRel(Long rel) {
		this.rel = rel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWechatJson() {
		return wechatJson;
	}
	public void setWechatJson(String wechatJson) {
		this.wechatJson = wechatJson;
	}
	

}
