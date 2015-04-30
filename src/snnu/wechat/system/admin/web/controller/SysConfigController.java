/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-28  Create	
 */
package snnu.wechat.system.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import snnu.wechat.commbiz.service.SysConfigService;


/**
 * 
 *
 */
@Controller
@RequestMapping("/admin/sys_config")
public class SysConfigController {
	@Autowired
	private SysConfigService sysConfigService;
	@RequestMapping("/list")
	public String list(ModelMap map){
	//	map.put("list",sysConfigService.getConfigList());
		map.put("list",sysConfigService.getWritableConfigList());
		return "sys_config/list";
	}
	@RequestMapping("update_sys_config_value.json")
	public String updateSysConfigValue(@RequestParam("id")Long id,@RequestParam("value")String value,ModelMap map){
		sysConfigService.updateSysConfigValue(id,value);
		
		return "json_update_sys_config_value";
	}
	
}
