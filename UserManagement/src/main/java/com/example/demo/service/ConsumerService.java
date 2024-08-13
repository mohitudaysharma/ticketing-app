package com.example.demo.service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

//	@KafkaListener(topics="new_topics",groupId="myGroup")
	public void consumeFromTopic(String message) {
		System.out.println("Consumer Message : "+message);
	}
}
