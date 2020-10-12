package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configs.MqttPushClient;

@RestController
@RequestMapping("/")
public class DemoController {
	@Autowired
	private MqttPushClient mqttPushClient;

	@GetMapping(value = "/publishTopic")
	public String publishTopic() {
		String topicString = "test";
		mqttPushClient.publish(0, false, topicString, "Test posting");
		return "ok";
	}

	// Send custom message content (using default theme)
	@RequestMapping("/publishTopic/{data}")
	public String test1(@PathVariable("data") String data) {
		String topicString = "test";
		mqttPushClient.publish(0, false, topicString, data);
		return "ok";
	}

	// Send custom message content and specify subject
	@RequestMapping("/publishTopic/{topic}/{data}")
	public String test2(@PathVariable("topic") String topic, @PathVariable("data") String data) {
		mqttPushClient.publish(0, false, topic, data);
		return "ok";
	}
}
