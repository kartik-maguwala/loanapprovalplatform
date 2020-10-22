package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.*;
import com.socgen.loanapprovalplatform.dto.FrontDeskApproveRequest;
import com.socgen.loanapprovalplatform.repository.CarLoanComplianceRepository;
import com.socgen.loanapprovalplatform.repository.LoanFrontDeskRepository;
import com.socgen.loanapprovalplatform.repository.RiskComplianceRepository;
import org.springframework.stereotype.Service;

@Service
public class FrontDeskServiceImpl implements FrontDeskService {

    private LoanFrontDeskRepository loanFrontDeskRepository;
    private CarLoanComplianceRepository carLoanComplianceRepository;
    private RiskComplianceRepository riskComplianceRepository;

    public FrontDeskServiceImpl(LoanFrontDeskRepository loanFrontDeskRepository,
                                CarLoanComplianceRepository carLoanComplianceRepository,
                                RiskComplianceRepository riskComplianceRepository) {
        this.loanFrontDeskRepository = loanFrontDeskRepository;
        this.carLoanComplianceRepository = carLoanComplianceRepository;
        this.riskComplianceRepository = riskComplianceRepository;
    }

    @Override
    public void approve(LoanFrontDesk loanFrontDesk, FrontDeskApproveRequest request) {
        // Update LoanFrontDesk
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
}
