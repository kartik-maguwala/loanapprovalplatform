package com.socgen.loanapprovalplatform.repository;

import com.socgen.loanapprovalplatform.domain.CarLoanDisburseInfo;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanDisburseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarLoanDisburseInfoRepository extends JpaRepository<CarLoanDisburseInfo, Long> {
    Optional<CarLoanDisburseInfo> findByCarLoanApplication_IdAndStatus(Long id, CarLoanDisburseStatus status);

    Page<CarLoanDisburseInfo> findAllByStatus(CarLoanDisburseStatus status, Pageable paging);

}
