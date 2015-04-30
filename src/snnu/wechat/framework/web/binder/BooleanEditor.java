/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-23  Create	
 */
package snnu.wechat.framework.web.binder;

import java.beans.PropertyEditorSupport;

/**
 * 
 *
 */
public class BooleanEditor extends PropertyEditorSupport  {
	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text.equals("1")){
			setValue(Boolean.TRUE);
		}
		else{
			setValue(Boolean.FALSE);
		}
	}
	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {
		if(Boolean.TRUE.equals(getValue())){
			return "1";
		}
		else{
			return "0";
		}
	}

}
