package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanFrontDeskStatus;
import com.socgen.loanapprovalplatform.repository.CarLoanApplicationRepository;
import com.socgen.loanapprovalplatform.repository.LoanFrontDeskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CarLoanApplicationServiceImpl implements CarLoanApplicationService {

    private final Logger log = LoggerFactory.getLogger(CarLoanApplicationServiceImpl.class);


    private CarLoanApplicationRepository carLoanApplicationRepository;
    private LoanFrontDeskRepository loanFrontDeskRepository;

    public CarLoanApplicationServiceImpl(CarLoanApplicationRepository carLoanApplicationRepository,
                                         LoanFrontDeskRepository loanFrontDeskRepository) {
        this.carLoanApplicationRepository = carLoanApplicationRepository;
        this.loanFrontDeskRepository = loanFrontDeskRepository;
    }

    @Override
    public CarLoanApplication applyLoan(CarLoanApplication carLoanApplication) {

        log.debug("Customer applying for the car loan with detail -> "+ carLoanApplication);

        CarLoanApplication returnedCarLoanApplication = carLoanApplicationRepository.save(carLoanApplication);
        loanFrontDeskRepository.save(new LoanFrontDesk().carLoanApplication(returnedCarLoanApplication).status(LoanFrontDeskStatus.PENDING));
        return returnedCarLoanApplication;
    }
}

