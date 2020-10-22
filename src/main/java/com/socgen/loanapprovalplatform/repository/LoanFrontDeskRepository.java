package com.socgen.loanapprovalplatform.repository;

import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanFrontDeskRepository extends JpaRepository<LoanFrontDesk, Long> {
    Optional<LoanFrontDesk> findByCarLoanApplicationId(Long id);
}
