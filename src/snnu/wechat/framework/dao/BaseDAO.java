/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.framework.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import snnu.wechat.framework.core.util.CollectionUtils;
import snnu.wechat.framework.core.util.StringUtils;
import snnu.wechat.framework.core.vo.BasePageForm;
import snnu.wechat.framework.core.vo.Page;
import snnu.wechat.framework.dao.hql.Hql;
import snnu.wechat.framework.log.Log;


public class BaseDAO<T> extends HibernateDaoSupport {
	protected Class getTargetClass() {
		Type genType = getClass().getGenericSuperclass();

		if (genType instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) genType)
					.getActualTypeArguments();

			if (params != null) {
				return (Class) params[0];
			}
		}
		return null;
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void delete(T t) {
		getHibernateTemplate().delete(t);
		Log.logKey("Delete", "Data[" + t + "]");
	}

	public void deleteWithCheck(T t) throws ConstraintViolationServiceException {
		try {
			delete(t);
			flush();
			Log.logKey("Delete", "Data[" + t + "]");
		} catch (DataIntegrityViolationException e) {
			throw new ConstraintViolationServiceException(e);
		}
	}

	public void deleteById(final Serializable id) {
		T t = findById(id);
		if (t != null) {
			getHibernateTemplate().delete(t);
			Log.logKey("Delete", "Data[type=" + getTargetClass().getName()
					+ ",id=" + id + "]");
		}
	}

	public String getFindAllHql() {
		return "from " + getTargetClass().getName();
	}

	public void save(T t) {
		save(t, true);

	}

	public void save(T t, boolean force) {
		if (force) {
			getHibernateTemplate().save(t);
			Log.logKey("Save", "Data[" + t + "]");
		} else {
			getHibernateTemplate().saveOrUpdate(t);
			Log.logKey("SaveOrUpdate", "Data[" + t + "]");
		}
	}

	public void saveWithCheck(T t) throws ConstraintViolationServiceException {
		try {
			save(t);
			flush();
		} catch (DataIntegrityViolationException e) {
			throw new ConstraintViolationServiceException(e);
		}
	}

	public void update(T t) {
		getHibernateTemplate().update(t);
		Log.logKey("Update", "Data[" + t + "]");
	}

	public void updateWithCheck(T t) throws ConstraintViolationServiceException {
		try {
			update(t);
			flush();
		} catch (DataIntegrityViolationException e) {
			throw new ConstraintViolationServiceException(e);
		}
	}

	public T findById(Serializable id) {
		if (id == null) {
			return null;
		}
		return (T) getHibernateTemplate().get(getTargetClass(), id);
	}

	public List<T> findByExample(T t) {
		return getHibernateTemplate().findByExample(t);
	}
	public T findOneByExample(T t){
		List<T> list=findByExample(t);
		return CollectionUtils.isEmpty(list)?null:list.get(list.size()-1);
	}

	public int findAllCount() {
		return findCountByHql(new Hql(getFindAllHql()));
	}

	public List<T> find(String hql) {
		return getHibernateTemplate().find(hql);
	}

	public List<T> queryByIds(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return Collections.EMPTY_LIST;
		}
		String hql = getFindAllHql() + "  where id in (" + ids + ")";
		return queryListByHql(hql);
	}

	public List<T> queryAll() {
		return getHibernateTemplate().find(getFindAllHql());
	}

	public Page<T> queryAll(int from, int offset) {
		return queryAll(from, offset, Order.DEFAULT_ORDER);
	}

	public Page<T> queryAll(int from, int offset, Order order) {
		String hqlStr = getFindAllHql() + new OrderBy(order).buildOrder(null);
		return queryPageByHql(hqlStr, from, offset);
	}

	public Page queryPageByHql(String hqlStr, BasePageForm form) {
		return queryPageByHql(new Hql(hqlStr), form);
	}

	public void executeUpdate(String hqlStr) {
		executeUpdate(new Hql(hqlStr));
	}

	public void executeUpdate(Hql hql) {
		getHibernateTemplate().execute(new HibernateUpdateCallback(hql));
	}

	protected T findUniqueByHql(Hql hql) {
		return (T)getHibernateTemplate().execute(
				new HibernateQueryUniqueCallback(hql));
	}

	protected List queryListByHql(Hql hql, int from, int offset) {
		return getHibernateTemplate().executeFind(
				new HibernateQueryListCallback(hql, from, offset));
	}

	protected List queryListByHql(String hqlstr, int from, int offset) {
		return queryListByHql(new Hql(hqlstr), from, offset);
	}

	protected List queryListByHql(Hql hql) {

		return getHibernateTemplate().executeFind(
				new HibernateQueryListCallback(hql));
	}

	protected List queryListByHql(String hqlstr) {
		return queryListByHql(new Hql(hqlstr));
	}

	protected Page queryPageByHql(Hql hql, int from, int offset) {
		return new Page(findCountByHql(hql), queryListByHql(hql, from, offset));
	}

	protected Page queryPageByHql(Hql hql, BasePageForm form) {
		Page result = null;
		int from = form.getFrom();
		int offset = form.getPageSize();
		String hqlStr = null;
		Hql newHql = hql;
		OrderBy orderBy = new OrderBy(form.getSortName(), form.getSortMode());
		hqlStr = hql.getHql();
		newHql = new Hql(hqlStr + orderBy.buildOrder(null));
		if (form.getExtraConditon() != null) {

			hqlStr = newHql.getHql();

			if (hqlStr.indexOf("where") > 0) {

				hqlStr = hqlStr.replace("where",
						"where " + form.getExtraConditon() + " and ");
			} else {
				hqlStr += " where " + form.getExtraConditon();
			}
			newHql = new Hql(hqlStr, hql.getParameterList());
		}
		if (form.isSelCount()) {
			result = new Page(findCountByHql(newHql), queryListByHql(newHql,
					from, offset));
		} else {
			result = new Page(-1, queryListByHql(newHql, from, offset));
		}
		return result;
	}

	protected Page queryPageByHql(String hqlstr, int from, int offset) {
		return queryPageByHql(new Hql(hqlstr), from, offset);
	}

	public T findUniqueByHql(String hqlstr) {
		return findUniqueByHql(new Hql(hqlstr));
	}

	public int findCountByHql(Hql hql) {
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateQueryUniqueCallback(new Hql("select count(*) "
						+ hql.getHql(), hql.getParameterList())));
		return count == null ? 0 : count.intValue();
	}

	protected int findCountByHql(String hqlstr) {
		return findCountByHql(new Hql(hqlstr));
	}

	public static void main(String argd[]) {

	}
}
