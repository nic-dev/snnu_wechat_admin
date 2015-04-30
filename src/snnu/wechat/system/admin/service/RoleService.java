/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-22  Create	
 */
package snnu.wechat.system.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import snnu.wechat.system.admin.dao.RoleDAO;
import snnu.wechat.system.admin.entity.Role;


/**
 * 
 *
 */
@Service
public class RoleService {
	@Autowired
	private RoleDAO roleDAO;
	public List<Role> listAll(){
		return roleDAO.queryAll();
	}
}
