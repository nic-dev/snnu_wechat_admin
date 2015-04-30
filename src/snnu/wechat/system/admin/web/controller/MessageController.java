/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.system.admin.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import snnu.wechat.commbiz.common.WechatErrCode;
import snnu.wechat.commbiz.entity.Message;
import snnu.wechat.commbiz.model.WechatResponseJson;
import snnu.wechat.commbiz.service.MessageService;
import snnu.wechat.commbiz.service.WechatSysClientService;
import snnu.wechat.commbiz.web.form.MessageForm;
import snnu.wechat.framework.core.util.BeanUtils;
import snnu.wechat.framework.core.util.StringUtils;
import snnu.wechat.framework.core.vo.BasePageForm;
import snnu.wechat.framework.core.vo.PageModel;
import snnu.wechat.framework.log.Log;
import snnu.wechat.system.admin.util.WebUtils;


@Controller
@RequestMapping("/admin/message")
public class MessageController extends BaseController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private WechatSysClientService wechatSysClientService;

//	@RequiresRoles("admin")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(BasePageForm form, ModelMap map) {

		map.put("pageModel", new PageModel<Message>(messageService.list(form),
				form));
		return "message/list";
	}

	@RequestMapping(value = "/list_embedded")
	public String listInIframe(MessageForm form, ModelMap map) {

		map.put("pageModel", new PageModel<Message>(messageService.list(form),
				form));
		return "message/list_in_iframe";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String multiAdd(@RequestParam("type") Integer type) {
		if (Message.MESSAGE_TYPE_MULTI.equals(type)) {
			return "message/multi";
		}
		return "message/single";
	}

	@RequestMapping(value = "/save.json", method = RequestMethod.POST)
	public String save(@Valid MessageForm messageForm,
			BindingResult bindingResult, ModelMap map) {
		if (bindingResult.hasErrors()) {
			Log.logInfo("message save hasError=" + bindingResult.hasErrors());
			WebUtils.processError2Map(bindingResult.getAllErrors(), map);
		} else {
			Message message = BeanUtils.copyTo(messageForm, Message.class);
	
			messageService.save(message);
			messageForm.setId(message.getId());
		}

		return "json_save";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String toEdit(@RequestParam("id") Long id, ModelMap map) {

		Message message = messageService.findById(id);
		map.put("msg", message);
		if (Message.MESSAGE_TYPE_SINGLE.equals(message.getType())) {
			return "message/single";
		} else {
			return "message/multi";
		}
	}

	@RequestMapping(value = "delete.json")
	public String delete(@RequestParam("id") Long id, ModelMap map) {

		messageService.delete(id);
		map.put("errCode", "0");
		map.put("msg", "删除成功");
		return "json_delete";
	}
}
