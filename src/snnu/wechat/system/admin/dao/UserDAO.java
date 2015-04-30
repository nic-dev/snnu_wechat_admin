/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 19, 2014  Create	
 */
package snnu.wechat.system.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import snnu.wechat.framework.core.util.CollectionUtils;
import snnu.wechat.framework.dao.BaseDAO;
import snnu.wechat.system.admin.entity.User;


@Repository
public class UserDAO extends BaseDAO<User> {

	public User findByUserName(String userName) {
		User user = new User();
		user.setUserName(userName);
		List<User> userList = getHibernateTemplate().findByExample(user);

		return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
	}

}
