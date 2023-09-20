package com.hdfc.poc.customer.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MessageSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	ObjectMapper mapper;

	@Value("${AUDIT.QUEUE}")
	private String auditQueue;

	@Value("${EMAIL.QUEUE}")
	private String emailQueue;

	public <T> String sendAuditMessage(String type, T payload) {
		return sendMessage(type, payload, auditQueue);
	}

	public <T> String sendEmailMessage(String type, T payload) {
		return sendMessage(type, payload, emailQueue);
	}

	private <T> String sendMessage(String type, T payload, String queue) {
		try {
			Message<T> message = new Message<T>(type, payload);
			try {

				String mJson = mapper.writeValueAsString(message);
				jmsTemplate.convertAndSend(queue, mJson);

			}
			catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "OK";
		}
		catch (JmsException ex) {
			ex.printStackTrace();
			return "FAIL";
		}
	}

}
