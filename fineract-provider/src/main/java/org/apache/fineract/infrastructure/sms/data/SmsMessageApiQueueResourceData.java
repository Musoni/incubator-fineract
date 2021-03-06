/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.infrastructure.sms.data;

import com.google.gson.Gson;

/** 
 * Immutable data object representing the API request body sent in the POST request to
 * the "/queue" resource 
 **/
public class SmsMessageApiQueueResourceData {
	private Long internalId;
	private String fineractTenantIdentifier;
	private String createdOnDate;
	private String sourceAddress;
	private String mobileNumber;
	private String message;
	
	/** 
	 * SmsMessageApiQueueResourceData constructor
	 **/
	private SmsMessageApiQueueResourceData(Long internalId, String fineractTenantIdentifier, String createdOnDate, 
			String sourceAddress, String mobileNumber, String message) {
		this.internalId = internalId;
		this.fineractTenantIdentifier = fineractTenantIdentifier;
		this.createdOnDate = createdOnDate;
		this.sourceAddress = sourceAddress;
		this.mobileNumber = mobileNumber;
		this.message = message;
	}
	
	/** 
	 * SmsMessageApiQueueResourceData constructor
	 **/
	protected SmsMessageApiQueueResourceData() {}
	
	/** 
	 * @return a new instance of the SmsMessageApiQueueResourceData class 
	 **/
	public static final SmsMessageApiQueueResourceData instance(Long internalId, String fineractTenantIdentifier, String createdOnDate, 
			String sourceAddress, String mobileNumber, String message) {
		
		return new SmsMessageApiQueueResourceData(internalId, fineractTenantIdentifier, createdOnDate, sourceAddress, 
				mobileNumber, message);
	}
	
	/**
	 * @return the internalId
	 */
	public Long getInternalId() {
		return internalId;
	}
	
	/**
	 * @return the fineractTenantIdentifier
	 */
	public String getFineractTenantIdentifier() {
		return fineractTenantIdentifier;
	}
	
	/**
	 * @return the createdOnDate
	 */
	public String getCreatedOnDate() {
		return createdOnDate;
	}

	/**
	 * @return the sourceAddress
	 */
	public String getSourceAddress() {
		return sourceAddress;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/** 
	 * @return JSON representation of the object 
	 **/
	public String toJsonString() {
		Gson gson = new Gson();
		
		return gson.toJson(this);
	}
}
