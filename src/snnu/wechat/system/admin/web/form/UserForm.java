/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-22  Create	
 */
package snnu.wechat.system.admin.web.form;

import snnu.wechat.framework.core.util.StringUtils;
import snnu.wechat.framework.core.vo.BasePageForm;

/**
 * 
 *
 */
public class UserForm extends BasePageForm {
	private Long id;
	private String userName;
	private String password;
	private String[] roleIdArr;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleIds() {
		return StringUtils.join(this.roleIdArr, ',');
	}
	public void setRoleIds(String roleIds) {
		this.roleIdArr =roleIds.split(",");
	}
	public String[] getRoleIdArr() {
		return roleIdArr;
	}
	public void setRoleIdArr(String[] roleIdArr) {
		this.roleIdArr = roleIdArr;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
