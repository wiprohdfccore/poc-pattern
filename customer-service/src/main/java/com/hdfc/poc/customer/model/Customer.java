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
package com.hdfc.poc.customer.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@Table(name = "cutomer")
@EntityListeners(AuditingEntityListener.class)
public class Customer extends BaseEntity {

	public Customer() {
	};

	public Customer(Long id, String customerDetails) {

		this.id = id;
		this.customerDetails = customerDetails;
		// TODO Auto-generated constructor stub
	}

	@Column(name = "creation_date")
	@CreatedDate
	public Date creationDate;

	@Column(name = "last_modified_date", updatable = true)
	@LastModifiedDate
	public Date lastModifiedDate;

	@Column(name = "customer_details")
	public String customerDetails;

	@Column(name = "status")
	public String status;

	public void setStatus(String status) {
		// TODO Auto-generated method stub
		this.status = status;

	}

}
