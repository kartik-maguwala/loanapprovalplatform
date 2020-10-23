package com.socgen.loanapprovalplatform.repository;

import com.socgen.loanapprovalplatform.domain.CarLoanCompliance;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanComplianceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarLoanComplianceRepository extends JpaRepository<CarLoanCompliance, Long> {
    Optional<CarLoanCompliance> findByCarLoanApplication_IdAndStatus(Long id, CarLoanComplianceStatus status);

    Page<CarLoanCompliance> findAllByStatus(CarLoanComplianceStatus status, Pageable paging);
}
