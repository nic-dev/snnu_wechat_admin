/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-28  Create	
 */
package snnu.wechat.system.admin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import snnu.wechat.commbiz.common.OperatorLogEventType;
import snnu.wechat.commbiz.common.SysConstants;
import snnu.wechat.commbiz.common.WechatErrCode;
import snnu.wechat.commbiz.entity.CustomMenu;
import snnu.wechat.commbiz.entity.OperationLog;
import snnu.wechat.commbiz.model.WechatResponseJson;
import snnu.wechat.commbiz.service.CustomMenuService;
import snnu.wechat.commbiz.service.WechatSysClientService;
import snnu.wechat.commbiz.web.form.CustomMenuForm;
import snnu.wechat.framework.core.util.BeanUtils;
import snnu.wechat.framework.core.util.StringUtils;
import snnu.wechat.framework.log.Log;
import snnu.wechat.system.admin.util.WebUtils;


/**
 * 
 *
 */
@Controller
@RequestMapping("/admin/custom_menu")
public class CustomMenuController extends BaseController {
	@Autowired
	private CustomMenuService customMenuService;
	@Autowired
	private WechatSysClientService wechatSysClientService;

	@RequestMapping("/list")
	public String list(ModelMap map) {
		map.put("wechatJson", customMenuService.listCustomMenu());
		return "custom_menu/list";
	}
	@RequestMapping("/item.json")
	public String item(@RequestParam("id")Long id, ModelMap map) {
		map.put("item", customMenuService.findById(id));
		return "json_item";
	}
	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
	public String add(CustomMenuForm form, ModelMap map) {
		// 数据格式校验暂只放在客户端
		Log.logInfo("wechatJson=" + form.getWechatJson());
		if (StringUtils.isEmpty(form.getWechatJson())) {
			WebUtils.fillErrorMap(map, "创建菜单失败");
		} else {
			CustomMenu menu = BeanUtils.copyTo(form, CustomMenu.class);
			customMenuService.saveWechatJson(form.getWechatJson());
			customMenuService.save(menu);
			form.setId(menu.getId());
		}
		return "json_add";
	}
	@RequestMapping(value = "/sort.json", method = RequestMethod.POST)
	public String sort(CustomMenuForm form, ModelMap map) {
		// 数据格式校验暂只放在客户端
		Log.logInfo("wechatJson=" + form.getWechatJson());
		if (StringUtils.isEmpty(form.getWechatJson())) {
			WebUtils.fillErrorMap(map, "菜单排序失败");
		} else {
			customMenuService.saveWechatJson(form.getWechatJson());
		}
		return "json_sort";
	}
	@RequestMapping(value = "/update_name.json", method = RequestMethod.POST)
	public String updateName(CustomMenuForm form, ModelMap map) {
		Log.logInfo("wechatJson=" + form.getWechatJson());
		if (StringUtils.isEmpty(form.getWechatJson())) {
			WebUtils.fillErrorMap(map, "编辑菜单失败");
		} else {
			customMenuService.saveWechatJson(form.getWechatJson());
			customMenuService.updateName(form.getId(), form.getName());
		}
		return "json_update_name";
	}
	@RequestMapping(value = "/update_wechat_json.json", method = RequestMethod.POST)
	public String updateWechatJson(CustomMenuForm form, ModelMap map) {
		Log.logInfo("wechatJson=" + form.getWechatJson());
		if (StringUtils.isEmpty(form.getWechatJson())) {
			WebUtils.fillErrorMap(map, "编辑菜单失败");
		} else {
			customMenuService.saveWechatJson(form.getWechatJson());
		}
		return "json_update_wechat_json";
	}
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public String delete(CustomMenuForm form, ModelMap map) {
		Log.logInfo("wechatJson=" + form.getWechatJson());
		if (StringUtils.isEmpty(form.getWechatJson())) {
			WebUtils.fillErrorMap(map, "编辑菜单失败");
		} else {
			customMenuService.saveWechatJson(form.getWechatJson());
			customMenuService.delete(form.getId());
		}
		return "json_delete";
	}
	@RequestMapping(value = "/publish.json", method = RequestMethod.POST)
	public String publish(CustomMenuForm form,HttpServletRequest request, ModelMap map) {
		Log.logInfo("wechatJson=" + form.getWechatJson());
		if (StringUtils.isEmpty(form.getWechatJson())) {
			WebUtils.fillErrorMap(map, "编辑菜单失败");
		} else {
			
			WechatResponseJson wechatResponseJson= wechatSysClientService.publishCustomMenu(form.getWechatJson());
			if(wechatResponseJson.getErrCode()!=WechatErrCode.OK){
				WebUtils.fillErrorMap(map, wechatResponseJson.getErrMsg());
			}
			saveLog(new OperationLog( request.getAttribute(SysConstants.CURRENT_USER).toString(),OperatorLogEventType.PUBLISH.getId(),OperatorLogEventType.PUBLISH.getDesc()+":"+form.getWechatJson()));
		}
		return "json_publish";
	}
}
