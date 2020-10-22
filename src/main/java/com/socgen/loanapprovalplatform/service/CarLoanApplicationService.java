package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import org.springframework.stereotype.Service;

public interface CarLoanApplicationService {

    CarLoanApplication applyLoan(CarLoanApplication carLoanApplication);
}
