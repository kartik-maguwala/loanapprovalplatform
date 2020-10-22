package com.socgen.loanapprovalplatform.web.rest;

import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanFrontDeskStatus;
import com.socgen.loanapprovalplatform.dto.FrontDeskApproveRequest;
import com.socgen.loanapprovalplatform.exception.ApplicationNotFoundException;
import com.socgen.loanapprovalplatform.repository.LoanFrontDeskRepository;
import com.socgen.loanapprovalplatform.service.FrontDeskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/fd")
@Transactional
public class FrontDeskResource {

    private final Logger log = LoggerFactory.getLogger(FrontDeskResource.class);

    private FrontDeskService frontDeskService;
    private LoanFrontDeskRepository loanFrontDeskRepository;

    public FrontDeskResource(FrontDeskService frontDeskService,
                             LoanFrontDeskRepository loanFrontDeskRepository) {
        this.frontDeskService = frontDeskService;
        this.loanFrontDeskRepository = loanFrontDeskRepository;
    }

    @PutMapping("/{applicationid}/approve")
    @ResponseStatus(HttpStatus.OK)
    public void approve(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid FrontDeskApproveRequest request) {

        LoanFrontDesk loanFrontDesk = loanFrontDeskRepository.findByCarLoanApplicationIdAndStatus(applicationid, LoanFrontDeskStatus.PENDING)
                .orElseThrow(() -> {
                    return new ApplicationNotFoundException("id-" + applicationid);
                });

        frontDeskService.approve(loanFrontDesk, request);
    }

    @PutMapping("/{applicationid}/reject")
    @ResponseStatus(HttpStatus.OK)
    public void reject(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid FrontDeskApproveRequest request) {

        LoanFrontDesk loanFrontDesk = loanFrontDeskRepository.findByCarLoanApplicationIdAndStatus(applicationid, LoanFrontDeskStatus.PENDING)
                .orElseThrow(() -> {
                    return new ApplicationNotFoundException("id-" + applicationid);
                });

        frontDeskService.reject(loanFrontDesk, request);
    }
}
