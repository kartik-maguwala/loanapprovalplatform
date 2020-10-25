package com.socgen.loanapprovalplatform.web.rest;

import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanFrontDeskStatus;
import com.socgen.loanapprovalplatform.dto.CarLoanApplicationDetailedResponse;
import com.socgen.loanapprovalplatform.dto.FrontDeskApproveRequest;
import com.socgen.loanapprovalplatform.exception.ApplicationNotFoundException;
import com.socgen.loanapprovalplatform.repository.CarLoanApplicationRepository;
import com.socgen.loanapprovalplatform.repository.LoanFrontDeskRepository;
import com.socgen.loanapprovalplatform.service.FrontDeskService;
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
@RequestMapping("/v1/api/fd")
@Transactional
public class FrontDeskResource {

    private final Logger log = LoggerFactory.getLogger(FrontDeskResource.class);

    private FrontDeskService frontDeskService;
    private LoanFrontDeskRepository loanFrontDeskRepository;
    private CarLoanApplicationRepository carLoanApplicationRepository;

    public FrontDeskResource(FrontDeskService frontDeskService,
                             LoanFrontDeskRepository loanFrontDeskRepository,
                             CarLoanApplicationRepository carLoanApplicationRepository) {
        this.frontDeskService = frontDeskService;
        this.loanFrontDeskRepository = loanFrontDeskRepository;
        this.carLoanApplicationRepository = carLoanApplicationRepository;
    }

    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_FRONTDESKOFFICER"})
    @PutMapping("/{applicationid}/approve")
    @ResponseStatus(HttpStatus.OK)
    public void approve(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid FrontDeskApproveRequest request) {

        log.debug("Front desk REST request to approve CarLoanApplication : {}", request);

        LoanFrontDesk loanFrontDesk = loanFrontDeskRepository.findByCarLoanApplication_IdAndStatus(applicationid, LoanFrontDeskStatus.PENDING)
                .orElseThrow(() -> {
                    return new ApplicationNotFoundException("id-" + applicationid);
                });

        frontDeskService.approve(loanFrontDesk, request);
    }

    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_FRONTDESKOFFICER"})
    @PutMapping("/{applicationid}/reject")
    @ResponseStatus(HttpStatus.OK)
    public void reject(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid FrontDeskApproveRequest request) {

        log.debug("Front desk REST request to reject CarLoanApplication : {}", request);

        LoanFrontDesk loanFrontDesk = loanFrontDeskRepository.findByCarLoanApplication_IdAndStatus(applicationid, LoanFrontDeskStatus.PENDING)
                .orElseThrow(() -> {
                    return new ApplicationNotFoundException("id-" + applicationid);
                });

        frontDeskService.reject(loanFrontDesk, request);
    }

    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_FRONTDESKOFFICER"})
    @GetMapping("/pending/{pageNo}/{pageSize}")
    public List<CarLoanApplicationDetailedResponse> getPendingCarLoanApplication(@PathVariable("pageNo") int pageNo,
                                                                                 @PathVariable("pageSize") int pageSize) {

        log.debug("Front desk REST request to get pending car loan application with pageno-{} and pagesize-{}", pageNo, pageSize);

        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<LoanFrontDesk> applications = loanFrontDeskRepository.findAllByStatus(LoanFrontDeskStatus.PENDING,
                paging);

        return applications.stream()
                .map(loanFrontDesk -> loanFrontDesk.getCarLoanApplication().toDetailedResponseDto())
                .collect(Collectors.toList());

    }
}
