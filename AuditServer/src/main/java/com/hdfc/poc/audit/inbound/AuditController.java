
package com.hdfc.poc.audit.inbound;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.poc.audit.model.AuditTransaction;
import com.hdfc.poc.audit.services.AuditService;

@RestController
@RequestMapping("/payment/accounts")
public class AuditController {

	@Autowired
	AuditService auditService;

	@RequestMapping(value = "/audit-transactions", method = { RequestMethod.GET })
	public ResponseEntity<List<AuditTransaction>> getAudits() {
		ResponseEntity<List<AuditTransaction>> tx = null;
		try {

			// docs = baseService.findAllBaseMethod();
			tx = ResponseEntity.ok().body(auditService.getAudts());
			return tx;

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return tx;
	}

}
