/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-17  Create	
 */
package snnu.wechat.framework.util.dataparse.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import com.thoughtworks.xstream.io.xml.AbstractPullReader;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.StaxWriter;

/**
 * 
 *
 */
public class StaxExtDriver extends StaxDriver {
	   public AbstractPullReader createStaxReader(XMLStreamReader in)
	    {
	        return new StaxExtReader(super.getQnameMap(), in, getNameCoder());
	    }

	    public StaxWriter createStaxWriter(XMLStreamWriter out, boolean writeStartEndDocument)
	        throws XMLStreamException
	    {
	    	
	        return new StaxExtWriter(super.getQnameMap(), out, writeStartEndDocument, isRepairingNamespace(), getNameCoder());
	    }
}
