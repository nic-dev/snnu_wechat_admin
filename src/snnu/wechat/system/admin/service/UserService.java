/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 19, 2014  Create	
 */
package snnu.wechat.system.admin.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import snnu.wechat.commbiz.service.BaseService;
import snnu.wechat.framework.core.vo.BasePageForm;
import snnu.wechat.framework.core.vo.Page;
import snnu.wechat.system.admin.dao.RoleDAO;
import snnu.wechat.system.admin.dao.UserDAO;
import snnu.wechat.system.admin.entity.User;
import snnu.wechat.system.admin.security.PasswordHelper;



@Service
public class UserService extends BaseService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private PasswordHelper passwordHelper;
	
/*	public User checkLogin(String userName, String password) {
		User user = userDAO.findByUserName(userName);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}*/
	public Page<User> list(BasePageForm form){
		return userDAO.queryPageByHql(userDAO.getFindAllHql(), form);
	}
	public User findById(Long id){
		return userDAO.findById(id);
	}
	public User findByUserName(String userName){
		return userDAO.findByUserName(userName);
	}
	public Set<String> findRoles(String userName){
		User user=userDAO.findByUserName(userName);
		
		return roleDAO.findRolesById(user.getRoleIdsSet());
	}
	public void save(User user){
		passwordHelper.encryptPassword(user);
		userDAO.save(user,false);
	}
	public void delete(Long id){
		userDAO.deleteById(id);
	}

}
