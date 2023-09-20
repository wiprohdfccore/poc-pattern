
package com.hdfc.poc.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdfc.poc.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
