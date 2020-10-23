package com.socgen.loanapprovalplatform.repository;

import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.domain.RiskCompliance;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanFrontDeskStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.RiskComplianceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiskComplianceRepository extends JpaRepository<RiskCompliance, Long> {
    Optional<RiskCompliance> findByCarLoanApplicationIdAndStatus(Long id, RiskComplianceStatus status);
}
