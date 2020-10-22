package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.CarLoanCompliance;
import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.dto.CarLoanComplianceRequest;
import com.socgen.loanapprovalplatform.dto.FrontDeskApproveRequest;

public interface CarLoanComplianceService {
    void approve(CarLoanCompliance carLoanCompliance, CarLoanComplianceRequest request);
    void reject(CarLoanCompliance carLoanCompliance, CarLoanComplianceRequest request);
}
