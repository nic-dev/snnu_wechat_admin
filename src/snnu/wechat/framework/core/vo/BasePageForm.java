/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.framework.core.vo;

import snnu.wechat.framework.core.ToStringSupport;

public class BasePageForm extends ToStringSupport {

	// 页码
	protected int pageNum = 1;
	// 每页项数
	protected int pageSize = 10;
	// 排序字段
	protected String sortName="id";
	// 排序类型
	protected String sortMode = "desc";

	protected boolean selCount = true;

	protected String extraConditon;

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	public boolean isSelCount() {
		return selCount;
	}

	public void setSelCount(boolean selCount) {
		this.selCount = selCount;
	}

	public String getExtraConditon() {
		return extraConditon;
	}

	public void setExtraConditon(String extraConditon) {
		this.extraConditon = extraConditon;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0 && pageSize <= 200){
			this.pageSize = pageSize;
		}
	}

	public void setPageNum(int pageNum) {
		if (pageNum <= 0) {
			pageNum = 1;
		}
		this.pageNum = pageNum;
	}

	public int getFrom() {
		int from = (this.pageNum - 1) * getPageSize();
		return (from < 0 ? 0 : from);
	}

	public String getPageUrl() {
		StringBuffer buff = new StringBuffer("pageNum=" + this.getPageNum());
		buff.append("&pageSize=").append(this.getPageSize());
		if (this.getSortName() != null
				&& this.getSortName().trim().length() > 0) {
			buff.append("&sortName=");
			buff.append(this.getSortName());
			if (this.getSortMode() != null
					&& this.getSortMode().trim().length() > 0) {
				buff.append("&sortMode=");
				buff.append(this.getSortMode());
			} else {
				this.setSortMode("DESC");
				buff.append("&sortMode=");
				buff.append(this.getSortMode());
			}
		}
		return buff.toString();
	}
}
