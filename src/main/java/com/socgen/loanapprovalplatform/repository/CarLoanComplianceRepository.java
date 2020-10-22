package com.socgen.loanapprovalplatform.repository;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.domain.CarLoanCompliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarLoanComplianceRepository extends JpaRepository<CarLoanCompliance, Long> {
}
