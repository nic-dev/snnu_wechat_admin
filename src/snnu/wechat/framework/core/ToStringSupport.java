/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.framework.core;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ToStringSupport {

	public String toString() {
		return toString(this);
	}

	public static String toString(Object obj) {
		return ToStringBuilder.reflectionToString(obj,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
