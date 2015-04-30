/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-11 Create	
 */
package snnu.wechat.framework.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import snnu.wechat.framework.dao.hql.Hql;



 
public abstract class HibernateQueryCallback implements HibernateCallback {

	private Hql hql;

	protected Hql getHql() {
		return hql;
	}

	public HibernateQueryCallback(Hql hql) {
		this.hql = hql;
	}

	
	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		Query query = HibernateUtils.createQuery(session, hql);
		return doInHibernate(query);
	}

	public abstract Object doInHibernate(Query query)
			throws HibernateException, SQLException;
}
