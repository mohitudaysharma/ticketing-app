package com.example.demo.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;

public class CommonUtils {
	
	public static Object getNewOrDefault(Object a, Object b) {
		return Optional.ofNullable(a).orElse(b);
	}
	
	@Value("${spring.kafka.topic.name}")
	static String val;
	
	@Value("${spring.kafka.consumer.group-id}")
	static String val2;

	public static final String topicName = (val!=null)?val:"";
	
	public static final String groupId = (val2!=null)?val2:"";
	
	public static Map<String, String> messageJson(String message) {
		Map<String, String> mapObj=new HashMap<>();
		mapObj.put("message", message);
		return mapObj;
	}

}