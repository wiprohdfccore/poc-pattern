package com.hdfc.poc.email.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdfc.poc.email.services.EmailService;

@Service
public class MessageListener {

	Logger logger = LoggerFactory.getLogger(MessageListener.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${ibm.mq.queue}")
	private String queue;

	@Autowired
	private EmailService emailService;

	@Autowired
	ObjectMapper mapper;

	@JmsListener(destination = "${ibm.mq.queue}")
	public <T> void recv(String m) {

		Message message;
		try {
			message = mapper.readValue(m, Message.class);
			logger.info(" ########################### Converted to mesagge with Payload" + message.getData()
					+ " ############################## ");
			logger.info("Received <" + m + ">");
			emailService.createEmailTransaction(m);
		}
		catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
