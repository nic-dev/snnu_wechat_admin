/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-10-7  Create	
 */
package snnu.wechat.commbiz.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import snnu.wechat.commbiz.common.WechatMediaType;
import snnu.wechat.commbiz.common.WechatSimulConfigBean;
import snnu.wechat.commbiz.model.WechatSimulMediaListJson;
import snnu.wechat.commbiz.model.WechatSimulMsgListJson;
import snnu.wechat.commbiz.model.WechatSimulResponseJson;
import snnu.wechat.commbiz.model.WechatUserJson;
import snnu.wechat.framework.log.Log;
import snnu.wechat.framework.util.dataparse.json.ObjectMappingExt;


/**
 * 
 *
 */
@Service
public class WechatSimulClientService {
	public static final String DEFAULT_VOICE_SUFFIX = ".mp3";

	public  String userName;
									
	public  String password;
	public  String userToken;

	private String userCookies;

	@Autowired
	@Qualifier("simulRestTemplate")
	private RestTemplate simulRestTemplate;
	@Autowired
	private WechatSimulConfigBean wechatSimulConfigBean;

	

	// 模拟登录相关
		public WechatSimulResponseJson simulLogin(String luserName, String password) {
			String loginUrl = wechatSimulConfigBean
					.getWebappSimulRestTemplateMapConfig("loginUrl");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.add("Referer",
					"https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN");
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			params.add("username", luserName);
			params.add("pwd", new Md5Hash(password).toHex());
			params.add("f", "json");
			HttpEntity request = new HttpEntity(params, headers);

			ResponseEntity<WechatSimulResponseJson> result = simulRestTemplate
					.postForEntity(loginUrl, request, WechatSimulResponseJson.class);
			HttpHeaders rheaders = result.getHeaders();
			String cookies = rheaders.get("Set-Cookie").toString();
			cookies = cookies.substring(1, cookies.length() - 1);
			cookies = cookies.replaceAll("Path=/;", "").replaceAll("Secure;", "")
					.replaceAll("HttpOnly,", "");// 去掉前后中括号
			userCookies = cookies;
			Log.logInfo("simulLogin fixed cookies =" + cookies);
			Log.logInfo("simulLogin result=" + result);

			String redirectUrl = result.getBody().getRedirectUrl();
			int tokenBeginIndex = redirectUrl.indexOf("token=") + "token=".length();
			// int tokenEndInddex=redirectUrl.indexOf("\"", tokenBeginIndex);
			userToken = redirectUrl.substring(tokenBeginIndex);
			Log.logInfo("simulLogin token=" + userToken);
			return result.getBody();
		}

		public WechatSimulResponseJson uploadMedia(File file) {
			simulLogin(userName, password);
			String msgListHtml = fecthMsgHtml(0,1);
			processMsgList(msgListHtml);
			WechatUserJson wechatUserJson = processWechatUserInfo(msgListHtml);
			HttpHeaders headers = new HttpHeaders();

			MultiValueMap<String, Object> postParams = new LinkedMultiValueMap<String, Object>();
			postParams.add("file", new FileSystemResource(file));
			postParams.add("Filename", file.getName());
			postParams.add("folder", "/cgi-bin/uploads");
			postParams.add("Upload", "Submit Query");
			headers.add("Cookie", userCookies);
			// headers.add("Referer",
			// "https://mp.weixin.qq.com/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&isMul=0&isNew=1&lang=zh_CN&token=1452848686");
			HttpEntity request = new HttpEntity(postParams, headers);
			String uploadMediaUrl = wechatSimulConfigBean
					.getWebappSimulRestTemplateMapConfig("uplodMediaUrl");

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("ticket_id", wechatUserJson.getUserName());
			params.put("ticket", wechatUserJson.getTicket());
			params.put("token", userToken);
			WechatSimulResponseJson result = simulRestTemplate.postForObject(
					uploadMediaUrl, request, WechatSimulResponseJson.class, params);
			Log.logInfo("uploadMedia result=" + result);
			return result;

		}

		public String fecthMsgHtml(Integer begin,Integer count) {
			return fetchContentHtml(begin,count, WechatMediaType.News.getId());
		}

