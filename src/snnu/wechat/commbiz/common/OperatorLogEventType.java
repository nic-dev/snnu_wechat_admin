/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-10-13  Create	
 */
package snnu.wechat.commbiz.common;

/**
 * 
 *
 */
public enum OperatorLogEventType {
	LOG_IN(1,"用户登录"),PUBLISH(2,"菜单发布"),PULL_MSGS(3,"素材同步"),LOG_OUT(4,"用户登出");
	private OperatorLogEventType(Integer id,String desc){
		this.id=id;
		this.desc=desc;
	}
	private Integer id;
	private String desc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
