package com.hdfc.poc.audit.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdfc.poc.audit.services.AuditService;

@Service
public class MessageListener {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${ibm.mq.queue}")
	private String queue;

	@Autowired
	private AuditService auditService;

	@Autowired
	ObjectMapper mapper;

	@JmsListener(destination = "${ibm.mq.queue}")
	public <T> void recv(String m) {

		Message message;
		try {
			message = mapper.readValue(m, Message.class);
			System.out.println(" ########################### Converted to mesagge with Payload" + message.getData()
					+ " ############################## ");
			System.out.println("Received <" + m + ">");
			auditService.auditTransaction(m);
		}
		catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
