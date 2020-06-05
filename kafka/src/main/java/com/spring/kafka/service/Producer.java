package com.spring.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.kafka.model.User;

@Service
public class Producer {

	private final Logger log = LoggerFactory.getLogger(Producer.class);
	
	@Value("${app.topic.foo}")
	private String topic;
	
	@Value("${app.topic.obj}")
	private String topicObj;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	

	public void sendMessage(String message){
		log.info("sending message = '{}' to topic='{}'", message, topic);
		kafkaTemplate.send(topic,message);
	}
	
	public void sendObjectUser(User user) throws Exception {
		//Use StringBuffer rather than String because StringBuffer's mutable sequence of characters.A string buffer is like a String, but can be modified.
		StringBuffer buffer = convertToJson(user);
		log.info("Sending message = '{}'", buffer);
		kafkaTemplate.send(topicObj, buffer.toString());
	}
	
	private StringBuffer convertToJson(User user) throws Exception {
		//ObjectMapper provides functionality for reading and writing JSON
		ObjectMapper mapper = new ObjectMapper();
		//Convert object to json use objectmapper
		return new StringBuffer(mapper.writeValueAsString(user));
	}
}
 