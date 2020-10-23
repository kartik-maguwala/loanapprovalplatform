package com.socgen.loanapprovalplatform.repository;

import com.socgen.loanapprovalplatform.domain.RiskCompliance;
import com.socgen.loanapprovalplatform.domain.enumeration.RiskComplianceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiskComplianceRepository extends JpaRepository<RiskCompliance, Long> {
    Optional<RiskCompliance> findByCarLoanApplication_IdAndStatus(Long id, RiskComplianceStatus status);

    Page<RiskCompliance> findAllByStatus(RiskComplianceStatus status, Pageable paging);
}
