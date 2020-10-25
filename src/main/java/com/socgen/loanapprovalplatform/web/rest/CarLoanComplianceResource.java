package com.socgen.loanapprovalplatform.web.rest;

import com.socgen.loanapprovalplatform.domain.CarLoanCompliance;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanComplianceStatus;
import com.socgen.loanapprovalplatform.dto.CarLoanApplicationDetailedResponse;
import com.socgen.loanapprovalplatform.dto.CarLoanComplianceRequest;
import com.socgen.loanapprovalplatform.exception.ApplicationNotFoundException;
import com.socgen.loanapprovalplatform.repository.CarLoanComplianceRepository;
import com.socgen.loanapprovalplatform.service.CarLoanComplianceService;
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

        log.debug("Car compliance REST request to approve CarLoanApplication : {}", request);
        CarLoanCompliance carLoanCompliance = getCarLoanCompliance(applicationid);

        carLoanComplianceService.approve(carLoanCompliance, request);
    }

    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_CARLOANCOMPLIANCE"})
    @PutMapping("/{applicationid}/reject")
    @ResponseStatus(HttpStatus.OK)
    public void reject(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid CarLoanComplianceRequest request) {

        log.debug("Car compliance REST request to reject CarLoanApplication : {}", request);

        CarLoanCompliance loanFrontDesk = getCarLoanCompliance(applicationid);

        carLoanComplianceService.reject(loanFrontDesk, request);
    }

    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_CARLOANCOMPLIANCE"})
    @GetMapping("/pending/{pageNo}/{pageSize}")
    public List<CarLoanApplicationDetailedResponse> getPendingCarLoanApplication(@PathVariable("pageNo") int pageNo,
                                                                                 @PathVariable("pageSize") int pageSize) {

        log.debug("Car compliance REST request to get pending car loan application with pageno-{} and pagesize-{}", pageNo, pageSize);

        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<CarLoanCompliance> carLoanCompliances = carLoanComplianceRepository.findAllByStatus(CarLoanComplianceStatus.PENDING,
                paging);

        return carLoanCompliances.stream()
                .map(carLoanCompliance -> carLoanCompliance.getCarLoanApplication().toDetailedResponseDto())
                .collect(Collectors.toList());

    }

    private CarLoanCompliance getCarLoanCompliance(@PathVariable("applicationid") Long applicationid) {
        return carLoanComplianceRepository.findByCarLoanApplication_IdAndStatus(applicationid, CarLoanComplianceStatus.PENDING)
                .orElseThrow(() -> {
                    return new ApplicationNotFoundException("id-" + applicationid);
                });
    }
}
