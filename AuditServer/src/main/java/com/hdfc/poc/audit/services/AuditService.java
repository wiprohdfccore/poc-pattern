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

package com.hdfc.poc.audit.services;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdfc.poc.audit.model.AuditTransaction;
import com.hdfc.poc.audit.repositories.AuditTransactionRepository;

@Service
public class AuditService {

	Logger logger = LoggerFactory.getLogger(AuditService.class);

	@Autowired
	AuditTransactionRepository auditRepo;

	@Autowired
	ObjectMapper mapper;

	public void auditTransaction(String txString) {
		JSONObject jsonTx = new JSONObject(txString);

		AuditTransaction tx = null;
		try {
			tx = mapper.readValue(jsonTx.get("data").toString(), AuditTransaction.class);
			logger.info("Log level: INFO");
			logger.info("###### auditlTransction #####:");
			logger.info(" Transction : " + tx);
		}
		catch (JsonProcessingException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		auditRepo.save(tx);

	}

	public List<AuditTransaction> getAudts() {
		// TODO Auto-generated method stub
		return auditRepo.findAll();
	}

}
