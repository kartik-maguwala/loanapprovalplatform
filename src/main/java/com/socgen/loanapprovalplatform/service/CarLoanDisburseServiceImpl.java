package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.domain.CarLoanDisburseInfo;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanDisburseStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanStatus;
import com.socgen.loanapprovalplatform.dto.CarLoanDisburseRequest;
import com.socgen.loanapprovalplatform.exception.ApplicationNotFoundException;
import com.socgen.loanapprovalplatform.repository.CarLoanApplicationRepository;
import com.socgen.loanapprovalplatform.repository.CarLoanDisburseInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CarLoanDisburseServiceImpl implements CarLoanDisburseService {

    private final Logger log = LoggerFactory.getLogger(CarLoanDisburseServiceImpl.class);


    private CarLoanDisburseInfoRepository carLoanDisburseInfoRepository;
    private CarLoanApplicationRepository carLoanApplicationRepository;

    public CarLoanDisburseServiceImpl(CarLoanDisburseInfoRepository carLoanDisburseInfoRepository,
                                      CarLoanApplicationRepository carLoanApplicationRepository) {
        this.carLoanDisburseInfoRepository = carLoanDisburseInfoRepository;
        this.carLoanApplicationRepository = carLoanApplicationRepository;
    }

    @Override
    public void disburse(CarLoanDisburseInfo carLoanDisburseInfo, CarLoanDisburseRequest request) {


        carLoanDisburseInfo.branchifsc(request.getBranchIfsc())
                .disbursedAmount(request.getDisburseAmount())
                .disbursedOn(request.getDisbursementDate())
                .status(CarLoanDisburseStatus.COMPLETED)
                .transactionid(request.getTransactionId());
        carLoanDisburseInfoRepository.save(carLoanDisburseInfo);

        CarLoanApplication carLoanApplication = carLoanApplicationRepository.findById(carLoanDisburseInfo.getCarLoanApplicationId())
                .orElseThrow(() ->
                        new ApplicationNotFoundException("id-" + carLoanDisburseInfo.getCarLoanApplicationId())
                );

        log.debug("Car Loan disbursing -> " + carLoanApplication);

        carLoanApplication.setStatus(CarLoanStatus.DISBURSED);
        carLoanApplicationRepository.save(carLoanApplication);
    }
}
