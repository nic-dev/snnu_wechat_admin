/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.framework.service.cache;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisService {
	public static final String CACHE_PREFIX="Redis";
	public static final String APP_USER="appUser";
	public static final String CACHE_REPLY="reply";
	public static final String CACHE_REPLY_AUTO="replyAuto";
	public static final String CACHE_REPLY_KEYWORD="replyKeyword";
	
	public static final String CACHE_EVENT="event";
	public static final String CACHE_EVENT_CLICK="eventClick";
	public static final String CACHE_CUSTOM_MENU="customMenu";
	public static final String CACHE_SYS_CONFIG="sysConfig";
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
	public void delete(Serializable bizId, Serializable key) {
		redisTemplate.opsForHash().delete(CACHE_PREFIX+bizId, key);
	}
	
	public void set(final Serializable bizId, final Serializable key,final Serializable value) {  
		redisTemplate.opsForHash().put(CACHE_PREFIX+bizId, key,value); 
	}
	public Serializable get(Serializable bizId, Serializable key) {  
        return (Serializable) redisTemplate.opsForHash().get(CACHE_PREFIX+bizId,key);  
    
    } 
}