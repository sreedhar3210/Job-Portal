package com.JobPortal.response;

public class SalesforceResponse {
    private String accountId;
    private String status;
    
    // Add getters and setters (or use Lombok @Data)
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
}