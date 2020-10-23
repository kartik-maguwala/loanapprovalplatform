package com.socgen.loanapprovalplatform.repository;

import com.socgen.loanapprovalplatform.domain.CarLoanCompliance;
import com.socgen.loanapprovalplatform.domain.CarLoanDisburseInfo;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanComplianceStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanDisburseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarLoanDisburseInfoRepository extends JpaRepository<CarLoanDisburseInfo, Long> {
    Optional<CarLoanDisburseInfo> findByCarLoanApplicationIdAndStatus(Long id, CarLoanDisburseStatus status);
}
