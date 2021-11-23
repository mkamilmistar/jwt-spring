package com.jwt.userservice.configuration.security.token;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TokenContent {
    private String fullName;
    private Boolean isRegistered;
    private String customerType;
    private String customerId;
    private String userId;
    private String branchNumber;
    private String role;
    private List<String> accessMenu;
    private String tellerId;
    private String tellerUserId;
    private String controlUnit;
    private String parentControlUnit;
}
