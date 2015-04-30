/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-11  Create	
 */
package snnu.wechat.system.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import snnu.wechat.commbiz.entity.Reply;
import snnu.wechat.commbiz.service.ReplyService;
import snnu.wechat.commbiz.web.form.ReplyForm;
import snnu.wechat.framework.core.util.BeanUtils;


/**
 * 
 *
 */
@Controller
@RequestMapping("/admin/reply")
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@RequestMapping("/index")
	public String index() {
		return "reply/index";
	}

	@RequestMapping("/list.json")
	public String list(ReplyForm form, ModelMap map) {
		Reply replyExample = new Reply();
		replyExample.setType(form.getType());
		if (Reply.TYPE_KEYWORD.equals(form.getType()))
		{
			map.put("replyList", replyService.findByExample(replyExample));
		} else {
			map.put("reply", replyService.findOneByExample(replyExample,false));
		}
		return "json_list";
	}

	@RequestMapping("/save.json")
	public String save(ReplyForm form, ModelMap map) {
		Reply reply = BeanUtils.copyTo(form, Reply.class);
		replyService.save(reply);
		form.setId(reply.getId());
		return "json_save";
	}
	@RequestMapping("/delete.json")
	public String delete(@RequestParam("id") Long id, ModelMap map) {
		
		replyService.delete(id);
		
		return "json_delete";
	}
}
