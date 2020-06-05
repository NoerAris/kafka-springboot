package com.spring.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.kafka.model.User;

@Service
public class Consumer {

	private final Logger log = LoggerFactory.getLogger(Consumer.class);
		
	@KafkaListener(topics = "${app.topic.foo}", groupId = "group_id")
	public void consume(String message) {
		log.info("Consumed message = '{}'", message);
	}
	
	@KafkaListener(topics = "${app.topic.obj}", groupId = "group_id")
	public void consumeObj(String message) throws Exception {
		//ObjectMapper provides functionality for reading and writing JSON
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(message, User.class);
		log.info("Consume message id = '{}', name = '{}', age = '{}'", user.getId(), user.getName(), user.getAge());
	}
}
