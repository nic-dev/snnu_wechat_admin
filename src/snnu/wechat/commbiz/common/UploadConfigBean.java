/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 21, 2014  Create	
 */
package snnu.wechat.commbiz.common;

import java.util.Map;

public class UploadConfigBean {
	private Map<String,Long> sizeMap;
	private Map<String, String> extMap;
	private String dirName;



	public Map<String, Long> getSizeMap() {
		return sizeMap;
	}

	public void setSizeMap(Map<String, Long> sizeMap) {
		this.sizeMap = sizeMap;
	}

	public Map<String, String> getExtMap() {
		return extMap;
	}

	public void setExtMap(Map<String, String> extMap) {
		this.extMap = extMap;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
}
