/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-9-28  Create	
 */
package snnu.wechat.framework.util.dataparse.xml;

import org.springframework.oxm.xstream.XStreamMarshaller;

/**
 * 
 *
 */
public class XstreamMarshallerExt extends XStreamMarshaller {
	/**
	 * 
	 */
	public XstreamMarshallerExt() {
		super();
		getXStream().ignoreUnknownElements();//忽略位置字段
	}
}