		public String fetchContentHtml(Integer begin,Integer count, int type) {
			HttpHeaders headers = new HttpHeaders();

			headers.add("Cookie", userCookies);
			HttpEntity request = new HttpEntity(null, headers);

			String fetchUrl;
			if (WechatMediaType.News.getId().equals(type)) {
				fetchUrl = wechatSimulConfigBean
						.getWebappSimulRestTemplateMapConfig("fetchMsgUrl");
			} else {
				fetchUrl = wechatSimulConfigBean
						.getWebappSimulRestTemplateMapConfig("fetchMediaUrl");
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("begin", begin);
			params.put("count", count);
			params.put("token", userToken);
			params.put("type", type);
			ResponseEntity<String> result = simulRestTemplate.exchange(fetchUrl,
					HttpMethod.GET, request, String.class, params);
			Log.logInfo("fecthMsgHtml result=" + result.getBody());
			return result.getBody();

		}

		public String downloadSimulMedia(String simulMediaId, String basePath,
				String contextPath) {
			if (simulMediaId == null) {
				return null;
			}

			String downloadMediaUrl = wechatSimulConfigBean
					.getWebappSimulRestTemplateMapConfig("downloadMediaUrl");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("token", userToken);
			params.put("fileId", simulMediaId);

			ResponseEntity<byte[]> result = simulRestTemplate.getForEntity(
					downloadMediaUrl, byte[].class, params);

			
			String fileName = new Date().getTime() + DEFAULT_VOICE_SUFFIX;
			try {
				String fileFullPathName = basePath + contextPath + fileName;
				FileUtils.copyInputStreamToFile(
						new ByteArrayInputStream(result.getBody()), new File(
								fileFullPathName));
			} catch (IOException e) {
				Log.logError("Exception=" + e);
				return null;
			}
			return contextPath + fileName;

		}

		public WechatSimulMsgListJson processMsgList(String msgHtml) {
			WechatSimulMsgListJson result = null;
			Pattern pattern = Pattern.compile("wx.cgiData\\s=\\s(.*);");

			Matcher matcher = pattern.matcher(msgHtml);

			if (matcher.find()) {
				Log.logInfo("processMsgList=" + matcher.group(1));
				ObjectMappingExt objectMappingExt = new ObjectMappingExt();
				result = objectMappingExt.readValueFromString(matcher.group(1),
						WechatSimulMsgListJson.class);

			}
			return result;
		}

		public WechatSimulMediaListJson processMediaList(String msgHtml) {
			WechatSimulMediaListJson result = null;
			Pattern pattern = Pattern.compile("wx.cgiData\\s=\\s(.*);");

			Matcher matcher = pattern.matcher(msgHtml);

			if (matcher.find()) {
				Log.logInfo("processMediaList=" + matcher.group(1));
				ObjectMappingExt objectMappingExt = new ObjectMappingExt();
				result = objectMappingExt.readValueFromString(matcher.group(1),
						WechatSimulMediaListJson.class);

			}
			return result;
		}

		public WechatSimulMsgListJson pullMsgList(Integer begin,Integer count) {
			simulLogin(userName, password);
			String msgHtml = fecthMsgHtml(begin, count);
			return processMsgList(msgHtml);
		}

		public WechatSimulMediaListJson pullMediaList(Integer begin,Integer count, Integer type) {

			simulLogin(userName, password);
			String contentHtml = fetchContentHtml(begin,count, type);
			WechatSimulMediaListJson wechatSimulMediaListJson = processMediaList(contentHtml);
			return wechatSimulMediaListJson;
		}

		public WechatUserJson processWechatUserInfo2(String msgHtml) {
			WechatUserJson wechatUserJson = null;
			int beginIndex = msgHtml.indexOf("window.wx =")
					+ "window.wx =".length();
			int endIndex = msgHtml.indexOf(";", beginIndex);
			String content = msgHtml.substring(beginIndex, endIndex);
			Log.logInfo("processWechatUserInfo content=" + content);
			ObjectMappingExt objectMappingExt = new ObjectMappingExt();
			wechatUserJson = objectMappingExt.readValueFromString(content,
					WechatUserJson.class);
			Log.logInfo("WechatUserJson=" + wechatUserJson);

			return wechatUserJson;
		}

		public WechatUserJson processWechatUserInfo(String msgHtml) {
			String userName = null;
			String ticket = null;
			WechatUserJson wechatUserJson = new WechatUserJson();
			Pattern userNamePattern = Pattern.compile("user_name:\"(.*)\"");
			Matcher matcher = userNamePattern.matcher(msgHtml);
			if (matcher.find()) {

				userName = matcher.group(1);
				Log.logInfo("WechatUserInfo user_name=" + userName);
				wechatUserJson.setUserName(userName);
			}
			Pattern ticketPattern = Pattern.compile("ticket:\"(.*)\"");
			matcher = ticketPattern.matcher(msgHtml);
			if (matcher.find()) {

				ticket = matcher.group(1);
				Log.logInfo("WechatUserInfo ticket=" + userName);
				wechatUserJson.setTicket(ticket);
			}
			return wechatUserJson;
		}

		@PostConstruct
		public void initUserInfo() {
			this.userName = wechatSimulConfigBean
					.getWebappSimulRestTemplateMapConfig("userName");
			this.password = wechatSimulConfigBean
					.getWebappSimulRestTemplateMapConfig("password");
		}

}
