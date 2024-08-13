package com.example.demo.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;

import com.example.demo.entity.DataEvent;

@Service
public class DataPublisherServiceImpl {
	//public static final String topic="usermsgs";
	
//	@Autowired
//	private KafkaTemplate<String, DataEvent> template;
//	
//	public KafkaTemplate<String, DataEvent> getTemp() {
//		return template;
//	}
	
    @Autowired
    private KafkaTemplate<String, DataEvent> template;


	private NewTopic topic;

    public DataPublisherServiceImpl(NewTopic topic, KafkaTemplate<String, DataEvent> kafkaTemplate) {
        this.topic = topic;
        this.template = kafkaTemplate;
    }

    public void sendMessage(String message){
    	template.send(topic.name(), new DataEvent(message, null));
    }

    public void sendMessage(DataEvent event){
        Message<DataEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        template.send(message);
    }
}
