package com.hdfc.poc.payment.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.hdfc.poc.payment.repositories.TransactionRepository;

import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BaseServiceTest {

	@Mock
	private TransactionRepository transactionRepository;

	private BaseService baseService;

	@BeforeEach
	void setUp() {
		this.baseService = new BaseService(this.transactionRepository);
	}

	@Test
	void getAllTransactionsTest() {
		baseService.getAllTransactions();
		verify(transactionRepository).findAll();
	}

}