/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-19  Create	
 */
package snnu.wechat.framework.util.dataparse.json;

import java.io.IOException;

import snnu.wechat.framework.core.BaseRuntimeException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ObjectMappingExt extends ObjectMapper  
{  
  
    public ObjectMappingExt()  
    {  
        super();  
        
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);  
        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);  
        this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);  
        this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true); 
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//忽略位置字段
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()  
        {  
  
            @Override  
            public void serialize(  
                    Object value,  
                    JsonGenerator jsonGenerator,  
                    SerializerProvider serializerProvider) throws IOException, JsonProcessingException  
            {  
                jsonGenerator.writeString("");  
            }  
        });  
  
    }  
    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.ObjectMapper#readValue(java.lang.String, java.lang.Class)
     */
 
    public <T> T readValueFromString(String arg0, Class<T> arg1)  {
    	T result=null;
    	try {
			result=super.readValue(arg0, arg1);
		} catch (JsonParseException e) {
			throw new BaseRuntimeException(e);
		} catch (JsonMappingException e) {
			throw new BaseRuntimeException(e);
			
		} catch (IOException e) {
			throw new BaseRuntimeException(e);
			
		}
    	return result;
    }
    public <T> String writeValueasStringWithoutException(T t){
     String result=null;
     	try {
			result=super.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			throw new BaseRuntimeException(e);
		}
     	return result;
     
    }
}  