/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-17  Create	
 */
package snnu.wechat.framework.util.dataparse.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import snnu.wechat.framework.core.util.StringUtils;

import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.QNameMap;

/**
 * 
 *
 */
public class StaxExtWriter extends com.thoughtworks.xstream.io.xml.StaxWriter {

	/**
	 * @param qnameMap
	 * @param out
	 * @param writeEnclosingDocument
	 * @param namespaceRepairingMode
	 * @param nameCoder
	 * @throws XMLStreamException
	 */
	public StaxExtWriter(QNameMap qnameMap, XMLStreamWriter out,
			boolean writeEnclosingDocument, boolean namespaceRepairingMode,
			NameCoder nameCoder) throws XMLStreamException {
		super(qnameMap, out, writeEnclosingDocument, namespaceRepairingMode,
				nameCoder);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thoughtworks.xstream.io.xml.StaxWriter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String text) {
		if (StringUtils.isNumber(text)) {
			super.setValue(text);
		} else {
			try {
				super.getXMLStreamWriter().writeCData(text);
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	super.setValue(XmlHelper.wrapCDATA(text));
		}
		
	}
}
