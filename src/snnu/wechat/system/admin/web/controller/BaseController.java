/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-16  Create	
 */
package snnu.wechat.system.admin.web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import snnu.wechat.commbiz.common.WechatMediaType;
import snnu.wechat.commbiz.entity.OperationLog;
import snnu.wechat.commbiz.service.OperationLogService;
import snnu.wechat.system.admin.service.RoleService;



public class BaseController {
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private RoleService roleService;
	@ModelAttribute("mediaType")
	public void mediaType(ModelMap map)
	{
		map.put("mediaType", WechatMediaType.typeNameMap());
	}
	@ModelAttribute("roleList")
	public void roleList(ModelMap map)
	{
		map.put("roleList",roleService.listAll() );
	}
	public void saveLog(OperationLog operationLog){
		operationLogService.save(operationLog);
	}
}
