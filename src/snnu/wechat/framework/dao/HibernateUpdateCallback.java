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



public class HibernateUpdateCallback extends HibernateQueryCallback {

	

	public HibernateUpdateCallback(Hql hql) {
		super(hql);
	}

	
	public Object doInHibernate(Query query) throws HibernateException,
			SQLException {
	
		return query.executeUpdate();
	}
}
