package com.socgen.loanapprovalplatform.repository;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarLoanApplicationRepository extends JpaRepository<CarLoanApplication, Long> {
}
