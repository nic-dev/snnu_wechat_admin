/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.framework.web.ajax;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import snnu.wechat.framework.util.dataparse.json.JsonSpread;



public class JsonView extends AbstractView {

	protected void renderMergedOutputModel(Map map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JsonData jsonData = new JsonData();

		if (map.get("errCode") != null) {
			jsonData.setErrCode(map.get("errCode").toString());

		}

		if (map.get("msg") != null) {
			jsonData.setMsg(map.get("msg").toString());

		}
		Map result = new HashMap();
		for (Object key : map.keySet()) {
			if (key != null
					&& !key.toString().startsWith(
							"org.springframework.validation.BindingResult")) {
				result.put(key, map.get(key));
			}
		}
		jsonData.setData(result);
		String jsonString = null;
		if (map.get("mixData") == null
				|| Boolean.TRUE == (Boolean) map.get("mixData")) {
			jsonString = JsonSpread.toJSONString(jsonData);
		} else {
			jsonString = JsonSpread.toJSONString(result);
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		response.getOutputStream().write(jsonString.getBytes("utf-8"));
		response.getOutputStream().flush();
	}

}
