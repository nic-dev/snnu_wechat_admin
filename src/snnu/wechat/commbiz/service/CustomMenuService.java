/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-29  Create	
 */
package snnu.wechat.commbiz.service;

import java.util.Date;

import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import snnu.wechat.commbiz.dao.CustomMenuDAO;
import snnu.wechat.commbiz.entity.CustomMenu;
import snnu.wechat.framework.service.cache.RedisService;

/**
 * 
 *
 */
@Service
public class CustomMenuService {
	@Autowired
	private CustomMenuDAO customMenuDAO;
	@Autowired
	private RedisService redisService;

	public CustomMenu listCustomMenu() {
		CustomMenu customMenu = (CustomMenu) redisService.get(
				RedisService.CACHE_CUSTOM_MENU, CustomMenu.PRIME_ID);

		if (customMenu == null) {
			customMenu = customMenuDAO.findById(CustomMenu.PRIME_ID);
			redisService.set(RedisService.CACHE_CUSTOM_MENU,
					CustomMenu.PRIME_ID, customMenu);
		}
		return customMenu;
	}

	public CustomMenu findById(Long id) {

		return customMenuDAO.findById(id);

	}

	public void saveWechatJson(String wechatJson) {
		CustomMenu customMenu = customMenuDAO.findById(CustomMenu.PRIME_ID);
		if (customMenu != null) {
			customMenu.setId(CustomMenu.PRIME_ID);
			customMenu.setDescription(wechatJson);
			customMenuDAO.update(customMenu);
		} else {
			customMenu = new CustomMenu();
			customMenu.setId(CustomMenu.PRIME_ID);
			customMenu.setDescription(wechatJson);
			customMenuDAO.save(customMenu);
		}
	}

	public void save(CustomMenu menu) {
		menu.setLastUpdateTime(new Date());
		if (menu.getId() == null) {
			menu.setCreateTime(new Date());
		}
		customMenuDAO.save(menu, false);
		redisService
				.delete(RedisService.CACHE_CUSTOM_MENU, CustomMenu.PRIME_ID);
	}

	public void updateName(Long id, String name) {
		CustomMenu menu = customMenuDAO.findById(id);
		if (menu != null) {
			menu.setName(name);
			menu.setLastUpdateTime(new Date());
		}
		customMenuDAO.update(menu);
		redisService
				.delete(RedisService.CACHE_CUSTOM_MENU, CustomMenu.PRIME_ID);
	}

	public void delete(Long id) {
		customMenuDAO.deleteById(id);
		redisService
				.delete(RedisService.CACHE_CUSTOM_MENU, CustomMenu.PRIME_ID);

	}
}
