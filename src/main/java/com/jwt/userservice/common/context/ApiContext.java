package com.jwt.userservice.common.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiContext {

	private HttpHeaders httpHeaders;
	private String userRefId;
	private String customerId;
	private String otpMobileNo;
	private String cid;
	private String passportNo;
	private String tokenScope;
	private String deviceId;
	private String boUserFullName;
	private String boUserId;
	private String authorization;
	private String language;
	private String requestId;
	private String correlationId;
	private String forwardedFor;
	private String userAgent;
	private String platform;
	private String clientVersion;
	private String channelId;
	private String apiKey;
	private String sleuthId;
	private String userName;
	private String userId;
	private String smUniversalId;
	private String clientId;
	private String clientSecret;
	private String secretKey;
	private String timestamp;
	private String mandiriKey;
	private String signature;
	private String role;
	private String branchNumber;
	private String tellerId;
	private String tellerUserId;
	private String controlUnit;
	private String parentControlUnit;
	private String customerType;
	private Boolean isCustomerRegistered;
}
