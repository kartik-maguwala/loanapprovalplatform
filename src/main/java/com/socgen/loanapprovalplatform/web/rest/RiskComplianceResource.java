package com.socgen.loanapprovalplatform.web.rest;

import com.socgen.loanapprovalplatform.domain.RiskCompliance;
import com.socgen.loanapprovalplatform.domain.enumeration.RiskComplianceStatus;
import com.socgen.loanapprovalplatform.dto.CarLoanApplicationDetailedResponse;
import com.socgen.loanapprovalplatform.dto.RiskComplianceRequest;
import com.socgen.loanapprovalplatform.exception.ApplicationNotFoundException;
import com.socgen.loanapprovalplatform.repository.RiskComplianceRepository;
import com.socgen.loanapprovalplatform.service.RiskComplianceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_RISKCOMPLIANCE"})
    @PutMapping("/{applicationid}/approve")
    @ResponseStatus(HttpStatus.OK)
    public void approve(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid RiskComplianceRequest request) {

        log.debug("Risk compliance REST request to approve CarLoanApplication : {}", request);

        RiskCompliance riskCompliance = getRiskCompliance(applicationid);

        riskComplianceService.approve(riskCompliance, request);
    }

    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_RISKCOMPLIANCE"})
    @PutMapping("/{applicationid}/reject")
    @ResponseStatus(HttpStatus.OK)
    public void reject(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid RiskComplianceRequest request) {

        log.debug("Risk compliance REST request to reject CarLoanApplication : {}", request);

        RiskCompliance riskCompliance = getRiskCompliance(applicationid);

        riskComplianceService.reject(riskCompliance, request);
    }

    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_RISKCOMPLIANCE"})
    @GetMapping("/pending/{pageNo}/{pageSize}")
    public List<CarLoanApplicationDetailedResponse> getPendingCarLoanApplication(@PathVariable("pageNo") int pageNo,
                                                                                 @PathVariable("pageSize") int pageSize) {

        log.debug("Risk compliance REST request to get pending car loan application with pageno-{} and pagesize-{}", pageNo, pageSize);

        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<RiskCompliance> riskCompliances = riskComplianceRepository.findAllByStatus(RiskComplianceStatus.PENDING,
                paging);

        return riskCompliances.stream()
                .map(riskCompliance -> riskCompliance.getCarLoanApplication().toDetailedResponseDto())
                .collect(Collectors.toList());

    }

    private RiskCompliance getRiskCompliance(@PathVariable("applicationid") Long applicationid) {
        return riskComplianceRepository.findByCarLoanApplication_IdAndStatus(applicationid, RiskComplianceStatus.PENDING)
                .orElseThrow(() -> {
                    return new ApplicationNotFoundException("id-" + applicationid);
                });
    }
}
