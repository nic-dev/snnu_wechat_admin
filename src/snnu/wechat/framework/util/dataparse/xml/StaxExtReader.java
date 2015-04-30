/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-17  Create	
 */
package snnu.wechat.framework.util.dataparse.xml;

import javax.xml.stream.XMLStreamReader;

import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxReader;

/**
 * 
 *
 */
public class StaxExtReader extends StaxReader {

	/**
	 * @param qnameMap
	 * @param in
	 * @param replacer
	 */
	public StaxExtReader(QNameMap qnameMap, XMLStreamReader in,
			NameCoder replacer) {
		super(qnameMap, in, replacer);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected String pullText() {
		return XmlHelper.unwrapCDATA(super.pullText());
		
	}

}
