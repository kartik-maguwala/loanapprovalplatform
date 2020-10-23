package com.socgen.loanapprovalplatform.repository;

import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanFrontDeskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanFrontDeskRepository extends JpaRepository<LoanFrontDesk, Long> {
    Optional<LoanFrontDesk> findByCarLoanApplication_IdAndStatus(Long id, LoanFrontDeskStatus status);

    Page<LoanFrontDesk> findAllByStatus(LoanFrontDeskStatus status, Pageable pageable);

}
