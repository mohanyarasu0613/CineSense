package com.spring.CineSense.Utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtility {
	
	public static <T> T JSON2Java(String json,TypeReference<T> typeRef) {

		ObjectMapper mapper = new ObjectMapper();
		
		try {
            return mapper.readValue(json, typeRef);
        } catch (Exception e) {
            throw new RuntimeException("JSON parsing error: " + e.getMessage(), e);
        }
		
	}

}
