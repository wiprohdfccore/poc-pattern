
package com.hdfc.poc.email.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.poc.email.model.EmailTransaction;
import com.hdfc.poc.email.services.EmailService;

@RestController
@RequestMapping("/email/customers")
public class EmailController {

	@Autowired
	EmailService emailService;

	@RequestMapping(value = "/get-todays-email", method = { RequestMethod.GET })
	public ResponseEntity<List<EmailTransaction>> getTodadysEmail() {
		ResponseEntity<List<EmailTransaction>> tx = null;
		try {

			// docs = baseService.findAllBaseMethod();
			tx = ResponseEntity.ok().body(emailService.getTodadysEmail());
			return tx;

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return tx;
	}

}
