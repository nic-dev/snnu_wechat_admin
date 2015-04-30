
/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-15 Create	
 */
package snnu.wechat.commbiz.service;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import snnu.wechat.commbiz.common.SysConfigType;
import snnu.wechat.commbiz.common.WechatErrCode;
import snnu.wechat.commbiz.entity.SysConfig;
import snnu.wechat.commbiz.model.WechatResponseJson;
import snnu.wechat.framework.log.Log;


/**
 * 
 *
 */
@Service
public class WechatSysClientService  {

	@Autowired
	private SysConfigService sysConfigService;
	
	@Autowired
	private RestTemplate restTemplate;

	protected boolean validateAccessToken(WechatResponseJson wechatResponseJson) {
		return wechatResponseJson.getErrCode() == WechatErrCode.ACCESS_TOKEN_EXPIRED
				|| wechatResponseJson.getErrCode() == WechatErrCode.ACCESS_TOKEN_ILLEGAL
				|| wechatResponseJson.getErrCode() == WechatErrCode.ACCESS_TOKEN_INVALID
				|| wechatResponseJson.getErrCode() == WechatErrCode.ACCESS_TOKEN_MISSING;
	}

	public WechatResponseJson getRemoteAccessToken() {

		Map<String, Object> params = new HashMap<String, Object>();
		SysConfig grantTypeConfig = sysConfigService
				.config(SysConfigType.WECHAT_ACCESS_TOKEN_GRANT_TYPE);
		// Log.logInfo(config.toString());
		params.put(grantTypeConfig.getDescription(), grantTypeConfig.getValue());

		SysConfig appIdConfig = sysConfigService
				.config(SysConfigType.WECHAT_APP_ID);
		// Log.logInfo(config.toString());
		params.put(appIdConfig.getDescription(), appIdConfig.getValue());

		SysConfig secretConfig = sysConfigService
				.config(SysConfigType.WECHAT_APP_SECRET);
		// Log.logInfo(config.toString());
		params.put(secretConfig.getDescription(), secretConfig.getValue());

		SysConfig accessTokenUrlConfig = sysConfigService
				.config(SysConfigType.WECHAT_ACCESS_TOKEN_URL);
		Log.logInfo(accessTokenUrlConfig.toString());

		return sendRequest(accessTokenUrlConfig.getValue(), params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see snnu.wechat.admin.service.WechatClientService#getAccessToken()
	 */
	// TODO 今后改为从缓存
	public String getAccessToken(boolean force) {
		int retryCount = 0;
		String result = sysConfigService.config(
				SysConfigType.WECHAT_ACCESS_TOKEN).getValue();
		if (result == null || force) {
			WechatResponseJson wechatJsonResponse = getRemoteAccessToken();
			if (wechatJsonResponse.getErrCode() == WechatErrCode.OK) {
				result = wechatJsonResponse.getAccessToken();
				SysConfig config = sysConfigService
						.config(SysConfigType.WECHAT_ACCESS_TOKEN);
				config.setValue(result);
				sysConfigService.updateConfig(config);
			} else {
				// TODO retry
			}
		}
		return result;
	}

	protected WechatResponseJson sendRequest(String urlTempplateStr,
			Map<String, Object> params) {
		WechatResponseJson result = restTemplate.getForObject(
				urlTempplateStr, WechatResponseJson.class, params);
		Log.logInfo("result=" + result);

		return result;

	}

	protected WechatResponseJson sendRequest(String baseUrl) {
		String url;
		String accessToken = getAccessToken(false);
		url = baseUrl + accessToken;
		Log.logInfo("url=" + url);
		ResponseEntity<WechatResponseJson> result = restTemplate
				.getForEntity(url, WechatResponseJson.class);
		Log.logInfo("result=" + result.getBody());
		if (validateAccessToken(result.getBody())) {
			accessToken = getAccessToken(true);
			url = baseUrl + accessToken;

			result = restTemplate.getForEntity(url,
					WechatResponseJson.class);
			Log.logInfo("result=" + result.getBody());
		}
		return result.getBody();

	}

	protected WechatResponseJson sendRequestJson(String baseUrl, String jsonStr) {
		String url;
		String accessToken = getAccessToken(false);
		url = baseUrl + accessToken;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(new MediaType("text", "plain", Charset
				.forName("utf-8")));

		HttpEntity<String> jsonEntity = new HttpEntity<String>(jsonStr, headers);
		Log.logInfo("jsonEntity=" + jsonEntity);
		ResponseEntity<WechatResponseJson> result = restTemplate
				.postForEntity(url, jsonEntity, WechatResponseJson.class);
		Log.logInfo("result=" + result.getBody());
		if (validateAccessToken(result.getBody())) {
			accessToken = getAccessToken(true);
			url = baseUrl + accessToken;
			jsonEntity = new HttpEntity<String>(jsonStr, headers);
			result = restTemplate.postForEntity(url, jsonEntity,
					WechatResponseJson.class);
			Log.logInfo("result=" + result.getBody());
		}
		return result.getBody();

	}

	/**
	 * @param file
	 * @param mediaType
	 * @return
	 */
	public WechatResponseJson sendMediaRequest(File file, String mediaType) {
		// Map<String, Object> params = new HashMap<String, Object>();
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		String wechatMediaUrl = sysConfigService.config(
				SysConfigType.WECHAT_MEDIA_URL).getValue();

		String accessToken = getAccessToken(false);
		params.add(sysConfigService.config(SysConfigType.WECHAT_ACCESS_TOKEN)
				.getDescription(), accessToken);
		params.add("type", mediaType);// TODO magic param name
		Log.logInfo("wechatMediaUrl=" + wechatMediaUrl + " mediaType="
				+ mediaType + " accessToken=" + accessToken);
		params.add("media", new FileSystemResource(file));
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity request = new HttpEntity(params, headers);

		WechatResponseJson result = restTemplate.postForObject(
				wechatMediaUrl, request, WechatResponseJson.class);
		Log.logInfo("result=" + result);
		if (validateAccessToken(result)) {
			accessToken = getAccessToken(true);
			params.set(
					sysConfigService.config(SysConfigType.WECHAT_ACCESS_TOKEN)
							.getDescription(), accessToken);

			result = restTemplate.postForObject(wechatMediaUrl, request,
					WechatResponseJson.class);
			Log.logInfo("result=" + result);
		}

		return result;
	}

	public WechatResponseJson publishCustomMenu(String jsonStr) {
		String publishMenuUrl = sysConfigService.config(
				SysConfigType.WECHAT_PUBLISH_MENU_URL).getValue();
		return sendRequestJson(publishMenuUrl, jsonStr);

	}

	public WechatResponseJson broadcastByGroup(String jsonStr) {

		String broadcastByGroupUrl = sysConfigService.config(
				SysConfigType.WECHAT_BROADCAST_BY_GROUP).getValue();
		return sendRequestJson(broadcastByGroupUrl, jsonStr);

	}

	public WechatResponseJson broadcastByOpenId(String jsonStr) {

		String broadcastByGroupUrl = sysConfigService.config(
				SysConfigType.WECHAT_BROADCAST_BY_OPEN_ID).getValue();
		return sendRequestJson(broadcastByGroupUrl, jsonStr);

	}
	
}
