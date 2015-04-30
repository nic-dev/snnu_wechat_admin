/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.framework.core.vo;

import java.util.Collection;

import snnu.wechat.framework.core.ToStringSupport;



public class PageModel<T> extends ToStringSupport {

	private Page<T> page;
	private BasePageForm pageForm;

	public PageModel(Page<T> page, BasePageForm pageForm) {
		super();
		this.page = page;
		this.pageForm = pageForm;
	}

	public PageModel(Collection<T> list, BasePageForm pageForm) {
		super();
		this.page = new Page(list);
		this.pageForm = pageForm;
	}

	public Page<T> getPage() {
		return page;
	}

	public BasePageForm getPageForm() {
		return pageForm;
	}

	public int getPageNum() {
		int pageNum = page.getCount() / pageForm.getPageSize();
		if (page.getCount() % pageForm.getPageSize() != 0) {
			pageNum++;
		}
		return pageNum;
	}

}
