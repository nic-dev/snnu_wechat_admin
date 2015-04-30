/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 19, 2014  Create	
 */
package snnu.wechat.system.admin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import snnu.wechat.commbiz.common.WechatMediaType;
import snnu.wechat.commbiz.service.FileUploadService;
import snnu.wechat.framework.log.Log;



@Controller
@RequestMapping("/ajax")
public class AjaxController {
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping("/file_upload.json")
	public String fileUpload(@RequestParam("action")
	Integer action, HttpServletRequest request, ModelMap map) {
		Log.logInfo("fileUpload action=" + action);
		if (WechatMediaType.Config.getId().equals(action)) {
			map.put("errCode", 0);
			map.put("msg", "global config");
		} else {
			fileUploadService.upload(request, action,map);
		}
		return "json_file_upload";
	}

}
