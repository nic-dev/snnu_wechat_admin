/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-11 Create	
 */
package snnu.wechat.framework.dao.hql;

/**
 * 
 *
 */
public class Parameter {
	private String name;
	private String opt;
	private Object value;

	public Parameter(String name,String opt, Object value) {
		this.name = name;
		this.opt=opt;
		this.value = value;
	}
	public Parameter(String name, Object value) {
		this(name,"=",value);
	}
	public String getName() {
		return name;
	}

	public String getOpt() {
		return opt;
	}

	public Object getValue() {
		return value;
	}
}
