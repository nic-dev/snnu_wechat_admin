/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-11 Create	
 */
package snnu.wechat.framework.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import snnu.wechat.framework.dao.hql.Hql;



public class HibernateQueryUniqueCallback extends HibernateQueryCallback {

	private int from;

	public HibernateQueryUniqueCallback(Hql hql, int from) {
		super(hql);
		this.from = from;
	}

	public HibernateQueryUniqueCallback(Hql hql) {
		this(hql, 0);
	}

	public Object doInHibernate(Query query) throws HibernateException,
			SQLException {
		if (this.from > 0) {
			query.setFirstResult(this.from);
		}

		return getByList(query);
	}

	protected Object getByList(Query query) {
		List list = query.list();
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
