/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-10  Create	
 */
package snnu.wechat.commbiz.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import snnu.wechat.commbiz.dao.MassDAO;
import snnu.wechat.commbiz.entity.Mass;
import snnu.wechat.commbiz.web.form.MassForm;
import snnu.wechat.framework.core.vo.PageModel;
import snnu.wechat.framework.dao.hql.Hql;
import snnu.wechat.framework.dao.hql.Parameter;


/**
 * 
 *
 */
@Service
public class MassService {
	@Autowired
	private MassDAO massDAO;

	public void save(Mass mass) {
		mass.setCreateTime(new Date());
		massDAO.save(mass);
	}

	public PageModel<Mass> list(MassForm form) {
		return new PageModel<Mass>(massDAO.queryPageByHql(
				massDAO.getFindAllHql(), form), form);
	}
	public boolean allowBroadcast(){
		String hqlStr=massDAO.getFindAllHql();
		Parameter parameter=new Parameter("createTime", ">=", new Date());
		Hql hql=new Hql(hqlStr,parameter);
		hql.compile();
		return massDAO.findCountByHql(hql)==0;
	}
}
