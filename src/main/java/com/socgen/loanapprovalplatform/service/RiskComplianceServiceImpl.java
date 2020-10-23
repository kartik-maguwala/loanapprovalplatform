package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.domain.CarLoanDisburseInfo;
import com.socgen.loanapprovalplatform.domain.RiskCompliance;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanComplianceStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanDisburseStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.RiskComplianceStatus;
import com.socgen.loanapprovalplatform.dto.RiskComplianceRequest;
import com.socgen.loanapprovalplatform.repository.CarLoanApplicationRepository;
import com.socgen.loanapprovalplatform.repository.CarLoanDisburseInfoRepository;
import com.socgen.loanapprovalplatform.repository.RiskComplianceRepository;
import org.springframework.stereotype.Service;

@Service
public class RiskComplianceServiceImpl implements RiskComplianceService {

    private RiskComplianceRepository riskComplianceRepository;
    private CarLoanDisburseInfoRepository carLoanDisburseInfoRepository;
    private CarLoanApplicationRepository carLoanApplicationRepository;

    public RiskComplianceServiceImpl(RiskComplianceRepository riskComplianceRepository,
                                     CarLoanDisburseInfoRepository carLoanDisburseInfoRepository,
                                     CarLoanApplicationRepository carLoanApplicationRepository) {
        this.riskComplianceRepository = riskComplianceRepository;
        this.carLoanDisburseInfoRepository = carLoanDisburseInfoRepository;
        this.carLoanApplicationRepository = carLoanApplicationRepository;
    }

    @Override
    public void approve(RiskCompliance riskCompliance, RiskComplianceRequest request) {
        riskCompliance.status(RiskComplianceStatus.APPROVED).notes(request.getNotes());
        riskComplianceRepository.save(riskCompliance);

        CarLoanApplication carLoanApplication = carLoanApplicationRepository.findById(riskCompliance.getCarLoanApplicationId()).get();

        carLoanDisburseInfoRepository.save(
                new CarLoanDisburseInfo()
                        .disbursedAmount(carLoanApplication.getAmount())
                        .status(CarLoanDisburseStatus.PENDING)
                        .carLoanApplicationId(riskCompliance.getCarLoanApplicationId()));


        carLoanApplication.setStatus(CarLoanStatus.APPROVED);
        carLoanApplicationRepository.save(carLoanApplication);
    }

    @Override
    public void reject(RiskCompliance riskCompliance, RiskComplianceRequest request) {
        riskCompliance.status(RiskComplianceStatus.REJECTED).notes(request.getNotes());
        riskComplianceRepository.save(riskCompliance);

        CarLoanApplication carLoanApplication = carLoanApplicationRepository.findById(riskCompliance.getCarLoanApplicationId()).get();
        carLoanApplication.setStatus(CarLoanStatus.REJECTED);
        carLoanApplicationRepository.save(carLoanApplication);
    }
}
