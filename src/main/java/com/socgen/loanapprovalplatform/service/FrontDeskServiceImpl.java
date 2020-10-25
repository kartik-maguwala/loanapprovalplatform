package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.domain.CarLoanCompliance;
import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.domain.RiskCompliance;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanComplianceStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanFrontDeskStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.RiskComplianceStatus;
import com.socgen.loanapprovalplatform.dto.FrontDeskApproveRequest;
import com.socgen.loanapprovalplatform.exception.ApplicationNotFoundException;
import com.socgen.loanapprovalplatform.repository.CarLoanApplicationRepository;
import com.socgen.loanapprovalplatform.repository.CarLoanComplianceRepository;
import com.socgen.loanapprovalplatform.repository.LoanFrontDeskRepository;
import com.socgen.loanapprovalplatform.repository.RiskComplianceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FrontDeskServiceImpl implements FrontDeskService {

    private final Logger log = LoggerFactory.getLogger(FrontDeskServiceImpl.class);


    private LoanFrontDeskRepository loanFrontDeskRepository;
    private CarLoanApplicationRepository carLoanApplicationRepository;
    private CarLoanComplianceRepository carLoanComplianceRepository;
    private RiskComplianceRepository riskComplianceRepository;

    public FrontDeskServiceImpl(LoanFrontDeskRepository loanFrontDeskRepository,
                                CarLoanApplicationRepository carLoanApplicationRepository, CarLoanComplianceRepository carLoanComplianceRepository,
                                RiskComplianceRepository riskComplianceRepository) {
        this.loanFrontDeskRepository = loanFrontDeskRepository;
        this.carLoanApplicationRepository = carLoanApplicationRepository;
        this.carLoanComplianceRepository = carLoanComplianceRepository;
        this.riskComplianceRepository = riskComplianceRepository;
    }

    @Override
    public void approve(LoanFrontDesk loanFrontDesk, FrontDeskApproveRequest request) {

        loanFrontDesk.status(LoanFrontDeskStatus.APPROVED).notes(request.getNotes());

        loanFrontDeskRepository.save(loanFrontDesk);
        // Add entry in CarLoan and Compliance
        carLoanComplianceRepository.save(
                new CarLoanCompliance()
                        .status(CarLoanComplianceStatus.PENDING)
                        .carLoanApplication(loanFrontDesk.getCarLoanApplication()));

        riskComplianceRepository.save(
                new RiskCompliance()
                        .status(RiskComplianceStatus.PENDING)
                        .carLoanApplication(loanFrontDesk.getCarLoanApplication()));

        CarLoanApplication carLoanApplication = getCarLoanApplicationFrom(loanFrontDesk);

        log.debug("Car loan approving by front desk department -> " + carLoanApplication);
        carLoanApplication.setStatus(CarLoanStatus.INPROCESS);
        carLoanApplicationRepository.save(carLoanApplication);
    }

    @Override
    public void reject(LoanFrontDesk loanFrontDesk, FrontDeskApproveRequest request) {

        loanFrontDesk.status(LoanFrontDeskStatus.REJECTED).notes(request.getNotes());
        loanFrontDeskRepository.save(loanFrontDesk);

        CarLoanApplication carLoanApplication = getCarLoanApplicationFrom(loanFrontDesk);

        log.debug("Car loan rejecting by front desk department -> " + carLoanApplication);
        carLoanApplication.setStatus(CarLoanStatus.REJECTED);
        carLoanApplicationRepository.save(carLoanApplication);
    }

    private CarLoanApplication getCarLoanApplicationFrom(LoanFrontDesk loanFrontDesk) {
        return carLoanApplicationRepository.findById(loanFrontDesk.getCarLoanApplicationId())
                .orElseThrow(() ->
                        new ApplicationNotFoundException("id-" + loanFrontDesk.getCarLoanApplicationId())
                );
    }
}
