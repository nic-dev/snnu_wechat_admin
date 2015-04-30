/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-10-13  Create	
 */
package snnu.wechat.commbiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import snnu.wechat.commbiz.dao.OperationLogDAO;
import snnu.wechat.commbiz.entity.OperationLog;


/**
 * 
 *
 */
@Service
@Transactional
public class OperationLogService  {
	@Autowired
	private OperationLogDAO operationLogDAO;
	public void save(OperationLog operationLog){
		operationLogDAO.save(operationLog);
	}
	public List<OperationLog> list(){
		return operationLogDAO.queryAll();
	}
	public List<OperationLog> listByType(Integer logType){
		String hqlStr=operationLogDAO.getFindAllHql()+" where type="+logType;
		return operationLogDAO.find(hqlStr);
	}

}
