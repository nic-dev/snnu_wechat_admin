/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 19, 2014  Create	
 */
package snnu.wechat.system.admin.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import snnu.wechat.commbiz.entity.Media;
import snnu.wechat.commbiz.service.MediaService;
import snnu.wechat.commbiz.web.form.MediaQueryForm;
import snnu.wechat.framework.core.vo.PageModel;
import snnu.wechat.framework.log.Log;
import snnu.wechat.system.admin.util.WebUtils;



@Controller
@RequestMapping("/admin/media")
public class MediaController extends BaseController {
	@Autowired
	private MediaService mediaService;

	@RequestMapping("/list")
	public String image(MediaQueryForm form,ModelMap map) {
		
		map.put("pageModel",new PageModel<Media>(mediaService.list(form),form));
		return "media/list";
	}
	@RequestMapping("/delete.json")
	public String delete(@RequestParam("id") Long id,ModelMap map){
		mediaService.delete(id);
		return "json_delete";
	}
	@RequestMapping(value="/update_name.json",method=RequestMethod.POST)
	public String updateName(@RequestParam("id") Long id,@RequestParam("name") String name,ModelMap map){
		if(StringUtils.isEmpty(name)){
			WebUtils.fillErrorMap(map, "文件名不能为空");
		}
		else{
			mediaService.updateName(id, name);
		}
		return "json_update_name";
	}
	@RequestMapping(value = "/list_embedded")
	public String listInIframe(MediaQueryForm form, ModelMap map) {

		map.put("pageModel", new PageModel<Media>(mediaService.list(form),
				form));
		return "media/list_in_iframe";
	}
	@RequestMapping("/download")
	public void download(@RequestParam("id")Long id,HttpServletRequest request,HttpServletResponse response) throws IOException{

		Media media=mediaService.findById(id);
		Log.logInfo("download id="+id+" media="+media);
		
		if(media!=null){
			
			String fullpath=request.getRealPath(media.getPath());
			Resource rs=new FileSystemResource(fullpath);
			Log.logInfo("[download] path="+fullpath+" resource exists="+rs.exists());
			response.setContentType("application/x-msdownload");
			response.setContentLength((int)rs.contentLength());
			response.setHeader("Content-Disposition", "attachment;filename="+media.getName());
	//		response.setHeader("windows-Target", "_blank");
			FileCopyUtils.copy(rs.getInputStream(), response.getOutputStream());
					
		}
		
	}

}
