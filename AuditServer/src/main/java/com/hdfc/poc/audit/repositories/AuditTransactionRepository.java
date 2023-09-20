
package com.hdfc.poc.audit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdfc.poc.audit.model.AuditTransaction;

public interface AuditTransactionRepository extends JpaRepository<AuditTransaction, Integer> {

}
