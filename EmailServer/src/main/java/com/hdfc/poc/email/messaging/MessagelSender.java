package com.hdfc.poc.email.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagelSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${AUDIT.QUEUE}")
	private String queue;

	public String send(String type, Object payload) {
		try {
			Message message = new Message(type, payload);
			jmsTemplate.convertAndSend(queue, message.toString());
			return "OK";
		}
		catch (JmsException ex) {
			ex.printStackTrace();
			return "FAIL";
		}
	}

}
