package com.hdfc.poc.customer.dto;

import java.util.Date;

import org.json.JSONObject;

public record CustomerRecord(Long id, String customerDetails, Date creationDate, Date lastModifiedDate, String status) {

}