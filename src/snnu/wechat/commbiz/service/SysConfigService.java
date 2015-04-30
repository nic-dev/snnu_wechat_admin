/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-16  Create	
 */
package snnu.wechat.commbiz.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import snnu.wechat.commbiz.common.SysConfigType;
import snnu.wechat.commbiz.dao.SysConfigDAO;
import snnu.wechat.commbiz.entity.SysConfig;
import snnu.wechat.framework.service.cache.RedisService;


/**
 * 
 *
 */
@Service
public class SysConfigService {
//	private List<SysConfig> configList;
	private List<SysConfig> writableConfigList;
//	private Map<String, SysConfig> configMap;
//	private Map<String, SysConfig> writableConfigMap;
	@Autowired
	private SysConfigDAO sysConfigDAO;
	@Autowired
	private RedisService redisService;

	private SysConfig config(String key) {
		return (SysConfig)redisService.get(RedisService.CACHE_SYS_CONFIG, key);
	//	return configMap.get(key);

	}

	public SysConfig config(SysConfigType sysConfigType) {
		return config(sysConfigType.getName());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see snnu.wechat.admin.service.SysConfigService#loadSysConfigs()
	 */
	@PostConstruct
	public void loadSysConfigs() {
	//	configMap = new HashMap<String, SysConfig>();
		writableConfigList = new ArrayList<SysConfig>();
	//	writableConfigMap = new HashMap<String, SysConfig>();
		
		reloadSysConfigs();
	}
	protected void reloadSysConfigs(){
	//	configMap.clear();
		writableConfigList.clear();
	//	writableConfigMap.clear();
//		configList.clear();
//		configList = sysConfigDAO.queryAll();
//		Log.logInfo("[configList size=]"+configList.size());
//		for (SysConfig sysConfig : configList) {
		for (SysConfig sysConfig : sysConfigDAO.queryAll()) {
	//		configMap.put(sysConfig.getName(), sysConfig);
			redisService.set(RedisService.CACHE_SYS_CONFIG, sysConfig.getName(),sysConfig);
			if (SysConfig.TYPE_WRITABLE.equals(sysConfig.getType())) {
				writableConfigList.add(sysConfig);
	//			writableConfigMap.put(sysConfig.getName(), sysConfig);
			}

		}
	}
	public void updateConfig(SysConfig config) {

		sysConfigDAO.update(config);
	}

//	public List<SysConfig> getConfigList() {
//		return configList;
//	}

	public List<SysConfig> getWritableConfigList() {
		return writableConfigList;
	}

	/**
	 * @param id
	 * @param value
	 */
	public void updateSysConfigValue(Long id, String value) {
		SysConfig sysConfig = sysConfigDAO.findById(id);
		if (sysConfig != null) {
			sysConfig.setValue(value);
			sysConfigDAO.update(sysConfig);
			reloadSysConfigs();

		}
	}
}
