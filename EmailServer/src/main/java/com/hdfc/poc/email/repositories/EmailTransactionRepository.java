
package com.hdfc.poc.email.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdfc.poc.email.model.EmailTransaction;

public interface EmailTransactionRepository extends JpaRepository<EmailTransaction, Integer> {

}
