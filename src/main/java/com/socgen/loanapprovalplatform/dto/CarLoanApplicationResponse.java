package com.socgen.loanapprovalplatform.dto;

public class CarLoanApplicationResponse {
    private Long applicationId;

    public CarLoanApplicationResponse(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getApplicationId() {
        return applicationId;
    }
}
