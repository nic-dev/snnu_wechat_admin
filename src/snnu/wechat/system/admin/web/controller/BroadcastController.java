/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-9  Create	
 */
package snnu.wechat.system.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import snnu.wechat.commbiz.common.WechatErrCode;
import snnu.wechat.commbiz.entity.Mass;
import snnu.wechat.commbiz.model.WechatResponseJson;
import snnu.wechat.commbiz.service.MassService;
import snnu.wechat.commbiz.service.WechatSysClientService;
import snnu.wechat.commbiz.web.form.MassForm;
import snnu.wechat.framework.core.util.BeanUtils;
import snnu.wechat.system.admin.util.WebUtils;


/**
 * 
 *
 */
@Controller
@RequestMapping("/admin/broadcast")
public class BroadcastController extends BaseController {
	@Autowired
	private MassService massService;
	@Autowired
	private WechatSysClientService wechatSysClientService;
	@RequestMapping("/index")
	public String index()
	{
		return "broadcast/index";
	}
	@RequestMapping("/list")
	public String list(MassForm form,ModelMap map)
	{
		map.put("pageModel", massService.list(form));
		return "broadcast/list";
	}
	@RequestMapping("/allow.json")
	public String allow(ModelMap map)
	{
		if(!massService.allowBroadcast()){
			WebUtils.fillErrorMap(map, "群发次数已达到系统限制");
		}
		return "json_allow";
	}
	@RequestMapping(value="/broadcast_by_group.json",method=RequestMethod.POST)
	public String byGroup(MassForm form,ModelMap map){
		Mass mass=BeanUtils.copyTo(form, Mass.class);
		
		WechatResponseJson wechatResponseJson= wechatSysClientService.broadcastByGroup(form.getWechatJson());
		if(wechatResponseJson.getErrCode()!=WechatErrCode.OK){
			WebUtils.fillErrorMap(map, wechatResponseJson.getErrMsg());
			
		}
		else{
			mass.setMsgId(wechatResponseJson.getMsgId());
			massService.save(mass);
		}
		return "json_broadcast_by_group";
	}
	@RequestMapping(value="/broadcast_by_open_id.json",method=RequestMethod.POST)
	public String byOpenId(MassForm form,ModelMap map){
		
		WechatResponseJson wechatResponseJson= wechatSysClientService.broadcastByOpenId(form.getWechatJson());
		if(wechatResponseJson.getErrCode()!=WechatErrCode.OK){
			WebUtils.fillErrorMap(map, wechatResponseJson.getErrMsg());
			
		}
		return "json_broadcast_by_open_id";
	}
}
