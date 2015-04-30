/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.framework.core.vo;

import java.io.Serializable;
import java.util.Collection;

import snnu.wechat.framework.core.ToStringSupport;



public class Page<T> extends ToStringSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	private int count;

	private Collection<T> list;

	public Page(int count, Collection<T> list) {
		this.count = count;
		this.list = list;
	}

	public Page(Collection<T> list) {
		this(list.size(), list);
	}

	public Page() {
	}

	public int getCount() {
		return count;
	}

	public Collection<T> getList() {
		return list;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setList(Collection<T> list) {
		this.list = list;
	}
}
