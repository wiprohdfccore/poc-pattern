package com.hdfc.poc.customer.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MessageListener {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${ibm.mq.queue}")
	private String queue;

	@Autowired
	ObjectMapper mapper;

	@JmsListener(destination = "${ibm.mq.queue}")
	public void recv(String m) {

		// String m = jmsTemplate.receiveAndConvert(queue).toString();
		System.out.println("Received <" + m + ">");

	}

}
