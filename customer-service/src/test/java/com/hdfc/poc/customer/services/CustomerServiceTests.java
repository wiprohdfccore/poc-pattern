package com.hdfc.poc.customer.services;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.hdfc.poc.customer.repositories.CustomerRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

	@Mock
	private CustomerRepository customerRepo;

	CustomerService cutomerService;

	@BeforeEach
	void setup() {
		this.cutomerService = new CustomerService(this.customerRepo);
	}

	@Test
	void getCustomersTest() {
		cutomerService.getCustomers(3);
		verify(customerRepo).findById((long) 3);
	}

}
