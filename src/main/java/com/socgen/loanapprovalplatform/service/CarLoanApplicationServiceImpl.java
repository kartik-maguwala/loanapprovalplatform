package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanFrontDeskStatus;
import com.socgen.loanapprovalplatform.repository.CarLoanApplicationRepository;
import com.socgen.loanapprovalplatform.repository.LoanFrontDeskRepository;
import org.springframework.stereotype.Service;

@Service
public class CarLoanApplicationServiceImpl implements CarLoanApplicationService {

    private CarLoanApplicationRepository carLoanApplicationRepository;
    private LoanFrontDeskRepository loanFrontDeskRepository;

    public CarLoanApplicationServiceImpl(CarLoanApplicationRepository carLoanApplicationRepository,
                                         LoanFrontDeskRepository loanFrontDeskRepository) {
        this.carLoanApplicationRepository = carLoanApplicationRepository;
        this.loanFrontDeskRepository = loanFrontDeskRepository;
    }

    @Override
    public CarLoanApplication applyLoan(CarLoanApplication carLoanApplication) {
        CarLoanApplication returnedCarLoanApplication = carLoanApplicationRepository.save(carLoanApplication);
//        loanFrontDeskRepository.save(new LoanFrontDesk().carLoanApplicationId(returnedCarLoanApplication.getId()).status(LoanFrontDeskStatus.PENDING));
        loanFrontDeskRepository.save(new LoanFrontDesk().carLoanApplication(returnedCarLoanApplication).status(LoanFrontDeskStatus.PENDING));
        return returnedCarLoanApplication;
    }
}

