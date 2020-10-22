package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.*;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanComplianceStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanFrontDeskStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.RiskComplianceStatus;
import com.socgen.loanapprovalplatform.dto.FrontDeskApproveRequest;
import com.socgen.loanapprovalplatform.repository.CarLoanApplicationRepository;
import com.socgen.loanapprovalplatform.repository.CarLoanComplianceRepository;
import com.socgen.loanapprovalplatform.repository.LoanFrontDeskRepository;
import com.socgen.loanapprovalplatform.repository.RiskComplianceRepository;
import org.springframework.stereotype.Service;

@Service
public class FrontDeskServiceImpl implements FrontDeskService {

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
                        .carLoanApplicationId(loanFrontDesk.getCarLoanApplicationId()));

        riskComplianceRepository.save(
                new RiskCompliance()
                        .status(RiskComplianceStatus.PENDING)
                        .carLoanApplicationId(loanFrontDesk.getCarLoanApplicationId()));
    }

    @Override
    public void reject(LoanFrontDesk loanFrontDesk, FrontDeskApproveRequest request) {

        loanFrontDesk.status(LoanFrontDeskStatus.REJECTED).notes(request.getNotes());
        loanFrontDeskRepository.save(loanFrontDesk);

        CarLoanApplication carLoanApplication = carLoanApplicationRepository.findById(loanFrontDesk.getCarLoanApplicationId()).get();
        carLoanApplication.setStatus(CarLoanStatus.REJECTED);
        carLoanApplicationRepository.save(carLoanApplication);
    }
}
