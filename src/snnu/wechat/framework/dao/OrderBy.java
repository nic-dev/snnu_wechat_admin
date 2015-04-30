/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-11 Create	
 */
package snnu.wechat.framework.dao;

/**
 * 
 *
 */
public class OrderBy {
	public final static String DESC = "desc";

	public final static String ASC = "asc";

	private Order order;

	public OrderBy(Order order) {
		this.order = order;
	}
	
	public OrderBy(String property,String orderType){
		this.order=new Order(property,orderType);
	}

	public String buildOrder(String alias) {
		if (alias == null || alias.length() == 0) {
			return " order by "+ this.order.getProperty() + " " + this.order.getOrder();
		}
		return " order by " + alias + "." + this.order.getProperty() + " "
				+ this.order.getOrder();
	}
}
