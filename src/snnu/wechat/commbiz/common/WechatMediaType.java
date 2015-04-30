/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 21, 2014  Create	
 */
package snnu.wechat.commbiz.common;

import java.util.HashMap;
import java.util.Map;

public enum WechatMediaType {
	Config(0, "config"), Text(1, "text"), Image(2, "image"), Voice(3, "voice"), Video(
			4, "video"), News(5, "news");
	private WechatMediaType(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
		// map.put(id, this);
	};

	private Integer id;
	private String desc;

	public Integer getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	// private Map<Integer,MediaType> map=new HashMap<Integer, MediaType>();
	public static Map<Integer, WechatMediaType> map() {
		if (map == null) {
			map = new HashMap<Integer, WechatMediaType>();
			for (WechatMediaType mediaType : WechatMediaType.values()) {
				map.put(mediaType.getId(), mediaType);

			}
		}
		return map;
	}

	public static Map<String, Integer> typeNameMap() {
		if (typeNameMap == null) {
			typeNameMap = new HashMap<String, Integer>();
			for (WechatMediaType mediaType : WechatMediaType.values()) {
				typeNameMap.put(mediaType.getDesc(), mediaType.getId());

			}
		}
		return typeNameMap;
	}

	private static Map<Integer, WechatMediaType> map;
	private static Map<String, Integer> typeNameMap;
}
