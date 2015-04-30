/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 21, 2014  Create	
 */
package snnu.wechat.commbiz.common;

import java.util.HashMap;
import java.util.Map;

public enum WechatRequestType {
	Text(0, "text"), Image(1, "image"), Voice(2, "voice"), Video(3, "video"),Event(4,"event");
	private WechatRequestType(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
//		map.put(id, this);
	};

	private  Integer id;
	private String desc;

	public Integer getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}
//	private  Map<Integer,MediaType> map=new HashMap<Integer, MediaType>();
	public static Map<Integer,WechatRequestType> map(){
		Map<Integer,WechatRequestType> map=new HashMap<Integer, WechatRequestType>();
		for (WechatRequestType mediaType : WechatRequestType.values()) {
			map.put(mediaType.getId(), mediaType);
			
		}
		return map;
	}
	public static Map<String,Integer> typeNameMap(){
		Map<String,Integer> map=new HashMap<String, Integer>();
		for (WechatRequestType mediaType : WechatRequestType.values()) {
			map.put(mediaType.getDesc(), mediaType.getId());
			
		}
		return map;
	}
}
