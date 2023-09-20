/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hdfc.poc.customer.services;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdfc.poc.customer.dto.CustomerRecord;
import com.hdfc.poc.customer.messaging.MessageSender;
import com.hdfc.poc.customer.model.Customer;
import com.hdfc.poc.customer.repositories.CustomerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class CustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	MessageSender messageSender;

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	ObjectMapper mapper;

	@PersistenceContext
	private EntityManager entityManager;

	public CustomerService(CustomerRepository customerRepo2) {
		// TODO Auto-generated constructor stub
	}

	public CustomerRecord updateCustomerDetails(CustomerRecord cust) {

		Customer c = new Customer(cust.id(), cust.customerDetails().toString());

		if (c != null && c.isNew()) {
			c.setStatus("CREATED");
		}
		else {
			c.setStatus("UPDATED");
		}
		c = customerRepo.save(c);

		cust = mapper.convertValue(c, CustomerRecord.class);

		logger.info("Log level: INFO");
		logger.info("###### CustomerDetails #####:");
		logger.info(" Customer : " + cust);
		messageSender.sendEmailMessage("customer-update-audit", cust);
		messageSender.sendAuditMessage("customer-update-audit", cust);
		return cust;

	}

	public List<CustomerRecord> getAllCustomers() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CustomerRecord> query = cb.createQuery(CustomerRecord.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(cb.construct(CustomerRecord.class, root.get("id"), root.get("customerDetails")));
		return entityManager.createQuery(query).getResultList();
	}

	public CustomerRecord getCustomers(long id) {
		Customer c = customerRepo.findById(id).get();
		return mapper.convertValue(c, CustomerRecord.class);
	}

}
