/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 19, 2014  Create	
 */
package snnu.wechat.system.admin.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

@Entity
@Table(name = "user")
public class User {
	public static final String ROLE_ID_DELIM=",";
	private Long id;
	private String userName;
	private String password;
	private String salt;
	private String roleIds;
	private String description;
	private boolean locked;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	public  Set<Long> getRoleIdsSet(){
		if(StringUtils.isEmpty(this.roleIds)){
			return null;
		}
		String [] roleIdStrs=this.roleIds.split(ROLE_ID_DELIM);
		Set<Long> roleIdsSet=new HashSet<Long>();
		for (String roleIdStr : roleIdStrs) {
			roleIdsSet.add(new Long(roleIdStr));
		}
		return roleIdsSet;
	}
	@Transient
	public void setRoleIdsSet(Set<Long> roleIdsSet){
		StringBuilder sb=new StringBuilder();
		for (Long roleId : roleIdsSet) {
			sb.append(roleId).append(ROLE_ID_DELIM);
		}
		if(sb.length()>0){
			this.roleIds=sb.substring(0,sb.length()-1);
		}
	}
	@Override
	public String toString(){
		return this.userName;
	}
	
}
