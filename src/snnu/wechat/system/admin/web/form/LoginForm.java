/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 20, 2014  Create	
 */
package snnu.wechat.system.admin.web.form;

import org.hibernate.validator.constraints.Length;

public class LoginForm {
	@Length(min = 1, max = 50, message = "用户名不能为空")
	private String userName;
	@Length(min = 1, max = 200, message = "密码不能为空")
	private String password;

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
}
