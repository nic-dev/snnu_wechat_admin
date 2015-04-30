/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 19, 2014  Create	
 */
package snnu.wechat.commbiz.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import snnu.wechat.commbiz.common.UploadConfigBean;
import snnu.wechat.commbiz.common.WechatErrCode;
import snnu.wechat.commbiz.common.WechatMediaType;
import snnu.wechat.commbiz.entity.Media;
import snnu.wechat.commbiz.model.WechatResponseJson;
import snnu.wechat.commbiz.util.WebUtils;
import snnu.wechat.framework.log.Log;


@Service
public class FileUploadService extends BaseService {
	@Autowired
	private UploadConfigBean uploadConfig;
	@Autowired
	private MediaService mediaService;
	@Autowired
	private WechatSysClientService wechatSysClientService;

	public void upload(HttpServletRequest request, Integer action, ModelMap map) {
		String fileBasePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ uploadConfig.getDirName();

		String fileBaseUrl = uploadConfig.getDirName();

		if (!ServletFileUpload.isMultipartContent(request)) {
			WebUtils.fillErrorMap(map, "请选择文件。");
			return;
		}
		// 检查目录
		File uploadDir = new File(fileBasePath);
		if (!uploadDir.isDirectory()) {
			WebUtils.fillErrorMap(map, "上传目录不存在。");
			return;

		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			WebUtils.fillErrorMap(map, "上传目录没有写权限。");
			return;

		}

		WechatMediaType mediaType = WechatMediaType.map().get(action);

		if (mediaType == null
				|| !uploadConfig.getExtMap().containsKey(mediaType.getDesc())) {
			WebUtils.fillErrorMap(map, "文件类型不正确。");
			return;

		}
		// 创建文件夹
		fileBasePath += mediaType.getDesc() + "/";
		fileBaseUrl += mediaType.getDesc() + "/";
		File saveDirFile = new File(fileBasePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		fileBasePath += ymd + "/";
		fileBaseUrl += ymd + "/";
		File dirFile = new File(fileBasePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");

		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);

		} catch (FileUploadException e1) {

			WebUtils.fillErrorMap(map, "文件解析出错。");
			return;

		}

		FileItem item = items.get(0);
		
		String fileName = item.getName();
		map.put("original", fileName);
		// for IE
		if (fileName.lastIndexOf('\\') >= 0) {
			fileName = fileName.substring(fileName.lastIndexOf('\\') + 1);
		}

		if (!item.isFormField()) {
			// 检查文件大小
			if (item.getSize() > uploadConfig.getSizeMap().get(
					mediaType.getDesc())) {
				WebUtils.fillErrorMap(map, "上传文件大小超过限制。");
				return;
			}
			map.put("size", item.getSize());

			// 检查扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
			if (!Arrays.<String> asList(
					uploadConfig.getExtMap().get(mediaType.getDesc())
							.split(",")).contains(fileExt)) {

				WebUtils.fillErrorMap(map, "上传文件扩展名是不允许的扩展名。\n只允许"
						+ uploadConfig.getExtMap().get(mediaType.getDesc())
						+ "格式。");
				return;

			}
			map.put("type", "." + fileExt); // Ueditor 响应规范要求加.
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_"
					+ new Random().nextInt(1000) + "." + fileExt;
			String fileUrl = fileBaseUrl + newFileName;
			String mediaId=null;
			try {
				File uploadedFile = new File(fileBasePath, newFileName);
				
				item.write(uploadedFile);
				Log.logInfo("ServiceConfigBean local="
						+ getServiceConfigBean().getIsLocal());
				if (getServiceConfigBean().getIsLocal()==false) {
					WechatResponseJson result=wechatSysClientService.sendMediaRequest(uploadedFile,
							mediaType.getDesc());
					if(result.getErrCode()==WechatErrCode.OK){
						mediaId=result.getMediaId();
					}else{
						//TODO 策略改为以后可手动再次同步
//						WebUtils.fillErrorMap(map, "同步微信服务器失败，请稍后再试");
						//	return;
					}
				}
			} catch (Exception e) {
				Log.logError("文件上传失败 exceptoin="+e.getCause());
				WebUtils.fillErrorMap(map, "上传文件失败。");
				return;

			}
			Media media = new Media();
			media.setCreateTime(new Date());
			media.setName(fileName);
			media.setCreatedName(newFileName);
			media.setPath(fileUrl);
			media.setType(mediaType.getId());
			media.setSize(item.getSize());
			media.setMediaId(mediaId);
			mediaService.save(media);
			map.put("id", media.getId());
			map.put("title", newFileName);
			map.put("mediaId", mediaId);
			map.put("errCode", 0);
			map.put("url", fileUrl);
			map.put("state", "SUCCESS");// Ueditor规范
			map.put("mixData", false);
		}

	}

}
