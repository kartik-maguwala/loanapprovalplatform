package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.CarLoanCompliance;
import com.socgen.loanapprovalplatform.domain.RiskCompliance;
import com.socgen.loanapprovalplatform.dto.CarLoanComplianceRequest;
import com.socgen.loanapprovalplatform.dto.RiskComplianceRequest;

public interface RiskComplianceService {
    void approve(RiskCompliance riskCompliance, RiskComplianceRequest request);
    void reject(RiskCompliance carLoanCompliance, RiskComplianceRequest request);
}
