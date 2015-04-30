/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-11 Create	
 */
package snnu.wechat.framework.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import snnu.wechat.framework.dao.hql.Hql;




public class HibernateQueryListCallback extends HibernateQueryCallback {

	private final static int DEFAULT_PAGE_SIZE = 10;
	private int from;
	private int offset;

	public HibernateQueryListCallback(Hql hql, int from, int offset) {
		super(hql);
		this.from = from;
		this.offset = offset;
	}

	public HibernateQueryListCallback(Hql hql) {
		this(hql, -1, -1);
	}

	
	public Object doInHibernate(Query query) throws HibernateException,
			SQLException {
		if (this.from >= 0) {
			query.setFirstResult(this.from);
		}
		if (this.offset > 0) {
			query.setMaxResults(this.offset);
		}
		else if (this.offset == 0) {
			query.setMaxResults(DEFAULT_PAGE_SIZE);
		}
		return query.list();
	}
}
