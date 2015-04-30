/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 20, 2014  Create	
 */
package snnu.wechat.system.admin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.validation.ObjectError;

import snnu.wechat.framework.log.Log;



public class WebUtils {
	public static void processError2Map(List<ObjectError> errorList,
			Map resultMap) {
		List<String> errMsgList = new ArrayList<String>();
		for (ObjectError objectError : errorList) {
			Log.logInfo("object Error=" + objectError);
			errMsgList.add(objectError.getDefaultMessage());
		}
		resultMap.put("errCode", 1);
		resultMap.put("errMsgList", errMsgList);
	}
	public static void fillErrorMap(ModelMap map, String message) {

		map.put("errCode", 1);
		map.put("msg", message);
		map.put("state", "FAILURE");// UEditor规范，成功返回SUCCESS
	}
	public static String getClientIp(HttpServletRequest request){
		return request.getRemoteAddr();
		
	}
}
