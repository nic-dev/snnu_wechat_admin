/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-22  Create	
 */
package snnu.wechat.system.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import snnu.wechat.framework.core.util.BeanUtils;
import snnu.wechat.framework.core.vo.PageModel;
import snnu.wechat.framework.log.Log;
import snnu.wechat.system.admin.entity.User;
import snnu.wechat.system.admin.service.UserService;
import snnu.wechat.system.admin.util.WebUtils;
import snnu.wechat.system.admin.web.form.UserForm;


/**
 * 
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping("/list")
	public String list(UserForm form, ModelMap map) {
		map.put("pageModel", new PageModel<User>(userService.list(form), form));
		return "/user/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd() {
		return "/user/add";
	}

	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
	public String add(UserForm userForm, BindingResult bindingResult,
			ModelMap map) {
		if (bindingResult.hasErrors()) {
			WebUtils.processError2Map(bindingResult.getAllErrors(), map);
		} else {

			User user = userService.findByUserName(userForm.getUserName());
			if (user != null
					&& (userForm.getId() == null || !user.getId().equals(
							userForm.getId()))) {
				WebUtils.fillErrorMap(map, "用户名已存在");
			} else {
				user = BeanUtils.copyTo(userForm, User.class);

				userService.save(user);
				userForm.setId(user.getId());
			}

		}
		return "json_add";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String toEdit(@RequestParam("id") Long id, ModelMap map) {
		map.put("user", userService.findById(id));
		return "/user/add";
	}

	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public String delete(@RequestParam("id") Long id) {
		userService.delete(id);
		return "json_delete";
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String toPassword() {

		return "/user/password";
	}

	@RequestMapping(value = "/password.json", method = RequestMethod.POST)
	public String password(UserForm form, ModelMap map) {
		User currentUser = userService.findById(form.getId());
		Log.logInfo("currentUser=" + currentUser);
		if (currentUser == null) {
			WebUtils.fillErrorMap(map, "密码修改失败，请重新登录系统");
		}
		currentUser.setPassword(form.getPassword());
		userService.save(currentUser);
		return "json_password";
	}
}
