package com.socgen.loanapprovalplatform.web.rest;

import com.socgen.loanapprovalplatform.domain.CarLoanCompliance;
import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanComplianceStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanFrontDeskStatus;
import com.socgen.loanapprovalplatform.dto.CarLoanComplianceRequest;
import com.socgen.loanapprovalplatform.dto.FrontDeskApproveRequest;
import com.socgen.loanapprovalplatform.exception.ApplicationNotFoundException;
import com.socgen.loanapprovalplatform.repository.CarLoanComplianceRepository;
import com.socgen.loanapprovalplatform.service.CarLoanComplianceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/clc")
@Transactional
public class CarLoanComplianceResource {
    private final Logger log = LoggerFactory.getLogger(CarLoanComplianceResource.class);

    private CarLoanComplianceRepository carLoanComplianceRepository;
    private CarLoanComplianceService carLoanComplianceService;

    public CarLoanComplianceResource(CarLoanComplianceRepository carLoanComplianceRepository, CarLoanComplianceService carLoanComplianceService) {
        this.carLoanComplianceRepository = carLoanComplianceRepository;
        this.carLoanComplianceService = carLoanComplianceService;
    }


    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_CARLOANCOMPLIANCE"})
    @PutMapping("/{applicationid}/approve")
    @ResponseStatus(HttpStatus.OK)
    public void approve(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid CarLoanComplianceRequest request) {

        CarLoanCompliance carLoanCompliance = getCarLoanCompliance(applicationid);

        carLoanComplianceService.approve(carLoanCompliance, request);
    }

    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_CARLOANCOMPLIANCE"})
    @PutMapping("/{applicationid}/reject")
    @ResponseStatus(HttpStatus.OK)
    public void reject(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid CarLoanComplianceRequest request) {

        CarLoanCompliance loanFrontDesk = getCarLoanCompliance(applicationid);

        carLoanComplianceService.reject(loanFrontDesk, request);
    }

    private CarLoanCompliance getCarLoanCompliance(@PathVariable("applicationid") Long applicationid) {
        return carLoanComplianceRepository.findByCarLoanApplicationIdAndStatus(applicationid, CarLoanComplianceStatus.PENDING)
                .orElseThrow(() -> {
                    return new ApplicationNotFoundException("id-" + applicationid);
                });
    }
}
