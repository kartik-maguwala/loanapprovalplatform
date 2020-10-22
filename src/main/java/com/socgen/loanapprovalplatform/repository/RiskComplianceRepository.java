package com.socgen.loanapprovalplatform.repository;

import com.socgen.loanapprovalplatform.domain.RiskCompliance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskComplianceRepository extends JpaRepository<RiskCompliance, Long> {
}
