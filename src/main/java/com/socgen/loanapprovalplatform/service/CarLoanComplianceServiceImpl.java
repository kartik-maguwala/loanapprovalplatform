package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.domain.CarLoanCompliance;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanComplianceStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanStatus;
import com.socgen.loanapprovalplatform.dto.CarLoanComplianceRequest;
import com.socgen.loanapprovalplatform.repository.CarLoanApplicationRepository;
import com.socgen.loanapprovalplatform.repository.CarLoanComplianceRepository;
import org.springframework.stereotype.Service;

@Service
public class CarLoanComplianceServiceImpl implements CarLoanComplianceService {

    private CarLoanComplianceRepository carLoanComplianceRepository;

    public CarLoanComplianceServiceImpl(CarLoanComplianceRepository carLoanComplianceRepository) {
        this.carLoanComplianceRepository = carLoanComplianceRepository;
    }

    @Override
    public void approve(CarLoanCompliance carLoanCompliance, CarLoanComplianceRequest request) {
        carLoanCompliance.status(CarLoanComplianceStatus.APPROVED).notes(request.getNotes());

        carLoanComplianceRepository.save(carLoanCompliance);
    }

    @Override
    public void reject(CarLoanCompliance carLoanCompliance, CarLoanComplianceRequest request) {

        carLoanCompliance.status(CarLoanComplianceStatus.REJECTED).notes(request.getNotes());
        carLoanComplianceRepository.save(carLoanCompliance);
    }


}
