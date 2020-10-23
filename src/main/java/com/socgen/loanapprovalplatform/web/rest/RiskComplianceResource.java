package com.socgen.loanapprovalplatform.web.rest;

import com.socgen.loanapprovalplatform.domain.CarLoanCompliance;
import com.socgen.loanapprovalplatform.domain.RiskCompliance;
import com.socgen.loanapprovalplatform.domain.enumeration.RiskComplianceStatus;
import com.socgen.loanapprovalplatform.dto.CarLoanComplianceRequest;
import com.socgen.loanapprovalplatform.dto.RiskComplianceRequest;
import com.socgen.loanapprovalplatform.exception.ApplicationNotFoundException;
import com.socgen.loanapprovalplatform.repository.RiskComplianceRepository;
import com.socgen.loanapprovalplatform.service.RiskComplianceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/rc")
@Transactional
public class RiskComplianceResource {
    private final Logger log = LoggerFactory.getLogger(CarLoanComplianceResource.class);

    private RiskComplianceRepository riskComplianceRepository;
    private RiskComplianceService riskComplianceService;

    public RiskComplianceResource(RiskComplianceRepository riskComplianceRepository,
                                  RiskComplianceService riskComplianceService) {
        this.riskComplianceRepository = riskComplianceRepository;
        this.riskComplianceService = riskComplianceService;
    }

    @PutMapping("/{applicationid}/approve")
    @ResponseStatus(HttpStatus.OK)
    public void approve(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid RiskComplianceRequest request) {

        RiskCompliance riskCompliance = getRiskCompliance(applicationid);

        riskComplianceService.approve(riskCompliance, request);
    }

    @PutMapping("/{applicationid}/reject")
    @ResponseStatus(HttpStatus.OK)
    public void reject(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid RiskComplianceRequest request) {

        RiskCompliance riskCompliance = getRiskCompliance(applicationid);

        riskComplianceService.reject(riskCompliance, request);
    }

    private RiskCompliance getRiskCompliance(@PathVariable("applicationid") Long applicationid) {
        return riskComplianceRepository.findByCarLoanApplicationIdAndStatus(applicationid, RiskComplianceStatus.PENDING)
                .orElseThrow(() -> {
                    return new ApplicationNotFoundException("id-" + applicationid);
                });
    }
}
