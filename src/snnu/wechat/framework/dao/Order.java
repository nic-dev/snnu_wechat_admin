/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-11 Create	
 */
package snnu.wechat.framework.dao;

public class Order {

	public final static String DESC = "desc";
	public final static String ASC = "asc";
	public static final Order DEFAULT_ORDER = new Order("id", DESC);
	private String property;
	private String order;

	public Order(String property, String order) {
		this.property = property;
		this.order = order;
	}

	public Order(String property) {
		this(property, ASC);
	}

	public String getOrder() {
		return order;
	}

	public String getProperty() {
		return property;
	}
}
