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

package com.hdfc.poc.email.services;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdfc.poc.email.model.EmailTransaction;
import com.hdfc.poc.email.repositories.EmailTransactionRepository;

@Service
public class EmailService {

	Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	EmailTransactionRepository emailRepo;

	@Autowired
	ObjectMapper mapper;

	public void createEmailTransaction(String txString) {
		JSONObject jsonTx = new JSONObject(txString);

		EmailTransaction tx = null;
		try {
			tx = mapper.readValue(jsonTx.get("data").toString(), EmailTransaction.class);
			logger.info("Log level: INFO");
			logger.info("###### emailTransction #####:");
			logger.info(" Transction : " + tx);
		}
		catch (JsonProcessingException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		emailRepo.save(tx);

	}

	public List<EmailTransaction> getTodadysEmail() {
		// TODO Auto-generated method stub
		return emailRepo.findAll();
	}

}
