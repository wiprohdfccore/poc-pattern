
package com.hdfc.poc.customer.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdfc.poc.customer.dto.CustomerRecord;
import com.hdfc.poc.customer.repositories.CustomerRepository;
import com.hdfc.poc.customer.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService cutomerService;

	@Autowired
	ObjectMapper mapper;

	private final CustomerRepository accounts = null;

	@RequestMapping(value = "/add-update", method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<CustomerRecord> addUpdateCustomer(@RequestBody String custDetails) {
		ResponseEntity<CustomerRecord> cust = null;
		try {
			JsonNode rec = mapper.readValue(custDetails, JsonNode.class);

			JsonNode idJson = rec.get("id");
			JsonNode custDetailsJson = rec.get("customerDetails");

			Long idStr = null;
			String custDetailsStr = null;

			if (idJson != null) {
				idStr = idJson.asLong();
			}

			if (custDetailsJson != null) {
				custDetailsStr = custDetailsJson.toPrettyString();
			}

			// DTO
			CustomerRecord c = new CustomerRecord(idStr, custDetailsStr, null, null, null);

			cust = ResponseEntity.ok().body(cutomerService.updateCustomerDetails(c));
			return cust;

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return cust;
	}

	@RequestMapping(value = "/get-all-customers", method = { RequestMethod.GET })
	public ResponseEntity<List<CustomerRecord>> getAll() {
		ResponseEntity<List<CustomerRecord>> customers = null;
		try {

			customers = ResponseEntity.ok().body(cutomerService.getAllCustomers());
			return customers;

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return customers;
	}

	@RequestMapping(value = "/get-customer", method = { RequestMethod.GET })
	public ResponseEntity<CustomerRecord> getCustomer(@RequestParam String id) {
		ResponseEntity<CustomerRecord> customer = null;
		try {
			customer = ResponseEntity.ok().body(cutomerService.getCustomers(Long.parseLong(id)));
			return customer;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

}
