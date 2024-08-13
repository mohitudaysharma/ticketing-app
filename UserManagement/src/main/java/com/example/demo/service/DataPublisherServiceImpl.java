package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataPublisherServiceImpl {
	public static final String topic="activitymsgs";
	
	@Autowired
	private KafkaTemplate<String, String> template;
	
	public KafkaTemplate<String, String> getTemp() {
		return template;
	}
	
	public void setTemp(String message) {
		this.template.send(topic,message);
	}
	public static String getTopic() {
		return topic;
	}

}
