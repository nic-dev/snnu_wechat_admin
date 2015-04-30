/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-12  Create	
 */
package snnu.wechat.commbiz.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import snnu.wechat.commbiz.dao.ReplyDAO;
import snnu.wechat.commbiz.entity.Reply;
import snnu.wechat.framework.core.util.StringUtils;
import snnu.wechat.framework.log.Log;
import snnu.wechat.framework.service.cache.RedisService;

/**
 * 
 *
 */
@Service
@Transactional
public class ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	@Autowired
	private RedisService redisService;

	public void save(Reply reply) {
		reply.setLastUpdateTime(new Date());
		if (reply.getId() == null) {
			reply.setCreateTime(new Date());
		}
		replyDAO.save(reply, false);
		if (reply.getType().equals(Reply.TYPE_KEYWORD)) {
			loadKeywordReplys();
		}
		else if(reply.getType().equals(Reply.TYPE_AUTO)){
			redisService.delete(RedisService.CACHE_REPLY, RedisService.CACHE_REPLY_AUTO); 
		}
	}

	public Reply findOneByExample(Reply reply, boolean fromCache) {
		Reply result = null;
		if (fromCache&& reply.getType().equals(Reply.TYPE_KEYWORD)) {
			if (reply != null

			
					&& !StringUtils.isEmpty(reply.getKeyword())) {
				result = redisService.get(RedisService.CACHE_REPLY,
						RedisService.CACHE_REPLY_KEYWORD) == null ? null
						: ((Map<String, Reply>) redisService.get(
								RedisService.CACHE_REPLY,
								RedisService.CACHE_REPLY_KEYWORD)).get(reply
								.getKeyword());
			}

		} else {
			
			result = replyDAO.findOneByExample(reply);
			Log.logInfo("result="+result);
		}
		return result;
	}

	public List<Reply> findByExample(Reply reply) {
		return replyDAO.findByExample(reply);
	}

	public List<Reply> list() {
		return replyDAO.queryAll();
	}

	public List<Reply> listByType(Integer type) {
		Reply reply = new Reply();
		reply.setType(type);
		return findByExample(reply);
	}

	public void delete(Long id) {
		Reply reply = replyDAO.findById(id);
		if (reply != null) {
			if (!StringUtils.isEmpty(reply.getKeyword())) {
				String[] keywordArr = reply.getKeyword().split("##");
				for (String keyword : keywordArr) {
					redisService.delete(RedisService.CACHE_REPLY, keyword);
				}

			}
			replyDAO.delete(reply);
		}

	}

		public void loadKeywordReplys() {
		redisService.delete(RedisService.CACHE_REPLY,
				RedisService.CACHE_REPLY_KEYWORD);
		List<Reply> replyList = listByType(Reply.TYPE_KEYWORD);
		HashMap<String, Reply> keywordMap = new HashMap<String, Reply>();
		for (Reply reply : replyList) {
			// 仅对关键词回复做缓存处理
			if (!StringUtils.isEmpty(reply.getKeyword())) {

				String[] keywordArr = reply.getKeyword().split("##");
				for (String keyword : keywordArr) {
					keywordMap.put(keyword, reply);
				}

			}
		}
		redisService.set(RedisService.CACHE_REPLY,
				RedisService.CACHE_REPLY_KEYWORD, keywordMap);
	}
}
