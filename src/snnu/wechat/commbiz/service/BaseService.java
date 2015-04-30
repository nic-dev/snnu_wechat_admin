/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-27  Create	
 */
package snnu.wechat.commbiz.service;

import org.springframework.beans.factory.annotation.Autowired;

import snnu.wechat.commbiz.common.ServiceConfigBean;
import snnu.wechat.commbiz.entity.OperationLog;


/**
 * 
 *
 */
public class BaseService {
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private ServiceConfigBean serviceConfigBean;

	public ServiceConfigBean getServiceConfigBean() {
		return serviceConfigBean;
	}

	public void setServiceConfigBean(ServiceConfigBean serviceConfigBean) {
		this.serviceConfigBean = serviceConfigBean;
	}
	public void saveLog(OperationLog operatorLog){
		operationLogService.save(operatorLog);
	}
}
