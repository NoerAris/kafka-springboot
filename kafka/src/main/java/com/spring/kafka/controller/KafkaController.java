package com.spring.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import com.spring.kafka.model.User;
import com.spring.kafka.service.Producer;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	
	private final Logger log = LoggerFactory.getLogger(KafkaController.class);

	@Autowired
	Producer producer;
	
	@PostMapping("/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message){
		log.info("send message = '{}'  ", message);
		producer.sendMessage(message);
	}
	
	@PostMapping("/produceObj")
	public ResponseEntity<String> sendMessage(@Valid @RequestBody User user) throws Exception {
		log.info("Send message object");
		producer.sendObjectUser(user);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
}
