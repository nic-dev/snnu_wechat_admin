/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-21  Create	
 */
package snnu.wechat.system.admin.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import snnu.wechat.framework.dao.BaseDAO;
import snnu.wechat.system.admin.entity.Role;


/**
 * 
 *
 */
@Repository
public class RoleDAO extends BaseDAO<Role> {

	/**
	 * @param roleIdsSet
	 * @return
	 */
	public Set<String> findRolesById(Set<Long> roleIdsSet) {
		Set<String> result=new HashSet<String>();
		Role role=null;
		for (Long id : roleIdsSet) {
			role=findById(id);
			if(role!=null){
				result.add(role.getRole());
			}
		}
		return result;
	}

}
